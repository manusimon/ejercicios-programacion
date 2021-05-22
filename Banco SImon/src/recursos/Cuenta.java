package recursos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Cuenta implements Serializable{
    
    String numeroCuenta;
    BigDecimal saldo;
    Set<Cliente> clientes;
    

    public Cuenta(String numero, BigDecimal saldo){

        this.numeroCuenta = numero;
        this.saldo = saldo;

        clientes = new HashSet<>();

    }

    public BigDecimal getSaldo(){

        return this.saldo;

    }
    
    public String getCuenta(){
        
        return this.numeroCuenta;
        
    }

    public void setSaldo(BigDecimal cantidad){

        this.saldo = this.saldo.add(cantidad.plus());

    }
    
    public void añadirCli(Cliente c){
        
        clientes.add(c);
        
    }
    
    
    public void eliminarCli(Cliente c){
        
        clientes.remove(c);
        
    }
    
    public Set<Cliente> getCliente(){
        
        return clientes;
        
    }
    
    public Iterator<Cliente> iteradorClientesCuenta(){
        
        return clientes.iterator();
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.numeroCuenta);
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
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.numeroCuenta, other.numeroCuenta)) {
            return false;
        }
        return true;
    }
    


    @Override
    public String toString(){

        return this.numeroCuenta+" "+this.saldo;


    }


}
