import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Grafo g = new Grafo();
        while (true) {
            System.out.println("0 afegir entitat\n1 esborrar entitat\n2 afegir relacio\n3 esborrar relacio\n" +
                    "4 relacions directes\n5 obtenir entitat\n6 obtenir matriu d'adjacencies");
            Scanner sc = new Scanner(System.in);
            int opcio = sc.nextInt();
            switch (opcio) {
                case 0: //afegir entitat
                    Scanner sc1 = new Scanner(System.in), sc2 = new Scanner(System.in);
                    String nom = sc1.nextLine(), tipus = sc2.nextLine();
                    g.addEntidad(nom, tipus);
                    System.out.println("Entitat afegida correctament");
                    break;
                case 1: //esborrar entitat
                    Scanner sc1 = new Scanner(System.in), sc2 = new Scanner(System.in);
                    String nom = sc1.nextLine(), tipus = sc2.nextLine();
                    g.deleteEntidad(nom, tipus);
                    System.out.println("Entitat esborrada correctament");
                    break;
                case 2: //afegir relacio
                    ;
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("T'has equivocat, gamarús!");
                    break;
            }
        }
    }
}
