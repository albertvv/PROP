import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class usuari_estandard extends Usuario_generico {
    public usuari_estandard() {}

    public usuari_estandard(String user, String pass) {super(user, pass);}

    public boolean definir_relacio(String nom, String path, String descripcio) {
        if (conjTRel.containsKey(nom)) return false;
        TipoRelacion relacio = new TipoRelacion(nom, path);
        if (descripcio != null) relacio.setDescripcion(descripcio);
        conjTRel.put(nom, relacio);
        return true;
    }

    public boolean modificar_relacio(String antic, String nom, String descripcio) {
        TipoRelacion relacio = conjTRel.get(antic);
        if (relacio == null) {
            return false;
        }
        if (nom != null) relacio.setNombre(nom);
        if (descripcio != null) relacio.setDescripcion(descripcio);
        return true;
    }
    public Map<String, TipoRelacion> getRelacions(){return conjTRel;}

    protected boolean modificar_usuari_aux(usuari_estandard user, String oldPass, String pass, String nom, String sexe, Date naix, boolean EsPrivilegiat){
        if(((pass == null) && (checkPassword(oldPass))) || (user.setPassword(oldPass, pass))) {
            if (nom != null) user.setNombre(nom);
            if (sexe != null) user.setSexo(sexe);
            if (naix != null) user.setFechaN(naix);
            return true;
        }
        return false;
    }
    public boolean modificar_usuari(String oldPass, String pass, String nom, String sexe, Date naix){
        return modificar_usuari_aux(this, oldPass, pass, nom, sexe, naix, false);
    }
    public void borrar_usuari(ConjuntoUsuarios cjt){
        cjt.deleteUsuario(getUsername());
    }
}