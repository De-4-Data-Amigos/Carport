package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class AdminFacade {
    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        return AdminMapper.getAllUsers(connectionPool);
    }


}
