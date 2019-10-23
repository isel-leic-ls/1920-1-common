package pt.isel.ls.handlers;

import pt.isel.ls.commands.CommandHandler;
import pt.isel.ls.commands.CommandResult;
import pt.isel.ls.commands.Parameters;
import pt.isel.ls.commands.exceptions.CommandHandlerException;
import pt.isel.ls.commands.exceptions.InfrastructureException;
import pt.isel.ls.results.ErrorResult;
import pt.isel.ls.results.OkResult;
import pt.isel.ls.sql.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostStudent implements CommandHandler {

    private final DataSource ds;

    public PostStudent(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public CommandResult execute(Parameters parameters) throws CommandHandlerException {
        int number = parameters.getMandatoryInt("number");
        String name = parameters.getMandatoryString("name");
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "insert into Students (number, name) values (?,?)");
            ps.setInt(1, number);
            ps.setString(2, name);
            ps.execute();
            return new OkResult();
        } catch (SQLException e) {
            if (Sql.isUniqueViolation(e)) {
                return ErrorResult.studentAlreadyExists();
            }
            throw new InfrastructureException(e);
        }
    }
}
