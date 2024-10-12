package Model.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "rutas")
public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idRuta;

    @Column(name = "origen", nullable = false)
    private String origen;

    @Column(name = "destino", nullable = false)
    private String destino;

    @ManyToMany
    @JoinTable(
            name = "rutas_calles",
            joinColumns = @JoinColumn(name = "idRuta"),
            inverseJoinColumns = @JoinColumn(name = "idCalle")
    )
    private List<Calle> calles = new ArrayList<>();

    public Ruta() {
    }

    public Ruta(int idRuta, String origen, String destino, List<Calle> calles) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.calles = calles;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<Calle> getCalles() {
        return calles;
    }

    public void setCalles(List<Calle> calles) {
        this.calles = calles;
    }
}
