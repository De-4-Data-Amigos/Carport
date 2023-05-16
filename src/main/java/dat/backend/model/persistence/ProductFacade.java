package dat.backend.model.persistence;

import dat.backend.model.entities.Product;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ProductFacade {
    public static List<Product> getAllProducts(ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.getAllProducts(connectionPool);
    }
}
