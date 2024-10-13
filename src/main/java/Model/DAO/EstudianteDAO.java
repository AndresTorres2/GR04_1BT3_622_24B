package Model.DAO;

import Model.Entity.Estudiante;
import jakarta.persistence.Query;

public class EstudianteDAO extends GenericDAO {

    // Metodo para obtener un estudiante por su ID
    public Estudiante obtenerEstudiantePorId(String idEstudiante) {
        Estudiante estudiante = null;
        try {
            estudiante = em.find(Estudiante.class, idEstudiante);
        } catch (Exception e) {
            e.printStackTrace(); // Esto debería ayudarte a ver si hay un problema al buscar
        }
        return estudiante;
    }



}
