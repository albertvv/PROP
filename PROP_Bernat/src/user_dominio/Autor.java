package user_dominio;

import com.sun.prism.shader.Solid_TextureRGB_AlphaTest_Loader;

public class Autor extends Entidad {

	public Autor(int ValorId, String ValorNombre){
		   super(ValorId, ValorNombre);
	}

	public Autor(int ValorId, String ValorNombre, String tag){
		super(ValorId, ValorNombre,tag);
	}
}
