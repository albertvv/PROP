/**
 * Created by Àlvar on 12/04/2016.
 */
package alvarhc2;
import java.util.Vector;

public class ResClustering {
    private Vector<Vector<String>> ConjGrups;

    public ResultadoClustering(QueryClustering q) {
        ConjGrups = q.Cerca();
    }

    public Vector<Vector<String>> Resultat() {
        return ConjGrups;
    }

}
