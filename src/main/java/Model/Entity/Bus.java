package Model.Entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "buses")
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "capacidad")
    private int capacidad;

    public Bus() {
    }

    public Bus(String id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

}
