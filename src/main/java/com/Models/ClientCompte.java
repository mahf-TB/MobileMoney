
package com.Models;

import java.util.List;


public class ClientCompte {
     private Client client;
    private Compte comptes;

    public ClientCompte(Client client, Compte comptes) {
        this.client = client;
        this.comptes = comptes;
    }

    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Compte getComptes() {
        return comptes;
    }

    public void setComptes(Compte comptes) {
        this.comptes = comptes;
    }    
    
}
