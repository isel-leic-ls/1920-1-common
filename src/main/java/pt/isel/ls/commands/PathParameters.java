package pt.isel.ls.commands;

import pt.isel.ls.commands.exceptions.ParameterException;

import java.util.Map;
import java.util.Optional;

public class PathParameters {

    private final Map<String, String> map;

    public PathParameters(Map<String, String> map) {

        this.map = map;
    }

    public String getMandatoryString(String name) throws ParameterException.MissingParameterException {
        String value = map.get(name);
        if (value == null) {
            throw new ParameterException.MissingParameterException();
        }
        return value;
    }

    public Optional<String> getOptionalString(String name) {
        return Optional.ofNullable(map.get(name));
    }

    public int getMandatoryInt(String name) throws ParameterException {
        String value = map.get(name);
        if (value == null) {
            throw new ParameterException.MissingParameterException();
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ParameterException.InvalidParameterException();
        }
    }

    public Optional<Integer> getOptionalInt(String name) throws ParameterException {
        String value = map.get(name);
        if (value == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            throw new ParameterException.InvalidParameterException();
        }
    }

    public Map<String, String> getMap() {
        return map;
    }
}
