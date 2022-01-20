package Clases;

import java.util.ArrayList;
import java.util.List;

/* @author Daniel Sarmiento */

public class Agente extends Persona{
    private int Codigo;//Cor worker
    private String cargo;
    private static List<Agente> Agentes = new ArrayList<Agente>();
    private int id_Agente;
    
    
    public Agente(int Codigo, String cargo, int Cedula, String Nombre, long NumCelular) {
        super(Cedula, Nombre, NumCelular);
        this.Codigo = Codigo;
        this.cargo = cargo;
    }
    
    public static void Create(Agente Agente){
        Agentes.add(Agente);
    }
    
    public static void Update(Agente Agente) {
        
    }
    
    public static void Delete(Agente AgenteD) {        
        for (Agente AgenteN : Agentes) {
            if (AgenteN.getCodigo() == AgenteD.getCodigo()) {
                Agentes.remove(AgenteD);
                break;
            }
        }        
    }    
    
    /**
     * @return the Codigo
     */
    public int getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the id_Agente
     */
    public int getId_Agente() {
        return id_Agente;
    }

    /**
     * @param id_Agente the id_Agente to set
     */
    public void setId_Agente(int id_Agente) {
        this.id_Agente = id_Agente;
    }
    
}
