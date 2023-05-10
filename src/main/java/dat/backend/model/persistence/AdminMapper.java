package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminMapper {
    static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM carport.user;";

        List<User> userList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("userid");
                    String email= rs.getString("email");
                    String password = rs.getString("password");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int phoneNumber = rs.getInt("phonenumber");
                    String role = rs.getString("role");




                    User user = new User(userId, email, password, firstname, lastname, phoneNumber, role);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return userList;
    }
}
