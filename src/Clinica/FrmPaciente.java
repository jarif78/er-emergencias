package Clinica;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class FrmPaciente extends JDialog {

	private JPanel contentPane;
	private JFormattedTextField txtDniPac;
	private JTextField txtIdPac;
	private JTextField txtNombrePac;
	private JTextField txtTelPac;
	private JLabel lblAlertaDni = new JLabel("");
	private JLabel lblAlertaNom = new JLabel("");
	private JLabel lblAlertaTel = new JLabel("");
	private JButton btnIngreso = new JButton("Guardar"); 
	private JLabel lblCombo = new JLabel("Obra Social");
	@SuppressWarnings("rawtypes")
	private JComboBox cmbCoberturaPac = new JComboBox();;
	private Paciente paciente = new Paciente();
	private JButton btnBuscar = new JButton("Buscar");
	private boolean ingreso;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPaciente dialog = new FrmPaciente();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmPaciente() {
		setModal(true);

		
		// Armado del formulario
		setBounds(100, 100, 540, 401);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Pacientes");
		menuBar.add(mnNewMenu);
		JMenuItem mntmNewIngresar = new JMenuItem("Ingresar");
		mnNewMenu.add(mntmNewIngresar);
		JMenuItem mntmNewModificar = new JMenuItem("Modificar");
		mnNewMenu.add(mntmNewModificar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Borrar");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				FrmBajaPaciente Fbaja = new FrmBajaPaciente();
				Fbaja.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(67, 39, 47, 14);
		contentPane.add(lblNewLabel);
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(111111); //valor mínimo
		formatter.setMaximum(999999999); //valor máximo
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(false);
		txtDniPac = new JFormattedTextField(formatter);
		txtDniPac.setEnabled(false);
		txtDniPac.setBounds(124, 36, 120, 20);
		contentPane.add(txtDniPac);
		txtDniPac.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(293, 39, 46, 14);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("Nombre y Apellido:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(24, 79, 90, 14);
		contentPane.add(lblNewLabel_2);
		txtNombrePac = new JTextField();
		txtNombrePac.setEnabled(false);
		txtNombrePac.setBounds(124, 76, 311, 20);
		contentPane.add(txtNombrePac);
		txtNombrePac.setColumns(10);
		JLabel lblNewLabel_3 = new JLabel("Telefono:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(68, 117, 46, 14);
		contentPane.add(lblNewLabel_3);
		txtTelPac = new JTextField();
		txtTelPac.setEnabled(false);
		txtTelPac.setBounds(124, 114, 155, 20);
		contentPane.add(txtTelPac);
		txtTelPac.setColumns(10);
		JLabel lblNewLabel_4 = new JLabel("Cobertura:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(61, 157, 53, 14);
		contentPane.add(lblNewLabel_4);
		
		cmbCoberturaPac.setModel(new DefaultComboBoxModel(new String[] {"Obra Social", "Prepaga", "Particular"}));
		
		cmbCoberturaPac.setBounds(124, 154, 155, 20);
		contentPane.add(cmbCoberturaPac);
		btnIngreso.setEnabled(false);
		btnIngreso.setBounds(250, 247, 89, 23);
		contentPane.add(btnIngreso);
		lblAlertaDni.setForeground(Color.RED);
		lblAlertaDni.setBounds(124, 22, 311, 14);
		contentPane.add(lblAlertaDni);
		lblAlertaNom.setForeground(Color.RED);
		lblAlertaNom.setBounds(124, 63, 311, 14);
		contentPane.add(lblAlertaNom);
		lblAlertaTel.setForeground(Color.RED);
		lblAlertaTel.setBounds(124, 101, 311, 14);
		contentPane.add(lblAlertaTel);
		lblCombo.setVisible(false);
		lblCombo.setBounds(186, 157, 46, 14);
		contentPane.add(lblCombo);
		btnBuscar.setVisible(false);
		btnBuscar.setBounds(346, 35, 89, 23);
		contentPane.add(btnBuscar);
		txtIdPac = new JTextField();
		txtIdPac.setEnabled(false);
		txtIdPac.setBounds(349, 36, 86, 20);
		contentPane.add(txtIdPac);
		txtIdPac.setColumns(10);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(371, 247, 89, 23);
		contentPane.add(btnNewButton);

		// Metodos dentro del formulario
		
		// Metodo para guardar la seleccion del combobox en un lbl Cobertura del paciente
        cmbCoberturaPac.addItemListener(event -> {					// Al cambiar la seleccion del combobox
            if (event.getStateChange() == ItemEvent.SELECTED) {
                lblCombo.setText(event.getItem().toString());
            }
        });

		
		
		txtTelPac.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				paciente.setTelefonoPac(txtTelPac.getText());	// Guarda el telefono en objeto paciente
				if (ingreso) activarIngreso();					// Si el form está en modo ingreso 
			}
		});

		
		
		txtDniPac.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String texto = txtDniPac.getText();
				if(txtDniPac.getText().length()>5) {			// Control si el textbox tiene mas de 5 caracteres
					if(texto.indexOf('.')>1) {
						texto = txtDniPac.getText().replaceAll("\\.", "");}
					if(texto.indexOf(',')>1) {
						texto = txtDniPac.getText().replaceAll("\\,", "");}					paciente.setDniPac(Integer.parseInt(texto));						// asigna al objeto paciente el dni
					if (ingreso) {								// Control si ingreso
						if(paciente.existeDNI()) {				// Control si el dni esta ingresado
							alertaDni();
							} else {
							lblAlertaDni.setText("");
							activarIngreso();								
							}
						}
 
				}
			}
		});

		
		txtNombrePac.addFocusListener(new FocusAdapter() {		// Al perder foco txtnombre
			@Override
			public void focusLost(FocusEvent e) {
				paciente.setNombrePac(txtNombrePac.getText());	// Asigno nombre a objeto paciente
				if(ingreso)activarIngreso();					// control si es ingreso
			}
		});
		
		mntmNewModificar.addMouseListener(new MouseAdapter() {	// Seleccion modificar paciente
			@Override
			public void mousePressed(MouseEvent arg0) {
				ingreso = false;
				formParaModificar();
			}
		});
		
		mntmNewIngresar.addMouseListener(new MouseAdapter() { // Seleccion ingresar paciente
			@Override
			public void mousePressed(MouseEvent arg0) {
				ingreso = true;
				formParaIngresar();
				txtDniPac.requestFocus();
				txtDniPac.selectAll();
			}
		});
		
		btnBuscar.addMouseListener(new MouseAdapter() {		// Boton de busqueda para modificar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String texto = txtDniPac.getText();
				if(texto.length()>5) {
					if(texto.indexOf('.')>1) {
						texto = txtDniPac.getText().replaceAll("\\.", "");}
					if(texto.indexOf(',')>1) {
						texto = txtDniPac.getText().replaceAll("\\,", "");}
					paciente.setDniPac(Integer.parseInt(texto));
					if(paciente.existeDNI()) {						// Busqueda de paciente
						ResultSet rs = paciente.pacienteConDNI();
						try {
							if(rs.next()) {									//Copiar los datos al objeto persona
								paciente.setIdPac(rs.getInt(1));
								paciente.setNombrePac(rs.getString(2));
								paciente.setTelefonoPac(rs.getString(4));
								paciente.setCoberturaPac(rs.getString(5));
								paciente.setDniPac(rs.getInt(3));
								cargarPacienteEnForm();						// Copiar los datos al formulario
								btnIngreso.setEnabled(true);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else alertaDni();							// Sino encuentra el DNI
						
				}
			}
		});
	
		
		btnIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ingreso) {											// Verificar si es para ingresar
					paciente.setCoberturaPac(lblCombo.getText());
					if (paciente.darAltaPaciente()) {
						ingresoCorrecto();
					} else {
						lblAlertaDni.setForeground(Color.red);
						lblAlertaDni.setText("El paciente no pudo ser ingresado - Reintentar");
					}} else {
						formCargarenPersona();
						paciente.editarPaciente();
						JOptionPane.showMessageDialog(btnIngreso, "El paciente ha sido modificado correctamente", "Modificar Pacientes", JOptionPane.INFORMATION_MESSAGE);
						formParaModificar();
					}
			}
		});
		
	}
	
	public void activarIngreso() {
		
		if (txtDniPac.getValue() != null&&txtNombrePac.getText().length()>6&&txtTelPac.getText().length()>6) {
			if(paciente.existeDNI()) {
				lblAlertaDni.setForeground(Color.RED);
				lblAlertaDni.setText("El documento ya se encuentra ingresado");
				txtDniPac.requestFocusInWindow();
				txtDniPac.selectAll();
			} else btnIngreso.setEnabled(true);
		}  
		
	}
	
	public void ingresoCorrecto() {
		
		txtDniPac.setEnabled(false);
		txtNombrePac.setEnabled(false);
		txtTelPac.setEnabled(false);
		btnIngreso.setEnabled(false);
		lblAlertaDni.setForeground(Color.black);
		lblAlertaDni.setText("El paciente fue ingresado correctamente");
		ResultSet rs = Dao.consulta("Select idPac FROM Paciente WHERE dniPac = "+ txtDniPac.getValue()+ "");
	    try {
	    	rs.next();
			int nro = rs.getInt (1);
			txtIdPac.setText(nro+"");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cargarPacienteEnForm() {
		lblCombo.setText(paciente.getCoberturaPac());
		cmbCoberturaPac.setSelectedItem(lblCombo.getText());
		lblAlertaDni.setText("Datos obtenidos");
		txtDniPac.setValue(paciente.getDniPac());
		txtNombrePac.setText(paciente.getNombrePac());
		txtIdPac.setText(paciente.getIdPac()+"");
		txtTelPac.setText(paciente.getTelefonoPac());
		
	}
	
	public void alertaDni() {
		lblAlertaDni.setForeground(Color.RED);
		if (ingreso) lblAlertaDni.setText("El documento ya se encuentra ingresado");
		else lblAlertaDni.setText("El documento no existe");
		txtDniPac.requestFocus();
		txtDniPac.selectAll();

	}
	
	
	
	/**
	 * 
	 */
	public void formParaModificar() {
		lblAlertaDni.setForeground(Color.black);
		lblAlertaDni.setText("Ingrese DNI de datos a modificar");
		txtDniPac.setEnabled(true);
		txtNombrePac.setEnabled(true);
		txtTelPac.setEnabled(true);
		btnBuscar.setVisible(true);
		txtIdPac.setVisible(false);
		txtDniPac.setText("");
		txtNombrePac.setText("");
		txtTelPac.setText("");
		txtIdPac.setText("");
		btnIngreso.setEnabled(false);

	}
	
	public void formParaIngresar() {
		txtDniPac.setEnabled(true);
		txtNombrePac.setEnabled(true);
		txtTelPac.setEnabled(true);
		txtDniPac.setText("");
		txtNombrePac.setText("");
		txtTelPac.setText("");
		txtIdPac.setText("");
		lblAlertaDni.setText("");
		btnIngreso.setVisible(true);
		btnIngreso.setEnabled(false);
		txtIdPac.setVisible(true);
		btnBuscar.setVisible(false);

	}
	
	public void formCargarenPersona() {
		paciente.setNombrePac(txtNombrePac.getText());
		paciente.setCoberturaPac(lblCombo.getText());
		paciente.setTelefonoPac(txtTelPac.getText());
		
	}
}
