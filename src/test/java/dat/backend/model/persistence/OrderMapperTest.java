package dat.backend.model.persistence;

import dat.backend.model.entities.Orders;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.services.RegisterHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    private static User user;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.orders LIKE carport.orders;");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from itemlist");
                stmt.execute("delete from orders");
                stmt.execute("delete from user");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into user (email, password, firstname, lastname, phonenumber) " +
                        "values ('user@mail.com','" + RegisterHelper.hashPassword("1234") + "','user', 'vic','245534'),('admin@mail.com','" + RegisterHelper.hashPassword("1234") + " ','admin','den', '744554'), ('ben@mail.com',' " + RegisterHelper.hashPassword("1234") + " ','user', 'ras', '647476')");
                user = UserFacade.login("user@mail.com","1234", connectionPool);
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
    void createOrder() throws DatabaseException {
        Orders newOrder = new Orders(369,400, user,  connectionPool);
        Orders expectedOrder =OrderFacade.getOrderById (newOrder.getId(), connectionPool);
        assertEquals(expectedOrder, newOrder);

    }

    @Test
    void setPrice() throws DatabaseException {
        float newPrice = 11000;
        Orders newOrder = new Orders(400,400, user,  connectionPool);
        newOrder.setDbPrice(newPrice,connectionPool);
        Orders order = OrderFacade.getOrderById(newOrder.getId(),connectionPool);

        assertEquals(newPrice, newOrder.getPrice());
        assertEquals(newPrice, order.getPrice());

    }
}