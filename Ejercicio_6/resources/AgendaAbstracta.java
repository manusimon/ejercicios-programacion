
import java.util.Iterator;
import java.util.ArrayList; 

public interface AgendaAbstracta {
    
    void add(String nombre, String telefono);
    MiIterator createIterator();
    String getPropietario();

}
