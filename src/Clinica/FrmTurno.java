package Clinica;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.util.Calendar;
import java.util.Locale;

import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class FrmTurno extends JDialog {
	private Especialidades E = new Especialidades();
	private AreaMedica A = new AreaMedica();
	private final JPanel contentPanel = new JPanel();
	private JTable table = new AuxTurnos();
//	private JTable table = new JTable();
	private int sem = 0;
	private Paciente P = new Paciente();
	private Turno T = new Turno();
	private JFormattedTextField txtDNI;
	private String fechaTurno = null;
	private String horaInicioTurno;
	private String horaFinTurno;
	private JLabel lblFecha = new JLabel("");
	private AuxTurnos aux = new AuxTurnos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmTurno dialog = new FrmTurno();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrmTurno() {
		setTitle("Turnos");
		setBounds(100, 100, 800, 573);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setEnabled(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 37, 690, 135);
		contentPanel.add(scrollPane);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setRowSelectionAllowed(false);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"a", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"b", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"c", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"d", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"d", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"e", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{"f", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Dia/Hora", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "01", "02", "03", "04", "05"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class, Byte.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(0).setMinWidth(70);
		table.getColumnModel().getColumn(0).setMaxWidth(70);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(23);
		table.getColumnModel().getColumn(1).setMinWidth(23);
		table.getColumnModel().getColumn(1).setMaxWidth(30);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		scrollPane.setViewportView(table);
		
		JScrollBar semana = new JScrollBar();
		semana.setBounds(10, 37, 17, 135);
		contentPanel.add(semana);
		
		JLabel lblNewLabel_3 = new JLabel("DNI:");
		lblNewLabel_3.setBounds(401, 201, 46, 14);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblNewLabel_3);
		
		NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(111111); //valor mínimo
		formatter.setMaximum(999999999); //valor máximo
		formatter.setAllowsInvalid(true);
		formatter.setCommitsOnValidEdit(false);
		txtDNI = new JFormattedTextField(formatter);
		txtDNI.setBounds(457, 198, 138, 20);
		contentPanel.add(txtDNI);
		txtDNI.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(605, 196, 89, 23);
		contentPanel.add(btnBuscar);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre:");
		lblNewLabel_4.setBounds(391, 226, 56, 14);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNombre = new JLabel("");
		lblNombre.setBounds(457, 229, 237, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblNewLabel_5 = new JLabel("Cobertura:");
		lblNewLabel_5.setBounds(365, 251, 82, 14);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblNewLabel_5);
		
		JLabel lblCobertura = new JLabel("");
		lblCobertura.setBounds(457, 254, 237, 14);
		contentPanel.add(lblCobertura);
		
		JButton btnTurno = new JButton("Asignar Turno");
		btnTurno.setEnabled(false);

		btnTurno.setBounds(457, 332, 138, 23);
		contentPanel.add(btnTurno);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(37, 183, 46, 14);
		contentPanel.add(lblNewLabel_6);
		
		lblFecha.setBounds(93, 183, 138, 14);
		contentPanel.add(lblFecha);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setEnabled(false);
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(38, 226, 237, 135);
		contentPanel.add(layeredPane);
		
		JSpinner spHoraInicio = new JSpinner();
		spHoraInicio.setBounds(86, 33, 58, 20);
		layeredPane.add(spHoraInicio);
		spHoraInicio.setModel(new SpinnerNumberModel(8, 0, 23, 1));
		
		JSpinner spMinutoInico = new JSpinner();
		spMinutoInico.setBounds(154, 33, 57, 20);
		layeredPane.add(spMinutoInico);
		spMinutoInico.setModel(new SpinnerNumberModel(0, 0, 50, 10));
		
		JSpinner spHoraFin = new JSpinner();
		spHoraFin.setModel(new SpinnerNumberModel(8, 0, 23, 1));
		spHoraFin.setBounds(86, 64, 58, 20);
		layeredPane.add(spHoraFin);
		
		JSpinner spMinutoFin = new JSpinner();
		spMinutoFin.setModel(new SpinnerNumberModel(9, 0, 59, 10));
		spMinutoFin.setBounds(154, 64, 57, 20);
		layeredPane.add(spMinutoFin);
		
		JLabel lblHoras = new JLabel("Hora Inicio:");
		lblHoras.setBounds(10, 36, 66, 14);
		layeredPane.add(lblHoras);
		lblHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblHoraFin = new JLabel("Hora Fin:");
		lblHoraFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoraFin.setBounds(10, 67, 66, 14);
		layeredPane.add(lblHoraFin);
		
		JLabel lblNewLabel_7 = new JLabel(":");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(144, 36, 13, 14);
		layeredPane.add(lblNewLabel_7);
		
		JLabel label = new JLabel(":");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(144, 67, 13, 14);
		layeredPane.add(label);
		
		DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
		Alinear.setHorizontalAlignment(SwingConstants.CENTER);
		
		limpiarTabla();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JLabel lblNewLabel = new JLabel(" Area ->> ");
		menuBar.add(lblNewLabel);
		
		JComboBox cmbArea = new JComboBox();

		cmbArea.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbArea.setPreferredSize(new Dimension(10, 20));
		menuBar.add(cmbArea);
		cmbArea.setModel(A.completaComboString(A.listadoAreaMedica()));
		cmbArea.setSelectedIndex(-1);
		
		
		JLabel lblNewLabel_1 = new JLabel(" Especialidad ->> ");
		menuBar.add(lblNewLabel_1);
		
		JComboBox cmbEspecialidad = new JComboBox();

		cmbEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbEspecialidad.setPreferredSize(new Dimension(10, 20));
		menuBar.add(cmbEspecialidad);
		
		JLabel lblNewLabel_2 = new JLabel("  ");
		menuBar.add(lblNewLabel_2);
		sumaSemana(E);
		
		spMinutoFin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int d = Integer.parseInt(spMinutoInico.getValue()+"");
				int h = Integer.parseInt(spMinutoFin.getValue()+"");
				int i = Integer.parseInt(spHoraInicio.getValue()+"");
				int f = Integer.parseInt(spHoraFin.getValue()+"");

				if(d>h&&i==f) {
					spHoraFin.setValue(i+1);
				} else if(d<h) {
					spHoraFin.setValue(i);
				}
			}
		});
		
		
		spMinutoInico.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int d = Integer.parseInt(spMinutoInico.getValue()+"");
				spMinutoFin.setValue(d+9);
			}
		});

		spHoraFin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int d = Integer.parseInt(spMinutoInico.getValue()+"");
				int h = Integer.parseInt(spMinutoFin.getValue()+"");
				int i = Integer.parseInt(spHoraInicio.getValue()+"");
				int f = Integer.parseInt(spHoraFin.getValue()+"");
				if(i==f&&d>h) {
					spMinutoFin.setValue(d+9);
				}
			}
		});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10&&table.getSelectedColumn()!=0) {
					int control = Integer.parseInt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) + "");
					int r = 0;
					if(control!=100) {
						if(control>=51) {r = JOptionPane.showConfirmDialog(null, "El horario se encuentra completo, desea dar sobreturno?", "Autorizar sobreturno", JOptionPane.YES_NO_OPTION);}
						if(r==0) {
							fechaTurno = table.getValueAt(table.getSelectedRow(), 0) + " ";
							lblFecha.setText(fechaTurno);
							int h = Integer.parseInt(table.getColumnName(table.getSelectedColumn()));
							spHoraInicio.setModel(new SpinnerNumberModel(h, h, h, 1));
							spHoraFin.setModel(new SpinnerNumberModel(h, h, h+2, 1));
							spMinutoFin.setModel(new SpinnerNumberModel(9, 0, 59, 10));
							spMinutoInico.setValue(0);
							spMinutoInico.requestFocus();
							T.turnosHora(fechaTurno, h);
							if(P.getIdPac()>0) {
								btnTurno.setEnabled(true);
						}}}}}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedColumn()!=0) {
					int control = Integer.parseInt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) + "");
					int r=0;
					if(control!=100) {
						if(arg0.getClickCount()==2){
							if(control>=51) {
								r = JOptionPane.showConfirmDialog(null, "El horario se encuentra completo, desea dar sobreturno?", "Autorizar sobreturno", JOptionPane.YES_NO_OPTION);}
								if(r==0) {
									fechaTurno = table.getValueAt(table.getSelectedRow(), 0) + " ";
									lblFecha.setText(fechaTurno);
									int h = Integer.parseInt(table.getColumnName(table.getSelectedColumn()));
									spHoraInicio.setModel(new SpinnerNumberModel(h, h, h, 1));
									spHoraFin.setModel(new SpinnerNumberModel(h, h, h+2, 1));
									spMinutoInico.setValue(0);
									spMinutoFin.setModel(new SpinnerNumberModel(9, 0, 59, 10));
									spMinutoInico.requestFocus();
									T.turnosHora(fechaTurno, h);
									if(P.getIdPac()>0) {
										btnTurno.setEnabled(true);
						}}}}}}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto = null;
				texto = txtDNI.getText();
				if(txtDNI.getText().length()>5) {
					if(texto.indexOf('.')>=1) {texto = txtDNI.getText().replaceAll("\\.", "");}
					if(texto.indexOf(',')>=1) {texto = txtDNI.getText().replaceAll("\\,", "");}
					P.setDniPac(Integer.parseInt(texto));
					if(P.existeDNI()) {
						P.pacienteConDNI();
						lblNombre.setText(P.getNombrePac());
						lblCobertura.setText(P.getCoberturaPac());
						if(fechaTurno==null) {
							lblFecha.setText("Seleccione fecha para el turno");
						} else {
							btnTurno.setEnabled(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "El numero ingresado no es correcto", "Numero de documento", JOptionPane.ERROR_MESSAGE);
						txtDNI.requestFocus();
					};
				}}
		});
		
		semana.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				sem = semana.getValue();
				diasHorasHabilitado(E);
				sumaSemana(E);

			}
		});

		cmbEspecialidad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()== ItemEvent.SELECTED) {
					limpiarTabla();
					E.setNomEsp(cmbEspecialidad.getSelectedItem()+"");
					E.cargarIdEspPorNombre();
					if(E.getIdEsp()>0) {table.setEnabled(true);};
					diasHorasHabilitado(E);
					semana.setValue(0);
					sumaSemana(E);
					
				}}
		});
		
		cmbArea.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()== ItemEvent.SELECTED) {
					limpiarTabla();
					A.setNombreAreaMedica(cmbArea.getSelectedItem()+"");
					A.cargarAreaConNombre();
					cmbEspecialidad.removeAllItems();
					cmbEspecialidad.setModel(E.ListadoEspecialidadesArea(A.getIdAreaMedica()));
					cmbEspecialidad.setSelectedIndex(-1);
				}}
			});
		
		btnTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				horaInicioTurno = agregaCero(spHoraInicio.getValue()) + ":" + agregaCero(spMinutoInico.getValue()) + ":00";
				horaFinTurno = agregaCero(spHoraFin.getValue()) + ":" + agregaCero(spMinutoFin.getValue()) + ":59";
				T.setIdEspecialidad(E.getIdEsp());
				T.setIdPac(P.getIdPac());
				armadoTurno(T);
				
				int r = JOptionPane.showConfirmDialog(null, "Turno para: " + E.getNomEsp() + "\n\nPaciente: " + P.getNombrePac() + 
						"\t\n\nFecha: " + T.getFechaDesde() + "\nHasta: " + T.getFechaHasta(),"Turno " + A.getNombreAreaMedica(), JOptionPane.YES_NO_OPTION);
				if(r==0) {T.cargarTurno();
				dispose();
				JOptionPane.showMessageDialog(null, "El turno se ingreso correctamente", "Turno aceptado", JOptionPane.INFORMATION_MESSAGE);
				FrmTurno FR = new FrmTurno();
				FR.setVisible(true);
				
				}
			}
		});
		
	}
	
	public void diasHorasHabilitado(Especialidades E) {
		ResultSet rs = E.horasYDiasHabilitados();
		try {
			while (rs.next()) {
				int d = rs.getInt(2);
				int h = rs.getInt(3);
				if(h<6&&d>0) {
					table.setValueAt(0,  d-1,24 + h-5);
				}else if(d>0) {
					table.setValueAt(0, d-1, h-5);
				}}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	
	public void limpiarTabla() {
		for(int x = 0; x<7; x++) {
			for(int y = 1; y < 25; y++) {
				table.setValueAt(100, x, y);
			}}
	}
	
	public void sumaSemana(Especialidades E) {
		T.setIdEspecialidad(E.getIdEsp());
		Calendar c = Calendar.getInstance(); // iniciar calendario con la fecha de hoy
		c.add(Calendar.WEEK_OF_YEAR, sem);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // buscar en la semana de la fecha de hoy el lunes 
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  // formato
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		for (int i = 0; i < 7; i++) { 
			table.setValueAt(df.format(c.getTime()), i, 0);
			if(i==0) {
				T.setFechaDesde(sdf.format(c.getTime())+" 00:00:00");
			} else if(i==6) {
				T.setFechaHasta(sdf.format(c.getTime())+" 23:59:59");
			}
			c.add(Calendar.DATE, 1);
		}
		ResultSet rs = null;
		rs = T.turnosSemana();
		try {
			while(rs.next()) {
				String desde = rs.getString(2);
				String hasta = rs.getString(3);
				aux.turnosTomados(desde, hasta);	
				sumaMinutos(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sumaMinutos(AuxTurnos aux) {
		
		int linea = 0;
		
		for(int x = 0; x < 7; x++) {
			if(aux.fecha.equals(table.getValueAt(x, 0))) { linea = x; x=7;};
		}
		
		if(aux.hora<6) {
			int m = (int) table.getValueAt(linea, aux.hora + 19);
			table.setValueAt(m + aux.sumaminutos,  linea, aux.hora + 19);
		}else {
			int m = (int) table.getValueAt(linea, aux.hora - 5);
			table.setValueAt(m + aux.sumaminutos,  linea, aux.hora - 5);
		}
		
		if(aux.acumulado>0) {
			int h = aux.hora + 1;
				if(h<6) {
					int m = (int) table.getValueAt(linea, h + 19);
					table.setValueAt(m + aux.acumulado,  linea, h + 19);
				}else {
					int m = (int) table.getValueAt(linea, h - 5);
					table.setValueAt(m + aux.acumulado,  linea, h - 5);
				}
			aux.acumulado = 0;
		}
	}


	
	
	
	public void armadoTurno(Turno T) {
		lblFecha.setText(fechaTurno);
		String diasql = null;
		//System.out.println(fechaTurno);
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");	
		try {
			diasql = new java.sql.Date(sdf.parse(fechaTurno).getTime()) + "";
		    //System.out.println("Fecha con el formato java.sql.Date: " + diasql);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		T.setFechaDesde(diasql + " " + horaInicioTurno);
		T.setFechaHasta(diasql + " " + horaFinTurno);
	}
	
	public String agregaCero(Object je) {
		String s = je+"";
		if(s.length()==1) {
			s = "0"+s;
		}
		return s;
	}
}
