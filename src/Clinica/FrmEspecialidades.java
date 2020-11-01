package Clinica;
import java.time.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.Month;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmEspecialidades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("rawtypes")
	private JComboBox [] cmbDes = new JComboBox[8];
	@SuppressWarnings("rawtypes")
	private JComboBox [] cmbHas = new JComboBox[8];
	private JCheckBox [] chkDia = new JCheckBox[8];
	private Especialidades esp = new Especialidades();
	private	JComboBox cmbEsp = new JComboBox();

	
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

	/**
	 * Create the dialog.
	 */
	public FrmEspecialidades() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		
		JComboBox cmbAreMed = new JComboBox();
		cmbAreMed.setBounds(225, 37, 180, 20);
		panGen.add(cmbAreMed);
		
		JLabel lblMedico = new JLabel("Medico Especialista:");
		lblMedico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedico.setBounds(25, 94, 190, 24);
		panGen.add(lblMedico);
		
		JComboBox cmbMedEsp = new JComboBox();
		cmbMedEsp.setBounds(225, 94, 180, 20);
		panGen.add(cmbMedEsp);
		
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
		panCer.add(jdateDesde);
		
		JDateChooser jdateHasta = new JDateChooser();
		jdateHasta.setBounds(125, 108, 155, 20);
		panCer.add(jdateHasta);
		
		JButton btnAgregarFeriado = new JButton("Inhabilitar");
		btnAgregarFeriado.setBounds(139, 214, 113, 23);
		panCer.add(btnAgregarFeriado);
		cmbEsp.setBounds(207, 23, 195, 20);
		cmbEsp.setEditable(true);
		contentPanel.add(cmbEsp);
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(463, 22, 89, 23);
		contentPanel.add(btnAgregar);
		
		
		chkDia[1] = new JCheckBox("Lunes");
		chkDia[2] = new JCheckBox("Martes");
		chkDia[3] = new JCheckBox("Miercoles");
		chkDia[4]= new JCheckBox("Jueves");
		chkDia[5] = new JCheckBox("Viernes");
		chkDia[6]= new JCheckBox("Sabado");
		chkDia[7]= new JCheckBox("Domingo");
	
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
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esp.setNomEsp((String) cmbEsp.getSelectedItem());
				if(esp.ingEsp()) JOptionPane.showMessageDialog(null, "Especialidad ingresada", "Especialidad", JOptionPane.INFORMATION_MESSAGE); 
					
			}
		});
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Especialidades esp = new Especialidades();
				int r = 0;
				for(int x = 1; x < 8; x++) {
					if(chkDia[x].isSelected()) {
						int d = cmbDes[x].getSelectedIndex();
						int h = cmbHas[x].getSelectedIndex();
						for(int y = 1; y < 25; y++) {
							if(y>= d&& y<=h) {
							esp.setDiaHorEsp(r,x,y);
							r = r+ 1;
							}
						}
					}
				}
			}
		});

		jdateDesde.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if(jdateDesde.getDate().before(FrmMenuPrincipal.fecha)) {
					JOptionPane.showMessageDialog(null, "No se puede inhabilitar fecha anterior a hoy", "Inhabilitar dias", JOptionPane.ERROR_MESSAGE);
					jdateDesde.setDate(new Date());
				}
				jdateHasta.setDate(jdateDesde.getDate());
			}
		});

		btnAgregarFeriado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(jdateHasta.getDate().before(jdateDesde.getDate())) {
					JOptionPane.showMessageDialog(null, "La fecha hasta no puede ser menor que la fecha desde", "Inhabilitar dias", JOptionPane.ERROR_MESSAGE);
					jdateHasta.setDate(jdateDesde.getDate());
				}
			}
		});

	}
	
	@SuppressWarnings("unchecked")
	private void setearJcombo (JComboBox j) {
		j.setModel(new DefaultComboBoxModel(new String[] {"00:00", "01:00","02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"}));
		((JLabel)j.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
	}
}