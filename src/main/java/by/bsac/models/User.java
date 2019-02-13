package by.bsac.models;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 *
 */
@Entity
@Proxy(lazy = false)
@Table(name="user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "user_fname")
    private String fName;

    @Column(name = "user_lname")
    private String lName;

    public User() {

    }

    public User(String a_fname, String a_lname) {

        super();
        this.setfName(a_fname);
        this.setlName(a_lname);

    }

    //Getters and setter:
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }


    //Overriding java.lang.Object
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "ID: " +getId() +", fname = " +getfName() +", lname = " +getlName();
    }
}
