package Modelo;

/* @author Daniel Sarmiento */

public class DataBd {

    /**
     * @return the Drive
     */
    public String getDrive() {
        return Drive;
    }

    /**
     * @return the User
     */
    public String getUser() {
        return User;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @return the Url
     */
    public String getUrl() {
        return Url;
    }
    private final String Drive = "com.mysql.jdbc.Driver";
    private final String User = "root";
    private final String Password = "";
    private final String Url = "jdbc:mysql://localhost:3306/reto_semana5";
    public DataBd() {
    }
    
}
