
import resources.AgendaAbstracta;

public class AgendaInfinita implements AgendaAbstracta{
    
    String propietario;
    ArrayList<Contacto> contacto = new ArrayList<>();

    AgendaInfinita(String propietario){

        this.propietario = propietario;

    }

    String getPropietario(){

        return propietario;

    }

    void add(String nombre, String telefono){

        contacto.add(nombre, telefono);

    }

    MiIterator createIterator(){

        Iterator<String> it = contacto.iterator();

    }


}
