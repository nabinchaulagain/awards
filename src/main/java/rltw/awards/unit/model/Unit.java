package rltw.awards.unit.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "units")
@SequenceGenerator(name = "unit_id_sequence", allocationSize = 1)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_id_sequence")
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "country")
    @NotEmpty
    private String country;

    @Column(name = "branch")
    @NotEmpty
    private String branch;

    @Column(name = "established_date")
    private Date establishedDate;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", branch='" + branch + '\'' +
                ", establishedDate=" + establishedDate +
                '}';
    }
}
