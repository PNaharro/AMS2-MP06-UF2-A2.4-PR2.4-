package com.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Autor", 
   uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Autor implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private Set<Llibre> llibres;

    public Autor() {
    }

    public Autor(String nom, Set<Llibre> llibres) {
        this.nom = nom;
        this.llibres = llibres;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        String string_libros = "";
        for (Llibre llibre : llibres) {
            string_libros += llibre.toString() + " | ";
        }
        return  autorId + " : " + nom + ", items: [ " + string_libros + "]";
    }
}
