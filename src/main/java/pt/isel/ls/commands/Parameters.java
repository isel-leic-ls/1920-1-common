package pt.isel.ls.commands;

import pt.isel.ls.commands.exceptions.ParameterException;
import pt.isel.ls.utils.Ints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Parameters {

    private static final Parameters EMPTY = new Parameters(new HashMap<>());

    private final Map<String, List<String>> map;

    private Parameters(Map<String, List<String>> map) {
        this.map = map;
    }

    public static Optional<Parameters> parse(String parameterString) {
        String[] pairs = parameterString.split("&");
        Map<String, List<String>> prms = new HashMap<>();
        for (String pair : pairs) {
            if (pair.trim().length() == 0) {
                return Optional.empty();
            }
            String[] nameAndValue = pair.split("=");
            if (nameAndValue.length != 2 || nameAndValue[0].trim().length() == 0) {
                return Optional.empty();
            }
            prms.computeIfAbsent(nameAndValue[0], key -> new LinkedList<>()).add(nameAndValue[1]);
        }

        return Optional.of(new Parameters(prms));
    }

    public static Parameters empty() {
        return EMPTY;
    }

    /*
     * Adds (joins) a map into the current parameters
     */
    public Parameters join(Map<String, String> otherPrms) {
        Map<String, List<String>> prms = new HashMap<>(map);
        for (Map.Entry<String, String> entry : otherPrms.entrySet()) {
            prms.computeIfAbsent(entry.getKey(), key -> new LinkedList<>()).add(entry.getValue());
        }
        return new Parameters(prms);
    }

    /*
     * Below are methods to retrieve and validate named parameters
     */
    public String getMandatoryString(String name) throws ParameterException {
        List<String> values = map.get(name);
        if (values == null) {
            throw new ParameterException.MissingParameterException();
        }
        if (values.size() != 1) {
            throw new ParameterException.MultipleValuesParameterException();
        }
        return values.get(0);
    }

    public Optional<String> getOptionalString(String name) throws ParameterException {

        List<String> values = map.get(name);
        if (values == null) {
            return Optional.empty();
        }
        if (values.size() != 1) {
            throw new ParameterException.MultipleValuesParameterException();
        }
        return Optional.of(values.get(0));
    }

    public int getMandatoryInt(String name) throws ParameterException {
        String value = getMandatoryString(name);
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ParameterException.InvalidParameterException();
        }
    }

    public Optional<Integer> getOptionalInt(String name) throws ParameterException {
        Optional<String> maybeValue = getOptionalString(name);
        return maybeValue.flatMap(Ints::parse);
    }

    public List<String> getStringList(String name) {
        List<String> values = map.get(name);
        if (values == null) {
            return new ArrayList<>();
        }
        return values;
    }
}
