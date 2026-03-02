package org.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "assures")
public class Assure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name="salaire_mensuel" , nullable = false)
    private Double salaireMensuel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employeur_id" , nullable = false)
    private  Employer employer;

    public Assure(long id, String name, Double salaireMensuel, Employer employer) {
        this.id = id;
        this.name = name;
        this.salaireMensuel = salaireMensuel;
        this.employer = employer;
    }

    public Assure() {

    }

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

    public Double getSalaireMensuel() {
        return salaireMensuel;
    }

    public void setSalaireMensuel(Double salaireMensuel) {
        this.salaireMensuel = salaireMensuel;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
