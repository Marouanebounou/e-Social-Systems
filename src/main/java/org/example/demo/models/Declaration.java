package org.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "declarations" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employer_id", "mois" , "annee"})
})
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int mois;

    @Column(nullable = false)
    private  int annee;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "employer_id" , nullable = false)
    private Employer employer;

    @Column(name = "date_declaration" , nullable = false)
    private LocalDate dateDeclaration;

    @OneToMany(mappedBy = "declaration" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Cotisation> Cotisation;

    public Declaration(long id, int mois, int annee, Employer employer, LocalDate dateDeclaration, List<Cotisation> cotisation) {
        this.id = id;
        this.mois = mois;
        this.annee = annee;
        this.employer = employer;
        this.dateDeclaration = dateDeclaration;
        Cotisation = cotisation;
    }

    public Declaration() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public LocalDate getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(LocalDate dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public List<Cotisation> getCotisation() {
        return Cotisation;
    }

    public void setCotisation(List<Cotisation> cotisation) {
        Cotisation = cotisation;
    }
}
