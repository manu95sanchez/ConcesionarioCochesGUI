package concesionarioGUI;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import concesionarioCoches.Modelo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alta extends Plantilla {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Alta() {
		setModal(true);
		setResizable(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAtras.setVisible(false);
		btnAdelante.setVisible(false);
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annadir();
			}
		});
		setTitle("Alta");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
	}

	private void annadir() {
		try {
			if (concesionarioCoches.annadir(matricula.getText(), getColor(),
					(Modelo) comboBoxModelo.getSelectedItem())) {
				JOptionPane.showMessageDialog(getContentPane(), "El coche se ha añadido con exito");
				System.out.println(concesionarioCoches);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), "No ha sido posible añadir el coche" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(concesionarioCoches);
		}
	}
}
