package Clases;

/* @author Daniel Sarmiento */
public class Persona {
    private int Cedula;
    private String Nombre;
    private long NumCelular;
    private int IdPersona;
    
    public Persona(int Cedula, String Nombre, long NumCelular) {
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.NumCelular = NumCelular;
    }

    /**
     * @return the Cedula
     */
    public int getCedula() {
        return Cedula;
    }

    /**
     * @param Cedula the Cedula to set
     */
    public void setCedula(int Cedula) {
        this.Cedula = Cedula;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the NumCelular
     */
    public long getNumCelular() {
        return NumCelular;
    }

    /**
     * @param NumCelular the NumCelular to set
     */
    public void setNumCelular(long NumCelular) {
        this.NumCelular = NumCelular;
    }

    /**
     * @return the IdPersona
     */
    public int getIdPersona() {
        return IdPersona;
    }

    /**
     * @param IdPersona the IdPersona to set
     */
    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }
    
}
