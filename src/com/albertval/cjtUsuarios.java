import java.util.HashMap;
import java.util.Iterator;

public class cjtUsuarios{
        private static HashMap<String, Usuario> cjt = new HashMap<String, Usuario>();
        public static void afegir_usuari(String nom, Usuario user) {
            cjt.put(nom, user);
        }
        public static boolean borrar_usuari(String nom) {
            if(cjt.remove(nom) == null) return false;
            return true;
        }
        public static Usuario consultar_usuari(String nom) {
            return cjt.get(nom);
        }
        public static void imprimir_usuaris(){
            Iterator it = cjt.keySet().iterator();
            while(it.hasNext()){
                String key = (String)it.next();
                informacio_usuari((usuari_estandard)consultar_usuari(key));
            }
        }
        public static void informacio_usuari(usuari_estandard user){
            if(user == null)System.out.println("no hi ha cap usuari seleccionat");
            else System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getNombre()+" "+user.getSexo()+" "+
                    transformar_data.dateToString(user.getFechaN()));
        }
}
