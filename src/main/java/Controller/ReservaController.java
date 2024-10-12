package Controller;

import Model.DAO.BusDAO;
import Model.DAO.ReservaDAO;
import Model.DAO.EstudianteDAO;
import Model.DAO.ViajeDAO;
import Model.Entity.Bus;
import Model.Entity.Reserva;
import Model.Entity.Estudiante;
import Model.Entity.Viaje;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ReservarAsientoServlet", value = "/ReservarAsientoServlet")
public class ReservaController extends HttpServlet {
    private ReservaDAO reservaDAO;
    private BusDAO busDAO;
    private EstudianteDAO estudianteDAO;
    private ViajeDAO viajeDAO;


    public void init() {
        reservaDAO = new ReservaDAO();
        busDAO = new BusDAO();
        estudianteDAO = new EstudianteDAO();
        viajeDAO = new ViajeDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (request.getParameter("action") == null) ? "listar" : request.getParameter("action");

        switch (action) {
            case "guardarReserva":
                guardarReserva(request, response);
                break;
            case "formularioReserva":
                mostrarFormularioReserva(request, response);
                break;
            case "consultarReservas":
                System.out.println("ruteador");
                listarReservas(request, response);
                break;
            case "detalleReserva":
                mostrarReserva(request,response);
                break;
            case "cancelarReserva":
                cancelarReserva(request,response);
                break;
            default:
                break;
        }
    }

    private void mostrarFormularioReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idsViajesParam = request.getParameter("idsViaje");
        System.out.println("soy el parametro; " +idsViajesParam);
        List<Integer> idViajesList = new ArrayList<>();

        if (idsViajesParam != null && !idsViajesParam.isEmpty()) {

            String[] idArray = idsViajesParam.split(",");


            for (String id : idArray) {
                try {

                    idViajesList.add(Integer.parseInt(id.trim()));
                } catch (NumberFormatException e) {

                    System.out.println("Error al convertir el ID: " + id);
                }
            }
        }
        System.out.println("Tamanio ids: " + idViajesList.size());
        List<Viaje> viajesList = new ArrayList<>();

        // Iterar sobre la lista de IDs de viajes
        for (Integer idViaje : idViajesList) {
            // Obtener el objeto Viaje a partir del ID
            Viaje viaje = viajeDAO.obtenerViajePorCodigo(idViaje);
            // Si se encuentra el viaje, agregarlo a la lista
            if (viaje != null) {
                viajesList.add(viaje);
            } else {
                System.out.println("No se encontró el viaje con ID: " + idViaje);
            }
        }
        System.out.println("Tamanio viajes: " + idViajesList.size());
        request.setAttribute("viajesList", viajesList);
        request.getRequestDispatcher("/View/reservarAsiento.jsp").forward(request, response);

        /*int busId = Integer.parseInt(request.getParameter("busId"));
        Bus bus = busDAO.obtenerBusPorCodigo(busId);
        request.setAttribute("bus", bus);
        request.getRequestDispatcher("/View/reservarAsiento.jsp").forward(request, response);*/
    }

        private void guardarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                String[] idsViajesSeleccionados = request.getParameterValues("idsViajesSeleccionados");
                List<Viaje> listaViajes = new ArrayList<>();
                Estudiante estudiante = estudianteDAO.obtenerEstudiantePorId("202110777");
                System.out.println("Soy el objeto :" +estudiante);
                if (idsViajesSeleccionados != null) {
                    // Convertir los IDs a enteros
                    int[] idsViajesEnteros = Arrays.stream(idsViajesSeleccionados)
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    // Para cada ID, obtener el objeto Viaje desde el DAO y agregarlo a la lista
                    ViajeDAO viajeDAO = new ViajeDAO();  // Asegúrate de que el DAO está correctamente inicializado

                    for (int id : idsViajesEnteros) {
                        Viaje viaje = viajeDAO.obtenerViajePorCodigo(id); // Método para obtener el viaje por ID
                        if (viaje != null) {  // Verificar que el viaje exista
                            listaViajes.add(viaje);
                        }
                    }


                }
                for (Viaje viaje : listaViajes) {
                    // Instanciar un nuevo objeto Reserva por cada Viaje

                    Reserva reserva = new Reserva(0, viaje, estudiante, viaje.getFecha());


                    // Opcional: guardar la reserva en la base de datos usando el DAO

                    reservaDAO.guardarReserva(reserva, viaje);  // Método para guardar la reserva en la base de datos
                }


                response.sendRedirect(request.getContextPath() + "/View/listarViajes.jsp");



    }





    private void listarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reserva> reservas = reservaDAO.obtenerTodasLasReservas();

        if (reservas == null || reservas.isEmpty()) {
            System.out.println("No se encontraron reservas.");
            request.setAttribute("errorMessage", "No hay reservas disponibles.");
        } else {
            System.out.println("Reservas obtenidas: " + reservas.size());
        }
        request.setAttribute("reservas", reservas);

        request.getRequestDispatcher("/View/consultarReservas.jsp").forward(request, response);

    }
    private void mostrarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dia = request.getParameter("dia");
        int idReserva = Integer.parseInt(request.getParameter("idReserva"));
        Reserva reservaSeleccionada = reservaDAO.obtenerReservaPorId(idReserva);
        System.out.println("Entre al metodo mostrar reserva");
        request.setAttribute("dia",dia);
        request.setAttribute("reserva", reservaSeleccionada);
        request.getRequestDispatcher("/View/detallesReserva.jsp").forward(request, response);

    }

    private void cancelarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idReserva = Integer.parseInt(request.getParameter("idReserva"));
        String dia = request.getParameter("dia");
        reservaDAO.borrarReservaPorDiaYId(idReserva, dia);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        response.sendRedirect(request.getContextPath() + "/ReservarAsientoServlet?action=consultarReservas");

    }
}