import java.util.Date;

public class usuari_privilegiat extends usuari_estandard {
    public usuari_privilegiat(String user, String pass){super(user,pass);}
    public usuari_privilegiat(usuari_estandard standard_user){
        super(standard_user.getUsername(), standard_user.getPassword());
        setNombre(standard_user.getNombre());
        setSexo(standard_user.getSexo());
        setFechaN(standard_user.getFechaN());
    }
    public boolean modificar_usuari(String username, String oldPass, String pass, String nom, String sexe, Date naix){
        usuari_estandard user = (usuari_estandard)cjtUsuarios.cjtUsuaris.consultar_usuari(username);
        return modificar_usuari_aux(user, oldPass, pass, nom, sexe, naix);
    }
    public boolean borrar_usuari(String username){
        return cjtUsuarios.cjtUsuaris.borrar_usuari(username);
    }
    public void crear_usuari(String nom, String password){
        usuari_estandard user = new usuari_estandard(nom, password);
        cjtUsuarios.cjtUsuaris.afegir_usuari(nom, user);
    }
    public void donar_privilegis(String username){
        usuari_estandard user = (usuari_estandard) cjtUsuarios.cjtUsuaris.consultar_usuari(username);
        usuari_privilegiat up = new usuari_privilegiat(user);
        cjtUsuarios.cjtUsuaris.afegir_usuari(username, up);
    }
}
