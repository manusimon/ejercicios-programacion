package aplicacion;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Scanner;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import recursos.Cliente;
import recursos.Cuenta;
import recursos.DNI;
import recursos.GestorDeDatos;

public class InterfaceConsola {
    
  public static String DOM2XML(Document doc) {
        String xmlString = null;
        try {
           
            TransformerFactory tf = TransformerFactory.newInstance();
            tf.setAttribute("indent-number",2);
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            t.transform(source, result);
            xmlString = result.getWriter().toString();
        } catch (TransformerException ex) {
            System.out.println("Error al pasar  DOM a XML");
            xmlString = null;
        }
        return xmlString;
    }


    public InterfaceConsola(GestorDeDatos gd) throws Exception{

        Scanner sc = new Scanner(System.in);
        int opcion;
        

        menuGestion();
        opcion = sc.nextInt();

        while(opcion != 3){

            switch(opcion){

                case  1:
                    menuClientes();
                    opcion = sc.nextInt();
    
                    switch(opcion){
    
                        case 1:
                            System.out.println("DNI y nombre de nuevo cliente: ");
                            gd.nuevoCliente(new DNI(sc.next()), sc.next());
                            break;
                        case 2:
                            Iterator <Cliente> it = gd.devolverClientes();
                            while(it.hasNext()){
                                Cliente cli = it.next();
                                System.out.println(cli.getDNI()+" "+cli.getNombre());
                                Iterator <Cuenta> itc = cli.iteradorCuentaClientes();
                                while(itc.hasNext()){
                                    Cuenta cuenta = itc.next();
                                    System.out.println("\t"+cuenta.getCuenta()+" "+cuenta.getSaldo());
                                }
                            }
                            break;
                        case 3:
                            System.out.println("DNI del cliente que desees borrar: ");
                            gd.borrarCliente(new DNI(sc.next()));
                            break;
                        case 4:
                            System.out.println("DNI del cliente a consultar");
                            System.out.println(gd.datosCliente(new DNI(sc.next())));  
                            break;
                        case 5:
                            Iterator<Cliente> todos = gd.devolverClientes();
                            while(todos.hasNext()){
                                Cliente cli = todos.next();
                                System.out.println(cli.getDNI()+" "+cli.getNombre());
                            }
                            break;
                        case 6:
                            
                            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                            Document doc = db.newDocument();
                            
                            Element root = doc.createElement("Clientes");
                            doc.appendChild(root);
                            
                            Iterator<Cliente> itc = gd.devolverClientes();
                            while(itc.hasNext()){
                                
                                Cliente cli = itc.next();
                                Element ecli = doc.createElement("Cliente");
                                ecli.setAttribute("DNI", cli.getDNI().toString());
                                Element nombre = doc.createElement("Nombre");
                                nombre.appendChild(doc.createTextNode(cli.getNombre()));
                                ecli.appendChild(nombre);

                                Iterator<Cuenta> itcuen = cli.iteradorCuentaClientes();
                                Element ecuen = doc.createElement("Cuentas");
                                while(itcuen.hasNext()){
                                    
                                    Cuenta cue = itcuen.next();
                                    Element ecue = doc.createElement("Cuenta");
                                    Element num = doc.createElement("Numero");
                                    num.appendChild(doc.createTextNode(cue.getCuenta()));
                                    ecue.appendChild(num);
                                    Element saldo = doc.createElement("Saldo");
                                    saldo.appendChild(doc.createTextNode(cue.getSaldo().toString()));
                                    ecue.appendChild(saldo);
                                    ecuen.appendChild(ecue);
                                    
                                    
                                }
                                
                                ecli.appendChild(ecuen);
                                doc.getDocumentElement().appendChild(ecli);
                                
                            }
                            
                            System.out.println(DOM2XML(doc));  
                            
                            break;
                        case 7:
                            break;
                        default:
                            System.out.println("Opcion no valida introduce un numero valido");
                            opcion = sc.nextInt();
                            break;
    
                    }
                    break;
                case 2:
                    menuCuentas();
                    opcion = sc.nextInt();
    
                    switch(opcion){
    
                        case 1:
                            System.out.println("Nombre de cuenta y saldo: ");
                            gd.nuevaCuenta(sc.next(), sc.nextBigDecimal());
                            break;
                        case 2:
                            System.out.println("Escribe el numero de cuenta y el DNI: ");
                            gd.añadirTitular(sc.next(), new DNI(sc.next()));
                            break;
                        case 3:
                            System.out.println("Escribe el numero de cuenta y el DNI:");
                            gd.eliminarTitular(sc.next(), new DNI(sc.next()));
                            break;
                        case 4:
                            System.out.println("Introduce el numero de cuenta a consultar");
                            String consulta = sc.next();
                            Iterator<Cuenta> itcue = gd.devolverCuentas();
                            while(itcue.hasNext()){
                                Cuenta cuenta = itcue.next();
                                if(cuenta.getCuenta().equals(consulta)){   
                                    Iterator<Cliente> itcli = cuenta.iteradorClientesCuenta();
                                    while(itcli.hasNext()){ 
                                        Cliente cliente = itcli.next();
                                        System.out.println(cliente.getDNI()+" "+cliente.getNombre());
                                    }
                                }
                                
                            }
                            break;
                        case 5:
                            menuOperaciones();
                            opcion = sc.nextInt();
    
                            switch(opcion){
    
                                case 1:
                                    System.out.println("Escribe el numero cuenta");
                                    System.out.println(gd.consultarSaldo(sc.next()));
                                    break;
                                case 2:
                                    System.out.println("Escibre el numero cuenta y saldo a ingresar:");
                                    gd.ingresoCuenta(sc.next(), sc.nextInt());
                                    //cambiar el sc next por la cuenta actual
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                default:
                                    System.out.println("Opcion no valida introduce un numero valido");
                                    opcion = sc.nextInt();
                                    break;
    
                            }
    
                            break;
                        case 6:
                            System.out.println("Introduce el nombre de la cuenta a borrar:");
                            gd.borrarCuenta(sc.next());
                            break;
                        case 7: 
                            Iterator <Cuenta> itcuenta = gd.devolverCuentas();
                            while(itcuenta.hasNext()){
                                Cuenta cuenta = itcuenta.next();
                                System.out.println(cuenta.getCuenta()+" "+cuenta.getSaldo());
                                Iterator <Cliente> itcliente = cuenta.iteradorClientesCuenta();
                                while(itcliente.hasNext()){
                                    Cliente cliente = itcliente.next();
                                    System.out.println("\t"+cliente.getDNI()+" "+cliente.getNombre());
                                }
                            }
                            break;
                        case 8:
                            break;
                        default:
                            System.out.println("Opcion no valida introduce un numero valido");
                            opcion = sc.nextInt();
                            break;
    
                    } 
            }

            menuGestion();
            opcion = sc.nextInt();

        }        
        
        gd.guardarDatos();
        sc.close();
        

    }
    

