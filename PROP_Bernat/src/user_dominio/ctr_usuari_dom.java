package user_dominio;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import user_dominio.*;
import user_persistencia.*;

public class ctr_usuari_dom {
    private Scanner sc = new Scanner(System.in);
    private ConjuntoUsuarios cjt = new ConjuntoUsuarios();
    private Grafo graf;
    private ctr_usuari_pers ctr_pers =new ctr_usuari_pers();
    private usuari_estandard user;
    private final int nusuaris = 6;
    private final int nrel = 3;
    private final int nreldir = 3;

    public void carregar_cjt_usuaris(ConjuntoUsuarios cjt) {
        this.cjt = cjt;
    }

    public void carregar_graf(Grafo graf) {
        this.graf = graf;
    }

    public String loggin(String nom, String pass) {
        usuari_estandard user = cjt.getUsuario(nom);
        if (user == null) return "0";
        if (user.checkPassword(pass)) {
            this.user = user;
            return user.getClass().getSimpleName();
        }
        return "0";
    }

    public void afegir_relacio(String nom, String path, String descripcio) {
        if (descripcio.equals("null")) user.addRelacion(nom, path);
        else user.addRelacion(nom, path, descripcio);
    }

    public boolean esborrar_relacio(String nom) {
        return user.deleteRelacion(nom);
    }

    public boolean modificar_relacio(String nom, String nomNou, String descripcio) {
        return user.modificar_relacio(nom, nomNou, descripcio);
    }

    public boolean modificar_usuari(String username, String oldPass, String pass, String nom, String sexe, String data, boolean esPrivilegiat) {
        Date date = transformar_data.stringToDate(data);
        if (esPrivilegiat) return (aux((usuari_privilegiat) user)).modificar_usuari(username, pass, nom, sexe, date, cjt);
        return user.modificar_usuari(oldPass, pass, nom, sexe, date);
    }

    public ArrayList<ArrayList<String>> relacions_directes(String nom, String tipus) {
        Vector<Entidad> vector = user.relacions_directes(nom, tipus, graf);
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Iterator it = vector.iterator();
        Entidad eaux;
        while (it.hasNext()) {
            eaux = (Entidad) it.next();
            ArrayList<String> aaux = new ArrayList<String>(nreldir);
            aaux.add(Integer.toString(eaux.getId()));
            aaux.add(eaux.getNombre());
            aaux.add(eaux.getEtiqueta());
            result.add(aaux);
        }
        return result;
    }

    public ArrayList<ArrayList<String>> informacio_relacions() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Map<String, TipoRelacion> map = user.getRelacions();
        TipoRelacion auxiliar;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            ArrayList<String> result_aux = new ArrayList<String>(nrel);
            String key = (String) it.next();
            auxiliar = map.get(key);
            result_aux.add(auxiliar.getNombre());
            result_aux.add(auxiliar.getPath());
            result_aux.add(auxiliar.getDescripcion());
            result.add(result_aux);
        }
        return result;
    }

    public void borrar_usuari_estandard() {
        user.borrar_usuari(cjt);
    }

    public boolean crear_usuari(String username, String pass, boolean privilegiat) {
        usuari_estandard user;
        if (privilegiat) user = new usuari_privilegiat(username, pass);
        else user = new usuari_estandard(username, pass);
        return cjt.addUsuario(user);
    }

    public boolean borrar_usuari(String user_borrar) {
        return (aux((usuari_privilegiat) user)).borrar_usuari(user_borrar, cjt);
    }

    private usuari_privilegiat aux(usuari_privilegiat up) {
        return up;
    }

    public boolean donar_privilegis(String username) {
        usuari_estandard usuari = cjt.getUsuario(username);
        if ((usuari == null) || (usuari instanceof usuari_privilegiat)) return false;
        (aux((usuari_privilegiat) user)).donar_privilegis(usuari, cjt);
        return true;
    }

    public ArrayList<ArrayList<String>> informacio_usuaris() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Map<String, usuari_estandard> map = (aux((usuari_privilegiat) user)).informacio_usuaris(cjt);
        usuari_estandard auxiliar;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            ArrayList<String> result_aux = new ArrayList<String>(nusuaris);
            String key = (String) it.next();
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

    public TipoRelacion consultar_relacio(String nom) {
        return user.getRelacion(nom);
    }

    public void afegir_element(String nom, String tipus, String etiq) {
        (aux((usuari_privilegiat) user)).afegir_element(nom, tipus, etiq, graf);
    }

    public void afegir_relacio_graf(String primer, String segon, String tipus) {
        (aux((usuari_privilegiat) user)).afegir_relacio_graf(primer, segon, tipus, graf);
    }

    public void esborrar_element(String nom, String tipus) {
        (aux((usuari_privilegiat) user)).esborrar_element(nom, tipus, graf);
    }

    public void esborrar_relacio_graf(String primer, String segon, String tipus) {
        (aux((usuari_privilegiat) user)).esborrar_relacio_graf(primer, segon, tipus, graf);
    }

    public void carregar_usuaris() {
        cjt.getConjunto().clear();
        ArrayList<ArrayList<String>> a =null;
        try {
            a = ctr_pers.carregar_usuaris();
        } catch (FileNotFoundException ex) {
            System.out.println("No existeix");
        }
        ArrayList<String> aux;
        for (int i = 0; i < a.size(); ++i) {
            aux = a.get(i);
            usuari_estandard user;
            if((aux.get(0)).equals("usuari_estandard")) user = new usuari_estandard(aux.get(1), aux.get(2));
            else user = new usuari_privilegiat(aux.get(1), aux.get(2));
            String nom = aux.get(3);
            if(nom != null) user.setNombre(nom);
            String sex = aux.get(4);
            if(sex != null) user.setSexo(sex);
            String data = aux.get(5);
            if(data != null) user.setFechaN(transformar_data.stringToDate(data));
            for(int j = 6; j < aux.size(); j+=3){
                String desc = aux.get(j + 2);
                if(desc == null) user.addRelacion(aux.get(j), aux.get(j + 1));
                else user.addRelacion(aux.get(j), aux.get(j + 1), desc);
            }
            cjt.addUsuario(user);
        }
    }
    public void guardar_usuaris(){
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        Map<String, usuari_estandard> users = cjt.getConjunto();
        Iterator it = users.keySet().iterator();
        usuari_estandard uaux;
        while(it.hasNext()){
            String key = (String) it.next();
            uaux = users.get(key);
            ArrayList<String> aaux = new ArrayList<String>();
            aaux.add(uaux.getClass().getSimpleName());
            aaux.add(uaux.getUsername());
            aaux.add(uaux.getPassword());
            aaux.add(uaux.getNombre());
            aaux.add(uaux.getSexo());
            aaux.add(transformar_data.dateToString(uaux.getFechaN()));
            Map<String, TipoRelacion> relacions = uaux.getRelacions();
            if(!relacions.isEmpty()){
                Iterator it2 = relacions.keySet().iterator();
                while (it2.hasNext()) {
                    String key2 = (String) it2.next();
                    TipoRelacion relacio = relacions.get(key2);
                    aaux.add(relacio.getNombre());
                    aaux.add(relacio.getPath());
                    aaux.add(relacio.getDescripcion());
                }
            }
            res.add(aaux);
        }
        ctr_pers.guardar_usuaris(res);
    }
}
