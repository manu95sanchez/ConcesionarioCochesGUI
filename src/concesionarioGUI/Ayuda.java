package concesionarioGUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ayuda extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setModal(true);
		setResizable(false);
		setTitle("Ayuda");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 442, 219);
		getContentPane().add(scrollPane);

		JTextArea textAreaAbout = new JTextArea();
		textAreaAbout.setEditable(false);
		scrollPane.setViewportView(textAreaAbout);
		textAreaAbout.setText("Ayuda Concesionario");

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnOk.setBounds(293, 239, 91, 23);
		getContentPane().add(btnOk);
	}

}