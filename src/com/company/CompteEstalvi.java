package com.company;

import java.util.ArrayList;
import java.util.List;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    private List<Client> llista_usuaris = new ArrayList<>();

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        saldo = 400;
    }


    public void addUser(Client client) {
        llista_usuaris.add(client);
    }

    /**
     Elimina un usuari d'aquest compte,
     Com que no pot quedar un compte sense usuari, si és l'ùltim és llança una excepció
     @throws BankAccountException
     *
     * @param dni*/
    public void removeUser(String dni) {
        llista_usuaris.removeIf(u -> {
            try {
                return dni.equals(u.getNom()) && remover();
            } catch (BankAccountException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    private boolean remover() throws BankAccountException {
        if (llista_usuaris.size() >= 2) {
            return true;
        } else {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
        }
    }


    /**
     * Afegeix m diners al saldo
     * @param m
     */
    public void ingressar(double m) {
        saldo += m;
    }

    /**
     * Treu m diners del compte si n'hi han suficient sinó es llança l'excepció
     * @param m
     * @throws BankAccountException
     */
    public void treure(double m) throws BankAccountException {
        if (saldo < m) {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_OVERDRAFT);
        } else {
            saldo -= m;
        }
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }
}
