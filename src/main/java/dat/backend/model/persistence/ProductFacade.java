package dat.backend.model.persistence;

import dat.backend.model.entities.Product;
import dat.backend.model.entities.ProductType;
import dat.backend.model.entities.Unit;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ProductFacade {
    public static List<Product> getAllProducts(ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.getAllProducts(connectionPool);
    }

    public static void removeProductList(int id, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper.removeProductList(id, connectionPool);
    }

    public static void removeProductVariant(int id, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper.removeProductVariant(id, connectionPool);
    }

    public static int addProduct(String name, String description, Unit unit, float pricePrUnit, ProductType type, ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.addProduct(name, description, unit, pricePrUnit, type, connectionPool);
    }

    public static int addProductVariant(int productId, float length, float width, float height, ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.addProductVariant(productId ,length, width, height, connectionPool);
    }

    }

