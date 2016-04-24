package user_dominio;

import java.util.*;

public class Usuario_generico {
	
	private String username;
	private String password;
	private String nombre;
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

	public String getPassword(){return password;}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public boolean deleteRelacion(String nombre) {return (conjTRel.remove(nombre) != null);}
	
	public TipoRelacion getRelacion(String nombre) {return conjTRel.get(nombre);}

	public void addRelacion(String nombre, String path) {
		TipoRelacion t = new TipoRelacion(path,nombre);
		conjTRel.put(nombre,t);
	}

	public void addRelacion(String nombre, String path, String descripcion) {
		TipoRelacion t = new TipoRelacion(path,nombre);
		if (!descripcion.isEmpty()) t.setDescripcion(descripcion);
		else t.setDescripcion(null);
		conjTRel.put(nombre,t);
	}

}
	
	