    void menuGestion(){

        System.out.println("------------GESTION BANCO------------");
        System.out.println("1. Gestion Clientes");
        System.out.println("2. Gestion Cuentas");
        System.out.println("3. Salir");
        
    }

    void menuClientes(){

        System.out.println("------------GESTION DE CLIENTES------------");
        System.out.println("1. Añadir un cliente");
        System.out.println("2. Cuentas que tiene un cliente");
        System.out.println("3. Borrar un cliente");
        System.out.println("4. Consultar nombre/datos de cliente");
        System.out.println("5. Ver todos los clientes");
        System.out.println("6. Ver todos los clientes en doc XML");
        System.out.println("7. Salir");

    }

    void menuCuentas(){

        System.out.println("------------GESTION DE CUENTAS------------");
        System.out.println("1. Añadir una cuenta");
        System.out.println("2. Añadir titular a una cuenta");
        System.out.println("3. Eliminar titular de una cuenta");
        System.out.println("4. Ver titulares de una cuenta");
        System.out.println("5. Operaciones con cuenta");
        System.out.println("6. Borrar una cuenta");
        System.out.println("7. Ver todas las cuentas");
        System.out.println("8. Salir");

    }

    void menuOperaciones(){

        System.out.println("------------OPERACIONES CON CUENTA ------------");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Ingreso");
        System.out.println("3. Reintegro");
        System.out.println("4. Salir");


    }

    
}


