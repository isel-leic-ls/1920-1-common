package pt.isel.ls.commands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Router {

    private final Map<Method, List<Route>> routesPerMethod = new HashMap<>();

    private static class Route {
        final PathTemplate template;
        final CommandHandler handler;

        Route(PathTemplate template, CommandHandler handler) {

            this.template = template;
            this.handler = handler;
        }
    }

    public Router add(Method method, PathTemplate pathTemplate, CommandHandler handler) {
        routesPerMethod.computeIfAbsent(method, m -> new LinkedList<>()).add(new Route(pathTemplate, handler));
        return this;
    }

    public Optional<Result> find(CommandRequest request) {
        List<Route> routes = routesPerMethod.get(request.getMethod());
        if (routes == null) {
            return Optional.empty();
        }
        for (Route route : routes) {
            Optional<PathParameters> matchResult = route.template.match(request.getPath());
            if (matchResult.isPresent()) {
                return Optional.of(new Result(route.handler, matchResult.get()));
            }
        }
        return Optional.empty();
    }

    public static class Result {
        private final CommandHandler handler;
        private final PathParameters pathParameters;

        Result(CommandHandler handler, PathParameters pathParameters) {

            this.handler = handler;
            this.pathParameters = pathParameters;
        }

        public CommandHandler getHandler() {
            return handler;
        }

        public PathParameters getPathParameters() {
            return pathParameters;
        }
    }

}
