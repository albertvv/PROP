import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String [ ] args){
        usuari_estandard user = crear_usuari(true);
        TipoRelacion relacio = new TipoRelacion("coautoria", "APA");
        while(true) {
            System.out.println("0 crear usuari 1 afegir relacio 2 esborrar relacio 3 consultar relacio 4 modificar usuari " +
                    "5 crear usuari privilegiat 6 guardar usuari 7 esborrar usuari");
            System.out.println("8 gestio de l'usuari privilegiat 9 informacio usuari 10 informacio relacio 11 get usuari");
            int opcio = sc.nextInt();
            switch(opcio){
                case 0: crear_usuari(false);
                    break;
                case 1: afegir_relacio(user);
                    break;
                case 2: esborrar_relacio(user);
                    break;
                case 3: relacio = consultar_relacio(user);
                    break;
                case 4: modificar_usuari(user);
                    break;
                case 5: crear_usuari(true);
                    break;
                case 6: user.guardar_usuari();
                    break;
                case 7: {
                    user.borrar_usuari();
                    user = null;
                }
                    break;
                case 8: if(!user.getClass().getSimpleName().equals("usuari_privilegiat")) System.out.println("L'usuari actual no es privilegiat");
                    else gestio_privilegiat((usuari_privilegiat)user);
                    break;
                case 9 : informacio_usuari(user);
                    break;
                case 10: informacio_relacio(relacio);
                    break;
                case 11: user = get_user();
                    break;
            }
        }
    }
    private static void informacio_usuari(usuari_estandard user){
        if(user == null)System.out.println("no hi ha cap usuari seleccionat");
        else System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getNombre()+" "+user.getSexo()+" "+
        transformar_data.dateToString(user.getFechaN()));
    }
    private static void informacio_relacio(TipoRelacion relacio){
        if(relacio == null)System.out.println("no hi ha cap relacio seleccionada");
        else System.out.println(relacio.getNombre()+" "+relacio.getPath()+" "+relacio.getDescripcio());
    }
    private static usuari_estandard get_user(){
        System.out.print("Introdueix el nom de l'usuari: ");
        String nom = sc.next();
        usuari_estandard user =  (usuari_estandard)cjtUsuarios.consultar_usuari(nom);
        if(user == null) System.out.println("L'usuari no existeix");
        return user;
    }
    private static void gestio_privilegiat(usuari_privilegiat user){
        int opcio = 1;
        while((opcio != 0) && (user!= null)){
            System.out.println("0 sortir 1 modificar un usuari 2 esborrar un usuari 3 donar privilegis a un usuari");
            opcio = sc.nextInt();
            switch (opcio){
                case 1: p_modificar_usuari(user);
                    break;
                case 2: p_borrar_usuari(user);
                    break;
                case 3: p_donar_privilegis(user);
            }
            }
    }
    private static void p_donar_privilegis(usuari_privilegiat user){
        System.out.print("Escriu l'usuari: ");
        String username = sc.next();
        usuari_estandard usuari = user.auxiliar(username);
        if(usuari == null) System.out.println("L'usuari no existia");
        else{
            user.donar_privilegis(usuari);
        }
    }
    private static void p_borrar_usuari(usuari_privilegiat user){
        System.out.print("Escriu l'usuari a esborrar: ");
        String usuari_a_borrar = sc.next();
        if(user.borrar_usuari(usuari_a_borrar)){
            System.out.println("Usuari esborrat");
            if(user.getUsername().equals(usuari_a_borrar)) user = null;
        }
        else System.out.println("L'usuari no existia");
    }
    private static void p_modificar_usuari(usuari_privilegiat user){
        System.out.print("Escriu l'usuari a modificar: ");
        String username = sc.next();
        usuari_estandard usuari_a_canviar = user.auxiliar(username);
        if (usuari_a_canviar == null) System.out.println("L'usuari no existeix");
        else{
            System.out.print("introdueix el nom nou o null:");
            String nom = sc.next();
            if(nom.equals("null")) nom = null;
            System.out.print("introdueix la contrasenya nova o null:");
            String pass = sc.next();
            String old_pass = null;
            if(pass.equals("null")) pass = null;
            else{
                System.out.print("introdueix la contrasenya antiga o null:");
                old_pass = sc.next();
            }
            System.out.print("introdueix el sexe o null:");
            String sexe = sc.next();
            System.out.print("introdueix la data de naixement o null:");
            Date data = transformar_data.stringToDate(sc.next());
            if(user.modificar_usuari(usuari_a_canviar, old_pass, pass, nom, sexe, data)) System.out.println("Usuari modificat");
            else System.out.println("contrasenya equivocada o tots null");
        }
    }
    private static usuari_estandard crear_usuari(boolean privilegiat){
        System.out.print("Escriu el nom d'usuari: ");
        String username = sc.next();
        System.out.print("Escriu la contrasenya: ");
        String pass = sc.next();
        if(privilegiat){
            usuari_privilegiat user = new usuari_privilegiat(username, pass);
            cjtUsuarios.afegir_usuari(username, user);
            return user;
        }
        usuari_estandard user = new usuari_estandard(username, pass);
        cjtUsuarios.afegir_usuari(username, user);
        return user;

    }
    private static void afegir_relacio(usuari_estandard usuari){
        System.out.print("Escriu el nom de la relacio: ");
        String nomR = sc.next();
        System.out.print("Escriu el path: ");
        String path = sc.next();
        System.out.print("Escriu la descripcio sense espais: ");
        String descripcio = sc.next();
        if(usuari.definir_relacio(nomR, path, descripcio)) System.out.println("Relacio afegida");
        else System.out.println("La relacio ja existia");
    }
    private static void esborrar_relacio(usuari_estandard usuari){
        System.out.print("Escriu la relacio a esborrar: ");
        String nomRelacioE = sc.next();
        if(usuari.esborrar_relacio(nomRelacioE)) System.out.println("Relacio esborrada");
        else System.out.println("No existia la relacio");
    }
    private static TipoRelacion consultar_relacio(usuari_estandard usuari){
        System.out.print("Escriu la relacio a consultar: ");
        String nomRelacioC = sc.next();
        TipoRelacion relacio = usuari.consultar_relacio(nomRelacioC);
        if(relacio == null)System.out.println("La relacio no existeix");
        return relacio;
    }
    private static void modificar_usuari(usuari_estandard usuari){
        System.out.print("Escriu la contrasenya nova o null:");
        String pass = sc.next();
        String old_pass = null;
        if(pass.equals("null")) pass = null;
        else {
            System.out.print("Escriu la contrasenya antiga:");
            old_pass = sc.nextLine();
        }
        System.out.print("Escriu el nom o null:");
        String nom = sc.next();
        if(nom.equals("null")) nom = null;
        System.out.print("Escriu el sexe o null:");
        String sexe = sc.next();
        if(sexe.equals("null")) sexe = null;
        System.out.print("Escriu la data en format dd/mm/aaaa o null:");
        String d = sc.next();
        if(d.equals("null")) usuari.modificar_usuari(old_pass, pass, nom, sexe, null);
        else{usuari.modificar_usuari(old_pass, pass, nom, sexe, transformar_data.stringToDate(d));}
    }
}
