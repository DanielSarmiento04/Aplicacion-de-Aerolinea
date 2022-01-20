package Clases;
import java.util.ArrayList;
import java.util.List;

/* @author Daniel Sarmiento  */

public class Vuelo {
    private String Codigo;
    private String Origen;
    private String Destino;
    private String HoraSalida;
    private String HoraLlegada;
    private String Tipo;
    private int id_vuelo;
    
    public Vuelo(String Codigo, String Origen, String Destino, String HoraSalida, String HoraLlegada, String Tipo) {
        this.Codigo = Codigo;
        this.Origen = Origen;
        this.Destino = Destino;
        this.HoraSalida = HoraSalida;
        this.HoraLlegada = HoraLlegada;
        this.Tipo = Tipo;
    }
    
    public String getTextInfo() { // For reduce time for programing
        String TxtInfo = Codigo + "," + Origen + "," +  HoraSalida + ","
                + Destino + "," + HoraLlegada + "," + Tipo;
        return TxtInfo;
    }
    
    /**
     * @return the Codigo
     */
    public String getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    /**
     * @return the Origen
     */
    public String getOrigen() {
        return Origen;
    }

    /**
     * @param Origen the Origen to set
     */
    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    /**
     * @return the Destino
     */
    public String getDestino() {
        return Destino;
    }

    /**
     * @param Destino the Destino to set
     */
    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    /**
     * @return the HoraSalida
     */
    public String getHoraSalida() {
        return HoraSalida;
    }

    /**
     * @param HoraSalida the HoraSalida to set
     */
    public void setHoraSalida(String HoraSalida) {
        this.HoraSalida = HoraSalida;
    }

    /**
     * @return the HoraLlegada
     */
    public String getHoraLlegada() {
        return HoraLlegada;
    }

    /**
     * @param HoraLlegada the HoraLlegada to set
     */
    public void setHoraLlegada(String HoraLlegada) {
        this.HoraLlegada = HoraLlegada;
    }

    /**
     * @return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the id_vuelo
     */
    public int getId_vuelo() {
        return id_vuelo;
    }

    /**
     * @param id_vuelo the id_vuelo to set
     */
    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }
   
}
