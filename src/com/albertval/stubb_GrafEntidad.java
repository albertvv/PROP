/**
 * Created by marc on 4/19/16.
 */
public class Entidad {

    public Integer id;
    public String  name;

    public Entidad() {}

    public String getNombre() { return name; }
}

public class Articulo extends Entidad {
    private Integer label1;

    public Articulo(Integer identitat, String nom) {
        id = identitat;
        name = nom;
    }

    public void incLabel1() { ++label1; }
    public Integer getLabel1() { return label1; }
}

public class Autor extends Entidad {
    private Integer label2;

    public Autor(Integer identitat, String nom) {
        id = identitat;
        name = nom;
    }

    public void incLabel1() { ++label2; }
    public Integer getLabel1() { return label2; }
}

public class Conferencia extends Entidad {
    private Integer label3;

    public Conferencia(Integer identitat, String nom) {
        id = identitat;
        name = nom;
    }

    public void incLabel1() { ++label3; }
    public Integer getLabel1() { return label3; }
}

public class Termino extends Entidad {
    private Integer label4;

    public Termino(Integer identitat, String nom) {
        id = identitat;
        name = nom;
    }

    public void incLabel1() { ++label4; }
    public Integer getLabel1() { return label4; }
}
