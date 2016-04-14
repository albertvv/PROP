/**
 * Created by Àlvar on 14/04/2016.
 */
package alvarhc2;
import java.util.Vector;

public class ConjRes {
    Vector<ResImportant> RelImps = new Vector<ResImportant>[6];
    Integer num_relimp = 0;
    Vector<ResClustering> Clusts = new Vector<ResClustering>[6];
    Integer num_clusts = 0;

    public ConjRes() {
    }

    public ResImportant getRelImp(Integer i) {
        return RelImps[i];
    }

    public ResClustering getClust(Integer i) {
        return Clusts[i];
    }

    public ResImportant addRelImp(ResImportant r) {
        if (num_relimp < 5) {
            RelImps[num_relimp] = r;
            ++num_relimp;
        }
        else{//es pot fer mes eficient amb llistes
            for(int i = 4; i >= 0; --i) {
                RelImps[i+1] = RelImps[i];
            }
            RelImps[0] = r;
        }
    }

    public ResClustering addClust(ResClustering r) {
        if (num_clusts < 5) {
            Clusts[num_clusts] = r;
            ++num_clusts;
        }
        else{//es pot fer mes eficient amb llistes
            for(int i = 4; i >= 0; --i) {
                Clusts[i+1] = Clusts[i];
            }
            Clusts[0] = r;
        }
    }
}
