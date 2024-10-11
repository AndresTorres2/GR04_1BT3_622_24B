package Model.Entity;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@Table(name = "calle")
public class Calle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCalle;
    @Column(unique = true, nullable = false)
    private String nombreCalle;
    public Calle(){

    }
    public Calle(int idCalle, String nombreCalle){
        this.idCalle = idCalle;
        this.nombreCalle = nombreCalle;
    }

    public int getIdCalle() {
        return idCalle;
    }

    public void setIdCalle(int idCalle) {
        this.idCalle = idCalle;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }
}
