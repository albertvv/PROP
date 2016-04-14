/**
 * Created by Àlvar on 12/04/2016.
 */
package alvarhc2;
import org.la4j.vector.SparseVector;

public class ResImportant {
    private SparseVector vecEntRel;

    public ResultadoRelImport(QueryRelimportant q) {
        vecEntRel = q.Cerca();
    }
    public SparseVector Resultat() {
        return vecEntRel;
    }
}