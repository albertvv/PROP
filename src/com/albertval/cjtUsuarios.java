import java.util.HashMap;

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
}
