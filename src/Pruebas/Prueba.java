package Pruebas;
import Controladores.ControladorAgente;
import java.io.IOException;
import Modelo.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/*@author Daniel Sarmiento */
public class Prueba {
    DataBd Data = new DataBd();
    
    public static void main(String[] args) throws IOException {
    try(Connection Connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/reto_semana5", "root","" );){
        System.out.println("Conexion exitosa");

        String Query = "SELECT count(id_aerolinea) FROM `tbl_aerolinea`;";
        PreparedStatement precount = Connection.prepareStatement(Query);
        ResultSet  EntradaBase = precount.executeQuery();
        while (EntradaBase.next()) {
            int Amount = EntradaBase.getInt(1);
            System.out.println(Amount);
        }
           // System.out.println(CantidadAerolinea);    

        } catch (Exception e) {
            System.out.println("No se pudo establecer conexion, error: " + e.getMessage());

        }
    }
    
}
