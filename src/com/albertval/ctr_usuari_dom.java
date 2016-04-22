import java.util.*;

public class ctr_usuari_dom {
    private Scanner sc = new Scanner(System.in);
    private ConjuntoUsuarios cjt;
    private usuari_estandard user;
    private Grafo graf;
    private final int nusuaris = 6;
    private final int nrel = 3;
    private final int nreldir = 3;
    public void carregar_cjt_usuaris(ConjuntoUsuarios cjt){
        this.cjt = cjt;
    }
    public void carregar_graf(Grafo graf){
        this.graf = graf;
    }
    public boolean crear_usuari(String username, String pass, boolean privilegiat){
        usuari_estandard user;
        if(privilegiat) user = new usuari_privilegiat(username, pass);
        else user = new usuari_estandard(username, pass);
        return cjt.addUsuario(user);
    }
    public void afegir_relacio(String nom, String path, String descripcio){
        if(descripcio.equals("null")) user.addRelacion(nom, path);
        else user.addRelacion(nom, path, descripcio);
    }
    public boolean modificar_relacio(String nom, String nomNou, String path, String descripcio){
        return user.modificar_relacio(nom, nomNou, path, descripcio);
    }
    public boolean esborrar_relacio(String nom){
        return user.deleteRelacion(nom);
    }
    public TipoRelacion consultar_relacio(String nom){
        return user.getRelacion(nom);
    }
    public ArrayList<ArrayList<String>> informacio_usuaris(){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Map<String, usuari_estandard> map = cjt.getConjunto();
        usuari_estandard auxiliar;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            ArrayList<String> result_aux = new ArrayList<String>(nusuaris);
            String key = (String)it.next();
            auxiliar = map.get(key);
            result_aux.add(auxiliar.getUsername());
            result_aux.add(auxiliar.getPassword());
            result_aux.add(auxiliar.getNombre());
            result_aux.add(auxiliar.getSexo());
            result_aux.add(transformar_data.dateToString(auxiliar.getFechaN()));
            result_aux.add(auxiliar.getClass().getSimpleName());
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
            ArrayList<String> result_aux = new ArrayList<String>(nrel);
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
    public void borrar_usuari_estandard(){
        cjt.deleteUsuario(user.getUsername());
    }
    private usuari_privilegiat aux(usuari_privilegiat up){return up;}
    public boolean modificar_usuari(String username, String oldPass, String pass, String nom, String sexe, Date data, boolean esPrivilegiat) {
        if(esPrivilegiat) return (aux((usuari_privilegiat)user)).modificar_usuari(username, pass, nom, sexe, data, cjt);
        else return user.modificar_usuari(oldPass, pass, nom, sexe, data);
    }
    public boolean borrar_usuari(String user_borrar) {
        return (aux((usuari_privilegiat) user)).borrar_usuari(user_borrar, cjt);
    }
    public boolean donar_privilegis(String username){
        usuari_estandard usuari = cjt.getUsuario(username);
        if((usuari == null) || (usuari instanceof usuari_privilegiat)) return false;
        (aux((usuari_privilegiat) user)).donar_privilegis(usuari, cjt);
        return true;
    }
    public void afegir_element(String nom, String tipus, String etiq){
        (aux((usuari_privilegiat) user)).afegir_element(nom, tipus, etiq, graf);
    }
    public void afegir_relacio_graf(String primer, String segon, String tipus){
        (aux((usuari_privilegiat) user)).afegir_relacio_graf(primer, segon, tipus, graf);
    }
    public void esborrar_element(String nom, String tipus){
        (aux((usuari_privilegiat) user)).esborrar_element(nom, tipus, graf);
    }
    public void esborrar_relacio_graf(String primer, String segon, String tipus){
        (aux((usuari_privilegiat) user)).esborrar_relacio_graf(primer, segon, tipus, graf);
    }
    public ArrayList<ArrayList<String>> relacions_directes(String nom, String tipus){
        Vector<Entidad> vector = graf.getRelacion(nom, tipus);
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Iterator it = vector.iterator();
        Entidad eaux;
        while(it.hasNext()){
            eaux = (Entidad)it.next();
            ArrayList<String> aaux = new ArrayList<String>(nreldir);
            aaux.add(Integer.toString(eaux.getId()));
            aaux.add(eaux.getNombre());
            aaux.add(eaux.getEtiq());
            result.add(aaux);
        }
        return result;
    }
}
