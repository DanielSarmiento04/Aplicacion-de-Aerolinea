package Controladores;
import Modelo.ModeloExcel;

/* @author Daniel Sarmiento */
public class ControladorExcel {
    ModeloExcel ModeloExcel;
    public ControladorExcel() {
        ModeloExcel = new ModeloExcel();        
    }
    public boolean CntAgenteExcel(String Nombre) {
        return ModeloExcel.MdlAgentesExcel(Nombre);
    }
    public boolean CntVueloExcel(String Nombre) {
        return ModeloExcel.MdlVuelosExcel(Nombre);
    }
    public boolean CntClienteExcel(String Nombre) {
        return ModeloExcel.MdlClientesExcel(Nombre);
    }
    
}
