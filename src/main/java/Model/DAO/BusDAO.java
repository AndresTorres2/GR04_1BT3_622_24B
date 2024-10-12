package Model.DAO;

import Model.Entity.Bus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class BusDAO extends GenericDAO {
    public BusDAO() {
        super();
    }

    public List<Object[]> listarBusesPorJornada(String jornada) {
        List<Object[]> resultList = new ArrayList<>();
        try {

            String sql = "SELECT b.horario, b.capacidad, r.origen, r.destino , b.codigo " +
                    "FROM Bus b " +
                    "JOIN Ruta r ON b.ruta_id = r.idRuta " +
                    "WHERE b.jornada = :jornada";

            Query query = em.createNativeQuery(sql);
            query.setParameter("jornada", jornada);

            resultList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    public Bus obtenerBusPorCodigo(int codigo) {
        try {
            return em.find(Bus.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void actualizarAsientosOcupados(Bus bus) {
        try {
            beginTransaction();
            //bus.setAsientosOcupados(bus.getAsientosOcupados() + 1); // Incrementar asientos ocupados
            em.merge(bus); // Actualiza el bus en la base de datos
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }


}
