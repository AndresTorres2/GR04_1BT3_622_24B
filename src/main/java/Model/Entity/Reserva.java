package Model.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private int idReserva;

    @ManyToOne
    @JoinColumn(name = "viajeId", nullable = false)
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @Column(name = "fecha_reserva", nullable = false)
    private Date fechaReserva;

    @ElementCollection
    @CollectionTable(name = "reserva_dias", joinColumns = @JoinColumn(name = "reserva_id"))
    @Column(name = "dia")
    private List<String> diasReservados;

    public Reserva() {
    }

    public Reserva(int idReserva, Viaje viaje, Estudiante estudiante, Date fechaReserva, List<String> diasReservados) {
        this.idReserva = idReserva;
        this.viaje = viaje;
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

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
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
