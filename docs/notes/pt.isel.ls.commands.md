# The package `pt.isel.ls.commands`

The package `pt.isel.ls.commands` contains types to represent command requests and its components.

The `Method` `enum` represents the method component of a command request.
It was designed as an `enum` since there is a small finite number of commands, characterized by a string.

The `Path` `class` represents a validated and processed path, exposing methods to access the path's segments.

Both `Method` and `Path` expose static _builder_ methods receiving strings and returning an `Optional`, which can be:
 * an _empty optional_ if the input string is not a valid _method_ or _path_.
 * an `Optional containing the resulting `Method` or `Path.
 
The option to use a static builder method and not a public constructor for `Path` is motivated by:
* The presence of a validation step, which can be done before creating any instance.
* The usage of `Optional` to signal success or failure, instead of exceptions (the `Path` constructor could not return an `Optional<Path>`).

The `CommandRequest` represents a validated and processed command request, exposing methods to access its components.
Here, the decision was to use exceptions to signal validation errors, because we want to convey more detaild information about the error type.
For that, an exception hierarchy was created, having `CommandRequestParseException` as the root exception.
 
