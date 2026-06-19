package Save;
import Save.dao.DocumentsDAO;
import Save.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Save.dao.UsersDAO;
import Save.entities.User;
import Save.entities.Document; // Assicurati che questo import esista

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-w3-d3pu");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        UsersDAO ud = new UsersDAO(em);

        DocumentsDAO dd = new DocumentsDAO(em);

        User Aldo = new User("Aldo", "Baglio");


       // Document aldoDoc = new Document("1234", "Italy", Aldo); Errato
        try {
            User aldoFromDb = ud.findById("27c61850-e499-43dc-9dcb-a0072287bfc6");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }



        dd.save(aldoDoc);
    }
}