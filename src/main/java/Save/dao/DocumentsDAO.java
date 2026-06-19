package Save.dao;
import Save.entities.Document;
import Save.entities.User;
import Save.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class DocumentsDAO {
    private final EntityManager entityManager;

    public DocumentsDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Document newDocument) {
        // 1. la creiamo
        EntityTransaction transaction = this.entityManager.getTransaction();
        // 2. la facciamo partire
        transaction.begin();
        // 3. siccome newDocument non è MANAGED, per aggiungerlo all'elenco degli oggetti
        this.entityManager.persist(newDocument);
        // 4. L'operazione di COMMIT sincronizza il PersistenceContext con il DB
        transaction.commit();
        // 5. log di avvenuto salvataggio
        System.out.println("Il documento" + newDocument + "è stato salvato nel db");

    }

    public Document findById(String id) {
      Document fromDb =  this.entityManager.find(Document.class, UUID.fromString(id));
      if (fromDb == null) throw new NotFoundException(id);
      return fromDb;
    }

}
