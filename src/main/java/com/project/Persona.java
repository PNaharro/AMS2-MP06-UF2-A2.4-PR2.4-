package com.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Persona", 
   uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Persona implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personaId;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nom")
    private String nom;
    
    @Column(name = "telefon")
    private String telefon;

    @ManyToMany(mappedBy = "persones", fetch = FetchType.EAGER)
    private Set<Llibre> llibres;
    

    public Persona() {
    }

    public Persona(String dni, String nom, String telefon, Set<Llibre> llibres) {
        this.dni = dni;
        this.nom = nom;
        this.telefon = telefon;
        this.llibres = llibres;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return "Persona [personaId=" + personaId + ", dni=" + dni + ", nom=" + nom + ", telefon=" + telefon
                + ", llibres=" + llibres + "]";
    }
}
