package concesionarioGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import concesionarioCoches.Coche;

public class MostrarConcesionario extends Plantilla {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public MostrarConcesionario() {
		setModal(true);
		setResizable(false);
		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente();
			}
		});
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior();
			}
		});
		actualizar();
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		Azul.setEnabled(false);
		Rojo.setEnabled(false);
		Plata.setEnabled(false);
		matricula.setEnabled(false);
		btnAccion.setVisible(false);
		setTitle("Mostrar Concesionario");
		setBounds(100, 100, 450, 300);

	}

	/**
	 * Muestra un coche
	 */
	@Override
	protected void mostrarCoche(Coche coche) {
		matricula.setText(coche.getMatricula());
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

}