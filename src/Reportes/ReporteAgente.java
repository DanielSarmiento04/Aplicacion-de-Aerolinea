package Reportes;

import Modelo.DataBd;
import Modelo.ModeloAgente;
import Clases.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/* @author Daniel Sarmiento */

public class ReporteAgente {
    DataBd Data;

    public ReporteAgente() {
        Data = new DataBd();
    }
    
    public List<Reporte> ListarPorPersona() {
        List<Reporte> ReportesPersona = new ArrayList<>();
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());){
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            int Cantidad = 0;
            String Declaracion = "SELECT \"Agentes\",COUNT(*) from tbl_agentes\n" +
            "UNION\n" +
            "SELECT \"Cliente\",COUNT(*) from tbl_clientes\n" +
            "UNION\n" +
            "SELECT \"Persona\", COUNT(*) from tbl_persona\n" +
            ";";
            PreparedStatement Enunciado = Connection.prepareStatement(Declaracion);
            ResultSet  EntradaBase = Enunciado.executeQuery();
            while (EntradaBase.next()) {
                String Tipo = EntradaBase.getString(1);
                Cantidad = EntradaBase.getInt(1);
                Reporte Reporte = new Reporte(Tipo, Cantidad);
                ReportesPersona.add(Reporte);
            }
        } catch (SQLException e) {
            System.out.println("Error tipo: " + e.getMessage());
        }
        return  ReportesPersona;
    }
}
