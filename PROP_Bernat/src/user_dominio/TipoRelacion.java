package user_dominio;

public class TipoRelacion {
	
	private String nombre;
	private String path;
	private String descripcion;
	
	public TipoRelacion(String path,String nombre) {
		this.nombre = nombre;
		this.path = path;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}
