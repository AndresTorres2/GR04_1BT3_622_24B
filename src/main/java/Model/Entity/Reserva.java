package Model.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import Model.Entity.Estudiante;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReserva;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false) // Clave foránea
    private Estudiante estudiante;

    @Column(name = "fecha_reserva")
    @Temporal(TemporalType.DATE)
    private Date fechaReserva;

    // Nueva propiedad para almacenar los días de la reserva
    @ElementCollection
    @CollectionTable(name = "reserva_dias", joinColumns = @JoinColumn(name = "reserva_id"))
    @Column(name = "dia")
    private List<String> diasReservados;  // Usamos String para almacenar los días ("Lunes", "Martes", etc.)

    public Reserva() {
    }

    public Reserva(int idReserva, Bus bus, Estudiante estudiante, Date fechaReserva, List<String> diasReservados) {
        this.idReserva = idReserva;
        this.bus = bus;
        this.estudiante = estudiante;
        this.fechaReserva = fechaReserva;
        this.diasReservados = diasReservados;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public List<String> getDiasReservados() {
        return diasReservados;
    }

    public void setDiasReservados(List<String> diasReservados) {
        this.diasReservados = diasReservados;
    }
}
