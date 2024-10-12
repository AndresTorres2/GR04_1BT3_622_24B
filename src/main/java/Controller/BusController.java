package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.BusDAO;
import Model.DAO.CalleDAO;
import Model.DAO.ViajeDAO;
import Model.Entity.Bus;
import Model.Entity.Calle;
import Model.Entity.Ruta;
import Model.Entity.Viaje;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "BusServlet", value = "/BusServlet")
public class BusController extends HttpServlet {
    private String message;
    private BusDAO busDAO;
    private CalleDAO calleDAO;
    private ViajeDAO viajeDAO;

    public void init() {

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("View/listarViajes.jsp");
        dispatcher.forward(req, resp);
    }

    public void verDetallesBus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int idBus = Integer.parseInt(req.getParameter("id"));
        //Viaje viaje = viajeDAO.obtenerViajePorCodigo(idBus);

        //Ruta ruta = viaje.getRuta();
        //int idRuta= ruta.getIdRuta();
        //List<Calle> listaCalles = calleDAO.obtenerCallesPorRutaId(idRuta);
        //ruta.setCalles(listaCalles);

        //req.setAttribute("viaje", viaje);
        //req.setAttribute("ruta", ruta);
        RequestDispatcher dispatcher = req.getRequestDispatcher("View/detallesViaje.jsp");
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
