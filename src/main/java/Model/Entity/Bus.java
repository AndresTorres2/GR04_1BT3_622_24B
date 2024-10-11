package Model.Entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import Model.Entity.Ruta;

@Entity
@Table(name = "Bus")
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha")
    private Date fecha;
    @Column(name="horario")
    private String horario;
    @Column(name="capacidad")
    private int capacidad;
    @Column(name="asientos_ocupados")
    private int asientosOcupados;
    @ManyToOne
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta  ruta;
    @Column(name = "jornada") // Nuevo atributo
    private String jornada;

    public Bus() {
    }
    public Bus(int codigo, Date fecha, String horario, int capacidad, int asientosOcupados, Ruta ruta, String jornada) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.horario = horario;
        this.capacidad = capacidad;
        this.asientosOcupados = asientosOcupados;
        this.ruta = ruta;
        this.jornada = jornada;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getAsientosOcupados() {
        return asientosOcupados;
    }

    public void setAsientosOcupados(int asientosOcupados) {
        this.asientosOcupados = asientosOcupados;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}
