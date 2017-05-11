package ficherosGUI;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Filtro extends FileFilter{
	
	private String ext;
	private String descripcion;
	
	public Filtro(String ext, String descripcion) {
		setExt(ext);
		setDescripcion(descripcion);
	}
	

	/**
	 *
	 */
	public String getExt() {
		return ext;
	}


	/**
	 *
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}


	/**
	 *
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 *
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public boolean accept(File fichero) {
		if(fichero.isDirectory())
			return true;
		return fichero.getName().endsWith(getExt());
	}

	@Override
	public String getDescription() {
		return getDescripcion() + String.format(" (*%s)", getExt());
	}
	
}