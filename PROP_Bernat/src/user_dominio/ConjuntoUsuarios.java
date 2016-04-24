package user_dominio;

import java.util.*;

public class ConjuntoUsuarios {
	private Map<String,usuari_estandard> conjUsuarios;

	/**
	 * Post:crea el conjunt i afegeix l’admin(usuari_privilegiat)
	 */
	public ConjuntoUsuarios() {

		conjUsuarios = new HashMap<String,usuari_estandard>();
		usuari_privilegiat admin = new usuari_privilegiat("admin","admin");
		conjUsuarios.put("admin", admin);
	}

	/**
	 *
	 * @param user
	 * @return
     */
	public boolean addUsuario(usuari_estandard user){
	// Pre:
	// Post: Se a�ade el usuario i se a�ade al conjunto //Util par la inicializacion????
	// Cierto si se ha podido a�adir correctamente
		String username = user.getUsername();
		if (!conjUsuarios.containsKey(username)){
			conjUsuarios.put(username,user);
			return true;
		}
		return false;
	}

	public boolean deleteUsuario(String username){
	// Pre:
	// Post: Se elimina el usuario con el username igual al parametro username
	// Cierto si se ha eliminado correctamente
		return (conjUsuarios.remove(username) !=  null);
	}


	public usuari_estandard getUsuario(String username){
	// Pre:
	// Post: Se retorna el usuario con username igual al parametro username, 
	// o null si no existe ningun usuario con el atributo username igual al parametro username
		return conjUsuarios.get(username);
	}
	
	//====================FUNCI�N DE COMPROBACI�N EN DRIVER======================//

	public Map<String,usuari_estandard> getConjunto() {
		return conjUsuarios;
	}
	
}
