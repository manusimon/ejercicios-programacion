import java.util.ArrayList;

public class IteradorDeAgendaInfinita implements MiIterator{

    ArrayList<Contacto> contactos = new ArrayList<>();
    int posSiguiente;

    public IteradorDeAgendaInfinita(AgendaInfinita a){

        Iterator<String> it = a.iterator();
 
    }

    @Override
    public boolean hasNext(){

        return posSiguiente < contactos.size() && contactos.get(posSiguiente) != null;
        
    }

    @Override
    public Contacto next(){

        return contactos.get(posSiguiente++);

    }

}
