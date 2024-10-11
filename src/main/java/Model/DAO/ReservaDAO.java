package Model.DAO;

import Model.Entity.Reserva;
import Model.Entity.Estudiante;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ReservaDAO extends GenericDAO {

    public ReservaDAO() {
        super();
    }

    public void guardarReserva(Reserva reserva) {
        try {
            beginTransaction();
            em.persist(reserva);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }
    public List<Reserva> obtenerTodasLasReservas() {
        List<Reserva> reservas = null;
        try {
            beginTransaction();
            System.out.println("hola entre al begin");
            TypedQuery<Reserva> query = em.createQuery("SELECT r FROM Reserva r", Reserva.class);
            reservas = query.getResultList();
            commitTransaction();
        } catch (Exception e) {
            System.out.println("hola me cai");
            rollbackTransaction();
            e.printStackTrace();
        }
        return reservas;
    }
    public Reserva obtenerReservaPorId(int idReserva) {
        Reserva reserva = null;
        try {
            beginTransaction();  // Iniciar la transacción

            // Crear una consulta parametrizada para obtener la reserva por su ID
            TypedQuery<Reserva> query = em.createQuery("SELECT r FROM Reserva r WHERE r.id = :idReserva", Reserva.class);
            query.setParameter("idReserva", idReserva);  // Pasar el parámetro

            reserva = query.getSingleResult();  // Obtener el resultado
            commitTransaction();  // Confirmar la transacción
        } catch (Exception e) {
            rollbackTransaction();  // Revertir en caso de error
            e.printStackTrace();
        }
        return reserva;
    }

    public void borrarReservaPorDiaYId(int idReserva, String dia) {
        try {
            beginTransaction();


            String sql = "DELETE FROM reserva_dias WHERE reserva_id= :idReserva AND dia = :dia";


            Query query = em.createNativeQuery(sql);
            query.setParameter("idReserva", idReserva);
            query.setParameter("dia", dia);

            query.executeUpdate();

            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }





}
