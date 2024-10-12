package Model.Entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "calles")
public class Calle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "ubicacionId", nullable = false)
    private Ubicacion ubicacion;

    public Calle() {
    }

    public Calle(int id, String nombre, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
