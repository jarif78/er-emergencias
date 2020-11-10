package Clinica;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

@SuppressWarnings("serial")
public class FrmBajaPaciente extends JDialog {
	private JTable table;
	private JTextField txtFiltroDni;
	private JTextField txtFiltroNombre;
	private JTextField txtIdPac;
	private JTextField txtNombrePac;
	private JTextField txtDniPac;
	private JTextField txtTelPac;
	private Paciente paciente = new Paciente();
	private JButton btnEliminar = new JButton("Eliminar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmBajaPaciente dialog = new FrmBajaPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmBajaPaciente() {
		setModal(true);
		setBounds(100, 100, 889, 585);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 54, 772, 274);
		getContentPane().add(scrollPane);
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "DNI", "Telefono", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		scrollPane.setViewportView(table);
		JButton btnNewButton = new JButton("Filtro");
		btnNewButton.setBounds(610, 10, 89, 23);
		getContentPane().add(btnNewButton);

		NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(111111); //valor mínimo
		formatter.setMaximum(999999999); //valor máximo
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(false);
		txtFiltroDni = new JFormattedTextField(formatter);
		txtFiltroDni.setBounds(52, 11, 148, 20);
		getContentPane().add(txtFiltroDni);
		txtFiltroDni.setColumns(10);
		
		txtFiltroNombre = new JTextField();
		txtFiltroNombre.setBounds(334, 11, 162, 20);
		getContentPane().add(txtFiltroNombre);
		txtFiltroNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setBounds(20, 17, 30, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(282, 14, 60, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtIdPac = new JTextField();
		txtIdPac.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				btnEliminar.setEnabled(true);
			}
		});

		txtIdPac.setEnabled(false);
		txtIdPac.setBounds(102, 385, 86, 20);
		getContentPane().add(txtIdPac);
		txtIdPac.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setBounds(79, 391, 21, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(409, 391, 46, 14);
		getContentPane().add(label);
		
		txtNombrePac = new JTextField();
		txtNombrePac.setColumns(10);
		txtNombrePac.setBounds(465, 385, 314, 20);
		getContentPane().add(txtNombrePac);
		
		
		txtDniPac = new JTextField();
		txtDniPac.setEnabled(false);
		txtDniPac.setColumns(10);
		txtDniPac.setBounds(256, 385, 120, 20);
		getContentPane().add(txtDniPac);
		
		JLabel label_1 = new JLabel("DNI:");
		label_1.setBounds(224, 391, 30, 14);
		getContentPane().add(label_1);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(44, 434, 347, 14);
		getContentPane().add(lblTelefono);
		
		txtTelPac = new JTextField();
		txtTelPac.setColumns(10);
		txtTelPac.setBounds(100, 431, 226, 20);
		getContentPane().add(txtTelPac);
		
		JLabel lblNewLabel_3 = new JLabel("Cobertura:");
		lblNewLabel_3.setBounds(395, 434, 60, 14);
		getContentPane().add(lblNewLabel_3);
		
		JComboBox cmbCoberturaPac = new JComboBox();
		cmbCoberturaPac.setBounds(467, 431, 120, 20);
		getContentPane().add(cmbCoberturaPac);
		cmbCoberturaPac.setModel(new DefaultComboBoxModel(new String[] {"Obra Social", "Prepaga", "Particular"}));

		btnEliminar.setEnabled(false);
		
		btnEliminar.setBounds(585, 487, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(746, 487, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
				if(txtFiltroNombre.getText().length()>5) {
					paciente.setNombrePac(txtFiltroNombre.getText());
					table.setModel(Dao.rellenoJTable(paciente.pacienteConNombre()));
				} else if(txtFiltroDni.getText().length()>5) {
					String texto = txtFiltroDni.getText().replaceAll("\\.", "");
					paciente.setDniPac(Integer.parseInt(texto));
					table.setModel(Dao.rellenoJTable(paciente.pacienteConDNI()));
				} else				
					table.setModel(Dao.rellenoJTable(Paciente.listadoPacientes()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnNewButton, "El filtro no arroja resultados", "Borrar pacientes", JOptionPane.ERROR);
					e.printStackTrace();
				}
				btnEliminar.setEnabled(false);
				paciente.borDatPac();
			}
			
		});
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int r = JOptionPane.showConfirmDialog(btnEliminar, "Desea borrar al paciente: " +paciente.getNombrePac(), "Borrar pacientes", JOptionPane.YES_NO_OPTION);
				if(r==0) {
					if(paciente.eliminarPaciente()) {
						try {
						table.setModel(Dao.rellenoJTable(Paciente.listadoPacientes()));
						} catch (SQLException e) {
				// TODO Auto-generated catch block
							e.printStackTrace();
						}};
				}}
			});

				
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
								
				txtIdPac.setText(table.getValueAt(table.getSelectedRow(), 0)+"");
				paciente.setIdPac(Integer.parseInt(txtIdPac.getText()+""));
				
				txtNombrePac.setText(table.getValueAt(table.getSelectedRow(), 1)+"");
				paciente.setNombrePac(table.getValueAt(table.getSelectedRow(), 1)+"");

				txtDniPac.setText(table.getValueAt(table.getSelectedRow(), 2)+"");
				paciente.setDniPac(Integer.parseInt(txtDniPac.getText()+""));
				
				txtTelPac.setText(table.getValueAt(table.getSelectedRow(), 3)+"");
				paciente.setTelefonoPac(table.getValueAt(table.getSelectedRow(), 3)+"");
				
				cmbCoberturaPac.setSelectedItem(table.getValueAt(table.getSelectedRow(), 4)+"");
				paciente.setTelefonoPac(table.getValueAt(table.getSelectedRow(), 4)+"");
				
				
				
			}
		});

		
		

	}
}
