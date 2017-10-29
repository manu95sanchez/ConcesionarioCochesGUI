package concesionarioGUI;

import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import concesionarioCoches.Concesionario;
import ficherosGUI.Fichero;
import ficherosGUI.Filtro;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;
/**
* @author Manuel Sánchez
* @version 1.0
* Proyecto Concesionario Coches
*/
public class ConcesionarioGUI implements Serializable {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Alta alta;
	private Baja baja;
	private static final Ayuda ayuda = new Ayuda();
	private BuscarPorMatricula buscarPorMatricula;
	private BuscarPorColor buscarPorColor;
	private SobreConcesionario sobreConcesionario;
	protected static Concesionario concesionarioCoches = new Concesionario();
	private MostrarConcesionario mostrarConcesionario;
	private Filtro filtro = new Filtro(".obj", "Objeto");
	private String nuevoConcesionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConcesionarioGUI window = new ConcesionarioGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConcesionarioGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Concesionario IES Gran Capitán");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 444, 21);
		frame.getContentPane().add(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('a');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevoConcesionario = new JMenuItem("Nuevo concesionario");

		mntmNuevoConcesionario.addActionListener(new ActionListener() {
			/**
			 * Crea un nuevo concesionario
			 */
			public void actionPerformed(ActionEvent arg0) {
				nuevoConcesionario();
			}
		});
		mntmNuevoConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevoConcesionario);

		JMenuItem mntmAbrirConcesionario = new JMenuItem("Abrir concesionario");
		mntmAbrirConcesionario.addActionListener(new ActionListener() {
			/**
			 * Abre un concesionario ya existente
			 */
			public void actionPerformed(ActionEvent e) {
				abrirConcesionario();
			}
		});

		mnArchivo.add(mntmAbrirConcesionario);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			/**
			 * Sirve para guarda un concesionario
			 */
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como");
		mntmGuardarComo.addActionListener(new ActionListener() {
			/**
			 * Sirve para guardar como
			 */
			public void actionPerformed(ActionEvent e) {
				guardarComo();
				concesionarioCoches.setModificado(false);
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();

			}

		});

