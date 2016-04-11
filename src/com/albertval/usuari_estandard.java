import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class usuari_estandard extends Usuario {
    private HashMap<String, TipoRelacion> Relacions = new HashMap<String, TipoRelacion>();

    public usuari_estandard() {}

    public usuari_estandard(String user, String pass) {super(user, pass);}

    public boolean esborrar_relacio(String nom) {
        if (Relacions.containsKey(nom)) {
            Relacions.remove(nom);
            return true;
        }
        return false;
    }

    public boolean definir_relacio(String nom, String path, String descripcio) {//descripcio pot ser NULL
        if (Relacions.containsKey(nom)) return false;
        TipoRelacion relacio = new TipoRelacion(nom, path);
        if (descripcio != null) relacio.setDescripcion(descripcio);
        Relacions.put(nom, relacio);
        return true;
    }

    public TipoRelacion consultar_relacio(String nom) {
        return Relacions.get(nom);
    }

    public boolean modificar_relacio(String antic, String nom, String path, String descripcio) {
        TipoRelacion relacio = Relacions.remove(antic);
        if (relacio == null) {
            return false;
        }
        if (nom != null) relacio.setNombre(nom);
        if (path != null) relacio.setPath(path);
        if (descripcio != null) relacio.setDescripcion(descripcio);
        Relacions.put(nom, relacio);
        return true;
    }

//------------------------------------GESTIO DE L'USUARI-------------------------------------------------------------
    protected void guardar_usuari(){
        cjtUsuarios.cjtUsuaris.afegir_usuari(getUsername(), this);
    }
    protected boolean modificar_usuari_aux(usuari_estandard user, String oldPass, String pass, String nom, String sexe, Date naix){
        boolean canvi = false;
        if(nom != null){
            user.setNombre(nom);
            canvi = true;
        }
        if(sexe != null) if(user.setSexo(sexe)) canvi = true;
        if(naix != null) if(user.setFechaN(naix)) canvi = true;
        if(pass != null) if(user.setPassword(oldPass, pass)) canvi = true;
        if(canvi) {
            cjtUsuarios.cjtUsuaris.afegir_usuari(user.getUsername(), user);
            return true;
        }
        return false;
    }
    public boolean modificar_usuari(String oldPass, String pass, String nom, String sexe, Date naix){
        return modificar_usuari_aux(this, oldPass, pass, nom, sexe, naix);
    }
    public boolean borrar_usuari(){return cjtUsuarios.cjtUsuaris.borrar_usuari(getUsername());}

}
