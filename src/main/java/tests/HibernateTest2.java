package tests;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import user.User;
import chat.GroupChat;
public class HibernateTest2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChatApp");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Query to fetch data from the database
            List<User> users = em.createQuery("SELECT e FROM User e", User.class).getResultList();

            // Print fetched data
            for (User user : users) {
                System.out.println(user.getUsername() + ": " + user.getPassword());
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
