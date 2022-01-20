package Modelo;

import Clases.Vuelo;
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

public class ModeloVuelo {
    DataBd Data;
    private final String Acceso = "C:/Users/Yolanda/Documents/Java_archivos/RetoSemana3/BaseDeDatosAgente.txt";
    Connection Coneccion;

    public ModeloVuelo() {
        Data = new DataBd();
    }
    
    public boolean MdlAgregarVuelo(Vuelo Vuelo){
        try (Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());){
            String Declaracion = "INSERT INTO `tbl_vuelo`( `codigo`, `origen`, `horasalida`, `destino`, `horallegada`, `tipo`, `id_aerolinea`) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement PstAvion = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstAvion.setString(1, Vuelo.getCodigo());// put in declaration sentence
            PstAvion.setString(2, Vuelo.getOrigen());
            PstAvion.setString(3, Vuelo.getHoraSalida());
            PstAvion.setString(4, Vuelo.getDestino());
            PstAvion.setString(5, Vuelo.getHoraLlegada());
            PstAvion.setString(6, Vuelo.getTipo());
            PstAvion.setInt(7, 1);//Aerolinea            
            int FilaInsertada = PstAvion.executeUpdate();
            if (FilaInsertada == 1) return true;      
        } catch (SQLException e) {
            System.out.println("Error tipo"+e.getMessage());
            return false;
        }
        return false;
    }
    public void MdlAlmacenarVuelo (List<Vuelo> Vuelos){
        try( FileWriter DocumentoVuelo = new FileWriter(Acceso, false) ){
            for (Vuelo Vuelo : Vuelos) {
                String TxtVuelo = Vuelo.getCodigo() + "," + Vuelo.getOrigen() + "," + Vuelo.getHoraSalida() + 
                "," + Vuelo.getDestino() + "," + Vuelo.getHoraLlegada() + "," + Vuelo.getTipo();
                DocumentoVuelo.write(TxtVuelo);
                DocumentoVuelo.write("\r\n"); 
            }    
        DocumentoVuelo.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public boolean MdlActualizarVuelo(Vuelo Vuelo) {
        try (Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());){
            String Declaracion = "UPDATE `tbl_vuelo` SET `origen`= ? ,`horasalida`= ? ,`destino`= ?,`horallegada`= ?,`tipo`= ? WHERE `codigo` = ?;";
            PreparedStatement PstAvion = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstAvion.setString(1, Vuelo.getOrigen());
            PstAvion.setString(2, Vuelo.getHoraSalida());
            PstAvion.setString(3, Vuelo.getDestino());
            PstAvion.setString(4, Vuelo.getHoraLlegada());            
            PstAvion.setString(5, Vuelo.getTipo());
            PstAvion.setString(6, Vuelo.getCodigo());// put in declaration sentence
            int FilaInsertada = PstAvion.executeUpdate();
            switch (FilaInsertada) {
                case 0: // if no posiible                    
                    return false;
                case 1: // if it cans
                    
                    return true;
                default: // ifcan't
                    throw new AssertionError();
            }
            
        } catch (SQLException e) {
            System.out.println("Error tipo"+e.getMessage());
            return false;
        }
    }
    
    
    public List<Vuelo> MdlLeerVuelos() {
        List<Vuelo> Vuelos = new ArrayList<>(); //List for all Vuelos
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            if (Connection != null) {
                System.out.println("Conexion exitosa leer agentes");
                String Consulta = "SELECT id_vuelo,codigo,origen,horasalida,destino,horallegada,tipo FROM `tbl_vuelo`;";
                PreparedStatement PstVuelo = Connection.prepareStatement(Consulta);
                ResultSet FilaVuelo = PstVuelo.executeQuery(); // this take row in table with specific id
                while (FilaVuelo.next()) {
                    int id_vuelo = FilaVuelo.getInt(1);
                    String Codigo = FilaVuelo.getString(2);
                    String Origen = FilaVuelo.getString(3);
                    String HoraSalida = FilaVuelo.getString(4);
                    String Destino = FilaVuelo.getString(5);
                    String HoraLlegada = FilaVuelo.getString(6);
                    String Tipo = FilaVuelo.getString(7);
                    Vuelo Vuelo = new Vuelo(Codigo, Origen, Destino, HoraSalida, HoraLlegada, Tipo);
                    Vuelo.setId_vuelo(id_vuelo);;
                    Vuelos.add(Vuelo);
                }
            } 
        } catch (SQLException e) {
            System.out.println("Error tipo" + e.getMessage());
        }
        return Vuelos;
    }
    public Vuelo MdlTraerVuelo(int IdCliente) {
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            if (Connection != null) {
                System.out.println("Conexion exitosa leer Vuelo de traer vuelo");
                String Consulta = "SELECT id_vuelo FROM `tbl_cliente_vuelo` c WHERE id_cliente = ? ;";
                PreparedStatement PstVueloId = Connection.prepareStatement(Consulta, Statement.RETURN_GENERATED_KEYS);
                PstVueloId.setInt(1, IdCliente);
                System.out.println(IdCliente);
                ResultSet FilaVueloId = PstVueloId.executeQuery(); // this take row in table with specific id
                System.out.println("Hasta aqui antes");
                if (FilaVueloId.next()) {
                    System.out.println("Despues");
                    int id_vuelo = FilaVueloId.getInt(1);
                    Consulta = "SELECT id_vuelo,codigo,origen,horasalida,destino,horallegada,tipo FROM `tbl_vuelo` where id_vuelo = ? ;";
                    PreparedStatement PstVuelo = Connection.prepareStatement(Consulta, Statement.RETURN_GENERATED_KEYS);
                    PstVuelo.setInt(1, id_vuelo);
                    ResultSet FilaVuelo = PstVuelo.executeQuery(); // this take row in table with specific id
                    if (FilaVuelo.next()) {
                        id_vuelo = FilaVuelo.getInt(1);
                        String Codigo = FilaVuelo.getString(2);
                        String Origen = FilaVuelo.getString(3);
                        String HoraSalida = FilaVuelo.getString(4);
                        String Destino = FilaVuelo.getString(5);
                        String HoraLlegada = FilaVuelo.getString(6);
                        String Tipo = FilaVuelo.getString(7);
                        Vuelo Vuelo = new Vuelo(Codigo, Origen, Destino, HoraSalida, HoraLlegada, Tipo);
                        Vuelo.setId_vuelo(id_vuelo);
                        return Vuelo;
                    }               
                }
            } 
        } catch (SQLException e) {
            System.out.println("Error tipo" + e.getMessage());
        }
        return null;
    }
    
    public boolean MdlEliminarVuelo(String Codigo) {
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "DELETE FROM `tbl_vuelo` WHERE codigo = ?;";
            PreparedStatement PstVuelo = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstVuelo.setString(1, Codigo);
            int VueloEliminado = PstVuelo.executeUpdate();
            if (VueloEliminado == 1) return true; 
        } catch (SQLException e) {
            System.out.println("Erro tipo"+  e.getMessage());
        }
        return false;
    }
    
    public int TraerIdVuelo(String Codigo) {
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            if (Connection != null) {
                System.out.println("Conexion exitosa leer Vuelo de traer vuelo");
                String Consulta = "SELECT `id_vuelo` FROM `tbl_vuelo` WHERE `codigo` = ?;";
                PreparedStatement PstVueloId = Connection.prepareStatement(Consulta, Statement.RETURN_GENERATED_KEYS);
                PstVueloId.setString(1, Codigo);
                ResultSet FilaVueloId = PstVueloId.executeQuery(); // this take row in table with specific id
                System.out.println("Hasta aqui antes");
                if (FilaVueloId.next()) {
                    System.out.println("Despues");
                    int IdVuelo = FilaVueloId.getInt(1);
                    return IdVuelo;
                }
            } 
        } catch (SQLException e) {
            System.out.println("Error tipo" + e.getMessage());
        }
        return 0;
        
    }
}
