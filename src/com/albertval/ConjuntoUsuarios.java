import java.util.*;

public class ConjuntoUsuarios<T extends Usuario_generico> {
// Datos
	protected Map<String,T> conjUsuarios;

// Funciones
	// Constructor
	public ConjuntoUsuarios() {
		conjUsuarios = new HashMap<String,T>();
	}

	public boolean addUsuario(T user){
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


	public T getUsuario(String username){
	// Pre:
	// Post: Se retorna el usuario con username igual al parametro username, 
	// o null si no existe ningun usuario con el atributo username igual al parametro username
		return conjUsuarios.get(username);
	}
	
	//====================FUNCI�N DE COMPROBACI�N EN DRIVER======================//
	
	public Map<String,T> getConjunto() {
		return conjUsuarios;
	}
	
}
