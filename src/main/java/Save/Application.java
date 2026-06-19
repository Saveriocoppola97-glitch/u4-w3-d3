package Save;
import Save.dao.DocumentsDAO;
import Save.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Save.dao.UsersDAO;
import Save.entities.User;
import Save.entities.Document;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-w3-d3pu");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        UsersDAO ud = new UsersDAO(em);
        DocumentsDAO dd = new DocumentsDAO(em);

        User Aldo = new User("Aldo", "Baglio");
        //------------------------------------------------- 1 TO 1--------------------------------------------
        // Document aldoDoc = new Document("1234", "Italy", aldo);
        // Non posso usare aldo perche è un oggetto new (cioe non managed o transient), è un oggetto semplice che nulla ha a che fare con il DB
        // Per ottenere oggetti MANAGED ho 2 opzioni: o lo salvo (persist) oppure lo LEGGO DAL DB

        try {
            User aldoFromDb = ud.findById("27c61850-e499-43dc-9dcb-a0072287bfc6");
            Document aldoDoc = new Document("1234", "Italy", aldoFromDb);
            dd.save(aldoDoc);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}