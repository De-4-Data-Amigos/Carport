package dat.backend.model.entities;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.services.CarportBuilderHelper;

import java.sql.Connection;
import java.sql.Timestamp;

public class Orders {


    private int id;
    private int user_id;
    private Timestamp timestamp;
    private float price;
    private int length;
    private int width;


    public Orders(int id, int user_id, Timestamp timestamp, float price, int length, int width) {
        this.id = id;
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.price = price;
        this.length = length;
        this.width = width;
    }

    public Orders(int length, int width, User user, ConnectionPool connectionPool) throws DatabaseException {
        this.length = length;
        this.width = width;
        this.price = CarportBuilderHelper.startPrice(width, length);
        user_id = user.getUserId();

        id = OrderFacade.createOrder(this, connectionPool);

    }



    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public float getPrice() {
        return price;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
