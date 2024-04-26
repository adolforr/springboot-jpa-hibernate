package com.springboot.springjpahibernate.springbootjpahibernate.entities;

/*import java.time.LocalDateTime;*/

import jakarta.persistence.*;

@Entity
@Table(name="persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    // @Column(name = "create_at")
    // private LocalDateTime createAt;

    // @Column(name = "update_at")
    // private LocalDateTime updateAt;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Embedded
    private Audit audit = new Audit();

    
    public Person() {
    }

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    
    public Person(Long id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }

    // @PrePersist
    // public void prePersist(){
    //     System.out.println("Evento del cilco de vida del entity pre persist");
    //     this.createAt = LocalDateTime.now();
    // }

    // @PreUpdate
    // public void preUpdate(){
    //     System.out.println("Evento del ciclo de vida del objeto entity pre-update");
    //     this.updateAt = LocalDateTime.now();
    // }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }    

    // public LocalDateTime getCreateAt() {
    //     return createAt;
    // }

    // public LocalDateTime getUpdateAt() {
    //     return updateAt;
    //}

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", lastname=" + lastname + ", createAt=" + audit.getCreatAt()
                + ", updateAt=" + audit.getUpdatedAt() + ", programmingLanguage=" + programmingLanguage + "]";
    }


    

    

}
