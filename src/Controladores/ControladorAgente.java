package Controladores;
import Clases.Agente;
import Modelo.ModeloAgente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/*@author Daniel Sarmiento  */

public class ControladorAgente {
    ModeloAgente ModeloAgente;
    
    public ControladorAgente() {
        ModeloAgente = new ModeloAgente();
    }
    
    public boolean CntCrearAgente(int Codigo, String cargo, int Cedula, String Nombre, long NumCelular) {
        Agente Agente = new Agente(Codigo, cargo, Cedula, Nombre, NumCelular);
        return ModeloAgente.MdlAgregarAgente(Agente);
    }
    
    public boolean CntBuscarAgente(int Cedula) {
        List<Agente> Agentes = ModeloAgente.MdlLeerAgentes();
        for (Agente AgenteN : Agentes) {
            if (AgenteN.getCedula()== Cedula) return true;
        }
        return false;
    }
    
    public void CntListarAgentes() {
        List<Agente> Agentes = ModeloAgente.MdlLeerAgentes();
    }
    public boolean CntActualizarAgente(int Codigo, String cargo, int Cedula, String Nombre, long NumCelular) {
        Agente Agente = new Agente(Codigo, cargo, Cedula, Nombre, NumCelular);
        return ModeloAgente.MdlActualizarAgente(Agente);
    }
    
    public Agente CntTraerAgente(int Cedula) {
        List<Agente> Agentes = ModeloAgente.MdlLeerAgentes();
        for (Agente AgenteN : Agentes) {
            if (AgenteN.getCedula() == Cedula) return AgenteN;             
        }
        return null;
    }
    
    public boolean CntEliminarAgente(int Ceduña) { // For Delete Object
        return ModeloAgente.MdlEliminarAgente(Ceduña);
    }
    
    public DefaultListModel CntCrearListaModeloAgente() { // For view panel
        DefaultListModel Modelo = new DefaultListModel(); //this list is for showin a listbox
        
        List<Agente> Agentes = ModeloAgente.MdlLeerAgentes();
        int i = 0;
        for (Agente Agente : Agentes) {
            String TextoBonito = "Nombre: " + Agente.getNombre() + ", cedula: " + Agente.getCedula() +", Telefono:" + Agente.getNumCelular() +", codigo: " + Agente.getCodigo() +", cargo: " + Agente.getCargo() + ".";
            Modelo.add(i, TextoBonito);
            i += 1;
        }
        return Modelo;
    }  
}