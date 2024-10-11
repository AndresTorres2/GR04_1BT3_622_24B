package Model.DAO;

import Model.Entity.Estudiante;
import jakarta.persistence.Query;

public class EstudianteDAO extends GenericDAO {

    // Metodo para obtener un estudiante por su ID
    public Estudiante obtenerEstudiantePorId(int idEstudiante) {
        Estudiante estudiante = null;
        try {
            estudiante = em.find(Estudiante.class, idEstudiante);
        } catch (Exception e) {
            e.printStackTrace(); // Esto debería ayudarte a ver si hay un problema al buscar
        }
        return estudiante;
    }


    // Metodo para guardar un nuevo estudiante
    public void guardarEstudiante(Estudiante estudiante) {
        try {
            beginTransaction();
            em.persist(estudiante); // Guarda el estudiante
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }


}
