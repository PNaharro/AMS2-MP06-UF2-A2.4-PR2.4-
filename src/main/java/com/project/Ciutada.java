package com.project;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Ciutada")
public class Ciutada implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "cognom")
    private String cognom;

    @Column(name = "edat")
    private int edat;

   

    public Ciutada() {}

    public Ciutada(String nom, String cognom, int edat) {
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
      
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return this.cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getEdat() {
        return this.edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }


    @Override
    public String toString() {
        return this.getId() + ": " + this.getNom() + " " + this.getCognom();
    }
}
