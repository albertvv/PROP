public class TipoRelacion {
    private String nom;
    private String path;
    private String descripcio;
    public TipoRelacion(String nom, String path){
        this.nom = nom;
        this.path = path;
    }
    public void setDescripcion(String descripcion){
        this.descripcio = descripcion;
    }
    public void setPath(String path){this.path = path;}
    public void setNombre(String nom){this.nom = nom;}
    public String getNombre(){return nom;}
    public String getPath(){return path;}
    public String getDescripcio(){return descripcio;}
}
