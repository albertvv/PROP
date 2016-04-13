import com.albertval.Metrica;
import com.albertval.QueryClustering;
import junit.framework.TestCase;
import org.la4j.matrix.SparseMatrix;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Albert on 12/04/2016.
 */
public class QueryClusteringTest extends TestCase {
    public void testrandom(){
        Metrica m = new Metrica();
        SparseMatrix m1 = null;
        SparseMatrix m2 = null;
        SparseMatrix m3 = null;
        String path = "APA";
        int numgrups = 3;
        Vector<String> vs = new Vector<>();
        int i=0;
       // Scanner b = new Scanner(System.in);
        int ninserts = 100;
        while(i<ninserts){
            vs.add("Persona"+i);
            ++i;
        }
        System.out.println("midavector :"+vs.size());
        QueryClustering qc= new QueryClustering(path,numgrups,vs,m1,m2,m3,m);
        Vector<Vector<String >> vvs= new Vector<>();
        qc.randommedioides(vvs);
        escriuvectors(vvs,vs);
    }

    private void escriuvectors(Vector<Vector<String>> vvs, Vector<String> vs) {
        for(int i=0;i<vvs.size();++i){
            for (int j = 0; j < vvs.get(i).size() ; j++) {
                System.out.println("valor de fila "+i+" i columna "+j+" : "+vvs.get(i).get(j));
            }
        }
        System.out.println(vs.size());
    }

    public void testassignagrups(){
        Metrica m = new Metrica();
        SparseMatrix m1 = null;
        SparseMatrix m2 = null;
        SparseMatrix m3 = null;
        String path = "APA";
        int numgrups = 3;
        Vector<String> vs = new Vector<>();
        int i=0;
        // Scanner b = new Scanner(System.in);
        int ninserts = 100;
        while(i<ninserts){
            vs.add("Persona"+i);
            ++i;
        }
        QueryClustering qc= new QueryClustering(path,numgrups,vs,m1,m2,m3,m);
        Vector<Vector<String >> vvs= new Vector<>();
        qc.randommedioides(vvs);
        escriuvectors(vvs,vs);
        qc.assignagrups(vvs);
        escriuvectors(vvs,vs);

    }
    public void testcentramedioides(){
        Metrica m = new Metrica();
        SparseMatrix m1 = null;
        SparseMatrix m2 = null;
        SparseMatrix m3 = null;
        String path = "APA";
        int numgrups = 3;
        Vector<String> vs = new Vector<>();
        int i=0;
        // Scanner b = new Scanner(System.in);
        int ninserts = 100;
        while(i<ninserts){
            vs.add("Persona"+i);
            ++i;
        }
        QueryClustering qc= new QueryClustering(path,numgrups,vs,m1,m2,m3,m);
        Vector<Vector<String >> vvs= new Vector<>();
        qc.randommedioides(vvs);
        qc.assignagrups(vvs);
        escriuvectors(vvs,vs);
        for(int j=0;j<numgrups;++j) qc.centramedioide(vvs.get(j));
        escriuvectors(vvs,vs);
    }
    public void testreassignagrups(){
        Metrica m = new Metrica();
        SparseMatrix m1 = null;
        SparseMatrix m2 = null;
        SparseMatrix m3 = null;
        String path = "APA";
        int numgrups = 3;
        Vector<String> vs = new Vector<>();
        int i=0;
        // Scanner b = new Scanner(System.in);
        int ninserts = 30;
        while(i<ninserts){
            vs.add("Persona"+i);
            ++i;
        }
        QueryClustering qc= new QueryClustering(path,numgrups,vs,m1,m2,m3,m);
        Vector<Vector<String >> vvs= new Vector<>();
        qc.randommedioides(vvs);
        qc.assignagrups(vvs);
        escriuvectors(vvs,vs);
        for(int j=0;j<numgrups;++j) qc.centramedioide(vvs.get(j));
        escriuvectors(vvs,vs);
        qc.reassigna(vvs);
        escriuvectors(vvs,vs);
    }
    public void testCerca(){
        Metrica m = new Metrica();
        SparseMatrix m1 = null;
        SparseMatrix m2 = null;
        SparseMatrix m3 = null;
        String path = "APA";
        int numgrups = 3;
        Vector<String> vs = new Vector<>();
        int i=0;
        // Scanner b = new Scanner(System.in);
        int ninserts = 1000;
        while(i<ninserts){
            vs.add("Persona"+i);
            ++i;
        }
        QueryClustering qc= new QueryClustering(path,numgrups,vs,m1,m2,m3,m);
        Vector<Vector<String>> v = qc.Cerca(1);
        escriuvectors(v,vs);

    }
}