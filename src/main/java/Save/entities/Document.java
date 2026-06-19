package Save.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "documents")

public class Document {
    @Id
    @GeneratedValue
    @Column(name = "document_id")
    // Se non metto nessuna Strategy, sarà AUTO (perfetta per UUID) ne creerà uno diverso a ogni inserimento
    private UUID id;

    @Column(name = "issue_data", nullable = false)
    private LocalDate issueDate;

    private LocalDate expirationDate;

    private String code;

    private String country;

//    1 a 1 la FK sta in questa classe
    @OneToOne // annotazione OBBLIGATORIA se ho un un attributo che ha un tipo corrispondente ad un Entity di un altra classe
    @JoinColumn(name = "user_id", nullable = false, unique = true )  // Annotazione OPZIONALE però consigliata per personalizzare la colonna FK!
    private User owner;

    public Document() {

    }

    public Document(String code, String country, User owner) {
        this.code = code;
        this.country = country;
        this.owner = owner;
        this.issueDate = LocalDate.now();
        this.expirationDate = LocalDate.now().plusYears(10);
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", issueDate=" + issueDate +
                ", expirationDate=" + expirationDate +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                ", owner=" + owner +
                '}';
    }
}
