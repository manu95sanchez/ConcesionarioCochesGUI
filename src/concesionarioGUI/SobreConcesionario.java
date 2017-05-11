package concesionarioGUI;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SobreConcesionario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public SobreConcesionario() {
		setTitle("Sobre concesionario");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 388, 232);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Concesionario IES Gran Capitan."
				+ " Realizado por Manuel Sanchez Blanco de 1ºDAW");
		textArea.setBounds(0, 0, 382, 153);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 382, 156);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnOk.setBounds(233, 173, 91, 23);
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		getContentPane().add(btnOk);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
