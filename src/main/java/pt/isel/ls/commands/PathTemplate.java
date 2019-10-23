package pt.isel.ls.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PathTemplate {

    private static final Optional<PathTemplate> root = Optional.of(new PathTemplate(new TemplateSegment[0]));

    private final TemplateSegment[] templateSegments;

    public static PathTemplate of(String value) {
        return PathTemplate.parse(value).orElseThrow(() -> new IllegalArgumentException());
    }

    public static Optional<PathTemplate> parse(String value) {
        if (value == null || value.equals("")) {
            return Optional.empty();
        }
        // needs to start with a '/' and not end with a '/'
        if (value.equals("/")) {
            return root;
        }
        if (value.charAt(0) != '/' || (value.length() > 1 && value.charAt(value.length() - 1) == '/')) {
            return Optional.empty();
        }
        String[] pathSegments = value.substring(1).trim().split("/");
        TemplateSegment[] templateSegments = new TemplateSegment[pathSegments.length];
        for (int i = 0; i < templateSegments.length; ++i) {
            if (isConstantSegment(pathSegments[i])) {
                templateSegments[i] = new ConstantSegment(pathSegments[i]);
            } else if (isVarSegment(pathSegments[i])) {
                templateSegments[i] = new VarSegment(getVarName(pathSegments[i]));
            } else {
                return Optional.empty();
            }
        }

        return Optional.of(new PathTemplate(templateSegments));
    }

    public PathTemplate(TemplateSegment[] templateSegments) {
        this.templateSegments = templateSegments;
    }

    public Optional<PathParameters> match(Path path) {
        if (path.getSize() != templateSegments.length) {
            return Optional.empty();
        }
        for (int i = 0; i < templateSegments.length; ++i) {
            if (!templateSegments[i].match(path.getSegmentAt(i))) {
                return Optional.empty();
            }
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < templateSegments.length; ++i) {
            templateSegments[i].addParameterTo(path.getSegmentAt(i), map);
        }
        return Optional.of(new PathParameters(map));
    }

    private static boolean isConstantSegment(String segment) {
        return !segment.startsWith("{") && !segment.endsWith("}");
    }

    private static boolean isVarSegment(String segment) {
        return segment.startsWith("{") && segment.endsWith("}");
    }

    private static String getVarName(String segment) {
        return segment.substring(1, segment.length() - 1);
    }

    private interface TemplateSegment {
        boolean match(String segment);

        void addParameterTo(String segment, Map<String, String> parameters);
    }

    private static class ConstantSegment implements TemplateSegment {

        private final String value;

        public ConstantSegment(String value) {

            this.value = value;
        }

        @Override
        public boolean match(String segment) {
            return Objects.equals(segment, value);
        }

        @Override
        public void addParameterTo(String segment, Map<String, String> parameters) {
            // nothing to do
        }
    }

    private static class VarSegment implements TemplateSegment {

        private final String name;

        public VarSegment(String name) {

            this.name = name;
        }

        @Override
        public boolean match(String segment) {
            return true;
        }

        @Override
        public void addParameterTo(String segment, Map<String, String> parameters) {
            parameters.put(name, segment);
        }
    }
}
