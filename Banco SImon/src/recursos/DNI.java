package recursos;

import java.io.Serializable;
import java.util.Objects;


public class DNI implements Serializable{
    private String dni;//8 digitos + letra
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    public DNI(String dni) throws Exception{
        //suponemos que un dni ES CORRECTO SI tiene  8 digitos + una letra. Sin guiones. 
        //comprobamos si llega String null
        if (dni == null) {
            throw new Exception("dni con valor nulo");
        }
        //comprobamos si la longitud del string no es exactamente 9
        if (dni.length() != 9) {
            throw new Exception("la longinturd de "+ dni +" no es de 9 carateres");
        }

        //comprobamos que último caracter es letra 
        //y la pasamos a mayuscula si no estuviera para simplificar ifs
        char letraDni = dni.charAt(dni.length() - 1);
        letraDni=Character.toUpperCase(letraDni);


        if (!Character.isLetter(letraDni)) {
            throw new Exception("el último caracter de "+ dni +" no es letra ");
        }

        //comprobamos que los 8 primeros caracteres son números
        String parteNumero = dni.substring(0, dni.length() - 1);
        if (!esNumero(parteNumero)) {
            throw new Exception("los 8 primeros caracteres de "+ dni +" no son todos números");
        }

        //compruebo el algortimo de la letra que detecta si hay error tanto en letra como en número
        char letraCorrecta = calcularLetra(parteNumero);
        if (letraDni != letraCorrecta) {
            throw new Exception(" el dni  "+ dni +" no cumple algortimo de validación módulo 23");
        }
        this.dni = dni;
    }


    public boolean esNumero(String parte){

        boolean b = false;

        try{

            Integer.parseInt(parte);
            b = true;

        }catch (NumberFormatException e){

            b = false;

        }

        return b;

    }

    public char calcularLetra(String parte){

        char letra;

        int resto = Integer.parseInt(parte) % 23;

        letra = LETRAS_DNI.charAt(resto);

        return letra;

    }
    
    @Override
    public String toString(){
        
        return this.dni;
             
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DNI other = (DNI) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }
    



}