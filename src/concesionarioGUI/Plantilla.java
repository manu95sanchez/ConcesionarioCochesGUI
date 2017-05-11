package concesionarioGUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import concesionarioCoches.Coche;
import concesionarioCoches.Color;
import concesionarioCoches.Concesionario;
import concesionarioCoches.Marca;
import concesionarioCoches.Modelo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Plantilla extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField matricula;
	protected final ButtonGroup buttonGroup = new ButtonGroup();
	protected JRadioButton Plata, Rojo, Azul;
	protected JComboBox<Marca> comboBoxMarca;
	protected JComboBox<Modelo> comboBoxModelo;
	protected Concesionario concesionarioCoches = ConcesionarioGUI.concesionarioCoches;
	protected JButton btnAccion;
	protected JButton btnAtras;
	protected JButton btnAdelante;
	protected int indiceCoche = -1;

	private Object[] getModelo(JComboBox<Marca> comboBoxMarca) {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m : Modelo.values()) {
			if (m.getMarca() == marca)
				modelos.add(m);
		}
		return modelos.toArray();
	}

	protected Color getColor() {
		if (Plata.isSelected())
			return Color.PLATA;
		else if (Rojo.isSelected())
			return Color.ROJO;
		else if (Azul.isSelected())
			return Color.AZUL;
		else
			return null;
	}

	/**
	 * Create the dialog.
	 */
	public Plantilla() {
		setResizable(false);
		setTitle("Plantilla");
		setBounds(100, 100, 423, 275);
		getContentPane().setLayout(null);

		JLabel lblMatrcula = new JLabel("Matr\u00EDcula");
		lblMatrcula.setBounds(42, 25, 69, 14);
		getContentPane().add(lblMatrcula);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(42, 66, 46, 14);
		getContentPane().add(lblColor);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(42, 110, 46, 14);
		getContentPane().add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(42, 155, 46, 14);
		getContentPane().add(lblModelo);

		matricula = new JTextField();
		matricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				matricula.setText(matricula.getText().toUpperCase());
			}
		});
		matricula.setBounds(98, 22, 159, 20);
		getContentPane().add(matricula);
		matricula.setColumns(10);

		Plata = new JRadioButton("Plata");
		buttonGroup.add(Plata);
		Plata.setBounds(94, 62, 69, 23);
		getContentPane().add(Plata);

		Rojo = new JRadioButton("Rojo");
		buttonGroup.add(Rojo);
		Rojo.setBounds(165, 62, 69, 23);
		getContentPane().add(Rojo);

		Azul = new JRadioButton("Azul");
		buttonGroup.add(Azul);
		Azul.setBounds(231, 62, 59, 23);
		getContentPane().add(Azul);

		comboBoxMarca = new JComboBox<Marca>();
		comboBoxMarca.setModel(new DefaultComboBoxModel<Marca>(Marca.values()));
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizarModelo();
			}

		});
		comboBoxMarca.setBounds(98, 106, 91, 22);
		getContentPane().add(comboBoxMarca);

		comboBoxModelo = new JComboBox<Modelo>();
		comboBoxModelo.setBounds(98, 151, 91, 22);
		getContentPane().add(comboBoxModelo);

		actualizarModelo();

		btnAccion = new JButton("A\u00F1adir");
		btnAccion.setBounds(196, 198, 91, 23);
		getContentPane().add(btnAccion);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnSalir.setBounds(297, 198, 91, 23);
		getContentPane().add(btnSalir);

		btnAtras = new JButton("<");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAtras.setBounds(231, 123, 46, 46);
		getContentPane().add(btnAtras);

		btnAdelante = new JButton(">");
		btnAdelante.setBounds(295, 123, 46, 46);
		getContentPane().add(btnAdelante);

	}

	private void actualizarModelo() {
		comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));
	}

	protected void mostrarCoche(Coche coche) {
		switch (coche.getColor()) {
		case PLATA:
			Plata.setSelected(true);
			break;
		case ROJO:
			Rojo.setSelected(true);
			break;
		case AZUL:
			Azul.setSelected(true);
		}
		comboBoxMarca.addItem(coche.getModelo().getMarca());
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.addItem(coche.getModelo());
		comboBoxModelo.setSelectedItem(coche.getModelo());
	}

	/**
	 * comprueba el estado de los botones y los habilita o deshabilita 
	 */
	protected void comprobarBotones() {
		if (concesionarioCoches.get(indiceCoche + 1) == null)
			btnAdelante.setEnabled(false);
		else
			btnAdelante.setEnabled(true);

		if (concesionarioCoches.get(indiceCoche - 1) == null)
			btnAtras.setEnabled(false);
		else
			btnAtras.setEnabled(true);
	}

	/**
	 * Comprueba si hay coches en el concesionario, muesta el primer coche y comprueba el estado
	 */
	void actualizar() {
		if (concesionarioCoches.size() == 0) {
			return;
		}
		indiceCoche = 0;
		mostrarCoche(concesionarioCoches.get(indiceCoche));
		comprobarBotones();
	}

	/**
	 * Muestra el siguiente coche del concesionario
	 */
	void mostrarSiguiente() {
		mostrarCoche(concesionarioCoches.get(++indiceCoche));
		comprobarBotones();
	}

	/**
	 * Muestra el coche anterior del concesionario
	 */
	void mostrarAnterior() {
		mostrarCoche(concesionarioCoches.get(--indiceCoche));
		comprobarBotones();
	}
}