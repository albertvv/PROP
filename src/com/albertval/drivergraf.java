import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;


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
                    Scanner sc1 = new Scanner(System.in), sc2 = new Scanner(System.in), sc3 = new Scanner(System.in);
                    String nom1 = sc1.nextLine(), nom2 = sc2.nextLine(), tipus = sc3.nextLine();
                    g.addRelacion(nom1, nom2, tipus);
                    System.out.println("Relació afegida correctament");
                    break;
                case 3: //esborrar relacio
                    Scanner sc1 = new Scanner(System.in), sc2 = new Scanner(System.in), sc3 = new Scanner(System.in);
                    String nom1 = sc1.nextLine(), nom2 = sc2.nextLine(), tipus = sc3.nextLine();
                    g.deleteRelacion(nom1, nom2, tipus);
                    System.out.println("Relació esborrada correctament");
                    break;
                case 4: //relacions directes
                    Scanner sc1 = new Scanner(System.in), sc2 = new Scanner(System.in);
                    String nom = sc1.nextLine(), tipus = sc2.nextLine();
                    Vector<Entidad> v = new Vector<Entidad>();
                    v = g.getRelacion(nom, tipus);
                    int n = v.size();
                    System.out.print("relacions directes obtingudes");
                    break;
                case 5: //obtenir entitat
                    Scanner sc1 = new Scanner(System.in), sc2 = new Scanner(System.in);
                    String nom = sc1.nextLine(), tipus = sc2.nextLine();
                    Entidad e = new Entidad();
                    e = g.getEntidad(nom, tipus);
                    break;
                case 6: //obtenir matriu d'adjacencies
                    Scanner sc1 = new Scanner(System.in);
                    String columna = sc1.nextLine();
                    SparseMatrix m = new SparseMatrix();
                    m = g.getMatriz("Articulo", columna);
                    System.out.println("matriu obtinguda correctament");
                    break;
                default:
                    System.out.println("T'has equivocat, gamarús!");
                    break;
            }
        }
    }
}
