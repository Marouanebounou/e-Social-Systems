package org.example.demo.models;

import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "cotisations")
public class Cotisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "declaration_id" , nullable = false)
    private Declaration declaration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assure_id" , nullable = false)
    private Assure assure;

    @Column(name = "salaire_declare", nullable = false)
    private Double salaireDeclare;

    @Column(name = "cotisation_salariale", nullable = false)
    private Double cotisationSalariale;

    @Column(name = "cotisation_patronale", nullable = false)
    private Double cotisationPatronale;

    public Cotisation(long id, Declaration declaration, Assure assure, Double salaireDeclare, Double cotisationSalariale, Double cotisationPatronale) {
        this.id = id;
        this.declaration = declaration;
        this.assure = assure;
        this.salaireDeclare = salaireDeclare;
        this.cotisationSalariale = cotisationSalariale;
        this.cotisationPatronale = cotisationPatronale;
    }

    public Cotisation() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Declaration getDeclaration() {
        return declaration;
    }

    public void setDeclaration(Declaration declaration) {
        this.declaration = declaration;
    }

    public Assure getAssure() {
        return assure;
    }

    public void setAssure(Assure assure) {
        this.assure = assure;
    }

    public Double getSalaireDeclare() {
        return salaireDeclare;
    }

    public void setSalaireDeclare(Double salaireDeclare) {
        this.salaireDeclare = salaireDeclare;
    }

    public Double getCotisationSalariale() {
        return cotisationSalariale;
    }

    public void setCotisationSalariale(Double cotisationSalariale) {
        this.cotisationSalariale = cotisationSalariale;
    }

    public Double getCotisationPatronale() {
        return cotisationPatronale;
    }

    public void setCotisationPatronale(Double cotisationPatronale) {
        this.cotisationPatronale = cotisationPatronale;
    }
}
