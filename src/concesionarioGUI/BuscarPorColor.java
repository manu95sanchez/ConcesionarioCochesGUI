package concesionarioGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import concesionarioCoches.Coche;

public class BuscarPorColor extends Plantilla {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Coche> CochePorColor = new ArrayList<Coche>();

	/**
	 * Create the dialog.
	 */
	public BuscarPorColor() {
		setModal(true);
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					indiceCoche = 0;
					CochePorColor = concesionarioCoches.getCochesColor(getColor());
					mostrarCoche(indiceCoche);
					comprobarBotones();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "No hay coches del color seleccionado!", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCoche(++indiceCoche);
				comprobarBotones();
			}
		});
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarCoche(--indiceCoche);
				comprobarBotones();
			}
		});
		actualizar();
		setTitle("Buscar por color");
		btnAccion.setText("Buscar");
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		matricula.setEnabled(false);
		setBounds(100, 100, 450, 300);
	}
	
	/**
	 * Muestra el coche
	 */
	protected void mostrarCoche(int indiceCoche) {
		switch (CochePorColor.get(indiceCoche).getColor()) {
		case PLATA:
			Plata.setSelected(true);
			break;
		case ROJO:
			Rojo.setSelected(true);
			break;
		case AZUL:
			Azul.setSelected(true);
		}
		matricula.setText(CochePorColor.get(indiceCoche).getMatricula());
		comboBoxMarca.setSelectedItem(CochePorColor.get(indiceCoche).getModelo().getMarca());
		comboBoxModelo.setSelectedItem(CochePorColor.get(indiceCoche).getModelo());
	}
	
	
	/**
	 * Comprueba el estado de los botones y los habilita o deshabilita cuando es necesario
	 */
	@Override
	protected void comprobarBotones() {
		if (indiceCoche + 1 >= CochePorColor.size()) {
			btnAdelante.setEnabled(false);
		} else {
			btnAdelante.setEnabled(true);
		}
		if (indiceCoche - 1 == -1) {
			btnAtras.setEnabled(false);
		} else {
			btnAtras.setEnabled(true);
		}
	}
}