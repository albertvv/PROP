import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import user_dominio.*;

public class main {
    public static void main(String[] args) {
        driver d = new driver();
        ctr_usuari_dom ctrdom = new ctr_usuari_dom();
        Grafo graf = new Grafo();
        ConjuntoUsuarios cjt = new ConjuntoUsuarios();
        ctrdom.carregar_cjt_usuaris(cjt);
        ctrdom.carregar_graf(graf);
        d.carregar_ctr_usuari_dom(ctrdom);
        d.inici();
    }
}