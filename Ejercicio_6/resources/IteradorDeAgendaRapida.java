
public class IteradorDeAgendaRapida implements MiIterator{
    
    Contacto contacto;
    int posSiguiente;

    public IteradorDeAgendaRapida(AgendaRapida a){

        Iterator<String> it = a.iterator();

    }

    @Override
    public boolean hasNext(){

        return posSiguiente < contacto.length && contacto[posSiguiente] != null;

    }

    @Override
    public Contacto next(){

        return contacto[posSiguiente++];

    }



}
