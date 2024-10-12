package Model.DAO;

import Model.Entity.Calle;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class CalleDAO extends GenericDAO{
    public List<Calle> obtenerCallesPorRutaId(int rutaId) {
        List<Calle> calles = new ArrayList<>();
        try {
            String sql = "SELECT c.idCalle, c.nombreCalle " +
                    "FROM ruta_calle rc " +
                    "JOIN calle c ON rc.calle_id = c.idCalle " +
                    "WHERE rc.ruta_id = :rutaId";

            Query query = em.createNativeQuery(sql);
            query.setParameter("rutaId", rutaId);

            List<Object[]> resultList = query.getResultList();

            for (Object[] result : resultList) {
                Calle calle = new Calle();
                calle.setId((Integer) result[0]);
                calle.setNombre((String) result[1]);
                calles.add(calle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calles;
    }

}
