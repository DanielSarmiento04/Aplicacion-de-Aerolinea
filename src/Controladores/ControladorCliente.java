package Controladores;
import Clases.Aerolinea;
import Clases.Cliente;
import Clases.Vuelo;
import Modelo.ModeloCliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
/*@author Daniel Sarmiento */

public class ControladorCliente {
    ModeloCliente ModeloCliente;

    public ControladorCliente() {
        ModeloCliente = new ModeloCliente();
    }
    // Definitely Creator For Customer
    
    public boolean CntCrearCliente (Cliente Cliente) {
        return ModeloCliente.MdlAgregarCliente(Cliente);
    }
    
    public boolean CntActualizarCliente(Cliente Cliente) {
        return ModeloCliente.MdlAgregarCliente(Cliente);
    }        
    
    public boolean CntBucarCliente (int CedulaB) {
        List<Cliente> Clientes = ModeloCliente.MdlLeerClientes();
        System.out.println("fe");
        for (Cliente ClienteN : Clientes) {
            if (ClienteN.getCedula() == CedulaB) return true;
        }
        return false;
    }
    public boolean CntEliminarCliente(int Cedula) {
        return ModeloCliente.MdlEliminarCliente(Cedula);
    }
    public Cliente CntTraerCliente(int Cedula) {        
        List<Cliente> Clientes = ModeloCliente.MdlLeerClientes();
        for (Cliente Cliente : Clientes) {
            if (Cliente.getCedula()== Cedula) return Cliente; 
        }
        return null; 
    }
   
    public DefaultListModel CntCrearListaModeloClientes() { // For view panel
        DefaultListModel Modelo = new DefaultListModel(); //this list is for showin a listbox
        List<Cliente> Clientes = ModeloCliente.MdlLeerClientes();
        int i = 0;
        for (Cliente Cliente : Clientes) {
            Vuelo Vuelo = Cliente.getVuelo();
            String TextoBonito = "Nombre: " + Cliente.getNombre() + ", cedula: " + Cliente.getCedula() + ".";
            Modelo.add(i, TextoBonito);
            i += 1;  // increase
        }
        return Modelo;
    }  
    
}
