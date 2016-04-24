package user_dominio;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class usuari_estandard extends Usuario_generico {

    private Date fechaNac;
    public usuari_estandard() {}

    public usuari_estandard(String user, String pass) {super(user, pass);}

    public void setFechaN(Date fecha) {
        fechaNac = fecha;
    }

    public Date getFechaN() {
        return fechaNac;
    }

    public boolean modificar_relacio(String nom, String nomNou, String descripcio) {
        TipoRelacion relacio = conjTRel.get(nom);
        if (relacio == null) {
            return false;
        }
        if (descripcio != null) relacio.setDescripcion(descripcio);
        if(nomNou != null){
            relacio.setNombre(nomNou);
            conjTRel.remove(nom);
            conjTRel.put(nomNou, relacio);
        }
        return true;
    }
    public Map<String, TipoRelacion> getRelacions(){return conjTRel;}

    protected boolean modificar_usuari_aux(usuari_estandard user, String oldPass, String pass, String nom, String sexe, Date naix){
        if(((pass == null) && (checkPassword(oldPass))) || (user.setPassword(oldPass, pass))) {
            if (nom != null) user.setNombre(nom);
            if (sexe != null) user.setSexo(sexe);
            if (naix != null) user.setFechaN(naix);
            return true;
        }
        return false;
    }
    public boolean modificar_usuari(String oldPass, String pass, String nom, String sexe, Date naix){
        return modificar_usuari_aux(this, oldPass, pass, nom, sexe, naix);
    }
    public void borrar_usuari(ConjuntoUsuarios cjt){
        cjt.deleteUsuario(getUsername());
    }
    public Vector<Entidad> relacions_directes(String nom, String tipus, Grafo graf){return graf.getRelacion(nom, tipus);}
}