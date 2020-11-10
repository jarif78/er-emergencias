package Clinica;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmAreaMedica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AreaMedica area = new AreaMedica();
	private Especialidades especialidadAgregar = new Especialidades();
	private Especialidades especialidadBorrar = new Especialidades();
	private Medico medico = new Medico();
	private boolean agregar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmAreaMedica dialog = new FrmAreaMedica();
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
	public FrmAreaMedica() {
		setBounds(100, 100, 583, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de Area Medica:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(26, 38, 162, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblAgregarEspecialidad = new JLabel("Agregar Especialidad:");
		lblAgregarEspecialidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAgregarEspecialidad.setBounds(26, 78, 162, 23);
		contentPanel.add(lblAgregarEspecialidad);
		
		JLabel lblEliminarEspecialidad = new JLabel("Eliminar Especialidad:");
		lblEliminarEspecialidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEliminarEspecialidad.setBounds(26, 118, 162, 23);
		contentPanel.add(lblEliminarEspecialidad);
		
		JComboBox cmbAgregarEspecialidad = new JComboBox();

		cmbAgregarEspecialidad.setBounds(198, 79, 248, 20);

		contentPanel.add(cmbAgregarEspecialidad);
		
		JComboBox cmbEliminarEspecialidad = new JComboBox();
		cmbEliminarEspecialidad.setBounds(198, 119, 248, 20);
		contentPanel.add(cmbEliminarEspecialidad);
		
		JLabel lblMedicoCoordinador = new JLabel("Medico Coordinador:");
		lblMedicoCoordinador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedicoCoordinador.setBounds(26, 158, 162, 23);
		contentPanel.add(lblMedicoCoordinador);
		
		JComboBox cmbMedicoCoordinador = new JComboBox();

		cmbMedicoCoordinador.setBounds(198, 159, 248, 20);
		contentPanel.add(cmbMedicoCoordinador);
		
		JButton btnGuardar = new JButton("Guardar");

		btnGuardar.setBounds(84, 238, 89, 23);
		contentPanel.add(btnGuardar);
		
		JButton btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(361, 238, 89, 23);
		contentPanel.add(btnNewButton_1);
		
		JComboBox cmbAreaMedica = new JComboBox();
		cmbAreaMedica.setEditable(true);
		cmbAreaMedica.setBounds(198, 39, 248, 20);
		contentPanel.add(cmbAreaMedica);
		
		cmbAreaMedica.setModel(area.completaComboString(area.listadoAreaMedica()));
		cmbAgregarEspecialidad.setModel(area.completaComboString(especialidadAgregar.especialidadSinArea()));
		cmbEliminarEspecialidad.setModel(area.completaComboString(especialidadBorrar.especialidadConArea(area.getIdAreaMedica())));
		cmbMedicoCoordinador.setModel(area.completaComboString(medico.listadoNombreMedicos()));
		
		cmbAreaMedica.setSelectedIndex(-1);
		cmbAgregarEspecialidad.setSelectedIndex(-1);
		cmbEliminarEspecialidad.setSelectedIndex(-1);
		cmbMedicoCoordinador.setSelectedIndex(-1);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(226, 238, 89, 23);
		contentPanel.add(btnEliminar);

		
		
		
		cmbAreaMedica.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String nombreArea = cmbAreaMedica.getSelectedItem()+"";
				if(nombreArea.trim().length()>5&&arg0.getStateChange() == ItemEvent.SELECTED) {
					area.setNombreAreaMedica(nombreArea);
					if(area.existeArea()) {
						agregar = false;
						cmbAgregarEspecialidad.setSelectedIndex(-1);
						cmbEliminarEspecialidad.setSelectedIndex(-1);
						medico.setIdMed(area.getIdMedicoCoordinador());
						medico.cargarMedicoConId();
						cmbMedicoCoordinador.setSelectedItem(medico.getNombreMedico());;
					} else if (arg0.getStateChange() == ItemEvent.SELECTED) {
						agregar = true;
						cmbMedicoCoordinador.setSelectedIndex(-1);
					}
					cmbEliminarEspecialidad.setModel(area.completaComboString(especialidadBorrar.especialidadConArea(area.getIdAreaMedica())));
					cmbAgregarEspecialidad.setModel(area.completaComboString(especialidadAgregar.especialidadSinArea()));
					cmbEliminarEspecialidad.setSelectedIndex(-1);
					cmbAgregarEspecialidad.setSelectedIndex(-1);

				}
			}
		});
		
		cmbAgregarEspecialidad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbAgregarEspecialidad.getSelectedIndex()>-1&&e.getStateChange() == ItemEvent.SELECTED) {
					especialidadAgregar.setNomEsp(cmbAgregarEspecialidad.getSelectedItem()+"");
					especialidadAgregar.cargarIdEspPorNombre();
					especialidadAgregar.setIdAreaMedica(area.getIdAreaMedica());
				}
			}
		});
		
		cmbEliminarEspecialidad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbEliminarEspecialidad.getSelectedIndex()>-1&&e.getStateChange() == ItemEvent.SELECTED) {
					especialidadBorrar.setNomEsp(cmbEliminarEspecialidad.getSelectedItem()+"");
					especialidadBorrar.cargarIdEspPorNombre();
					especialidadBorrar.setIdAreaMedica(area.getIdAreaMedica());
					
				}
			}
		});

		cmbMedicoCoordinador.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbMedicoCoordinador.getSelectedIndex()>-1&&e.getStateChange() == ItemEvent.SELECTED) {
					medico.setNombreMedico(cmbMedicoCoordinador.getSelectedItem()+"");
					medico.cargarMedicoConNombre();
				}
			}
		});		
		
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(agregar&&cmbMedicoCoordinador.getSelectedIndex()>-1) {
					area.agregarArea(medico);
				} else if(agregar){
					area.agregarArea();
				}
				if(area.getIdAreaMedica()>0&&cmbAgregarEspecialidad.getSelectedIndex()>-1) {
					especialidadAgregar.guardarIDAreaMedica();
				}
				if(area.getIdAreaMedica()>0&&cmbEliminarEspecialidad.getSelectedIndex()>-1) {
					especialidadBorrar.borrarAreaMedica();
				}
				cmbMedicoCoordinador.setModel(area.completaComboString(medico.listadoNombreMedicos()));
				cmbAreaMedica.setModel(area.completaComboString(area.listadoAreaMedica()));
				cmbEliminarEspecialidad.setModel(area.completaComboString(especialidadBorrar.especialidadConArea(area.getIdAreaMedica())));
				cmbAgregarEspecialidad.setModel(area.completaComboString(especialidadAgregar.especialidadSinArea()));
				cmbEliminarEspecialidad.setSelectedIndex(-1);
				cmbAgregarEspecialidad.setSelectedIndex(-1);
				cmbAreaMedica.setSelectedIndex(-1);
				cmbMedicoCoordinador.setSelectedIndex(-1);
				JOptionPane.showMessageDialog(null, "El Area Medica " + area.getNombreAreaMedica() + " se agrego correctamente.", "Area Medica", JOptionPane.INFORMATION_MESSAGE);
					
			}

		});
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				area.eliminarAreaMedica();
				cmbMedicoCoordinador.setModel(area.completaComboString(medico.listadoNombreMedicos()));
				cmbAreaMedica.setModel(area.completaComboString(area.listadoAreaMedica()));
				cmbEliminarEspecialidad.setModel(area.completaComboString(especialidadBorrar.especialidadConArea(area.getIdAreaMedica())));
				cmbAgregarEspecialidad.setModel(area.completaComboString(especialidadAgregar.especialidadSinArea()));
				cmbMedicoCoordinador.setSelectedIndex(-1);
				cmbAreaMedica.setSelectedIndex(-1);
				cmbEliminarEspecialidad.setSelectedIndex(-1);
				cmbAgregarEspecialidad.setSelectedIndex(-1);

				JOptionPane.showMessageDialog(null, "El Area Medica " + area.getNombreAreaMedica() + " se elimino correctamente.", "Area Medica", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});

		
		
	}
}
