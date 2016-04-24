package user_dominio;

public class Termino extends Entidad {

    public Termino() {}
    public Termino (int idTermino, String nombreTermino) {
        super(idTermino,nombreTermino);
    }
    public Termino(int ValorId, String ValorNombre,String tag){
        super(ValorId, ValorNombre,tag);
    }
}
