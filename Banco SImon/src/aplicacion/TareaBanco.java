package aplicacion;

import recursos.GestorDeDatos;


public class TareaBanco {
    public static void main(String[] args) throws Exception{
        
        new InterfaceConsola(new GestorDeDatos());

    }
    
}
