package Clases;

/* @author Daniel Sarmiento */

public class Aerolinea {
    private String Nombre;
    private String Ciudad;
    private int Nit;
    private Vuelo Vuelo;    

    public Aerolinea(String Nombre, String Ciudad, int Nit) {
        this.Nombre = Nombre;
        this.Ciudad = Ciudad;
        this.Nit = Nit;
    }
    
    
    public Aerolinea(String Nombre, String Ciudad, int Nit, Vuelo Vuelo) {
        this.Nombre = Nombre;
        this.Ciudad = Ciudad;
        this.Nit = Nit;
        this.Vuelo = Vuelo;
    }
    
    public String getTxtInfo() {
        String TxtInfo = Nombre + "{{" + Ciudad + "{{" + Nit ;//+ "{{" + Vuelo;
        return TxtInfo;
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
     * @return the Ciudad
     */
    public String getCiudad() {
        return Ciudad;
    }

    /**
     * @param Ciudad the Ciudad to set
     */
    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    /**
     * @return the Nit
     */
    public int getNit() {
        return Nit;
    }

    /**
     * @param Nit the Nit to set
     */
    public void setNit(int Nit) {
        this.Nit = Nit;
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
    
    
}
