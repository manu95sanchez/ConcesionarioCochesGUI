package concesionarioGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.Coche;

public class BuscarPorMatricula extends Plantilla {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */

	public BuscarPorMatricula() {
		setModal(true);
		btnAtras.setVisible(false);
		btnAdelante.setVisible(false);
		btnAccion.setText("Buscar");
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		Azul.setEnabled(false);
		Rojo.setEnabled(false);
		Plata.setEnabled(false);
		setTitle("Buscar por matricula");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		btnAccion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Coche coche = concesionarioCoches.get(matricula.getText());
					if (coche != null);
					
					mostrarCoche(coche);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(contentPanel, "No existe ningún coche con esa matricula.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}
}