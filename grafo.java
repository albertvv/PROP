/* Llibreries */

public class Grafo {
	
	//ATRIBUTS
	private SparseVector<Autor>		vectorAutor;
	private SparseVector<Conferencia>	vectorConferencia;
	private SparseVector<Termino>		vectorTermino;
	private SparseVector<Articulo>		vectorArticulo;

	private SparseMatrix<boolean>	matrizPaperAutor;
	private SparseMatrix<boolean>	matrizPaperConferencia;
	private SparseMatrix<boolean>	matrizPaperTermino;

	private HashMap<String,Integer> diccionariEntitats; //[NOU] per trobar la id a partir del nom d'una Entitat

	private Integer lastId;


	//CONSTRUCTORA
	public Grafo() {
		vectorAutor 		= new SparseVector<Autor>();
		vectorConferencia	= new SparseVector<Conferencia>();
		vectorTermino 		= new SparseVector<Termino>();
		vectorArticulo 		= new SparseVector<Articulo>();

		matrizPaperAutor 	= new SparseMatrix<boolean>();
		matrizPaperConferencia 	= new SparseMatrix<boolean>();
		matrizPaperTermino 	= new SparseMatrix<boolean>();

		diccionariEntitats	= new HashMap<String,Integer>();

		lastId = -1;
	}


	//METODES	     /*___________*/
	public void addEntidad(Integer id, String nombre, String tipoEntidad) {
		if (!diccionariEntitats.containskey(nombre))
			System.out.println("El nom ha de ser valid");
		else {
			switch (tipoEntidad) {
				Integer index = diccionariEntitats.get(nombre);
				case "Articulo":
					Articulo tmpArticulo = new Articulo(index, nombre);
					vectorArticulo.set(index, tmpArticulo);
					//matrizPaperAutor[index][0..nAutors] = 0
					//matrizPaperConferencia[index][0..nConferencies] = 0
					//matrizPaperTermino[index][0..nTerminos] = 0
					break;
				case "Autor":
					Autor tmpAutor = new Autor(index, nombre);
					vectorAutor.set(index, tmpAutor);
					//matrizPaperAutor[0..nPapers][index] = 0
					break;
				case "Conferencia":
					Conferencia tmpConferencia = new Conferencia(index, nombre);
					vectorConferencia.set(index, tmpConferencia);
					//matrizPaperConferencia[0..nPapers][index] = 0
					break;
				case "Termino":
					Termino tmpTermino = new Termino(index, nombre);
					vectorTermino.set(index, tmpTermino);
					//matrizPaperTermino[0..nPapers][index] = 0
					break;
				default:
					System.out.println("Cal triar un tipus d'entitat valid");
					break;
			}
		}
	}

	public void deleteEntidad(String nombre, String tipoEntidad) {
		switch (tipoEntidad) {
			//esborrar instancia del map
			case "Articulo":
				//esborrar referencia
				//esborrar relacions matrizPaperAutor
				//esborrar relacions matrizPaperConferencia
				//esborrar relacions matrizPaperTermino
				break;
			case "Autor":
				//esborrar referencia
				//esborrar relacions matrizPaperAutor
				break;
			case "Conferencia":
				//esborrar referencia
				//esborrar relacions matrizPaperConferencia
				break;
			case "Termino":
				//esborrar referencia
				//esborrar relacions matrizPaperTermino
				break;
			default:
				System.out.println("Cal triar un tipus d'entitat valid");
				break;
		}
	}

