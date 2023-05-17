package dat.backend.control;


import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.OrderView;
import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.util.List;

@WebServlet(name = "RemoveChosenOrder", value = "/RemoveChosenOrder")
public class RemoveChosenOrder extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("order", null);

        request.getRequestDispatcher("carportbuilder.jsp").forward(request, response);

    }
}
