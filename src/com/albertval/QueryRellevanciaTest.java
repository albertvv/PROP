import com.albertval.Metrica;
import com.albertval.QueryRellevancia;
import junit.framework.TestCase;
import org.la4j.matrix.SparseMatrix;

import java.util.Vector;

/**
 * Created by Albert on 13/04/2016.
 */
public class QueryRellevanciaTest extends TestCase {
    void testCerca() {
        Metrica m = new Metrica();
        SparseMatrix m1 = null;
        SparseMatrix m2 = null;
        SparseMatrix m3 = null;
        String path = "APAP";
        Vector<String> vs = new Vector<>();
        vs.add("Pepito");
        vs.add(null);
        vs.add("Grillo");
        vs.add("donJose");
        QueryRellevancia qr = new QueryRellevancia(path,vs,m,m1,m2,m3);
        System.out.println("hey la rellevancia es :"+qr.Cerca());
    }
}