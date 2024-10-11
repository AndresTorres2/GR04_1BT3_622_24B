package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.BusDAO;
import Model.DAO.CalleDAO;
import Model.DAO.ClienteDAO;
import Model.Entity.Bus;
import Model.Entity.Calle;
import Model.Entity.Cliente;
import Model.Entity.Ruta;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/BusServlet")
public class BusController extends HttpServlet {
    private String message;
    private ClienteDAO clienteDAO;
    private BusDAO busDAO;
    private CalleDAO calleDAO;

    public void init() {

        clienteDAO = new ClienteDAO();
        busDAO =  new BusDAO();
        calleDAO = new CalleDAO();
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

    public void verDetallesBus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int idBus = Integer.parseInt(req.getParameter("id"));
        Bus bus = busDAO.obtenerBusPorCodigo(idBus);

        Ruta ruta = bus.getRuta();
        int idRuta= ruta.getIdRuta();
        List<Calle> listaCalles = calleDAO.obtenerCallesPorRutaId(idRuta);
        ruta.setCalles(listaCalles);

        req.setAttribute("bus", bus);
        req.setAttribute("ruta", ruta);
        RequestDispatcher dispatcher = req.getRequestDispatcher("View/detallesBus.jsp");
        dispatcher.forward(req, resp);


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
            case "verDetalles":
                this.verDetallesBus(req, resp);
                break;
                default:
                    break;

        }

    }
}
