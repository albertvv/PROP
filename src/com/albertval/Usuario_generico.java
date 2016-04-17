import java.util.*;

public class Usuario_generico {
	
	String username;
	String password;
	private String nombre;
	private Date fechaNac;
	private String sexo;
	protected Map<String,TipoRelacion> conjTRel = new HashMap<String, TipoRelacion>();
	
	public Usuario_generico(){}
	
	public Usuario_generico(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaN(Date fecha) {
		this.fechaNac = fecha;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Boolean setPassword(String oldPass, String newPass){
		if (oldPass.equals(this.password)) {
			this.password = newPass;
			return true;
		}
		return false;
	}
	
	public String getSexo(){
		return this.sexo;
	}
	
	public Date getFechaN() {
		return this.fechaNac;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public boolean deleteRelacion(String nombre) {return (conjTRel.remove(nombre) != null);}
	
	public TipoRelacion getRelacion(String nombre) {return conjTRel.get(nombre);}

}
	
	