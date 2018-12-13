package excepcions.ActivitatExceptions.Control;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Model.CompteEstalvi;

public class OperacionsBanc {

    public static boolean verifyDNI(String dni) {
        ValidarDNI validarDNI = new ValidarDNI(dni); //Creamos una instancia de la clase que valida y le pasamos el dni
        return validarDNI.validar(); //Devolvemos el valor que calcula
    }
}
