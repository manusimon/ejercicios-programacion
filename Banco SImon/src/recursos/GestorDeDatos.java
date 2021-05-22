package recursos;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.io.*;
import java.util.Collections;
import java.util.Iterator;


public class GestorDeDatos implements Serializable{

    Map<DNI, Cliente> clientesBD = new HashMap<>();
    Map<String, Cuenta> cuentasBD = new HashMap<>();
    ObjectInputStream clientesin;
    ObjectInputStream cuentasin;
    ObjectOutputStream clientesout;
    ObjectOutputStream cuentasout;
    
    public GestorDeDatos() throws Exception{
        
        clientesBD = leerClientes();
        cuentasBD = leerCuentas();
        
    }
    

    public void nuevoCliente(DNI dni, String nombre) throws Exception {

        clientesBD.put((dni), new Cliente(dni, nombre));

    }

    public void nuevaCuenta(String numero, BigDecimal saldo) {

        cuentasBD.put(numero, new Cuenta(numero, saldo));

    }

    public void borrarCliente(DNI dni) throws Exception {

        clientesBD.remove(dni);

    }

    public void borrarCuenta(String numero) {

        cuentasBD.remove(numero);

    }

    public void ingresoCuenta(String numero, int cantidad) {

        cuentasBD.get(numero).setSaldo(new BigDecimal(cantidad));

    }

    public String consultarSaldo(String numero) {

        return cuentasBD.get(numero).getSaldo().toString();

    }

    public Cliente datosCliente(DNI dni) throws Exception {

        return clientesBD.get(dni);

    }

    public void eliminarTitular(String numero, DNI dni) throws Exception {

        cuentasBD.get(numero).eliminarCli(clientesBD.get(dni));
        clientesBD.get(dni).eliminarCu(cuentasBD.get(numero));

    }

    public void añadirTitular(String numero, DNI dni) throws Exception {

        cuentasBD.get(numero).añadirCli(clientesBD.get(dni));
        clientesBD.get(dni).añadirCu(cuentasBD.get(numero));

    }

    public Iterator<Cuenta> devolverCuentas() {

        return cuentasBD.values().iterator();

    }

    public Iterator<Cliente> devolverClientes() {
        
        
        return clientesBD.values().iterator();

    }
    
    public void guardarDatos() throws Exception{
        
        clientesout = new ObjectOutputStream(new FileOutputStream("clientes.dat"));
        clientesout.writeObject(clientesBD);
        cuentasout = new ObjectOutputStream(new FileOutputStream("cuentas.dat"));
        cuentasout.writeObject(cuentasBD);
        
    }
    
    private Map<DNI,Cliente> leerClientes() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        clientesin = new ObjectInputStream(new FileInputStream("clientes.dat"));
        return (Map<DNI,Cliente>) clientesin.readObject();
        
    }
    
    private Map<String,Cuenta> leerCuentas() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        cuentasin = new ObjectInputStream(new FileInputStream("cuentas.dat"));
        return (Map<String,Cuenta>) cuentasin.readObject();
        
    }
    
    
    
    
    /*
    public Map<String, Cuenta> leerCuentas() {

        Map<String, Cuenta> map = new HashMap<>();
        BufferedReader br = null;

        try {

            File fichero = new File("cuentas.txt");
            br = new BufferedReader(new FileReader(fichero));

            String linea = null;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(":");

                String numero = partes[0].trim();
                String saldo = partes[1].substring(partes[1].indexOf(" "), partes[1].indexOf(".")).trim();
                
                int nuevosaldo = Integer.parseInt(saldo);
                
                if (!numero.equals("") && !saldo.equals("")) {
                    map.put(numero, new Cuenta(numero, new BigDecimal(nuevosaldo)));
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (br != null) {

                try {

                    br.close();

                } catch (Exception e) {

                };

            }

        }

        cuentasBD = map;

        return cuentasBD;
    }

    public Map<DNI, Cliente> leerClientes() {

        Map<DNI, Cliente> map = new HashMap<>();
        BufferedReader br = null;

        try {

            File fichero = new File("clientes.txt");
            br = new BufferedReader(new FileReader(fichero));

            String linea = null;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(":");

                String dni = partes[0].trim();
                String nombre = partes[1].substring(9, partes[1].length());

                if (!dni.equals("") && !nombre.equals("")) {
                    map.put(new DNI(dni), new Cliente(new DNI(dni), nombre));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (br != null) {

                try {

                    br.close();

                } catch (Exception e) {

                };

            }

        }

        clientesBD = map;

        return clientesBD;

    }

    public void serializarCuentas() {

        File cuentas = new File("cuentas.txt");
        BufferedWriter bf = null;

        try {

            bf = new BufferedWriter(new FileWriter(cuentas));

            for (Map.Entry<String, Cuenta> entry : cuentasBD.entrySet()) {

                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();

            }

            bf.flush();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                bf.close();

            } catch (Exception e) {

            }

        }

    }

    public void serializarClientes() {

        File clientes = new File("clientes.txt");
        BufferedWriter bf = null;

        try {

            bf = new BufferedWriter(new FileWriter(clientes));

            for (Map.Entry<DNI, Cliente> entry : clientesBD.entrySet()) {

                bf.write(entry.getKey() + ":" + entry.getValue());

                bf.newLine();
            }

            bf.flush();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                bf.close();

            } catch (Exception e) {

            }

        }
    }

    */
    
}
