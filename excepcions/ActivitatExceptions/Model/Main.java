package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;
import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;

import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<CompteEstalvi> listaComptes = null;
        List<Client> listaClientes = null;


        System.out.println("Que quieres hacer?\n 1.Crear Cliente\n 2.Crear Cuenta\n 3.Añadir Cliente a Cuenta");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
            try {
                System.out.println("Nombre de cliente?");
                String nombre = scanner.nextLine();
                System.out.println("Apellido/s de cliente?");
                String apellidos = scanner.nextLine();
                System.out.println("DNI del cliente?");
                String dni = scanner.nextLine();
                Client client = new Client(nombre, apellidos, dni);

                System.out.println("Cliente correcto");

            } catch (ClientAccountException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage()); //Controlamos el error y lanzamos el mensaje
            }
            break;

            case 2:
                System.out.println("Numero de cuenta?");
                String numero = scanner.nextLine();
                CompteEstalvi compteEstalvi = new CompteEstalvi(numero);
                listaComptes.add(compteEstalvi);
            break;

            case 3:
                CompteEstalvi compteEstalvi1 = null;
                Client client = null;
                boolean cuentaEncontrada = false;
                boolean clienteEncontrado = false;
                System.out.println("Numero de cuenta que quieres añadir algun cliente");
                String numero2 = scanner.nextLine();
                for ( CompteEstalvi c : listaComptes) {
                    if (c.getNumCompte().equals(numero2)) {
                        compteEstalvi1 = c;
                        cuentaEncontrada = true;
                    }

                }
                if (!cuentaEncontrada){
                    System.out.println("No hay ninguna cuenta con ese numero");
                    break;
                }

                System.out.println("Dni del cliente que quieres añadir a la cuenta");
                String dni2 = scanner.nextLine();
                for ( Client c : listaClientes) {
                    if (c.getDNI().equals(dni2)) {
                        compteEstalvi1.addUser(c);
                    }

                }
                if (!cuentaEncontrada){
                    System.out.println("No hay ningun cliente con ese DNI");
                    break;
                }
                System.out.println("Cuenta añadida correctamente");
                break;
        }
    }
}

// case 2:
//         System.out.println("Numero de cuenta?");
//         String numero = scanner.nextLine();
//         CompteEstalvi compteEstalvi = new CompteEstalvi(numero);
//         try {
//         compteEstalvi.ingressar(100);
//         compteEstalvi.treure(100);
//
//         System.out.println("Dinero retirado satisfcatoriamente");
//         } catch (BankAccountException e) {
//         e.printStackTrace();
//         throw new RuntimeException(e.getMessage());
//         }
//         break;
