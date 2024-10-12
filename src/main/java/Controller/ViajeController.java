package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.BusDAO;
import Model.DAO.CalleDAO;
import Model.DAO.ViajeDAO;
import Model.Entity.Calle;
import Model.Entity.Ruta;
import Model.Entity.Viaje;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ViajeServlet", value = "/ViajeServlet")
public class ViajeController extends HttpServlet {
    private CalleDAO calleDAO;
    private ViajeDAO viajeDAO;
    private BusDAO busDAO;

    public void init() {
        viajeDAO =  new ViajeDAO();
        calleDAO = new CalleDAO();
        busDAO = new BusDAO();
    }

    public void listarViajes(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String jornada = req.getParameter("jornada");
        System.out.println("Jornada recibida: " + jornada);
        List<Object[]> viajes = new ArrayList<>();

        if (jornada != null && !jornada.isEmpty()) {
            viajes = viajeDAO.listarViajesPorJornada(jornada);
        }
        for (Object[] viaje : viajes) {
            System.out.println("Viaje:");
            for (Object campo : viaje) {
                System.out.println(" - " + campo);
            }
        }
        req.setAttribute("jornada",jornada);
        req.setAttribute("viajes", viajes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("View/listarViajes.jsp");
        dispatcher.forward(req, resp);
    }

    public void verDetallesViaje(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String idsViaje = req.getParameter("ids");
        List<Integer> idViajesList = new ArrayList<>();

        if (idsViaje != null && !idsViaje.isEmpty()) {

            String[] idArray = idsViaje.split(",");


            for (String id : idArray) {
                try {

                    idViajesList.add(Integer.parseInt(id.trim()));
                } catch (NumberFormatException e) {

                    System.out.println("Error al convertir el ID: " + id);
                }
            }
        }

        Viaje viaje = viajeDAO.obtenerViajePorCodigo(idViajesList.get(0));

        Ruta ruta = viaje.getRuta();
        int idRuta= ruta.getId();
        List<Calle> listaCalles = calleDAO.obtenerCallesPorRutaId(idRuta);
        ruta.setCalles(listaCalles);
        System.out.println("tamanio de calle"+ ruta.getCalles().size());
        req.setAttribute("idViajes", idsViaje);
        req.setAttribute("viaje", viaje);
        req.setAttribute("ruta", ruta);
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
                this.listarViajes(req, resp);
                break;
            case "verDetalles":
                this.verDetallesViaje(req, resp);
                break;
            default:
                break;

        }

    }
}
