package Clases;


import java.util.ArrayList;
import java.util.List;
/*@author Daniel Sarmient */

public class Cliente extends Persona{
    private String Dreccion;
    private String Email;
    private String Pasaporte;
    private String Tipo;
    private Vuelo Vuelo; 
    private Aerolinea Aerolinea;
    private int id_vuelo;

    public Cliente(String Dreccion, String Email, String Pasaporte, String Tipo, Vuelo Vuelo, Aerolinea Aerolinea, int Cedula, String Nombre, long NumCelular) {
        super(Cedula, Nombre, NumCelular);
        this.Dreccion = Dreccion;
        this.Email = Email;
        this.Pasaporte = Pasaporte;
        this.Tipo = Tipo;
        this.Vuelo = Vuelo;
        this.Aerolinea = Aerolinea;
    }
    
    public String getTextInfo(){
        String TxtInfo = Dreccion + "//" + Email + "//" + Pasaporte + "//" + Tipo
                + "//" + Vuelo.getTextInfo() + "//" + Aerolinea.getTxtInfo() + "//" + super.getCedula() + "//" + super.getNombre()
                +"//"+super.getNumCelular();// + "pp" + Aerolinea.getTxtInfo();
        return TxtInfo;
    }
    /**
     * @return the Dreccion
     */
    public String getDreccion() {
        return Dreccion;
    }

    /**
     * @param Dreccion the Dreccion to set
     */
    public void setDreccion(String Dreccion) {
        this.Dreccion = Dreccion;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Vuelo
     */
    public Vuelo getVuelo() {
        return Vuelo;
    }

    /**
     * @param Vuelo the Vuelo to set
     */
    public void setVuelo(Vuelo Vuelo) {
        this.Vuelo = Vuelo;
    }

    /**
     * @return the Pasaporte
     */
    public String getPasaporte() {
        return Pasaporte;
    }

    /**
     * @param Pasaporte the Pasaporte to set
     */
    public void setPasaporte(String Pasaporte) {
        this.Pasaporte = Pasaporte;
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
     * @return the Aerolinea
     */
    public Aerolinea getAerolinea() {
        return Aerolinea;
    }

    /**
     * @param Aerolinea the Aerolinea to set
     */
    public void setAerolinea(Aerolinea Aerolinea) {
        this.Aerolinea = Aerolinea;
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
