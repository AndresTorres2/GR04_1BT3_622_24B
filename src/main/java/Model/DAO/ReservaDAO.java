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
            beginTransaction(); // Inicia la transacción
            em.persist(reserva); // Guarda la reserva
            commitTransaction(); // Confirma la transacción
        } catch (Exception e) {
            rollbackTransaction(); // Revierte la transacción en caso de error
            e.printStackTrace();
        }
    }


}
