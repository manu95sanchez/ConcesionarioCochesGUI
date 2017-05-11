package ficherosGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

public class Fichero implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Pattern patron = Pattern.compile("^((\\w)+(\\.obj))$");
	public static File fichero = new File("Sin Titulo");

	/**
	 * Metodo set
	 */
	public static void setFichero(String fichero) {
		Fichero.fichero = new File(fichero);
	}

	/**
	 * Metodo get
	 */
	public static File getFichero() {
		return fichero;
	}
	/**
	 * Metodo para crear un nuevo fichero
	 */
	public static void nuevo(){
		setFichero("SinTitulo.obj");
	}

	/**
	 * Metodo que te deja guardar como
	 */
	public static void guardarComo(Object objeto, File nombre) throws IOException {
		fichero = comprobarFichero(nombre);
		guardar(objeto);
	}

	/**
	 * Metodo que te permite guardar un concesionario que ya existe
	 */
	public static void guardar(Object objeto) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fichero))) {
			out.writeObject(objeto);
		}
	}

	/**
	 * Metodo que te permite abrir un concesionario que ya existe
	 */
	public static Object abrir(File archivo) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
			return in.readObject();
		}
	}

	/**
	 * Este metodo comprueba si el nombre del archivo cumple los requisitos
	 */
	public static File comprobarFichero(File ficheroUsuario) {
		if (patron.matcher(ficheroUsuario.getName()).matches()) {
			return ficheroUsuario;
		} else {
			setFichero(ficheroUsuario.getAbsolutePath() + ".obj");
			return fichero;
		}

	}

}
