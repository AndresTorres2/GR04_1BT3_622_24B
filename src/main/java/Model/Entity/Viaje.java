package Model.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Viajes")
public class Viaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idViaje;

    @ManyToOne
    @JoinColumn(name = "busId", nullable = false)
    private Bus bus;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "horaDeSalida", nullable = false)
    private Time horaDeSalida;

    @ManyToOne
    @JoinColumn(name = "idRuta", nullable = false)
    private Ruta ruta;

    @Column(name = "jornada", nullable = false, length = 20)
    private String jornada;

    @Column(name = "asientosOcupados")
    private int asientosOcupados;

    public Viaje() {
    }

    public Viaje(int idViaje, Bus bus, Date fecha, Time horaDeSalida, Ruta ruta, String jornada, int asientosOcupados) {
        this.idViaje = idViaje;
        this.bus = bus;
        this.fecha = fecha;
        this.horaDeSalida = horaDeSalida;
        this.ruta = ruta;
        this.jornada = jornada;
        this.asientosOcupados =  asientosOcupados;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraDeSalida() {
        return horaDeSalida;
    }

    public void setHoraDeSalida(Time horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getAsientosOcupados() {
        return asientosOcupados;
    }

    public void setAsientosOcupados(int asientosOcupados) {
        this.asientosOcupados = asientosOcupados;
    }
}
