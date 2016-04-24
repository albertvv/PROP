package user_dominio;

public class Conferencia extends Entidad {

	public Conferencia(int ValorId, String ValorNombre){
		   super(ValorId, ValorNombre);
	}
	public Conferencia(int ValorId, String ValorNombre,String tag){
		super(ValorId, ValorNombre,tag);
	}
}
