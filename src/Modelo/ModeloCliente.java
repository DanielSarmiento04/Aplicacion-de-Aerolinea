package Modelo;

import Clases.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Modelo.ModeloVuelo;

/*@author Daniel Sarmiento */

public class ModeloCliente {
    DataBd Data;
    private final String Acceso = "C:/Users/Yolanda/Documents/Java_archivos/RetoSemana3/BaseDeDatosAgente.txt";
    ModeloVuelo ModeloVuelo;
    
    public ModeloCliente() {
        Data = new DataBd();
        ModeloVuelo = new ModeloVuelo();
    }
      
    public boolean MdlActualizarCliente(Cliente Cliente){
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "UPDATE `tbl_persona` SET `nombre`= ?,`numero_cel`= ? where cedula = ?;";
            PreparedStatement PstPersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstPersona.setString(1,Cliente.getNombre());
            PstPersona.setLong(2,Cliente.getNumCelular());
            PstPersona.setInt(3,Cliente.getCedula()); //put all atributes person
            int EntradaPersona = PstPersona.executeUpdate();
            if (EntradaPersona == 1) {// person already create
                System.out.println("Persona Actualizada correctamente");
                Declaracion = "SELECT `id_persona` FROM `tbl_persona` WHERE `cedula` = ?;";
                PreparedStatement PstIdPersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                PstIdPersona.setInt(1, Cliente.getCedula());
                ResultSet FilaIdPersona = PstPersona.getGeneratedKeys();                
                if (FilaIdPersona.next()) {
                    int IdPersona = FilaIdPersona.getInt(1); 
                    Declaracion = "UPDATE `tbl_clientes` SET `direccion`= ?,`email`= ? ,`pasaporte`= ? ,`tipo`= ? WHERE `id_persona`= ?;";
                    PreparedStatement PstCliente = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                    PstCliente.setString(1, Cliente.getDreccion());
                    PstCliente.setString(2, Cliente.getEmail());
                    PstCliente.setString(3, Cliente.getPasaporte());
                    PstCliente.setString(4, Cliente.getTipo());
                    PstCliente.setInt(4, IdPersona);
                    int FilaCliente = PstCliente.executeUpdate();
                    if (FilaCliente == 1) {
                        System.out.println("Cliente Actualizado.");
                        Vuelo Vuelo = Cliente.getVuelo();
                        int IdVuelo = Vuelo.getId_vuelo();
                        Declaracion = "UPDATE `tbl_cliente_vuelo` SET `id_vuelo`= ? WHERE `id_persona`= ?;";
                        PreparedStatement PstClienteVuelo = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                        PstClienteVuelo.setInt(1,IdVuelo );
                        PstClienteVuelo.setInt(2,IdPersona);
                        int FilaVuelo = PstClienteVuelo.executeUpdate();
                        if (FilaVuelo == 1)return true;
                    }
                }
            }     
        } catch (SQLException e) {
            System.out.println("Eroor" + e.getMessage());
        }
        return false;
    }
    public boolean MdlAgregarCliente(Cliente Cliente){
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "INSERT INTO `tbl_persona`( `nombre`, `numero_cel`, `cedula`) VALUES (?,?,?);";
            PreparedStatement PstPersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstPersona.setString(1,Cliente.getNombre());
            PstPersona.setLong(2,Cliente.getNumCelular());
            PstPersona.setInt(3,Cliente.getCedula()); //put all atributes person
            int EntradaPersona = PstPersona.executeUpdate();
            if (EntradaPersona == 1) {// person already create
                System.out.println("Persona Creada correctamente");
                ResultSet SeguimientoTabla = PstPersona.getGeneratedKeys();
                if (SeguimientoTabla.next()) {
                    int IdPersona = SeguimientoTabla.getInt(1);//take number from table
                    Cliente.setIdPersona(IdPersona);
                    Declaracion = "INSERT INTO `tbl_clientes`(`direccion`, `email`, `pasaporte`, `tipo`, `id_persona`) VALUES (?,?,?,?,?);";
                    PreparedStatement PstCliente = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                    PstCliente.setString(1,Cliente.getDreccion());
                    PstCliente.setString(2, Cliente.getEmail());
                    PstCliente.setString(3,Cliente.getPasaporte());
                    PstCliente.setString(4, Cliente.getTipo());
                    PstCliente.setInt(5,IdPersona);
                    int FilaAgente = PstCliente.executeUpdate();
                    if (FilaAgente == 1){
                        System.out.println("Cliente creado exitosamente.");
                        SeguimientoTabla = PstCliente.getGeneratedKeys();
                        if(SeguimientoTabla.next()){
                            int IdCliente = SeguimientoTabla.getInt(1);//take id_from table
                            Declaracion = "INSERT INTO `tbl_persona_aerolinea`(`id_aerolinea`, `id_cliente`,`id_persona`) VALUES (?,?,?);";
                            PreparedStatement PstClienteAerolinea = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                            PstClienteAerolinea.setInt(1, 1);
                            PstClienteAerolinea.setInt(2, IdCliente);
                            PstClienteAerolinea.setInt(3, IdPersona);
                            int FilaRegistro = PstClienteAerolinea.executeUpdate();
                            if (FilaRegistro == 1) {// for register Float
                                Vuelo Vuelo = Cliente.getVuelo();
                                int IdVuelo = Vuelo.getId_vuelo();
                                if (IdVuelo == 0) IdVuelo = Cliente.getId_vuelo();
                                System.out.println(IdVuelo);
                                System.out.println(IdVuelo + "Este es el Id");
                                Declaracion = "INSERT INTO `tbl_cliente_vuelo`(`id_vuelo` , `id_aerolinea`, `id_cliente`,`id_persona`) VALUES (?,?,?,?);";
                                PreparedStatement PstClienteVuelo = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);                                                          
                                PstClienteVuelo.setInt(1, IdVuelo);
                                PstClienteVuelo.setInt(2, 1);
                                System.out.println(IdCliente);
                                PstClienteVuelo.setInt(3, IdCliente);
                                PstClienteVuelo.setInt(4, IdPersona);
                                System.out.println(PstClienteVuelo.toString());
                                int FilaClienteVuelo = PstClienteVuelo.executeUpdate();
                                if (FilaClienteVuelo == 1) return true; 
                            }
                        }
                    }
                }
            }     
        } catch (SQLException e) {
            System.out.println("Eroor" + e.getMessage());
        }
        return false;
    }
    
    public void MdlAlmacenarCliente(List<Cliente> Clientes){
        try( FileWriter DocumentoCliente = new FileWriter(Acceso, false) ){
            for (Cliente Cliente : Clientes) {
                DocumentoCliente.write(Cliente.getTextInfo());
                DocumentoCliente.write("\r\n"); 
            }    
            DocumentoCliente.close();
        }catch(IOException e){
        }
    }
    
    
    public List<Cliente> MdlLeerClientes() {
        List<Cliente> Clientes = new ArrayList<>();
            /*      create bufferReader type from- Create reader type */
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            if (Connection != null) {
                System.out.println("Conexion exitosa leer CLientes");
                String Consulta = "SELECT p.nombre, p.numero_cel, p.cedula, c.direccion,c.email,c.pasaporte,c.tipo,c.id_cliente, p.id_persona\n" +
"                        FROM tbl_persona p, tbl_clientes c\n" +
"                        WHERE p.id_persona = c.id_persona;";
                PreparedStatement PstCliente = Connection.prepareStatement(Consulta, Statement.RETURN_GENERATED_KEYS);
                ResultSet FilaCliente = PstCliente.executeQuery(); // this take row in table with specific id
                while (FilaCliente.next()) {
                    String Nombre = FilaCliente.getString(1);
                    long NumeroCelular = FilaCliente.getLong(2);
                    int Cedula = FilaCliente.getInt(3);
                    String Direccion = FilaCliente.getString(4);
                    String Email = FilaCliente.getString(5);
                    String Pasaporte = FilaCliente.getString(6);
                    String Tipo = FilaCliente.getString(7);
                    int IdCliente = FilaCliente.getInt(8);
                    int IdPersona = FilaCliente.getInt(9);
                    Vuelo Vuelo = ModeloVuelo.MdlTraerVuelo(IdCliente);
                    System.out.println(Vuelo.getCodigo());
                    Cliente Cliente = new Cliente(Direccion, Email, Pasaporte, Tipo, Vuelo, null, Cedula, Nombre, NumeroCelular);
                    Cliente.setIdPersona(IdPersona);
                    Clientes.add(Cliente);
                }
            } 
        } catch (SQLException e) {
            System.out.println("Error tipo" + e.getMessage());
        }
        
        return Clientes;
    }
    public boolean MdlEliminarCliente(int Cedula) {
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "DELETE FROM `tbl_persona`  WHERE cedula = ?;";
            PreparedStatement PstPersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstPersona.setInt(1, Cedula);
            int PersonaEliminada = PstPersona.executeUpdate();
            if (PersonaEliminada ==1) return true; 
        } catch (SQLException e) {
            System.out.println("Erro tipo"+  e.getMessage());
        }
        return false;
    }
}
