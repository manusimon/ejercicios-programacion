
import resources.*;

public class Cliente {
    public static void main(String[] args) {

        AgendaAbstracta agendaChuchi= new AgendaInfinita("Chuchi infinito");
        agendaChuchi.add("eluno", "11111");
        agendaChuchi.add("dosi", "22222");
        agendaChuchi.add("tresi", "333333");
        MiIterator  it= agendaChuchi.createIterator();
        System.out.println("Agenda de "+ agendaChuchi.getPropietario());

        while(it.hasNext()){
            System.out.println(it.next());
        }

         System.out.println("------------------------------------");
        agendaChuchi= new AgendaRapida("Chuchi Rapido");
        agendaChuchi.add("eluno", "11111");
        agendaChuchi.add("dosi", "22222");
        agendaChuchi.add("tresi", "333333");
        it= agendaChuchi.createIterator();
        System.out.println("Agenda de "+ agendaChuchi.getPropietario());
        while(it.hasNext()){
            System.out.println(it.next());

        }
    }
}
