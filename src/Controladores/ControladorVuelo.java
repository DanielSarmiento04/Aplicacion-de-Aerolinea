package Controladores;
import Clases.Vuelo;
import Modelo.ModeloVuelo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/*@author Daniel Sarmiento */

public class ControladorVuelo {
    ModeloVuelo ModeloVuelo;

    public ControladorVuelo() {
        ModeloVuelo = new ModeloVuelo();
    }
    
    public boolean CntCrearVuelo(String Codigo, String Origen, String HoraSalida, String Destino, String HoraLlegada, String Tipo) {
        Vuelo Vuelo = new Vuelo(Codigo, Origen, Destino, HoraSalida, HoraLlegada, Tipo);
        return ModeloVuelo.MdlAgregarVuelo(Vuelo);
    }
    public boolean CntActualizarVuelo(String Codigo, String Origen, String HoraSalida, String Destino, String HoraLlegada, String Tipo) {
        Vuelo Vuelo = new Vuelo(Codigo, Origen, Destino, HoraSalida, HoraLlegada, Tipo);
        return ModeloVuelo.MdlActualizarVuelo(Vuelo);
    }
    public boolean CntBuscarVuelo(String CodigoB) {
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        for (Vuelo VueloN : Vuelos) if(CodigoB.equals(VueloN.getCodigo())) return true;
        return false; 
    }
    
    public Vuelo CntTraerVuelo(String CodigoB) {
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        for (Vuelo VueloN : Vuelos) {
            if (CodigoB.equals(VueloN.getCodigo())) return VueloN;
        }
        return null;
    }
    public boolean CntEliminarVuelo(String Codigo) {
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        for (Vuelo VueloN : Vuelos) {
            if (VueloN.getCodigo().equals(Codigo)) {   
            return ModeloVuelo.MdlEliminarVuelo(Codigo);
            }
        }
        return false;
    }

    public DefaultListModel CntCrearListaModeloVuelo() { // For view panel
        DefaultListModel Modelo = new DefaultListModel(); //this list is for showin a listbox
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        int i = 0;  // Star count
        for (Vuelo Vuelo : Vuelos) {
            String TextoBonito = "Codigo: " + Vuelo.getCodigo()+", Origen: " + Vuelo.getOrigen() +", Hora Salida: " + Vuelo.getHoraSalida() +", Destnino: " + Vuelo.getDestino() +
                    ", Hora llegada: " + Vuelo.getHoraLlegada() +", Tipo: " + Vuelo.getTipo()+ ".";
            Modelo.add(i, TextoBonito);
            i+=1;
        }
        return Modelo;
    }  
    public List<String> CntCrearListaStringModeloVuelo() { // For view panel
        List<String> Modelo = new ArrayList<String>(); //this list is for showin a listbox
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        int i = 0;  // Star count
        for (Vuelo Vuelo : Vuelos) {
            String TextoBonito = "Codigo: " + Vuelo.getCodigo()+", Origen: " + Vuelo.getOrigen() +", Hora Salida: " + Vuelo.getHoraSalida() +", Destnino: " + Vuelo.getDestino() +
                    ", Hora llegada: " + Vuelo.getHoraLlegada() +", Tipo: " + Vuelo.getTipo()+ ".";
            Modelo.add(i, TextoBonito);
            i += 1;
        }
        return Modelo;
    }  
    
    public Vuelo CntTraerVueloSeleccionado(String Seleccion) { // For view panel        
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        int i = 0;  // Star count
        for (Vuelo Vuelo : Vuelos) {
            String TextoBonito = "Codigo: " + Vuelo.getCodigo()+", Origen: " + Vuelo.getOrigen() +", Hora Salida: " + Vuelo.getHoraSalida() +", Destnino: " + Vuelo.getDestino() +
                    ", Hora llegada: " + Vuelo.getHoraLlegada() +", Tipo: " + Vuelo.getTipo()+ ".";
            if(TextoBonito.equals(Seleccion))return Vuelo;
            i+=1;
        }       
        return null;
    }
    
    public Vuelo CntCreadorVuelo(String TxtVuelo) {
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        String[] Data = TxtVuelo.split(","); // This it's the key  remember that each class has a diferrent key
        String Codigo = Data[0];
        String Origen = Data[1];
        String Destino = Data[2];
        String HoraSalida = Data[3];
        String HoraLlegada = Data[4];
        String Tipo = Data[5];
        Vuelo Vuelo = new Vuelo(Codigo, Origen, Destino, HoraSalida, HoraLlegada, Tipo);
        return Vuelo;
    }
    
}