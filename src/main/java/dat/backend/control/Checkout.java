package dat.backend.control;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.AdminFacade;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "Checkout", value = "/Checkout")
public class Checkout extends HttpServlet {

    private ConnectionPool connectionPool;

    public void init() throws ServletException{
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Hvad skal Checkout?
        - Vise den ordre, som er blevet sammensat af kunden på forrige side
        - Trække mål og pris fra databasen
        - Kan gå videre til næste side vha. bekræft-bestilling knap på jsp (tjek)
         */


        HttpSession session = request.getSession();
        Orders order = (Orders)session.getAttribute("");



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
}
