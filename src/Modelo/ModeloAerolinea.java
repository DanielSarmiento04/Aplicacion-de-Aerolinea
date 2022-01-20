package Modelo;

import Clases.Aerolinea;
import java.io.BufferedReader; // For Read Line complety
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;   // For exception
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/*@author Daniel Sarmiento */

public class ModeloAerolinea {
    DataBd Data;
    private final String Acceso = "C:/Users/Yolanda/Documents/Java_archivos/RetoSemana3/BaseDeDatosAgente.txt";
    Connection Coneccion;

    public ModeloAerolinea() {
        Data = new DataBd();
    }
   
    
    public boolean mdlAgregarAeropuerto(Aerolinea Aerolinea) {
        System.out.println(Data.getUrl());
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());){
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            int Cantidad = 0;
            String Declaracion = "SELECT count(id_aerolinea) FROM `tbl_aerolinea`;";
            PreparedStatement Enunciado = Connection.prepareStatement(Declaracion);
            ResultSet  EntradaBase = Enunciado.executeQuery();
            while (EntradaBase.next()) Cantidad = EntradaBase.getInt(1);
            
            if (Cantidad == 0) { // putaerolinea in DataBase
                Declaracion = "INSERT INTO `tbl_aerolinea`(`nombre`, `ciudad`, `nit`) VALUES (?,?,?);";
                PreparedStatement PstAerolinea = Connection.prepareStatement(Declaracion,Statement.RETURN_GENERATED_KEYS );
                PstAerolinea.setString(1,Aerolinea.getNombre());// save information in Query  
                PstAerolinea.setString(2, Aerolinea.getCiudad());
                PstAerolinea.setInt(3, Aerolinea.getNit()); 
                int FilaInsertada = PstAerolinea.executeUpdate();
                switch (FilaInsertada) {
                    case 0: // IF no posiible wirte in database
                        System.out.println("No fue posible");
                        break;
                    case 1: // iF write in database the Atributes
                        System.out.println("Fue posible");
                        return true;
                    default:
                        throw new AssertionError();
                }
            }  
            return false;
           
        } catch (SQLException e) {
            System.out.println("Error tipo: " + e.getMessage());
            return false;
        }
        
    }
    public boolean mdlAgregarAeropuerto(String Nombre,String Ciudad,int Nit) {
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());){
            System.out.println("Conexion exitosa");
            
            String Query = "SELECT COUNT(id_aerolinea) FROM `tbl_aerolinea`;";
            Query = "INSERT INTO `tb_pet`( `name`, `code`, `born_year`, `color`, `heal_status`) VALUES (?,?,?,?,?)";
            PreparedStatement PstPet = Connection.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS );
            PstPet.setString(1, Nombre);// sve information in Query  
            PstPet.setString(2, Ciudad);
            PstPet.setInt(3, Nit);
            int Rowinsert = PstPet.executeUpdate();
            
            return false;
        } catch (Exception e) {
            System.out.println("No se pudo establecer conexion, error: " + e.getMessage());
            return false;
        }
        
    }

}
