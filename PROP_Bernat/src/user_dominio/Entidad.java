package user_dominio;

public class Entidad {

    private int id;
	private String nombre;
    private String etiqueta;

	public Entidad(){}	//Creadora default
	
	public Entidad(int ValorId, String ValorNombre){
		id = ValorId;
		nombre = ValorNombre;
	}
    public Entidad(int ValorId, String ValorNombre,String tag){
        id = ValorId;
        nombre = ValorNombre;
        etiqueta = tag;
    }
    public String getNombre(){ return nombre; }
    
    public int getId(){ return id; }

    public String getEtiqueta() {return etiqueta;}

    public void setNombre(String ValorNombre){ nombre = ValorNombre; }
    
    public void setId(int ValorId){ id = ValorId; }

    public void setEtiqueta (String etiqueta) {this.etiqueta = etiqueta;}
    
}

    