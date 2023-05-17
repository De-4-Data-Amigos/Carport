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

@WebServlet(name = "Checkout", value = "/checkout")
public class Checkout extends HttpServlet {

    private ConnectionPool connectionPool;

    public void init() throws ServletException{
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    /**
     * I Checkout's doGet metode vil vi vise den carport til kunden, som de på forrige side har valgt da de valgte bredde og længde
     * på deres ønskede carport. Metoden viser bredde, længde samt en ca. pris - og har en knap til hvis de fortryder.
     *
     * @param request er det vi ønsker at få
     * @param response er det vi ønsker at sende
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String widthString = request.getParameter("width");
        String lengthString = request.getParameter("length");

        int width = Integer.parseInt(widthString);
        int length = Integer.parseInt(lengthString);

        HttpSession session = request.getSession();
        Orders order = null;
        User user = (User)session.getAttribute("user");

        int orderID;

        try {
            order = new Orders(width, length, user, connectionPool);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("width", width);
        request.setAttribute("length", length);
        session.setAttribute("order", order);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);





        // Tilføje ordren ved at sammensætte de forskellige dele fra den store udregning?
        //


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
}
