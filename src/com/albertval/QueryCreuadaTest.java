import com.albertval.QueryCreuada;
import com.albertval.ResImportant;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Albert on 12/04/2016.
 */
public class QueryCreuadaTest extends TestCase {
    public void testCreuaResultats() {
        Map<Integer,Double> map1 = new HashMap<>();
        map1.put(10,0.5);
        map1.put(20,0.1);
        map1.put(30,0.7);
        Map<Integer,Double> map2 = new HashMap<>();
        map2.put(10,0.3);
        map2.put(30,0.5);
        map2.put(3,0.8);
        ResImportant r1 = new ResImportant(map1);
        ResImportant r2 = new ResImportant(map2);
        QueryCreuada qc = new QueryCreuada(r1,r2);
        Map<Integer,Double> map = qc.CreuaResultats();
        System.out.println("hola");
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            Integer key = (Integer) it.next();
            System.out.println("Clave: " + key + " -> Valor: " + map.get(key));
        }
    }
}