package Clases;

/* @author Daniel Sarmiento  */
public class Reporte {
    private String tipo;
    private int Contador;

    public Reporte(String tipo, int Contador) {
        this.tipo = tipo;
        this.Contador = Contador;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the Contador
     */
    public int getContador() {
        return Contador;
    }

    /**
     * @param Contador the Contador to set
     */
    public void setContador(int Contador) {
        this.Contador = Contador;
    }
    
}
