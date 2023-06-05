package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.services.RegisterHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static dat.backend.model.persistence.ProductFacade.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    private static User user;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement statement = testConnection.createStatement()) {
                // Create test database - if not exist
                statement.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                statement.execute("CREATE TABLE IF NOT EXISTS carport_test.orders LIKE carport.orders;");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement statement = testConnection.createStatement()) {
                // TODO: Remove all rows from all tables - add your own tables here
                statement.execute("delete from itemlist");
                statement.execute("delete from orders");
                statement.execute("delete from user");
                statement.execute("delete from product_variant");
                statement.execute("delete from product");
                statement.execute("delete from user");

                statement.execute("insert into user (email, password, firstname, lastname, phonenumber) " +
                        "values ('user@mail.com','" + RegisterHelper.hashPassword("1234") + "','user', 'vic','245534'),('admin@mail.com','" + RegisterHelper.hashPassword("1234") + " ','admin','den', '744554'), ('ben@mail.com',' " + RegisterHelper.hashPassword("1234") + " ','user', 'ras', '647476')");
                user = UserFacade.login("user@mail.com", "1234", connectionPool);
            }

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        } catch (DatabaseException databaseException) {
            System.out.println(databaseException.getMessage());
            fail("Database failed");
        }
    }

    @Test
    void testAddProduct() {
        String name = "Product";
        String description = "test product";
        Unit unit = Unit.pieces;
        float pricePrUnit = 9.99f;
        ProductType type = ProductType.wood;

        try {
            int productid = addProduct(name, description, unit, pricePrUnit, type, connectionPool);
            ProductFacade.addProductVariant(productid, 1,1,1, connectionPool);
            List<ProductAndProductVariant> productList = getAllProducts(connectionPool);
            assertNotNull(productList);
            assertEquals(1, productList.size());
            ProductAndProductVariant actually = productList.get(0);
            assertEquals(name, actually.getName());
            assertEquals(description, actually.getDescription());
            assertEquals(unit, actually.getUnit());
            assertEquals(pricePrUnit, actually.getPricePrUnit(), 0.001);
            assertEquals(type, actually.getType());
        } catch (DatabaseException e) {
            fail(e.getMessage());
        }
    }

}