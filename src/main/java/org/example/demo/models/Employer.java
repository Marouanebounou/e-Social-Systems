package org.example.demo.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "raison_social", nullable = false)
    private String raison_social;

    @Column(name = "secteur_activite")
    private String secteur_activite;

    @OneToMany(mappedBy = "employer" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Assure> assures;

    @OneToMany(mappedBy = "employer" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Declaration> declarations;

    public Employer(long id, String raison_social, String secteur_activite, List<Assure> assures, List<Declaration> declarations) {
        this.id = id;
        this.raison_social = raison_social;
        this.secteur_activite = secteur_activite;
        this.assures = assures;
        this.declarations = declarations;
    }

    public Employer() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRaison_social() {
        return raison_social;
    }

    public void setRaison_social(String raison_social) {
        this.raison_social = raison_social;
    }

    public String getSecteur_activite() {
        return secteur_activite;
    }

    public void setSecteur_activite(String secteur_activite) {
        this.secteur_activite = secteur_activite;
    }

    public List<Assure> getAssures() {
        return assures;
    }

    public void setAssures(List<Assure> assures) {
        this.assures = assures;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }
}
