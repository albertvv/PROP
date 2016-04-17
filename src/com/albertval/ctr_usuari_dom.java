import java.util.*;

public class ctr_usuari_dom {
    private Scanner sc = new Scanner(System.in);
    private ConjuntoUsuarios<usuari_estandard> cjt = new ConjuntoUsuarios<usuari_estandard>();
    private usuari_estandard user = new usuari_privilegiat("admin", "admin");

    public boolean crear_usuari(String username, String pass, boolean privilegiat){
        usuari_estandard user;
        if(privilegiat) user = new usuari_privilegiat(username, pass);
        else user = new usuari_estandard(username, pass);
        return cjt.addUsuario(user);
    }
    public boolean afegir_relacio(String nom, String path, String descripcio){
        return user.definir_relacio(nom, path, descripcio);
    }
    public boolean esborrar_relacio(String nom){
        return user.deleteRelacion(nom);
    }
    private TipoRelacion consultar_relacio(String nom){
        return user.getRelacion(nom);
    }
    public ArrayList<ArrayList<String>> informacio_usuaris(){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Map<String, usuari_estandard> map = cjt.getConjunto();
        usuari_estandard auxiliar;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            ArrayList<String> result_aux = new ArrayList<String>(5);
            String key = (String)it.next();
            auxiliar = map.get(key);
            result_aux.add(auxiliar.getUsername());
            result_aux.add(auxiliar.getPassword());
            result_aux.add(auxiliar.getNombre());
            result_aux.add(auxiliar.getSexo());
            result_aux.add(transformar_data.dateToString(auxiliar.getFechaN()));
            result.add(result_aux);
        }
        return result;
    }
    public ArrayList<ArrayList<String>> informacio_relacions(){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Map<String, TipoRelacion> map = user.getRelacions();
        TipoRelacion auxiliar;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            ArrayList<String> result_aux = new ArrayList<String>(3);
            String key = (String)it.next();
            auxiliar = map.get(key);
            result_aux.add(auxiliar.getNombre());
            result_aux.add(auxiliar.getPath());
            result_aux.add(auxiliar.getDescripcion());
            result.add(result_aux);
        }
        return result;
    }
    public String loggin(String nom, String pass){
        usuari_estandard user = cjt.getUsuario(nom);
        if(user == null) return "0";
        if(user.checkPassword(pass)){
            this.user = user;
            return user.getClass().getSimpleName();
        }
        return "0";
    }
    public boolean modificar_usuari(String username, String oldPass, String pass, String nom, String sexe, String data, boolean esPrivilegiat) {
        Date dataT = transformar_data.stringToDate(data);
        if(esPrivilegiat) return aux_modificar_usuari((usuari_privilegiat)user, username, pass, nom, sexe, dataT);
        else return user.modificar_usuari(oldPass, pass, nom, sexe, dataT);
    }
    public boolean aux_modificar_usuari(usuari_privilegiat up, String username, String pass, String nom, String sexe, Date data){
        return up.modificar_usuari(username, pass, nom, sexe, data, cjt);
    }
    public boolean borrar_usuari(String user_borrar){
        return aux_borrar_usuari((usuari_privilegiat)user, user_borrar);
    }
    public boolean aux_borrar_usuari(usuari_privilegiat up, String user_borrar){
        return up.borrar_usuari(user_borrar, cjt);
    }
    public boolean donar_privilegis(String username){
        return aux_donar_privilegis((usuari_privilegiat)user, username);
    }
    public boolean aux_donar_privilegis(usuari_privilegiat user, String username){
        return user.donar_privilegis(username, cjt);
    }
}
