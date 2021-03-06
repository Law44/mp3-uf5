package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;
import excepcions.ActivitatExceptions.Exceptions.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompteEstalvi {
    private Scanner scanner = new Scanner(System.in);
    private String numCompte;
    private double saldo;
    private List<Client> llista_usuaris = new ArrayList<>();

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        saldo = 0;
    }

    /**
        Afegeix un usuari d'aquest compte
        @param client
        @return quantitat d'usuaris que té el compte

     **/
    public int addUser(Client client) {
        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     Elimina un usuari d'aquest compte
     @param dni
     @return quantitat d'usuaris que té el compte
     @throws BankAccountException
     **/
    public int removeUser(String dni) throws BankAccountException {
        if (llista_usuaris.size() > 1) {
            llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
            return llista_usuaris.size();
        }
        else {throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);}
    }

    /**
     * Afegeix m diners al saldo
     * @param m
     */
    public void ingressar(double m) throws BankAccountException {
        if (this.llista_usuaris.size() > 0) {
            if (m > 0) {
                saldo += m;
            } else {
                throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
            }
        }
        else {
                throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
            }

    }

    /**
     * Treu m diners del compte si n'hi han suficient
     * @param m
     * @throws BankAccountException
     */
    public void treure(double m) throws BankAccountException {
        if (this.llista_usuaris.size() > 0) {
            if (saldo >= m) saldo -= m;
            else {
                throw new BankAccountException(ExceptionMessage.ACCOUNT_OVERDRAFT);
            }
        }
        else {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
        }
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() throws BankAccountException {
        if (this.llista_usuaris.size() > 0) {
            return saldo;
        }
        else {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
        }
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }
}
