public class Contacto {
    
    String nombre;
    String telefono;

    public Contacto(String nombre, String telefono){

        this.nombre = nombre;
        this.telefono = telefono;

    }

    public String toString(){

        return "nombre="+ nombre +", telefono="+ telefono;

    }


}
