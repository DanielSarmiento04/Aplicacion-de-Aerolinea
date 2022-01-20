package Modelo;

import Clases.Agente;
import java.io.IOException;// For ger access in System
import java.io.FileWriter; // For writer
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/* @author Daniel Sarmiento*/

public class ModeloAgente {
    DataBd Data;
    private final String Acceso = "C:/Users/Yolanda/Documents/Java_archivos/RetoSemana3/BaseDeDatosAgente.txt";
    Connection Coneccion;

    public ModeloAgente() {
        Data = new DataBd();
    }
    
    public boolean MdlAgregarAgente(Agente Agente){
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "INSERT INTO `tbl_persona`( `nombre`, `numero_cel`, `cedula`) VALUES (?,?,?);";
            PreparedStatement PstPersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstPersona.setString(1,Agente.getNombre());
            PstPersona.setLong(2,Agente.getNumCelular());
            PstPersona.setInt(3,Agente.getCedula()); //put all atributes person
            int EntradaPersona = PstPersona.executeUpdate();
            if (EntradaPersona == 1) {
                System.out.println("Persona Creada correctamente");
                ResultSet SeguimientoTabla = PstPersona.getGeneratedKeys();
                if (SeguimientoTabla.next()) {
                    int IdPersona = SeguimientoTabla.getInt(1);//take number from table
                    Agente.setIdPersona(IdPersona);
                    Declaracion = "INSERT INTO `tbl_agentes`( `codigo`, `cargo`, `id_persona`) VALUES (?,?,?);";
                    PreparedStatement PstAgente = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                    PstAgente.setInt(1, Agente.getCodigo());
                    PstAgente.setString(2, Agente.getCargo() );
                    PstAgente.setInt(3,IdPersona);
                    int FilaAgente = PstAgente.executeUpdate();
                    if (FilaAgente == 1){
                        System.out.println("Agente creado Exitosamente");
                        SeguimientoTabla = PstAgente.getGeneratedKeys();
                        if(SeguimientoTabla.next()){
                            int Id_Agente = SeguimientoTabla.getInt(1);//take id_from table
                            Declaracion = "INSERT INTO `tbl_persona_aerolinea`(`id_aerolinea`, `id_agente`) VALUES (?,?);";
                            PreparedStatement PstAgenteAerolinea = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                            PstAgenteAerolinea.setInt(1, 1);
                            PstAgenteAerolinea.setInt(2, Id_Agente);
                            int FilaRegistro = PstAgenteAerolinea.executeUpdate();
                            if (FilaRegistro == 1) return true;
                        }
                    }
                }
            }     
        } catch (SQLException e) {
        
        }
        return false;
    }
    public boolean MdlActualizarAgente(Agente Agente){
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            System.out.println("Conexion exitosa");  // We'll go to query if in database there is Aeroline
            String Declaracion = "UPDATE `tbl_persona` SET `nombre`= ?,`numero_cel`= ? where cedula = ?;";
            PreparedStatement PstPersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
            PstPersona.setString(1,Agente.getNombre());
            PstPersona.setLong(2,Agente.getNumCelular());
            PstPersona.setInt(3,Agente.getCedula()); //put all atributes person
            int EntradaPersona = PstPersona.executeUpdate();
            System.out.println(EntradaPersona);
            if (EntradaPersona == 1) {
                System.out.println("Agente Actualizado Correctamente");
                
                //SELECT `id_persona` FROM `tbl_persona` WHERE `cedula` =456;
                Declaracion = "SELECT `id_persona` FROM `tbl_persona` WHERE `cedula` = ?;";
                PreparedStatement PstIdPersona = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                PstIdPersona.setInt(1, Agente.getCedula());
                ResultSet FilaIdPersona =  PstIdPersona.executeQuery();
                if (FilaIdPersona.next()) {
                    int IdPersona = FilaIdPersona.getInt(1);
                    Declaracion = "UPDATE `tbl_agentes` SET `codigo`= ?,`cargo` = ? where`id_persona`= ?  ";
                    PreparedStatement PstAgente = Connection.prepareStatement(Declaracion, Statement.RETURN_GENERATED_KEYS);
                    PstAgente.setInt(1, Agente.getCodigo());
                    PstAgente.setString(2, Agente.getCargo() );
                    PstAgente.setInt(3,IdPersona);
                    int FilaAgente = PstAgente.executeUpdate();
                    if (FilaAgente == 1){
                        System.out.println("Msion CUmplida");
                        return true;
                    }   
                }
            }     
        } catch (SQLException e) {
        
        }
        return false;
    }
    
    public void MdlAlmacenarAgentes(List<Agente> Agentes){
        try( FileWriter DocumentoAgente = new FileWriter(Acceso, false) ){
            for (Agente Agente : Agentes) {
                String TxtAgente = Agente.getNombre() + "," + Agente.getCedula() + "," + Agente.getNumCelular() + 
                "," + Agente.getCodigo() + "," + Agente.getCargo();
                DocumentoAgente.write(TxtAgente);
                DocumentoAgente.write("\r\n"); 
            }    
        DocumentoAgente.close();
        }catch(IOException e){
        }
    }
    
    public List<Agente> MdlLeerAgentes() {
        List<Agente> Agentes = new ArrayList<Agente>();
        try(Connection Connection = (Connection) DriverManager.getConnection(Data.getUrl(), Data.getUser(), Data.getPassword());) {
            if (Connection != null) {
                System.out.println("Conexion exitosa leer agentes");
                String Consulta = "SELECT p.nombre, p.numero_cel, p.cedula , a.codigo,a.cargo, a.id_persona\n" +
                        "FROM tbl_persona p, tbl_agentes a\n" +
                        "WHERE p.id_persona = a.id_persona;";
                PreparedStatement PstAgente = Connection.prepareStatement(Consulta);
                ResultSet FilaAgente = PstAgente.executeQuery(); // this take row in table with specific id
                while (FilaAgente.next()) {
                    String Nombre = FilaAgente.getString(1);
                    long NumeroCelular = FilaAgente.getLong(2);
                    int Cedula = FilaAgente.getInt(3);
                    int Codigo = FilaAgente.getInt(4);
                    String Cargo = FilaAgente.getString(5);
                    int id_persona = FilaAgente.getInt(6);
                    Agente Agente = new Agente(Codigo, Cargo, Cedula, Nombre,NumeroCelular);
                    Agente.setIdPersona(id_persona);
                    Agentes.add(Agente);
                }
            } 
        } catch (SQLException e) {
            System.out.println("Error tipo" + e.getMessage());
        }
        return Agentes;
    }
    public boolean MdlEliminarAgente(int Cedula) {
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
