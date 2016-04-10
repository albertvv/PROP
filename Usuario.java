import java.util.Date;

public class Usuario {
    private String user;
    private String pass;
    private String nom;
    private Date fecha;
    private String sexe;
    public Usuario(){}
    public Usuario(String user, String pass){
        this.user = user;
        this.pass= pass;
    }
    public String getUsername(){return user;}
    public String getPassword(){return pass;}
    public String getNombre(){return nom;}
    public String getSexo(){return sexe;}
    public Date getFechaN(){return fecha;}
    public void setNombre(String nombre){nom = nombre;}
    public boolean setFechaN (Date fecha){this.fecha = fecha;return true;}
    public boolean setSexo(String sexo){sexe = sexo; return true;}
    public boolean setPassword(String oldPass, String newPass){pass = newPass; return true;}
}