		JMenu mnCoche = new JMenu("Coche");
		mnCoche.setMnemonic('c');
		menuBar.add(mnCoche);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta = new Alta();
				alta.setVisible(true);

			}
		});
		mnCoche.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionarioCoches.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El concesionario esta vacio.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				baja = new Baja();
				baja.setVisible(true);
			}
		});

		mnCoche.add(mntmBaja);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar Concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (concesionarioCoches.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay concesionario que mostrar.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				mostrarConcesionario = new MostrarConcesionario();
				mostrarConcesionario.setVisible(true);
			}
		});
		mnCoche.add(mntmMostrarConcesionario);

		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('b');
		menuBar.add(mnBuscar);

		JMenuItem mntmPorMatricula = new JMenuItem("Por matricula");
		mntmPorMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionarioCoches.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El concesionario esta vacio.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				buscarPorMatricula = new BuscarPorMatricula();
				buscarPorMatricula.setVisible(true);
			}
		});
		mnBuscar.add(mntmPorMatricula);

		JMenuItem mntmPorColor = new JMenuItem("Por color");
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionarioCoches.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El concesionario esta vacio.", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				buscarPorColor = new BuscarPorColor();
				buscarPorColor.setVisible(true);
			}
		});
		mnBuscar.add(mntmPorColor);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmAyuda);

		JMenuItem mntmSobreConcesionario = new JMenuItem("Sobre concesionario");
		mntmSobreConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sobreConcesionario = new SobreConcesionario();
				sobreConcesionario.setVisible(true);
			}
		});
		mnAyuda.add(mntmSobreConcesionario);
		mnAyuda.add(mntmAyuda);
	}

	/**
	 * Metodo que permite la creacion de un nuevo archivo concesionario
	 */
	private void nuevoConcesionario() {
		if (concesionarioCoches.isModificado()) {
			Object[] options = { "SI", "NO", "CANCELAR" };
			int respuesta = JOptionPane.showOptionDialog(null, "No has guardado, ¿Desea Guardar?", "NO HAS GUARDADO",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (respuesta == 0) {
				guardarComo();
				String titulo = JOptionPane.showInputDialog(null, "Introduce el nombre del nuevo concesionario",
						"Nombre del Concesionario", JOptionPane.QUESTION_MESSAGE);
				Fichero.setFichero(titulo);
				concesionarioCoches = new Concesionario();
				frame.setTitle(Fichero.fichero.getName());
				concesionarioCoches.setModificado(false);
			} else if (respuesta == 1) {
				String titulo = JOptionPane.showInputDialog(null, "Introduce el nombre del nuevo concesionario",
						"Nombre del Concesionario", JOptionPane.QUESTION_MESSAGE);
				Fichero.setFichero(titulo);
				concesionarioCoches = new Concesionario();
				frame.setTitle(Fichero.fichero.getName());
				concesionarioCoches.setModificado(false);
			} else {
			}
		} else {

			Fichero.setFichero("nuevoConcesionario - Sin titulo");
			concesionarioCoches = new Concesionario();
			frame.setTitle(Fichero.fichero.getName());
			concesionarioCoches.setModificado(false);
		}
	}

	/**
	 * Este metodo sirve para abrir un concesionario existente
	 */
	private void abrirConcesionario() {
		if (concesionarioCoches.isModificado()) {
			Object[] options = { "SI", "NO", "CANCEL" };
			int respuesta = JOptionPane.showOptionDialog(null, "No has guardado, ¿Desea Guardar?", "NO HAS GUARDADO",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (respuesta == 0) {
				guardarComo();
			} else if (respuesta == 1) {
				try {
					abrirFicheroFileChooser();
				} catch (IOException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Error al abrir fichero", "ERROR", JOptionPane.ERROR_MESSAGE);
					Fichero.nuevo();
				}
			} else {
			}
		} else {
			try {
				abrirFicheroFileChooser();
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "Error al abrir fichero", "ERROR", JOptionPane.ERROR_MESSAGE);
				Fichero.nuevo();
			}

		}
	}

	/**
	 * Este metodo te crea un FileChooser
	 */
	private void abrirFicheroFileChooser() throws FileNotFoundException, ClassNotFoundException, IOException {
		JFileChooser abrir = new JFileChooser();
		abrir.setAcceptAllFileFilterUsed(false);
		abrir.addChoosableFileFilter(filtro);
		if (abrir.showDialog(abrir, "Abrir Fichero") == JFileChooser.APPROVE_OPTION) {
			Fichero.fichero = abrir.getSelectedFile();
			concesionarioCoches = (Concesionario) Fichero.abrir(abrir.getSelectedFile());
			frame.setTitle(Fichero.getFichero().getName());
			concesionarioCoches.setModificado(false);

		}
	}

	/**
	 * Este metodo te permite guardar un fichero
	 */
	private void guardar() {
		if (Fichero.fichero.getName().equalsIgnoreCase("Sin titulo")) {
			guardarComo();
			concesionarioCoches.setModificado(false);
		} else {
			try {
				Fichero.guardar(concesionarioCoches);
				concesionarioCoches.setModificado(false);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error al guardar", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Este metodo te permite guardar un fichero a partir del guardar como
	 */
	private void guardarComo() {
		JFileChooser guardarComo = new JFileChooser();
		guardarComo.setAcceptAllFileFilterUsed(false);
		guardarComo.addChoosableFileFilter(filtro);

		if (JFileChooser.APPROVE_OPTION == guardarComo.showDialog(guardarComo, "Guardar Archivo")) {

			guardarComo.setAcceptAllFileFilterUsed(false);
			Fichero.comprobarFichero(guardarComo.getSelectedFile());
			if (Fichero.getFichero().exists()) {
				Object[] options = { "Si", "No" };
				int respuesta = JOptionPane.showOptionDialog(null, "El archivo ya existe, ¿Desea Sobreescribir?",
						"Guardando", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
				if (respuesta == 0) {
					try {
						Fichero.guardarComo(concesionarioCoches, Fichero.getFichero());
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				guardar();
			}

			frame.setTitle(Fichero.getFichero().getName());
			concesionarioCoches.setModificado(false);
		}
	}

	private void salir() {
		if (concesionarioCoches.isModificado()) {
			Object[] options = { "SI", "NO", "CANCELAR" };
			int respuesta = JOptionPane.showOptionDialog(null, "No has guardado, ¿Desea Guardar?", "NO HAS GUARDADO",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (respuesta == 0) {
				guardarComo();
			} else if (respuesta == 1) {
				System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}
}
