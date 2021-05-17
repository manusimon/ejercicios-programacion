
import resources.AgendaAbstracta;

public class AgendaRapida implements AgendaAbstracta{
    
    String propietario;
    Contacto contactos;


    AgendaRapida(String propietario){

        this.propietario = propietario;

    }

    String getPropietario(){

        return propietario;

    }

    void add(String propietario, String telefono){

        contactos.add(propietario, telefono);

    }

    MiIterator createIterator(){

        Iterator<String> it = contactos.iterator();

    }


}
