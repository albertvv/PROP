import java.util.Date;

public class usuari_privilegiat extends usuari_estandard {
    public usuari_privilegiat(String user, String pass){super(user,pass);}
    public usuari_privilegiat(usuari_estandard standard_user){
        super(standard_user.getUsername(), standard_user.getPassword());
        setNombre(standard_user.getNombre());
        setSexo(standard_user.getSexo());
        setFechaN(standard_user.getFechaN());
        conjTRel = standard_user.getRelacions();
    }
    public boolean modificar_usuari(String username, String pass, String nom, String sexe, Date naix, ConjuntoUsuarios<usuari_estandard> cjt){
        usuari_estandard user = cjt.getUsuario(username);
        if(user == null) return false;
        return modificar_usuari_aux(user, user.getPassword(), pass, nom, sexe, naix, true);
    }
    public boolean borrar_usuari(String username, ConjuntoUsuarios cjt){
        return cjt.deleteUsuario(username);
    }
    public boolean crear_usuari(String nom, String password, ConjuntoUsuarios cjt){
        usuari_estandard user = new usuari_estandard(nom, password);
        return cjt.addUsuario(user);
    }
    public boolean donar_privilegis(String username, ConjuntoUsuarios cjt){
        usuari_estandard ue = (usuari_estandard)cjt.getUsuario(username);
        if(ue == null) return false;
        usuari_privilegiat up = new usuari_privilegiat(ue);
        cjt.deleteUsuario(username);
        cjt.addUsuario(up);
        return true;
    }
}

