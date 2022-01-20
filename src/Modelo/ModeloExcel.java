package Modelo;

import Clases.*;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/* @author Daniel Sarmiento*/

public class ModeloExcel {
    final private String[] CabecerasAgente = {"Nombre","Cedula","Numero Telefonico","Cargo","Codigo"};
    final private String[] CabecerasVuelo = {"Codigo","Origen","HoraSalida","Destino","HoraLlegada","Tipo"};
    final private String[] CabecerasCliente = {"Nombre","Cedula","Numero Telefonico","Direccion","Pasaporte","Email"};
    
    //Final because sttring[] might no modify
    ModeloAgente ModeloAgente;
    ModeloVuelo ModeloVuelo;
    ModeloCliente ModeloCliente;
    
    public ModeloExcel() {
        ModeloAgente = new ModeloAgente();
        ModeloVuelo = new ModeloVuelo();
        ModeloCliente = new ModeloCliente();
    }
    
    public boolean MdlAgentesExcel(String NombreA) {        
        HSSFWorkbook Libro = new HSSFWorkbook(); //Create a white bookwork    
        HSSFSheet Hoja = Libro.createSheet("Agentes");//another form to do the same
        HSSFRow FilaCab = Hoja.createRow(0);
        int Columna = 0;
        for (String Cabecera : CabecerasAgente) {
            HSSFCell Celda = FilaCab.createCell(Columna);// Create Cell
            Celda.setCellValue(Cabecera);// Add incell
            Columna++;
        }
        List<Agente> Agentes = ModeloAgente.MdlLeerAgentes();  
        int Fila = 1;

        for (Agente Agente : Agentes) {
            HSSFRow FilaDa = Hoja.createRow(Fila);
            String Nombre = Agente.getNombre();
            String Cargo = Agente.getCargo();
            int Cedula = Agente.getCedula();
            int Codigo = Agente.getCodigo();
            long NumeroTel = Agente.getNumCelular();
            
            FilaDa.createCell(0).setCellValue(Nombre);
            FilaDa.createCell(1).setCellValue(Cedula);
            FilaDa.createCell(2).setCellValue(NumeroTel);
            FilaDa.createCell(3).setCellValue(Cargo);
            FilaDa.createCell(4).setCellValue(Codigo);
            Fila++;
        }// in this part you have all information in  a amoment it's neccesary save it
        
        try (FileOutputStream Archivo = new FileOutputStream(NombreA+".xls");){
            Libro.write(Archivo);//Put in documente
            return true;
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
            return false;
        }
    }
    public boolean MdlClientesExcel(String NombreA) {        
        HSSFWorkbook Libro = new HSSFWorkbook(); //Create a white bookwork    
        HSSFSheet Hoja = Libro.createSheet("Vuelo");//another form to do the same
        HSSFRow FilaCab = Hoja.createRow(0);
        int Columna = 0;
        for (String Cabecera : CabecerasCliente) {
            HSSFCell Celda = FilaCab.createCell(Columna);// Create Cell
            Celda.setCellValue(Cabecera);// Add incell
            Columna++;
        }
        
        List<Cliente> Clientes = ModeloCliente.MdlLeerClientes();
        int Fila = 1;
        for (Cliente Cliente : Clientes) {
            HSSFRow FilaDa = Hoja.createRow(Fila);
            String Nombre = Cliente.getNombre();
            int Cedula = Cliente.getCedula();
            long NumeroTel = Cliente.getNumCelular();
            String Direccion = Cliente.getDreccion();            
            String Email = Cliente.getEmail();
            String Pasaporte = Cliente.getPasaporte();
            
            FilaDa.createCell(0).setCellValue(Nombre);
            FilaDa.createCell(1).setCellValue(Cedula);
            FilaDa.createCell(2).setCellValue(NumeroTel);
            FilaDa.createCell(3).setCellValue(Direccion);
            FilaDa.createCell(4).setCellValue(Email);            
            FilaDa.createCell(5).setCellValue(Pasaporte);
            Fila++;
        }
       // in this part you have all information in  a amoment it's neccesary save it
        
        try (FileOutputStream Archivo = new FileOutputStream(NombreA+".xls");){
            Libro.write(Archivo);//Put in documente
            return true;
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
            return false;
        }
    }
    
    public boolean MdlVuelosExcel(String NombreA) {        
        HSSFWorkbook Libro = new HSSFWorkbook(); //Create a white bookwork    
        HSSFSheet Hoja = Libro.createSheet("Vuelo");//another form to do the same
        HSSFRow FilaCab = Hoja.createRow(0);
        int Columna = 0;
        for (String Cabecera : CabecerasVuelo) {
            HSSFCell Celda = FilaCab.createCell(Columna);// Create Cell
            Celda.setCellValue(Cabecera);// Add incell
            Columna++;
        }
        List<Vuelo> Vuelos = ModeloVuelo.MdlLeerVuelos();
        int Fila = 1;
        for (Vuelo Vuelo : Vuelos) {
            HSSFRow FilaDatos = Hoja.createRow(Fila);
            String Codigo = Vuelo.getCodigo();
            String Origen = Vuelo.getOrigen();
            String HoraSalida = Vuelo.getHoraSalida();
            String Destino = Vuelo.getDestino();
            String HoraLlegada = Vuelo.getHoraLlegada();
            String Tipo = Vuelo.getTipo();
            FilaDatos.createCell(0).setCellValue(Codigo);
            FilaDatos.createCell(1).setCellValue(Origen);
            FilaDatos.createCell(2).setCellValue(HoraSalida);
            FilaDatos.createCell(3).setCellValue(Destino);
            FilaDatos.createCell(4).setCellValue(HoraLlegada);            
            FilaDatos.createCell(5).setCellValue(Tipo);     
            Fila++;
        }// in this part you have all information in  a amoment it's neccesary save it
        
        try (FileOutputStream Archivo = new FileOutputStream(NombreA+".xls");){
            Libro.write(Archivo);//Put in documente
            return true;
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
            return false;
        }
    }
    
}