	public void addRelacion(String nombre1, String nombre2, String tipoRelacion) {
		if (nombre1.equals(nombre2))
			System.out.println("Els noms han de ser diferents!");
		else if (!diccionariEntitats.containskey(nombre1) || !diccionariEntitats.containskey(nombre2))
			System.out.println("Els dos noms han de ser valids");
		else {
			Integer index1 = diccionariEntitats.get(nombre1), index2 = diccionariEntitats.get(nombre2);
			switch (tipoRelacion) {
				case "Articulo":
					matrizPaperAutor.set(index1, index2, TRUE);
					matrizPaperAutor.set(index2, index1, TRUE);
					matrizPaperConferencia.set(index1, index2, TRUE);
					matrizPaperConferencia.set(index2, index1, TRUE);
					matrizPaperTermino.set(index1, index2, TRUE);
					matrizPaperTermino.set(index2, index1, TRUE);
					break;
				case "Autor":
					matrizPaperAutor.set(index1, index2, TRUE);
					matrizPaperAutor.set(index2, index1, TRUE);
					break;
				case "Conferencia":
					matrizPaperConferencia.set(index1, index2, TRUE);
					matrizPaperConferencia.set(index2, index1, TRUE);
					break;
				case "Termino":
					matrizPaperTermino.set(index1, index2, TRUE);
					matrizPaperTermino.set(index2, index1, TRUE);
					break;
				default:
					System.out.println("Cal triar un tipus d'entitat valid");
					break;
			}
		}
	}

	public void deleteRelacion(String nombreOrigen, String nombreDesti, String tipoRelacion) {
		if (nombre1.equals(nombre2))
			System.out.println("Els noms han de ser diferents!");
		else if (!diccionariEntitats.containskey(nombre1) || !diccionariEntitats.containskey(nombre2))
			System.out.println("Els dos noms han de ser valids");
		else {
			Integer index1 = diccionariEntitats.get(nombre1), index2 = diccionariEntitats.get(nombre2);
			switch (tipoRelacion) {
				case "Articulo":
					matrizPaperAutor.set(index1, index2, FALSE);
					matrizPaperAutor.set(index2, index1, FALSE);
					matrizPaperConferencia.set(index1, index2, FALSE);
					matrizPaperConferencia.set(index2, index1, FALSE);
					matrizPaperTermino.set(index1, index2, FALSE);
					matrizPaperTermino.set(index2, index1, FALSE);
					break;
				case "Autor":
					matrizPaperAutor.set(index1, index2, FALSE);
					matrizPaperAutor.set(index2, index1, FALSE);
					break;
				case "Conferencia":
					matrizPaperConferencia.set(index1, index2, FALSE);
					matrizPaperConferencia.set(index2, index1, FALSE);
					break;
				case "Termino":
					matrizPaperTermino.set(index1, index2, FALSE);
					matrizPaperTermino.set(index2, index1, FALSE);
					break;
				default:
					System.out.println("Cal triar un tipus d'entitat valid");
					break;
			}
		}
	}

	public Vector< Entidad > getRelacion(String nombre, String tipoEntidad) {
		if (!diccionariEntitats.containskey(nombre))
			System.out.println("El nom ha de ser valid!");
		else {
			switch (tipoEntidad) {
				case "Articulo":
					//relacions a matrizPaperAutor
					//relacions a matrizPaperConferencia
					//relacions a matrizPaperTermino
					break;
				case "Autor":
					//relacions a matrizPaperAutor
					break;
				case "Conferencia":
					//relacions a matrizPaperConferencia
					break;
				case "Termino":
					//relacions a matrizPaperTermino
					break;
				default:
					System.out.println("Cal triar un tipus d'entitat valid");
					break;
			}
		}
	}

	public Entidad getEntidad(String nombre, String tipoEntidad) {
		if (!diccionariEntitats.containskey(nombre)) {
			System.out.println("El nom ha de ser valid!");
			return NULL;
		}
		else {
			Integer index = diccionariEntitats.get(nombre);
			switch (tipoEntidad) {
				case "Articulo":
					return vectorArticulo.get(index);
				case "Autor":
					return vectorAutor.get(index);
				case "Conferencia":
					return vectorConferencia.get(index);
				case "Termino":
					return vectorTermino.get(index);
				default:	
					System.out.println("Cal triar un tipus d'entitat valid");
					return NULL;
			}
		}
	}

	public Integer getIndice(String nombre, String tipo) {
		if (!diccionariEntitats.containskey(nombre)) return -1;
		return diccionariEntitats.get(nombre);
	}

	public Vector< Vector<Integer> > getMatriz(String tipoFila, String tipoColumna) {
		//Per a què?
	}
}
