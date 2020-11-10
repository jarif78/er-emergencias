package Clinica;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;

import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings({"serial", "rawtypes", "unused"})
public class FrmEspecialidades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox [] cmbDes = new JComboBox[8];
	private JComboBox [] cmbHas = new JComboBox[8];
	private JCheckBox [] chkDia = new JCheckBox[8];
	private Especialidades esp = new Especialidades();
	private	JComboBox cmbEsp = new JComboBox();
	private int ID = 0;
	private String especialidad;
	private byte tab = 5;
	private JComboBox cmbDiasInhabilitados = new JComboBox();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JButton btnEliminar = new JButton("Eliminar Especializacion");
	private JComboBox cmbAreMed = new JComboBox();
	private JComboBox cmbMedEsp = new JComboBox();
	private Medico m = new Medico();
	private AreaMedica a = new AreaMedica();
	private JCheckBox chckbxNewCheckBox = new JCheckBox("Asignar Medico especialidad indistinta");


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmEspecialidades dialog = new FrmEspecialidades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings({ "unchecked"})
	public FrmEspecialidades() throws ParseException  {
		setModal(true);
		Especialidades esp = new Especialidades();
		Date fecha = new Date();
		Date fechaHoy;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		fechaHoy = formatter.parse(formatter.format(fecha));
		tabbedPane.setEnabled(false);
		tabbedPane.setSelectedIndex(-1);
		tabbedPane.setBounds(31, 68, 590, 363);
		contentPanel.add(tabbedPane);
		JPanel panGen = new JPanel();
		tabbedPane.addTab("General", null, panGen, null);
		panGen.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Corresponde al Area Medica:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(25, 37, 190, 24);
		panGen.add(lblNewLabel_2);
		
		cmbAreMed.setBounds(225, 37, 180, 20);
		panGen.add(cmbAreMed);
		
		JLabel lblMedico = new JLabel("Medico Especialista:");
		lblMedico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedico.setBounds(25, 94, 190, 24);
		panGen.add(lblMedico);
		cmbMedEsp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		
		cmbMedEsp.setBounds(225, 94, 180, 20);
		panGen.add(cmbMedEsp);
		
		JButton btnGuardarGral = new JButton("Guardar");

		btnGuardarGral.setBounds(453, 273, 89, 23);
		panGen.add(btnGuardarGral);
		
		chckbxNewCheckBox.setVerticalAlignment(SwingConstants.TOP);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxNewCheckBox.setBounds(117, 125, 288, 24);
		panGen.add(chckbxNewCheckBox);
		
		JPanel PanHora = new JPanel();
		tabbedPane.addTab("Dias y horarios", null, PanHora, null);
		PanHora.setLayout(null);

		setBounds(100, 100, 678, 541);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Nombre de Especializacion:");
		lblNewLabel.setBounds(31, 26, 166, 14);
		contentPanel.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("Dias de Atencion:");
		lblNewLabel_1.setBounds(31, 20, 104, 14);
		PanHora.add(lblNewLabel_1);
		JLabel lblHorarioDesde = new JLabel("Horario desde:");
		lblHorarioDesde.setBounds(176, 20, 97, 14);
		PanHora.add(lblHorarioDesde);
		JLabel lblHorarioHasta = new JLabel("Horario hasta:");
		lblHorarioHasta.setBounds(305, 20, 97, 14);
		PanHora.add(lblHorarioHasta);
		
		JButton btnAgrHor = new JButton("Habilitar");
		btnAgrHor.setBounds(467, 286, 89, 23);
		PanHora.add(btnAgrHor);
		
		JPanel panCer = new JPanel();
		tabbedPane.addTab("Fechas Sin atencion", null, panCer, null);
		panCer.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Desde:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(21, 57, 74, 24);
		panCer.add(lblNewLabel_3);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHasta.setBounds(21, 108, 74, 24);
		panCer.add(lblHasta);
		JDateChooser jdateDesde = new JDateChooser();
		jdateDesde.setBounds(125, 57, 155, 20);
		jdateDesde.setDate(new Date());
		jdateDesde.setMinSelectableDate(fechaHoy);
		panCer.add(jdateDesde);
		JDateChooser jdateHasta = new JDateChooser();
		jdateHasta.setBounds(125, 108, 155, 20);
		jdateHasta.setMinSelectableDate(fechaHoy);
		panCer.add(jdateHasta);
		JButton btnAgregarFeriado = new JButton("Inhabilitar");
		btnAgregarFeriado.setBounds(99, 205, 113, 23);
		panCer.add(btnAgregarFeriado);
		cmbDiasInhabilitados.setBounds(362, 59, 170, 20);
		panCer.add(cmbDiasInhabilitados);
		JButton btnHabilitar = new JButton("Habilitar");
		btnHabilitar.setBounds(393, 205, 113, 23);
		panCer.add(btnHabilitar);
		cmbEsp.setBounds(207, 23, 195, 20);
		cmbEsp.setEditable(true);
		cmbEsp.setModel(esp.LisEsp());
		contentPanel.add(cmbEsp);
		especialidad = (String) cmbEsp.getSelectedItem();
		esp.setNomEsp(especialidad);
		esp.cargarIdEspPorNombre();
		ID = esp.getIdEsp();
		JButton btnAgrEsp = new JButton("Agregar");
		btnAgrEsp.setBounds(463, 22, 89, 23);
		contentPanel.add(btnAgrEsp);

		
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(41, 442, 195, 23);
		contentPanel.add(btnEliminar);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(532, 442, 89, 23);
		contentPanel.add(btnNewButton);
		chkDia[1] = new JCheckBox("Lunes");
		chkDia[2] = new JCheckBox("Martes");
		chkDia[3] = new JCheckBox("Miercoles");
		chkDia[4] = new JCheckBox("Jueves");
		chkDia[5] = new JCheckBox("Viernes");
		chkDia[6] = new JCheckBox("Sabado");
		chkDia[7] = new JCheckBox("Domingo");
		for (byte x = 1; x<8; x++) {
			cmbDes[x] = new JComboBox();				// Crear y rellenar 7 combobox hora desde
			int a = 10 + x*40;							// posicion altura
			setearJcombo(cmbDes[x]);					// relleno
			cmbDes[x].setBounds(174, a, 97, 20);		// colcocar en lugar
			PanHora.add(cmbDes[x]);						// agregar al panel
			cmbDes[x].setSelectedIndex(8);
			cmbHas[x] = new JComboBox();				// Crear y rellenar 7 combobox hora hasta
			setearJcombo(cmbHas[x]);					// relleno
			cmbHas[x].setBounds(305, a, 97, 20);
			PanHora.add(cmbHas[x]);
			cmbHas[x].setSelectedIndex(18);
			chkDia[x].setBounds(31, a, 97, 23);
			PanHora.add(chkDia[x]);
		}
		
		cmbMedEsp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					m.setNombreMedico(cmbMedEsp.getSelectedItem()+"");
					m.cargarMedicoConNombre();
					esp.setIdMed(m.getIdMed());
				}
			}
		});

		cmbAreMed.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					a.setNombreAreaMedica(cmbAreMed.getSelectedItem()+"");
					a.cargarAreaConNombre();
					
				}
			}
		});

		
		btnGuardarGral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if(a.getIdAreaMedica()!=0&&a.getIdAreaMedica()!=esp.getIdAreaMedica()) {
					esp.setIdAreaMedica(a.getIdAreaMedica());
					esp.guardarIDAreaMedica();
				}
				if(m.getIdMed()!=0) {
					esp.guardarIdMedico();
				}
			}
		});
		
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(chckbxNewCheckBox.isSelected()) {
					cmbMedEsp.removeAllItems();
					cmbMedEsp.setModel(m.completaComboString(m.listadoNombreMedicos()));
				} else {
					cmbMedEsp.removeAllItems();
					cmbMedEsp.setModel(m.completaComboString(m.listadoMedicoPorEspecializacion(especialidad)));
				}
				cmbMedEsp.setSelectedIndex(-1);
			}
		});


		btnHabilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDiasInhabilitados.getSelectedIndex()>=0) {
					java.sql.Date d = (java.sql.Date) cmbDiasInhabilitados.getSelectedItem();
					esp.habilitarDias(d);
					cargarForm(esp, m);
				}}
		});
		
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tab != tabbedPane.getSelectedIndex()) {
					tab = (byte) tabbedPane.getSelectedIndex();
					if(tab==1) {
						}
				} else if(tab==2) {
				}
			}
		});

		cmbEsp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					if(especialidad==(String) cmbEsp.getSelectedItem()) {
					} else {
						cargarForm(esp, m);
						if(ID!=0) {
						} else {
							btnEliminar.setEnabled(false);
							tabbedPane.setEnabled(false);
						}
					}
				}}
		});
		
		btnAgrEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				especialidad = (String) cmbEsp.getSelectedItem();
				if(especialidad.length()>5) {
					esp.setNomEsp(especialidad);
					btnEliminar.setEnabled(false);
					if(esp.exiEsp()) {
						btnAgrEsp.setText("Modificar");
					} else {
						if(esp.AgregarEspecialidad()) {
							JOptionPane.showMessageDialog(btnAgrEsp, "La especialidad " + especialidad + " se ingreso correctamente.", "Especialidades", JOptionPane.INFORMATION_MESSAGE);
							reiniciarForm();
						};
					}
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r = JOptionPane.showConfirmDialog(btnEliminar, "Desea borrar la especializacion: " +esp.getNomEsp(), "Especializacion", JOptionPane.YES_NO_OPTION);
				if(r==0) {
					esp.eliminarEspecializacion();
					JOptionPane.showMessageDialog(null, "La especialidad " + especialidad + " se elimino correctamente.", "Especialidades", JOptionPane.INFORMATION_MESSAGE);
					reiniciarForm();
				}
				
			}
		});
		
		btnAgrHor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int r = 0;
				for(int x = 1; x < 8; x++) {
					if(chkDia[x].isSelected()) {
						int d = cmbDes[x].getSelectedIndex();
						int h = cmbHas[x].getSelectedIndex();
						for(int y = 0; y < 25; y++) {
							if(y>= d&& y<=h) {
							esp.setDiaHorEsp(r,x,y);
							//System.out.println("linea: " + r +" - dia: " + x + " - hora: " + y );
							r = r+ 1;
							}
						}
					}
				}
				esp.setDiaHorEsp2();
				cargarForm(esp, m);
			}
		});

		jdateDesde.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if (jdateDesde.getDate().before(fechaHoy)) {
					JOptionPane.showMessageDialog(null, "No se puede inhabilitar fecha anterior a hoy", "Inhabilitar dias", JOptionPane.ERROR_MESSAGE);
					jdateDesde.setDate(new Date());
				}	jdateHasta.setDate(jdateDesde.getDate());
			}
		});

		btnAgregarFeriado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jdateHasta.getDate().before(jdateDesde.getDate())) {
					JOptionPane.showMessageDialog(null, "La fecha hasta no puede ser menor que la fecha desde", "Inhabilitar dias", JOptionPane.ERROR_MESSAGE);
					jdateHasta.setDate(jdateDesde.getDate());
					}
				sumarDiaFecha(jdateDesde.getDate(), jdateHasta.getDate(), esp);
				cargarForm(esp, m);
				//System.out.println(jdateHasta.getDate());
			}
		});
		
	}
	
	public void completarCampos() {
	}
	@SuppressWarnings("unchecked")
	private void setearJcombo (JComboBox j) {
		j.setModel(new DefaultComboBoxModel(new String[] {"00:00", "01:00","02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"}));
		((JLabel)j.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	public void limpiarHoras() {
		for(byte x = 1; x<8; x++) {
			chkDia[x].setSelected(false);
			cmbDes[x].setSelectedIndex(8);
			cmbHas[x].setSelectedIndex(18);
		}
		cmbDiasInhabilitados.removeAllItems();
	}
	
	public void reiniciarForm(){
		try {
			dispose();
			FrmEspecialidades a = new FrmEspecialidades();
			a.setVisible(true);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void cargarForm(Especialidades esp, Medico m) {
		chckbxNewCheckBox.setSelected(false);
		especialidad= (String) cmbEsp.getSelectedItem();
		esp.setNomEsp(especialidad);
		esp.cargarIdEspPorNombre();
		ID = esp.getIdEsp();
		if(ID!=0) {
			if(esp.getIdMed()==0) {
				cmbMedEsp.removeAllItems();
				cmbMedEsp.setModel(m.completaComboString(m.listadoMedicoPorEspecializacion(especialidad)));
				cmbMedEsp.setSelectedIndex(-1);
			}else {
				cmbMedEsp.removeAllItems();
				m.setIdMed(esp.getIdMed());
				m.cargarMedicoConId();
				cmbMedEsp.addItem(m.getNombreMedico());
				esp.setIdMed(m.getIdMed());
			}
			
			if(esp.getIdAreaMedica()==0) {
				cmbAreMed.removeAllItems();
				cmbAreMed.setModel(a.completaComboString(a.listadoAreaMedica()));
				cmbAreMed.setSelectedIndex(-1);
			}else {
				cmbAreMed.removeAllItems();
				a.setIdAreaMedica(esp.getIdAreaMedica());
				a.cargarAreaConID();
				cmbAreMed.addItem(a.getNombreAreaMedica());
				esp.setIdAreaMedica(a.getIdAreaMedica());
			}
				
			limpiarHoras();
			esp.carDiaHorEsp();
			btnEliminar.setEnabled(true);
			tabbedPane.setEnabled(true);
			cmbDiasInhabilitados.setModel(esp.listadoDiasInhabilitados());
			int [] a = new int [2];
			for(int x = 1; x<8; x++) {
				a = esp.devolverHorario(x);
				if(a[1]!=0) {
					chkDia[x].setSelected(true);
					cmbDes[x].setSelectedIndex(a[0]);
					cmbHas[x].setSelectedIndex(a[1]);
			}}
			esp.limpiarDiasHoras();
		}
	}
	
	
	public void sumarDiaFecha(Date desde, Date hasta, Especialidades esp){
	      Calendar calendar1 = Calendar.getInstance();
	      calendar1.setTime(desde); // Configuramos la fecha que se recibe
	      calendar1.set(Calendar.HOUR_OF_DAY, 0);
	      calendar1.set(Calendar.MINUTE, 0);
	      calendar1.set(Calendar.SECOND, 0);
	      calendar1.set(Calendar.MILLISECOND, 0);
	      Calendar calendar2 = Calendar.getInstance();
	      calendar2.setTime(hasta); // Configuramos la fecha que se recibe
	      calendar2.set(Calendar.HOUR_OF_DAY, 0);
	      calendar2.set(Calendar.MINUTE, 0);
	      calendar2.set(Calendar.SECOND, 0);
	      calendar2.set(Calendar.MILLISECOND, 0);
	      esp.setIdEsp(ID);
	      esp.inhabilitarDias(calendar1);
	      while (calendar1.compareTo(calendar2) < 0) {
	    	  calendar1.add(Calendar.DAY_OF_MONTH, 1); // suma un día al calendario 1
	          esp.inhabilitarDias(calendar1);
	      }}
}
