package Controller;

import Model.DAO.BusDAO;
import Model.DAO.ReservaDAO;
import Model.DAO.EstudianteDAO;
import Model.Entity.Bus;
import Model.Entity.Reserva;
import Model.Entity.Estudiante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ReservarAsientoServlet", value = "/ReservarAsientoServlet")
public class ReservaController extends HttpServlet {
    private ReservaDAO reservaDAO;
    private BusDAO busDAO;
    private EstudianteDAO estudianteDAO;

    public void init() {
        reservaDAO = new ReservaDAO();
        busDAO = new BusDAO();
        estudianteDAO = new EstudianteDAO();
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
        int busId = Integer.parseInt(request.getParameter("busId"));
        Bus bus = busDAO.obtenerBusPorCodigo(busId);
        request.setAttribute("bus", bus);
        request.getRequestDispatcher("/View/reservarAsiento.jsp").forward(request, response);
    }

    private void guardarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idBus = Integer.parseInt(request.getParameter("busId"));
            int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));

            Bus bus = busDAO.obtenerBusPorCodigo(idBus);
            Estudiante estudiante = estudianteDAO.obtenerEstudiantePorId(idEstudiante);

            if (bus.getCapacidad() > bus.getAsientosOcupados()) {
                String[] dias = request.getParameterValues("diasReservados");

                Reserva reserva = new Reserva();
                reserva.setBus(bus);
                reserva.setEstudiante(estudiante);
                reserva.setFechaReserva(new Date(System.currentTimeMillis()));
                reserva.setDiasReservados(List.of(dias));

                reservaDAO.guardarReserva(reserva);

                // Actualiza los asientos ocupados en el bus
                busDAO.actualizarAsientosOcupados(bus);

                // Redirigir a la lista de buses
                response.sendRedirect(request.getContextPath() + "/View/listarBus.jsp");
            } else {
                // Redirigir igualmente si no hay asientos disponibles
                response.sendRedirect(request.getContextPath() + "/View/listarBus.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/View/reservarAsiento.jsp");
        }
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

