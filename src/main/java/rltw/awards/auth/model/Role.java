package rltw.awards.auth.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@SequenceGenerator(name = "role_id_sequence", allocationSize = 1)
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

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
}
