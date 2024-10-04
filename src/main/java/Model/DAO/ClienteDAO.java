package Model.DAO;

import Model.Entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class ClienteDAO {

    private EntityManagerFactory entityManagerFactory;

    public ClienteDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit-name");
    }

    public void crearCliente(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Cliente obtenerCliente(String cedula) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Cliente.class, cedula);
        } finally {
            entityManager.close();
        }
    }

    public void actualizarCliente(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void eliminarCliente(String cedula) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Cliente cliente = entityManager.find(Cliente.class, cedula);
            if (cliente != null) {
                entityManager.remove(cliente);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> obtenerTodosLosClientes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("FROM Cliente").getResultList();
        } finally {
            entityManager.close();
        }
    }
}