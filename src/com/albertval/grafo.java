import java.util.Vector;
import org.la4j.matrix.SparseMatrix;

public class Grafo {
	
	//ATRIBUTS
	/*Vectors d'entitats*/
	private Vector<Autor>		vectorAutor;
	private Vector<Conferencia>	vectorConferencia;
	private Vector<Termino>		vectorTermino;
	private Vector<Articulo>	vectorArticulo;
	/*Matrius d'adjacencia*/
	private SparseMatrix	matrizPaperAutor;
	private SparseMatrix	matrizPaperConferencia;
	private SparseMatrix	matrizPaperTermino;
	/*Darrera ID emprada*/
	private Integer lastId;


	//CONSTRUCTORA
	public Grafo() {
		vectorAutor 			= new Vector<Autor>();
		vectorConferencia		= new Vector<Conferencia>();
		vectorTermino 			= new Vector<Termino>();
		vectorArticulo 			= new Vector<Articulo>();

		matrizPaperAutor 		= new SparseMatrix();
		matrizPaperConferencia	= new SparseMatrix();
		matrizPaperTermino 		= new SparseMatrix();

		lastId = -1;
	}


	//METODES
	public void addEntidad(String nombre, String tipoEntidad) {
		switch (tipoEntidad) {
			case "Articulo":
				Articulo tmpArticulo = new Articulo(getIndice(lastId++, nombre));
				vectorArticulo.addElement(index, tmpArticulo);
				//matrizPaperAutor[index][0..nAutors] = 0
				//matrizPaperConferencia[index][0..nConferencies] = 0
				//matrizPaperTermino[index][0..nTerminos] = 0
				break;
			case "Autor":
				Autor tmpAutor = new Autor(lastId++, nombre);
				vectorAutor.addElement(index, tmpAutor);
				//matrizPaperAutor[0..nPapers][index] = 0
				break;
			case "Conferencia":
				Conferencia tmpConferencia = new Conferencia(lastId++, nombre);
				vectorConferencia.addElement(index, tmpConferencia);
				//matrizPaperConferencia[0..nPapers][index] = 0
				break;
			case "Termino":
				Termino tmpTermino = new Termino(lastId++, nombre);
				vectorTermino.addElement(tmpTermino);
				//matrizPaperTermino[0..nPapers][index] = 0
				break;
			default:
				System.out.println("Cal triar un tipus d'entitat valid");
				break;
		}
	}

