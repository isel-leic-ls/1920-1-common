package pt.isel.ls.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.commands.CommandHandler;
import pt.isel.ls.commands.Parameters;
import pt.isel.ls.commands.exceptions.CommandHandlerException;
import pt.isel.ls.commands.exceptions.InfrastructureException;
import pt.isel.ls.results.TableCommandResult;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetStudents implements CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetStudents.class);

    private final DataSource dataSource;

    public GetStudents(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public TableCommandResult execute(Parameters prms) throws CommandHandlerException {

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from Students");
            ResultSet rs = ps.executeQuery();
            TableCommandResult result = new TableCommandResult("Number", "Name");
            while (rs.next()) {
                result.addLine(Integer.toString(rs.getInt(1)), rs.getString(2));
            }
            return result;
        } catch (SQLException e) {
            logger.warn("Exception while accessing DB", e);
            throw new InfrastructureException(e);
        }
    }
}
