package excepcions.ActivitatExceptions.Control;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;
import excepcions.ActivitatExceptions.Exceptions.ExceptionMessage;
import excepcions.ActivitatExceptions.Model.Client;
import excepcions.ActivitatExceptions.Model.CompteEstalvi;

import java.util.List;
import java.util.Scanner;

public class OperacionsBanc {

    private Scanner scanner = new Scanner(System.in);

    public static boolean verifyDNI(String dni) {
        ValidarDNI validarDNI = new ValidarDNI(dni); //Creamos una instancia de la clase que valida y le pasamos el dni
        return validarDNI.validar(); //Devolvemos el valor que calcula
    }

    public CompteEstalvi encontrarCuenta ( List<CompteEstalvi> listaComptes) throws BankAccountException {
        CompteEstalvi compteEstalvi = null;
        boolean cuentaEncontrada = false;
        System.out.println("Numero de cuenta");
        String numeroCuenta = scanner.nextLine();
        if (listaComptes != null){
            for (CompteEstalvi c : listaComptes) {
                if (c == null) {
                    continue;
                } else if (c.getNumCompte().equals(numeroCuenta)) {
                    compteEstalvi = c;
                    cuentaEncontrada = true;
                }

            }
        }
        if (!cuentaEncontrada){
            throw new BankAccountException(ExceptionMessage.ACCOUNT_NOT_FOUND);

        }
        else {
            return compteEstalvi;
        }

    }

    public Client encontrarCliente (List<Client> listaClientes) throws ClientAccountException{
        System.out.println("Dni del cliente");
        String dni = scanner.nextLine();
        boolean clienteEncontrado = false;
        Client client = null;
        if (listaClientes != null) {
            for (Client c : listaClientes) {
                if (c == null) {
                    continue;
                } else if (c.getDNI().equals(dni)) {
                     client = c;
                     clienteEncontrado = true;
                }

            }
        }
        if (!clienteEncontrado) {
            throw new ClientAccountException(ExceptionMessage.DNI_NOT_FOUND);
        }
        else {
            return client;
        }
    }

    public void transferencia(CompteEstalvi font, CompteEstalvi desti, double suma) throws BankAccountException {
        if (suma > 0) {
            font.treure(suma);
            desti.ingressar(suma);
            System.out.println("Se han transferido " + suma + " euros de la cuenta con numero " + font.getNumCompte() + " a la cuenta con numero " + desti.getNumCompte());
        }
        else {
            throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);

        }
    }
}
