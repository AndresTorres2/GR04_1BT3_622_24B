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
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
                consultarReservas(request, response);
                break;
            case "verReservasDia":
                verReservasDia(request, response);
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

        String viajesIds = request.getParameter("idsViaje");
        List<Integer> viajesIdList = new ArrayList<>();

        if (viajesIds != null && !viajesIds.isEmpty()) {

            String[] idArray = viajesIds.split(",");


            for (String id : idArray) {
                viajesIdList.add(Integer.parseInt(id.trim()));

            }
        }
        List<Viaje> viajesList = new ArrayList<>();

        // Iterar sobre la lista de IDs de viajes
        for (Integer idViaje : viajesIdList) {
            // Obtener el objeto Viaje a partir del ID
            Viaje viaje = viajeDAO.obtenerViajePorCodigo(idViaje);
            // Si se encuentra el viaje, agregarlo a la lista
            if (viaje != null) {
                viajesList.add(viaje);
            } else {
                System.out.println("No se encontró el viaje con ID: " + idViaje);
            }
        }
        request.setAttribute("viajesList", viajesList);
        request.getRequestDispatcher("/View/reservarAsiento.jsp").forward(request, response);


    }

    private void guardarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                String[] viajesIdsSeleccionados = request.getParameterValues("idsViajesSeleccionados");
                List<Viaje> listaViajes = new ArrayList<>();
                Estudiante estudiante = estudianteDAO.obtenerEstudiantePorId("202110777");
                if (viajesIdsSeleccionados != null) {
                    int[] idsViajesEnteros = Arrays.stream(viajesIdsSeleccionados)
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    ViajeDAO viajeDAO = new ViajeDAO();  // Asegúrate de que el DAO está correctamente inicializado

                    for (int id : idsViajesEnteros) {
                        Viaje viaje = viajeDAO.obtenerViajePorCodigo(id); // Método para obtener el viaje por ID
                        if (viaje != null) {
                            listaViajes.add(viaje);
                        }
                    }


                }
                for (Viaje viaje : listaViajes) {

                    Reserva reserva = new Reserva(0, viaje, estudiante, viaje.getFecha());


                    reservaDAO.guardarReserva(reserva, viaje);
                }
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);

                response.sendRedirect(request.getContextPath() + "/View/listarViajes.jsp");



    }
    private void verReservasDia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String diaSeleccionado = request.getParameter("dia");
        List<Reserva> reservas = reservaDAO.obtenerTodasLasReservas();
        List<Reserva> reservasFiltradas = new ArrayList<>();
        for (Reserva reserva : reservas) {

            String diaReserva = String.valueOf(reserva.getFecha().getDay());

            if (diaReserva.equals(diaSeleccionado)) {
                reservasFiltradas.add(reserva);
            }
        }

        request.setAttribute("reservas", reservasFiltradas);

        request.getRequestDispatcher("/View/consultarReservas.jsp").forward(request, response);
    }





    private void consultarReservas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/View/consultarReservas.jsp").forward(request, response);

    }
    private void mostrarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservaId = Integer.parseInt(request.getParameter("reservaId"));
        Reserva reservaSeleccionada = reservaDAO.obtenerReservaPorId(reservaId);
        request.setAttribute("reserva", reservaSeleccionada);
        request.getRequestDispatcher("/View/detallesReserva.jsp").forward(request, response);

    }

    private void cancelarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservaId = Integer.parseInt(request.getParameter("reservaId"));
        Reserva reserva = reservaDAO.obtenerReservaPorId(reservaId);
        Viaje viaje =  viajeDAO.obtenerViajePorCodigo(reserva.getViaje().getId());



        reservaDAO.cancelarReserva(reservaId, viaje);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.sendRedirect(request.getContextPath() + "/ReservarAsientoServlet?action=consultarReservas");

    }
}