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
            default:
                listarReservas(request, response);
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
        // Lógica para listar las reservas (se puede implementar si es necesario)
    }
}

