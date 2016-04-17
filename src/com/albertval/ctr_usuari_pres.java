import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class ctr_usuari_pres {
    private String usuari = "admin";
    private boolean privilegiat = true;
    private Scanner sc = new Scanner(System.in);
    private ctr_usuari_dom ctrdom = new ctr_usuari_dom();
    private final int nusuari = 5;
    private final int nrel = 3;
    public void gestio_usuari(){
        int opcio = 0;
        while(opcio != 8) {
            System.out.println("0 usuari actual 1 afegir relacio 2 esborrar relacio 3 modificar usuari 4 informacio usuaris ");
            System.out.println("5 informacio relacions de l'usuari 6 loggin 7 gestio usuari privilegiat 8 sortir");
            try{opcio = sc.nextInt();}
            catch (InputMismatchException e){
                sc.next();
            }
            switch(opcio){
                case 0: usuari_actual();
                    break;
                case 1: afegir_relacio();
                    break;
                case 2: esborrar_relacio();
                    break;
                case 3: modificar_usuari(false);
                    break;
                case 4: informacio_usuaris();
                    break;
                case 5: informacio_relacions();
                    break;
                case 6: loggin();
                    break;
                case 7: if(!privilegiat) System.out.println("L'usuari actual no es privilegiat");
                    else gestio_privilegiat();
                    break;
                default: if(opcio != 8)System.out.println("Numero invalid");
                    break;
            }
        }
    }
    private void usuari_actual(){
        String tipus = "estandard";
        if (privilegiat)  tipus = "privilegiat";
        System.out.println(usuari+" "+tipus);
    }
    private void gestio_privilegiat(){
        int opcio = 1;
        while(opcio != 4){
            System.out.println("0 crear_usuari 1 modificar un usuari 2 esborrar un usuari 3 donar privilegis a un usuari 4 sortir");
            opcio = sc.nextInt();
            switch (opcio){
                case 0: crear_usuari();
                    break;
                case 1: modificar_usuari(true);
                    break;
                case 2: borrar_usuari();
                    break;
                case 3: donar_privilegis();
            }
        }
    }
    private void crear_usuari(){
        System.out.println("privilegiat per crear un usuari privilegiat o qualsevol altre cosa per crear-ne un estandard");
        String tipus = sc.next();
        boolean esPrivilegiat = false;
        if(tipus.equals("privilegiat")) esPrivilegiat = true;
        System.out.print("Escriu el nom d'usuari: ");
        String username = sc.next();
        System.out.print("Escriu la contrasenya: ");
        String pass = sc.next();
        if(ctrdom.crear_usuari(username, pass, esPrivilegiat)) System.out.println("Usuari creat");
        else System.out.println("L'usuari ja existia");
    }
    private void afegir_relacio(){
        System.out.print("Escriu el nom de la relacio: ");
        String nomR = sc.next();
        System.out.print("Escriu el path: ");
        String path = sc.next();
        System.out.print("Escriu la descripcio sense espais: ");
        String descripcio = sc.next();
        if(ctrdom.afegir_relacio(nomR, path, descripcio)) System.out.println("Relacio afegida");
    }
    private void esborrar_relacio(){
        System.out.print("Escriu la relacio a esborrar: ");
        String nomRelacioE = sc.next();
        if(ctrdom.esborrar_relacio(nomRelacioE)) System.out.println("Relacio esborrada");
        else System.out.println("La relacio no existia");
    }
    private void modificar_usuari(boolean esPrivilegiat){
        String username = null;
        if(esPrivilegiat){
            System.out.print("Escriu l'usuari a modificar");
            username = sc.next();
        }
        System.out.print("Escriu la contrasenya nova o null");
        String pass = sc.next();
        if(pass.equals("null")) pass = null;
        String oldPass = null;
        if(!esPrivilegiat) {
            System.out.print("Escriu la contrasenya antiga");
            oldPass = sc.next();
        }
        System.out.print("Escriu el nom o null:");
        String nom = sc.next();
        if(nom.equals("null")) nom = null;
        System.out.print("Escriu el sexe o null:");
        String sexe = sc.next();
        if(sexe.equals("null")) sexe = null;
        System.out.print("Escriu la data en format dd/mm/aaaa o null:");
        String data = sc.next();
        if(ctrdom.modificar_usuari(username, oldPass, pass, nom, sexe, data, esPrivilegiat)) System.out.println("Usuari modificat");
        else{
            if(esPrivilegiat) System.out.println("L'usuari no existeix");
            else System.out.println("Contrasenya incorrecte");
        }
    }
    private void informacio_usuaris(){
        ArrayList<ArrayList<String>> usuaris = ctrdom.informacio_usuaris();
        ArrayList<String> aux;
        for(int i = 0;i < usuaris.size(); ++i){
            aux = usuaris.get(i);
            for(int j = 0;j < nusuari; ++j){
                System.out.print(aux.get(j)+" ");
            }
            System.out.println();
        }
    }
    private void informacio_relacions(){
        ArrayList<ArrayList<String>> relacions = ctrdom.informacio_relacions();
        ArrayList<String> aux;
        for(int i = 0;i < relacions.size(); ++i){
            aux = relacions.get(i);
            for(int j = 0;j < nrel; ++j){
                System.out.print(aux.get(j)+" ");
            }
            System.out.println();
        }
    }
    private void loggin(){
        System.out.print("Introdueix el nom de l'usuari: ");
        String nom = sc.next();
        System.out.print("Introdueix el nom la contrasenya: ");
        String pass = sc.next();
        String res = ctrdom.loggin(nom, pass);
        if(res == "0") System.out.println("Usuari o contrasenya incorrectes");
        else{
            usuari = nom;
            if(res.equals("usuari_privilegiat"))privilegiat = true;
            else privilegiat = false;
            System.out.println("Loggin com "+nom+" "+res);
        }
    }
    private void borrar_usuari(){
        System.out.print("Escriu l'usuari a esborrar: ");
        String usuari_a_borrar = sc.next();
        if(ctrdom.borrar_usuari(usuari_a_borrar)) System.out.println("Usuari borrat");
        else System.out.println("L'usuari no existia");
    }
    private void donar_privilegis(){
        System.out.print("Escriu l'usuari: ");
        String username = sc.next();
        if(ctrdom.donar_privilegis(username)) System.out.println("L'usuari "+username+" ara es privilegiat");
        else System.out.println("L'usuari no existia");
    }
}
