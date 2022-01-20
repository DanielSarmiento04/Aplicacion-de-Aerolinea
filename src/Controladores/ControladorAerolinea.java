package Controladores;
import Modelo.ModeloAerolinea;
import Clases.Aerolinea;

/* @author Daniel Sarmiento */

public class ControladorAerolinea {
    ModeloAerolinea ModeloAerolinea;

    public ControladorAerolinea() {
        ModeloAerolinea = new ModeloAerolinea();
    }
    public boolean CntCreadorAerolinea(String Nombre,String Ciudad, int Nit) { 
        Aerolinea Aeorlinea = new Aerolinea(Nombre, Ciudad, Nit);
        return ModeloAerolinea.mdlAgregarAeropuerto(Aeorlinea);
    }
    
}
