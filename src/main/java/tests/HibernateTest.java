package tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import user.User;
import chat.GroupChat;

import java.util.ArrayList;
import java.util.HashSet;

public class HibernateTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChatApp");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();

        // Create and persist entities
        User user = new User("username", "password");
        GroupChat groupChat = new GroupChat("GroupName", new ArrayList<>(), 10);

        em.persist(user);
        em.persist(groupChat);

        // Commit transaction
        em.getTransaction().commit();

        // Fetch and print entities
        User fetchedUser = em.find(User.class, "username");
        GroupChat fetchedGroupChat = em.find(GroupChat.class, groupChat.getChatId());

        System.out.println(fetchedUser);
        System.out.println(fetchedGroupChat);

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}

