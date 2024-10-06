package Model.DAO;

import Model.Entity.Cliente;
import jakarta.persistence.*;

import java.util.List;

public class ClienteDAO {

    EntityManager em = null;

    public ClienteDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
        this.em  = emf.createEntityManager();
    }

    public void crearCliente(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = null;
        try {
            em.getTransaction().begin();
            clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return clientes;
    }

    public void eliminarCliente(String id) {
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } catch (RollbackException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }


}