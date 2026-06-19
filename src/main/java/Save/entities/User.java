package Save.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue // Se non metto nessuna Strategy, sarà AUTO (perfetta per UUID) ne creerà uno diverso a ogni inserimento
    @Column(name = "user_id")
    private UUID id;
    private String name;
    private String surname;

    public User(){
    }

    public User(String name,String surname){
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
