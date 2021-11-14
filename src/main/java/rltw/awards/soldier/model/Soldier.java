package rltw.awards.soldier.model;

import rltw.awards.unit.model.Unit;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Entity
@Table(name = "soldiers")
@SequenceGenerator(name = "soldier_id_sequence", allocationSize = 1)
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soldier_id_sequence")
    private long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_death")
    private Date dateOfDeath;

    @Column(name = "birthplace")
    @NotEmpty
    private String birthplace;

    @Column(name = "deathplace")
    @NotEmpty
    private String deathplace;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getDeathplace() {
        return deathplace;
    }

    public void setDeathplace(String deathPlace) {
        this.deathplace = deathPlace;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
