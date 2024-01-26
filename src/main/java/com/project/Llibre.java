package com.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Llibre", 
   uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Llibre implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long llibreId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "editorial")
    private String editorial;
    
    @ManyToOne
    @JoinColumn(name = "autor_id", insertable=false,updatable=false)
    private Autor autor;

    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Llibre_Persona", joinColumns = @JoinColumn(name = "llibreId"), inverseJoinColumns = @JoinColumn(name = "personaId"))
    private Set<Persona> persones;

    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;

    public Llibre() {
    }

    public Llibre(String nom, String editorial, Autor autor, Set<Persona> persones, Biblioteca biblioteca) {
        this.nom = nom;
        this.editorial = editorial;
        this.autor = autor;
        this.persones = persones;
        this.biblioteca = biblioteca;
    }

    public long getLlibreId() {
        return llibreId;
    }

    public void setLlibreId(long llibreId) {
        this.llibreId = llibreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Set<Persona> getPersones() {
        return persones;
    }

    public void setPersones(Set<Persona> persones) {
        this.persones = persones;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public String toString() {
        return llibreId +" : " + nom + ", " + editorial;
    }

}
