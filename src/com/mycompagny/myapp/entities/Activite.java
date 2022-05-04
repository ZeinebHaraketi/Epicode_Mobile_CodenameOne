/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.myapp.entities;

/**
 *
 * @author zeine
 */
public class Activite {
    private int id_a;
    private String nom_a;
    private int cat_age;
    private String type;
    private String image;
    private int id_enfant;

    public Activite() {
    }

    public Activite(int id_a, String nom_a, int cat_age, String type, String image, int id_enfant) {
        this.id_a = id_a;
        this.nom_a = nom_a;
        this.cat_age = cat_age;
        this.type = type;
        this.image = image;
        this.id_enfant = id_enfant;
    }

    public Activite(String nom_a, int cat_age, String type, String image, int id_enfant) {
        this.nom_a = nom_a;
        this.cat_age = cat_age;
        this.type = type;
        this.image = image;
        this.id_enfant = id_enfant;
    }

    public int getId_a() {
        return id_a;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
    }

    public String getNom_a() {
        return nom_a;
    }

    public void setNom_a(String nom_a) {
        this.nom_a = nom_a;
    }

    public int getCat_age() {
        return cat_age;
    }

    public void setCat_age(int cat_age) {
        this.cat_age = cat_age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(int id_enfant) {
        this.id_enfant = id_enfant;
    }

    @Override
    public String toString() {
        return "Activite{" + "id_a=" + id_a + ", nom_a=" + nom_a + ", cat_age=" + cat_age + ", type=" + type + ", image=" + image + ", id_enfant=" + id_enfant + '}';
    }
    
    
    
}
