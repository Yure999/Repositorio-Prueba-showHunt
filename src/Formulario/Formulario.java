package Formulario;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.ListSelectionModel;

public class Formulario extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JComboBox comboBox;
	private JCheckBox chckbxSeleccionado;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField pwdPassword;
	private JSpinner spinner;
	private JList list;

	/**
	 * Create the application.
	 */
	@SuppressWarnings("unchecked")
	public Formulario() {
		setBounds(100, 100, 563, 327);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		JLabel lblLabel = new JLabel("Busqueda:");
		lblLabel.setBounds(10, 11, 136, 23);
		getContentPane().add(lblLabel);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().isEmpty())
					comboBox.addItem(textField.getText());
				if (chckbxSeleccionado.isSelected()) {
					System.out.println("Esta Seleccionado");
					textField.setEnabled(false);
				} else
					System.out.println("No seleccionado");
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println(pwdPassword.getPassword());
			}
			});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(list.getSelectedValuesList());
			}
			});
		btnBuscar.setBounds(426, 11, 89, 23);
		getContentPane().add(btnBuscar);

		comboBox = new JComboBox();
		comboBox.addItem("Lugar");
		comboBox.addItem("Comida");
		comboBox.addItem("Pago");
		comboBox.setBounds(340, 11, 76, 22);
		getContentPane().add(comboBox);
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola");
				btnBuscar.setEnabled(true);
			}
		});

		textField.setBounds(66, 12, 276, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		chckbxSeleccionado = new JCheckBox("Seleccionado");
		chckbxSeleccionado.setBounds(395, 61, 128, 23);
		getContentPane().add(chckbxSeleccionado);

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String texto = ((JRadioButton) e.getSource()).getText();
				if (e.getStateChange() == ItemEvent.DESELECTED)
					System.out.format("Botón %s deseleccionado.\n", texto);
				else if (e.getStateChange() == ItemEvent.SELECTED)
					System.out.format("Botón %s seleccionado.\n", texto);
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(20, 41, 109, 23);
		getContentPane().add(rdbtnMale);

		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String texto = ((JRadioButton) e.getSource()).getText();
				if (e.getStateChange() == ItemEvent.DESELECTED)
					System.out.format("Botón %s deseleccionado.\n", texto);
				else if (e.getStateChange() == ItemEvent.SELECTED)
					System.out.format("Botón %s seleccionado.\n", texto);
			}
		});

		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(20, 67, 109, 23);
		getContentPane().add(rdbtnFemale);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setEchoChar('*');
		pwdPassword.setBounds(20, 123, 128, 19);
		getContentPane().add(pwdPassword);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 200, 1));
		spinner.setBounds(20, 166, 39, 30);
		getContentPane().add(spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(395, 123, 109, 103);
		getContentPane().add(scrollPane);
		
		list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 17));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Pipo", "Es", "Un", "Buen", "Perro"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);

	}
}