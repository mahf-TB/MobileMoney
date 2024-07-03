/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Models;

/**
 *
 * @author macbookpro
 */
public class EnvoyerRetrait {

    private Transaction debit;
    private Transaction credit;

    public EnvoyerRetrait(Transaction debit, Transaction credit) {
        super();
        this.debit = debit;
        this.credit = credit;
    }

    public Transaction getDebit() {
        return debit;
    }

    public void setDebit(Transaction debit) {
        this.debit = debit;
    }

    public Transaction getCredit() {
        return credit;
    }

    public void setCredit(Transaction credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "EnvoyerRetrait{" + "debit=" + debit + ", credit=" + credit + '}';
    }
    
    
}
