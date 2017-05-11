package concesionarioGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.Coche;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Baja extends Plantilla {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Baja() {
		setModal(true);
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coche coche = concesionarioCoches.get(matricula.getText());
					if (coche != null) {
						mostrarCoche(coche);
						int i = JOptionPane.showOptionDialog(contentPanel, "¿Está seguro de que desea eliminarlo?",
								"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
								null);

						switch (i) {
						case JOptionPane.YES_OPTION:
							concesionarioCoches.eliminar(matricula.getText());
							clear();
							break;
						}
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(contentPanel, "No se ha podido eliminar." + exception.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAtras.setVisible(false);
		btnAdelante.setVisible(false);
		btnAccion.setText("Borrar");
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		Azul.setEnabled(false);
		Plata.setEnabled(false);
		Rojo.setEnabled(false);
		setTitle("Baja");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

	private void clear() {
		matricula.setText("");
		buttonGroup.clearSelection();
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);
	}

}
