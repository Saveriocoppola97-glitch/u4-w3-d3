package Save.dao;
import Save.entities.User;
import Save.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;
// DAO termine tecnico che significa data access Object, è un astrazione, siccome le interazioni con il DB richiedono parecchie righe di codice non semplici,
//creiamo questa classe che fornisce metodi semplici da usare nel main nascondendo un po queste complessità che inseriremo qui

public class UsersDAO {

    private final EntityManager entityManager;
    // Tutti i metodi di questo DAO avranno bisogno di utilizzare 1'EntityManager poichè é l'oggetto che mi consente
    // di salvare, cancellare, leggere, sincronizzarmi col DB. Siccome l'oggetto entity manager viene creato nel main
    // è comodo passarlo come parametro del costruttore del DAO in maniera da avercelo già a disposizione in tutti i suoi metodi


    public UsersDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(User newUser) {
        // Entity manager vuole che quando facciamo modifiche esige una transizione
        // 1. la creiamo
        EntityTransaction transaction = this.entityManager.getTransaction();
        // 2. la facciamo partire
        transaction.begin();
        // 3. siccome newUser non è MANAGED, per aggiungerlo all'elenco degli oggetti
        // monitorati (PersistenceContext) dobbiamo effettuare un operazione di PERSIST
        // l'oggetto managed però è ancora parte del DB
        this.entityManager.persist(newUser);
        // 4. L'operazione di COMMIT sincronizza il PersistenceContext con il DB
        // siccome in questo caso c'è un oggetto nuovo nel PC, creerà una nuova riga nella tabella User.
        transaction.commit();
        // 5. log di avvenuto salvataggio
        System.out.println("L'utente" + newUser + "è stato salvato nel db");

    }

    public User findById(String id) {
      User fromDb =  this.entityManager.find(User.class, UUID.fromString(id)); // se non trova niente mi torna null
      if (fromDb == null) throw new NotFoundException(id);
      return fromDb;
    }



}
