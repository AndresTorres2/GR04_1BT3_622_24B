package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.BusDAO;
import Model.DAO.ClienteDAO;
import Model.Entity.Bus;
import Model.Entity.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/BusServlet")
public class BusController extends HttpServlet {
    private String message;
    private ClienteDAO clienteDAO;
    private BusDAO busDAO;

    public void init() {

        clienteDAO = new ClienteDAO();
        busDAO =  new BusDAO();
    }

    public void listarBus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String jornada = req.getParameter("jornada");
        List<Object[]> buses = new ArrayList<>();

        if (jornada != null && !jornada.isEmpty()) {

            buses = busDAO.listarBusesPorJornada(jornada);

        }


        req.setAttribute("buses", buses);
        RequestDispatcher dispatcher = req.getRequestDispatcher("View/listarBus.jsp");
        dispatcher.forward(req, resp);
    }
    public void eliminar(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String cedula = req.getParameter("cedula");

        try {
            clienteDAO.eliminarCliente(cedula);
            resp.sendRedirect(req.getContextPath() + "/ClienteServlet?ruta=listarCliente");

        } catch (Exception e) {
            // Manejar el error y redirigir a una página de error si es necesario
            req.setAttribute("errorMessage", "Error al eliminar el cliente: " + e.getMessage());
        }

    }




    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.ruteador(req, resp);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.ruteador(req, resp);
    }

    public void ruteador(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        String ruta = (req.getParameter("ruta") == null)? "listarCliente": req.getParameter("ruta");
        switch (ruta) {
            case "seleccionarJornada":
                this.listarBus(req, resp);
                break;
                default:
                    break;

        }

    }
}
