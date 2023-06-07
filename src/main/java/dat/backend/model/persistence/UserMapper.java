package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.services.RegisterHelper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper {
    protected static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, RegisterHelper.hashPassword(password));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int phonenumber = rs.getInt("phonenumber");
                    String role = rs.getString("role");

                    user = new User(userId, email, password, firstname, lastname, phonenumber, role);
                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    protected static User createUser(String email, String password, String firstname, String lastname, int phonenumber, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

       //Laver et user-objekt
        User user;

        //Sql-query template på de værdier vi ønsker at indsætte i den her rækkefølge.
        String sql = "insert into user (email, password, firstname, lastname, phonenumber) values (?,?,?,?,?)";

        //Forsøger at få fat på en connection til databasen
        try (Connection connection = connectionPool.getConnection()) {

            //PreparedStatement laves ud fra query'en ovenover:
            //Den skal returnere user_id ud fra generated-keys
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                //værdierne fra brugeren som skal indsættes i SQL-sætningen ovenover
                ps.setString(1, email);
                ps.setString(2, RegisterHelper.hashPassword(password));
                ps.setString(3, firstname);
                ps.setString(4, lastname);
                ps.setInt(5, phonenumber);

                //Kører SQL'en ind i databasen med de værdier vi har valgt og returner hvor mange rækker er berørt
                //Kører hele lortet
                int rowsAffected = ps.executeUpdate();

                //Den returnerer et resultset, og dette resultset er vores nyligt lavede rækker (altså hele den nye række)
                //Den får fat på hele lortet
                ResultSet rs = ps.getGeneratedKeys();

                //Tjekker om bruger er korrekt indsat på én række
                //Hvis user_id dukker op som -1, så ved vi at der er sket en fejl.
                if (rowsAffected == 1) {
                    int userId = -1;
                    String role = "user";

                    //Ovenstående overwrites - for vi forventer ikke fejl.
                    //Får fat på det generede User_id og dette sættes
                    if (rs.next()) {
                        userId = rs.getInt(1);
                    }


                    user = new User(userId, email, password, firstname, lastname, phonenumber, role);

                    return user;

                    //Hvis fejl: db-exception
                } else {
                    throw new DatabaseException("The user with email = " + email + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }

    }


}
