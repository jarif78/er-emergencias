package Clinica;


import java.awt.EventQueue;


import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class FormMedico extends JDialog {

	private Medico medico = new Medico();
	private JTable table;
	private JTextField txtNombreMedico= new JTextField();
	private JTextField txtDniMedico = new JTextField();
	private JTextField txtTelMedico= new JTextField();
	@SuppressWarnings("rawtypes")
	private JComboBox cmbEspecializacion = new JComboBox();


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMedico dialog = new FormMedico();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FormMedico() {
		setModal(true);
		//frmProfesionales = new JFrame();
		setTitle("Profesionales");
		setBounds(100, 100, 812, 487);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 90, 736, 258);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Id Medico", "Nombre y Apellido", "DNI", "Especialidad", "Telefono"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		try {
			table.setModel(Dao.rellenoJTable(medico.listadoMedicos()));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		JLabel lblNewLabel = new JLabel("Nombre y apellido:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 11, 142, 25);
		getContentPane().add(lblNewLabel);
			
		
		txtNombreMedico.setBounds(162, 13, 234, 20);
		getContentPane().add(txtNombreMedico);
		txtNombreMedico.setColumns(10);
			
		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(421, 16, 73, 14);
		getContentPane().add(lblNewLabel_1);

		NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(new Integer(111111)); //valor mínimo
		formatter.setMaximum(new Integer(999999999)); //valor máximo
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(false);
		

		//InternationalFormatter formato = new InternationalFormatter();
		//formato.setMaximum(new Integer(999999999));
		//formato.setMinimum(new Integer(111111));
		//formato.setAllowsInvalid(true);
		//formato.setCommitsOnValidEdit(true);
		txtDniMedico = new JFormattedTextField(formatter);
		
		
		

		txtDniMedico.setBounds(504, 13, 133, 20);
		getContentPane().add(txtDniMedico);
		txtDniMedico.setColumns(10);
			
		JLabel lblEspecializacion = new JLabel("Especializacion:");
		lblEspecializacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspecializacion.setBounds(10, 42, 142, 25);
		getContentPane().add(lblEspecializacion);
			
		cmbEspecializacion.setBounds(162, 44, 234, 20);
		getContentPane().add(cmbEspecializacion);
		Especialidades e = new Especialidades();
		cmbEspecializacion.setModel(e.LisEsp());
		cmbEspecializacion.setSelectedIndex(1);
		cmbEspecializacion.removeItemAt(0);
			
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(421, 47, 73, 14);
		getContentPane().add(lblTelefono);

			
		txtTelMedico.setBounds(504, 44, 133, 20);
		getContentPane().add(txtTelMedico);
		txtTelMedico.setColumns(10);
			
		JButton btnNewButton = new JButton("Guardar");

		btnNewButton.setBounds(666, 12, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(548, 387, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");

		btnLimpiar.setBounds(667, 43, 89, 23);
		getContentPane().add(btnLimpiar);
		
		JButton btnSalir = new JButton("Cerrar");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();				
			}
		});

		btnSalir.setBounds(667, 387, 89, 23);
		getContentPane().add(btnSalir);


		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(241);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setPreferredWidth(109);
		table.getColumnModel().getColumn(4).setPreferredWidth(113);
		medico.setEspecialidadMed(cmbEspecializacion.getSelectedItem()+"");

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				String a = table.getValueAt(table.getSelectedRow(), 0) + "";
				medico.setIdMed(Integer.parseInt(a));
				//medico.cargarMedicoConId();
				medico.setNombreMedico(table.getValueAt(table.getSelectedRow(), 1) + "");
				medico.setDniMedico(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2) + ""));
				medico.setEspecialidadMed(table.getValueAt(table.getSelectedRow(), 3) + "");
				medico.setTelMedico(table.getValueAt(table.getSelectedRow(), 4) + "");
				txtNombreMedico.setText(medico.getNombreMedico());
				txtDniMedico.setText(medico.getDniMedico()+"");
				cmbEspecializacion.setSelectedItem(medico.getEspecialidadMed());
				txtTelMedico.setText(medico.getTelMedico());

			}
		});
		
		
		txtNombreMedico.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtNombreMedico.getText().trim().length()>5) {			// trim sirve para que no completen espacios con spacebar
					medico.setNombreMedico(txtNombreMedico.getText());
				}
			}
		});
		
		txtDniMedico.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtDniMedico.getText().trim().length()>5) {			// trim sirve para que no completen espacios con spacebar
					String dni = txtDniMedico.getText();
					if(dni.indexOf('.')>1) {dni = dni.replaceAll("\\.", "");}
					if(dni.indexOf(',')>1) {dni = dni.replaceAll("\\,", "");}
						try {
							medico.setDniMedico(Integer.parseInt(dni));						// asigna al objeto paciente el dni
						} catch (Exception e1){
							JOptionPane.showMessageDialog(null, "El numero ingresado no es correcto", "Numero de documento", JOptionPane.INFORMATION_MESSAGE);
							txtDniMedico.requestFocus();
						} 
				}				
			} 
		});

		
		cmbEspecializacion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				medico.setEspecialidadMed(cmbEspecializacion.getSelectedItem()+"");
			}
		});

		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarForm();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(camposCompletos()) {
					if(medico.existeDniMedico()) {
						medico.modificarDatosMedico();
					} else {
						medico.setEspecialidadMed(cmbEspecializacion.getSelectedItem()+"");
						medico.agregarMedico();
					}
					try {
						table.setModel(Dao.rellenoJTable(medico.listadoMedicos()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				limpiarForm();
			}
		});
		
		txtTelMedico.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtTelMedico.getText().trim().length()>4) {
					medico.setTelMedico(txtTelMedico.getText());
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r = JOptionPane.showConfirmDialog(btnEliminar, "Desea borrar el medico: " +medico.getNombreMedico(), "Medico", JOptionPane.YES_NO_OPTION);
				if(r==0) {
					medico.eliminarMedico();
					JOptionPane.showMessageDialog(null, "El medico " + medico.getNombreMedico() + " se borro correctamente.", "Medico", JOptionPane.INFORMATION_MESSAGE);
					try {
						table.setModel(Dao.rellenoJTable(medico.listadoMedicos()));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				limpiarForm();
			}
		});
		
		
		

	}
	
	
	
	public boolean camposCompletos() {
		boolean r = false;
		int a = txtNombreMedico.getText().trim().length();
		int b = txtDniMedico.getText().length();
		int c = txtTelMedico.getText().length();
		if(a>5&&b>5&&c>5) {
			r = true;
		}
		return r;
	}
	
	public void limpiarForm() {
		txtNombreMedico.setText("");
		txtDniMedico.setText("");
		txtTelMedico.setText("");
		cmbEspecializacion.setSelectedIndex(0);
	}
}
