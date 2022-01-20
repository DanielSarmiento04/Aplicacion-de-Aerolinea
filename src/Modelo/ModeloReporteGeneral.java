package Modelo;
import Modelo.DataBd;
import Clases.Agente;
import Clases.Reporte;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/* @author Daniel Sarmiento */

public class ModeloReporteGeneral {
    DataBd Data;
    
    public ModeloReporteGeneral() {
        Data = new DataBd();
    }
    
    public List<Reporte> MdlReportePersona() {
        List<Reporte> Reportes = new ArrayList<Reporte>();
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "SELECT 'Clientes',COUNT(*) FROM `tbl_clientes`\n" +
                    "UNION\n" +
                    "select 'Agentes',COUNT(*) from tbl_agentes  ;";
            PreparedStatement PstReportePersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            ResultSet Informacion = PstReportePersona.executeQuery();  //Put in sql
            while (Informacion.next()) { // scan in line
                String Tipo = Informacion.getString(1);
                int Total = Informacion.getInt(2);
                Reporte Reporte = new Reporte(Tipo, Total);
                Reportes.add(Reporte);
            }
        } catch (SQLException e) {      
            System.out.println("Erro tipo"+  e.getMessage());
        }
        return Reportes;
    }    
    public List<Reporte> MdlReporteVuelo() {
        List<Reporte> Reportes = new ArrayList<Reporte>();
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "SELECT 'Nacional',COUNT(*) FROM `tbl_vuelo` WHERE tipo = 'Nacional'\n" +
                        "UNION\n" +
                        "SELECT 'Internacional', COUNT(*)FROM `tbl_vuelo` WHERE tipo = 'Internacional';";
            PreparedStatement PstReportePersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            ResultSet Informacion = PstReportePersona.executeQuery();  //Put in sql
            while (Informacion.next()) { // scan in line
                String Tipo = Informacion.getString(1);
                int Total = Informacion.getInt(2);
                Reporte Reporte = new Reporte(Tipo, Total);
                Reportes.add(Reporte);
            }
        } catch (SQLException e) {      
            System.out.println("Erro tipo"+  e.getMessage());
        }
        return Reportes;
    }    
    public List<Reporte> MdlReporteCliente() {
        List<Reporte> Reportes = new ArrayList<Reporte>();
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "SELECT 'Premium',COUNT(*) FROM tbl_clientes WHERE tipo = 'Premium'\n" +
                "	UNION\n" +
                "SELECT 'Vip', COUNT(*)FROM tbl_clientes WHERE tipo = 'Vip';";
            PreparedStatement PstReportePersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            ResultSet Informacion = PstReportePersona.executeQuery();  //Put in sql
            while (Informacion.next()) { // scan in line
                String Tipo = Informacion.getString(1);
                int Total = Informacion.getInt(2);
                Reporte Reporte = new Reporte(Tipo, Total);
                Reportes.add(Reporte);
            }
        } catch (SQLException e) {      
            System.out.println("Erro tipo"+  e.getMessage());
        }
        return Reportes;
    }    
}
