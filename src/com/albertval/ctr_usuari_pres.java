import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Date;

public class ctr_usuari_pres {
    private String usuari;
    private boolean privilegiat;
    private Scanner sc = new Scanner(System.in);
    private ctr_usuari_dom ctrdom = new ctr_usuari_dom();
    private final int nusuari = 6;
    private final int nrel = 3;
    public void inici(){
        int opcio = 4;
        while (opcio != 3){
            System.out.println("1 crear un usuari 2 loggin 3 sortir");
            try{opcio = sc.nextInt();}
            catch (InputMismatchException e){
                sc.next();
                opcio = 4;
            }
            switch (opcio){
                case 1: crear_usuari();
                    break;
                case 2: if(loggin()) gestio_usuari();
                    break;
                default: if(opcio != 3)System.out.println("Numero invalid");
                    break;
            }
        }
    }
    private void gestio_usuari(){
        int opcio = 11;
        boolean sortir = false;
        while((opcio != 10) && (!sortir)) {
            System.out.println("0 usuari actual 1 afegir relacio 2 esborrar relacio 3 modificar relacio 4 modificar usuari ");
            System.out.println("6 informacio relacions de l'usuari 7 loggin 8 borrar l'usuari 9 gestio usuari privilegiat 10 sortir");
            try{opcio = sc.nextInt();}
            catch (InputMismatchException e){
                sc.next();
                opcio = 11;
            }
            switch(opcio){
                case 0: usuari_actual();
                    break;
                case 1: afegir_relacio();
                    break;
                case 2: esborrar_relacio();
                    break;
                case 3: modificar_relacio();
                    break;
                case 4: modificar_usuari(false);
                    break;
                case 6: informacio_relacions();
                    break;
                case 7: loggin();
                    break;
                case 8: {
                    sortir = true;
                    borrar_usuari_estandard();
                }
                    break;
                case 9: if(!privilegiat) System.out.println("L'usuari actual no es privilegiat");
                    else sortir = gestio_privilegiat();
                    break;
                default: if(opcio != 10)System.out.println("Numero invalid");
                    break;
            }
        }
    }
    private void usuari_actual(){
        String tipus = "estandard";
        if (privilegiat)  tipus = "privilegiat";
        System.out.println(usuari+" "+tipus);
    }
    private boolean gestio_privilegiat(){
        int opcio = 6;
        boolean sortir = false;
        while((opcio != 5) && !sortir){
            System.out.println("0 crear_usuari 1 modificar un usuari 2 esborrar un usuari 3 donar privilegis a un usuari 4 informacio de tots el usuaris 5 sortir");
            try{opcio = sc.nextInt();}
            catch (InputMismatchException e){
                sc.next();
                opcio = 6;
            }
            switch (opcio){
                case 0: crear_usuari();
                    break;
                case 1: modificar_usuari(true);
                    break;
                case 2: sortir = borrar_usuari();
                    break;
                case 3: donar_privilegis();
                    break;
                case 4: informacio_usuaris();
                    break;
                default: if(opcio != 5) System.out.println("Numero invalid");
            }
        }
        return sortir;
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
        sc.nextLine();
        System.out.print("Escriu la descripcio o null: ");
        String descripcio = sc.nextLine();
        if(descripcio.equals("null")) descripcio = null;
        if(ctrdom.afegir_relacio(nomR, path, descripcio)) System.out.println("Relacio afegida");
    }
    private void modificar_relacio(){
        System.out.print("Escriu el nom antic: ");
        String nomAntic = sc.next();
        System.out.print("Escriu el nom nou o null: ");
        String nomNou = sc.next();
        if(nomNou.equals("null")) nomNou = null;
        System.out.print("Escriu la descripcio o null: ");
        String descripcio = sc.next();
        if(descripcio.equals("null")) descripcio = null;
        if((nomNou == null) && (descripcio == null)) System.out.println("Res a modificar");
        else {
            if (ctrdom.modificar_relacio(nomAntic, nomNou, descripcio)) System.out.println("Relacio modificada");
            else System.out.println("La relacio no existia");
        }
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
        Date date = transformar_data.stringToDate(data);
        if((pass!= null) ||(nom!=null) || (sexe!=null) || (date != null)) {
            if (ctrdom.modificar_usuari(username, oldPass, pass, nom, sexe, date, esPrivilegiat))
                System.out.println("Usuari modificat");
            else {
                if (esPrivilegiat) System.out.println("L'usuari no existeix");
                else System.out.println("Contrasenya incorrecte");
            }
        }
        else System.out.println("Res a modificar");
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
    private boolean loggin(){
        System.out.print("Introdueix el nom de l'usuari: ");
        String nom = sc.next();
        System.out.print("Introdueix el nom la contrasenya: ");
        String pass = sc.next();
        String res = ctrdom.loggin(nom, pass);
        if(res == "0") {
            System.out.println("Usuari o contrasenya incorrectes");
            return false;
        }
        usuari = nom;
        if(res.equals("usuari_privilegiat"))privilegiat = true;
        else privilegiat = false;
        System.out.println("Loggin com "+nom+" "+res);
        return true;
    }
    private void borrar_usuari_estandard(){
        ctrdom.borrar_usuari_estandard();
    }
    private boolean borrar_usuari(){
        System.out.print("Escriu l'usuari a esborrar: ");
        String usuari_a_borrar = sc.next();
        if(ctrdom.borrar_usuari(usuari_a_borrar)) System.out.println("Usuari borrat");
        else System.out.println("L'usuari no existia");
        if(usuari.equals(usuari_a_borrar)) return true;
        return false;
    }
    private void donar_privilegis(){
        System.out.print("Escriu l'usuari: ");
        String username = sc.next();
        if(ctrdom.donar_privilegis(username)) System.out.println("L'usuari "+username+" ara es privilegiat");
        else System.out.println("L'usuari no existia o ja era privilegiat");
    }
}
