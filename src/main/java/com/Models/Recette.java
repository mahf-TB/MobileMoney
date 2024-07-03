package com.Models;

import java.sql.Date;

/**
 *
 * @author macbookpro
 */
public class Recette {

    private int id;
    private double montant;
    private Date date;

    public Recette( double montant) {
        super();
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Recette{" + "id=" + id + ", montant=" + montant + ", date=" + date + '}';
    }
    
    
    

}