	public void deleteEntidad(String nombre, String tipoEntidad) {
		switch (tipoEntidad) {
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
		switch (tipoRelacion) {
			case "Articulo":
				Integer index1 = getIndice(nombre1, "Articulo"), index2 = getIndice(nombre2, "Articulo");
				matrizPaperAutor.set(index1, index2, 1);
				matrizPaperAutor.set(index2, index1, 1);
				matrizPaperConferencia.set(index1, index2, 1);
				matrizPaperConferencia.set(index2, index1, 1);
				matrizPaperTermino.set(index1, index2, 1);
				matrizPaperTermino.set(index2, index1, 1);
				break;
			case "Autor":
				Integer index1 = getIndice(nombre1, "Autor"), index2 = getIndice(nombre2, "Autor");
				matrizPaperAutor.set(index1, index2, 1);
				matrizPaperAutor.set(index2, index1, 1);
				break;
			case "Conferencia":
				Integer index1 = getIndice(nombre1, "Conferencia"), index2 = getIndice(nombre2, "Conferencia");
				matrizPaperConferencia.set(index1, index2, 1);
				matrizPaperConferencia.set(index2, index1, 1);
				break;
			case "Termino":
				Integer index1 = getIndice(nombre1, "Termino"), index2 = getIndice(nombre2, "Termino");
				matrizPaperTermino.set(index1, index2, 1);
				matrizPaperTermino.set(index2, index1, 1);
				break;
			default:
				System.out.println("Cal triar un tipus d'entitat valid");
				break;
		}
	}

	public void deleteRelacion(String nombreOrigen, String nombreDesti, String tipoRelacion) {
		switch (tipoRelacion) {
			case "Articulo":
				Integer index1 = getIndice(nombre1, "Articulo"), index2 = getIndice(nombre2, "Articulo");
				matrizPaperAutor.set(index1, index2, 0);
				matrizPaperAutor.set(index2, index1, 0);
				matrizPaperConferencia.set(index1, index2, 0);
				matrizPaperConferencia.set(index2, index1, 0);
				matrizPaperTermino.set(index1, index2, 0);
				matrizPaperTermino.set(index2, index1, 0);
				break;
			case "Autor":
				Integer index1 = getIndice(nombre1, "Autor"), index2 = getIndice(nombre2, "Autor");
				matrizPaperAutor.set(index1, index2, 0);
				matrizPaperAutor.set(index2, index1, 0);
				break;
			case "Conferencia":
				Integer index1 = getIndice(nombre1, "Conferencia"), index2 = getIndice(nombre2, "Conferencia");
				matrizPaperConferencia.set(index1, index2, 0);
				matrizPaperConferencia.set(index2, index1, 0);
				break;
			case "Termino":
				Integer index1 = getIndice(nombre1, "Termino"), index2 = getIndice(nombre2, "Termino");
				matrizPaperTermino.set(index1, index2, 0);
				matrizPaperTermino.set(index2, index1, 0);
				break;
			default:
				System.out.println("Cal triar un tipus d'entitat valid");
				break;
		}
	}

	public Vector<Entidad> getRelacion(String nombre, String tipoEntidad) {
		Vector<Entidad> vR = new Vector<Entidad>();
		switch (tipoEntidad) {
			case "Articulo":
				//relacions a matrizPaperAutor
				//relacions a matrizPaperConferencia
				//relacions a matrizPaperTermino
				for (int i = 0; i < ???; i++) {
					if (i < vectorAutor.length())
						if (matrizPaperAutor.get(i, ???) == 1) vR.addElement(vectorArticulo.elementAt(i));
					if (i < vectorConferencia.length())
						if (matrizPaperConferencia.get(i, ???) == 1) vR.addElement(vectorConferencia.elementAt(i));
					if (i < vectorTermino.length())
						if (matrizPaperTermino.get(i, ???) == 1) vR.addElement(vectorTermino.elementAt(i));
				}
				return vR;
			case "Autor":
				//relacions a matrizPaperAutor
				for (int i = 0; i < ???; ++i) {
					if (matrizPaperAutor.get(i, ???) == 1) vR.addElement(vectorArticulo.elementAt(i));
				}
				return vR;
			case "Conferencia":
				//relacions a matrizPaperConferencia
				for (int i = 0; i < ???; ++i) {
					if (matrizPaperConferencia.get(i, ???) == 1) vR.addElement(vectorConferencia.elementAt(i));
				}
				return vR;
			case "Termino":
				//relacions a matrizPaperTermino
				for (int i = 0; i < ???; ++i) {
					if (matrizPaperTermino.get(i, ???) == 1) vR.addElement(vectorTermino.elementAt(i));
				}
				return vR;
			default:
				System.out.println("Cal triar un tipus d'entitat valid");
				return vR;
		}
	}

	public Entidad getEntidad(String nombre, String tipoEntidad) {
		switch (tipoEntidad) {
			case "Articulo":
				return vectorArticulo.get(getIndice(nombre, "Articulo"));
			case "Autor":
				return vectorAutor.get(getIndice(nombre, "Autor"));
			case "Conferencia":
				return vectorConferencia.get(getIndice(nombre, "Conferencia"));
			case "Termino":
				return vectorTermino.get(getIndice(nombre, "Termino"));
			default:	
				System.out.println("Cal triar un tipus d'entitat valid");
				return NULL;
		}
	}

	public Integer getIndice(String nombre, String tipo) {
		//
	}

	public SparseMatrix getMatriz(String tipoFila, String tipoColumna) {
		//Per a què?
	}
}
