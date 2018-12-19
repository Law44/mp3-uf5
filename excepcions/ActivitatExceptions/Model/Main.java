package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;
import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<CompteEstalvi> listaComptes = new ArrayList<>();
        List<Client> listaClientes = new ArrayList<>();
        OperacionsBanc operacionsBanc = new OperacionsBanc();

        boolean cuentaEncontrada;
        boolean clienteEncontrado;
        String numeroCuenta;
        CompteEstalvi compteEstalvi;
        Client client;
        String dni;

        int opcion;

        do {
            System.out.println("Que quieres hacer?\n 1.Crear Cliente\n 2.Crear Cuenta\n 3.Añadir Cliente a Cuenta\n 4.Mostrar dinero cuenta\n 5.Ingresar dinero en cuenta\n 6.Retirar dinero de cuenta\n 7.Retirar usuario de cuenta\n 8.Hacer transferencia\n 9.Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    try {
                        System.out.println("Nombre de cliente?");
                        String nombre = scanner.nextLine();
                        System.out.println("Apellido/s de cliente?");
                        String apellidos = scanner.nextLine();
                        System.out.println("DNI del cliente?");
                        dni = scanner.nextLine();
                        client = new Client(nombre, apellidos, dni);
                        listaClientes.add(client);
                        System.out.println("Cliente correcto");

                    } catch (ClientAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    }
                    break;

                case 2:
                    System.out.println("Numero de cuenta?");
                    numeroCuenta = scanner.nextLine();
                    compteEstalvi = new CompteEstalvi(numeroCuenta);
                    listaComptes.add(compteEstalvi);
                    break;

                case 3:
                    compteEstalvi = null;

                    try {
                        compteEstalvi = operacionsBanc.encontrarCuenta(listaComptes);
                    } catch (BankAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                        break;
                    }

                    try {
                        client = operacionsBanc.encontrarCliente(listaClientes);
                        compteEstalvi.addUser(client);
                    } catch (ClientAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                        break;
                    }

                    System.out.println("Cuenta añadida correctamente");
                    break;
                case 4:
                    compteEstalvi = null;

                    try {
                        compteEstalvi = operacionsBanc.encontrarCuenta(listaComptes);
                        System.out.println("EL saldo es de " + compteEstalvi.getSaldo());
                    } catch (BankAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    }
                    break;
                case 5:
                    compteEstalvi = null;
                    try {
                        compteEstalvi = operacionsBanc.encontrarCuenta(listaComptes);
                        System.out.println("Cuanto quieres ingresar? (Formato 00.00)");
                        double ingreso = scanner.nextDouble();
                        scanner.nextLine();
                        compteEstalvi.ingressar(ingreso);
                        System.out.println("Se han ingresado " + ingreso + " correctamente");
                    } catch (BankAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    }
                    break;
                case 6:
                    compteEstalvi = null;
                    try {
                        compteEstalvi = operacionsBanc.encontrarCuenta(listaComptes);
                        System.out.println("Cuanto quieres retirar? (Formato 00.00)");
                        double ingreso = scanner.nextDouble();
                        scanner.nextLine();
                        compteEstalvi.treure(ingreso);
                        System.out.println("Se han retirado " + ingreso + " correctamente");
                    } catch (BankAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    }
                    break;
                case 7:
                    compteEstalvi = null;

                    try {
                        compteEstalvi = operacionsBanc.encontrarCuenta(listaComptes);
                    } catch (BankAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    }

                    try {
                        client = operacionsBanc.encontrarCliente(listaClientes);
                        System.out.println("Ahora quedan " + compteEstalvi.removeUser(client.getDNI()) + " usuarios en esta cuenta");
                    } catch (ClientAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    } catch (BankAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    }
                    break;
                case 8:
                    try {
                        System.out.println("Cuenta Origen de la transferencia");
                        CompteEstalvi compteEstalvi1 = operacionsBanc.encontrarCuenta(listaComptes);
                        System.out.println("Cuenta Destino de la transferencia");
                        CompteEstalvi compteEstalvi2 = operacionsBanc.encontrarCuenta(listaComptes);
                        System.out.println("Cantidad que quieres transferir?");
                        double transferencia = scanner.nextDouble();
                        scanner.nextLine();
                        operacionsBanc.transferencia(compteEstalvi1, compteEstalvi2, transferencia);
                    } catch (BankAccountException e) {
                        e.printStackTrace();
                        System.out.println("\n");
                    }
                    break;
            }
        }while(opcion < 9);

    }

}




