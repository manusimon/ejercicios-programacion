package recursos;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Cliente implements Serializable{
    
    DNI idCliente;
    String nombre;
    Set<Cuenta> cuentas;

    public Cliente(DNI dni, String nombre){

        this.idCliente = dni;
        this.nombre = nombre;

        cuentas = new HashSet<>();

    }

    public Set<Cuenta> getCuenta(){
        
        return cuentas;

    }

    public void setCliente(String dni)throws Exception{

        this.idCliente = new DNI(dni);

    }
    
    public DNI getDNI(){
        
        return this.idCliente;
        
    }
    
    public String getNombre(){
        
        return this.nombre;
        
    }
    
    public void añadirCu(Cuenta c){
        
        cuentas.add(c);
        
    }
    
    public void eliminarCu(Cuenta c){
        
        cuentas.remove(c);
        
    }
    
     public Iterator<Cuenta> iteradorCuentaClientes(){
        
        return cuentas.iterator();
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idCliente);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        
        return this.idCliente+" "+this.nombre;
        
    }

    
    /*
    @Override
    public String toString(){

        String cuentasCliente = this.nombre+"tiene las cuentas ";

        for(Cuenta c : cuentas){

            cuentasCliente += c.numeroCuenta+" ";

        }

        return cuentasCliente;


    }
    */

}
