package Clinica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FrmMenuPrincipal {
	private static JButton [] button = new JButton[3];
	private JFrame frame;
	private static JLabel lblConectando = new JLabel("Conectando a la Base de datos");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Dao d = new Dao();
				d.execute();
				try {
					FrmMenuPrincipal window = new FrmMenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button_0 = new JButton();
		JButton button_1 = new JButton();
		JButton button_2 = new JButton();
		JButton button_3 = new JButton();
		
		
		button_0 = new JButton("Pacientes");
		button_0.setVisible(true);
		button_0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FrmPaciente FP = new FrmPaciente();
				FP.setVisible(true);
			}
		});
		button_0.setBounds(320, 70, 156, 63);
		frame.getContentPane().add(button_0);
		
		
		button_1 = new JButton ("Especialidades");
		button_1.setVisible(true);
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FrmEspecialidades FE;
				try {
					FE = new FrmEspecialidades();
					FE.setVisible(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(117, 70, 156, 63);
		frame.getContentPane().add(button_1);
		
		
		button_2 = new JButton ("Medicos");
		button_2.setVisible(true);
		button_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FormMedico ME;
				ME = new FormMedico();
				ME.setVisible(true);
			}
		});
		button_2.setBounds(117, 173, 156, 63);
		frame.getContentPane().add(button_2);
	
		
		
		
		
		
		//lblConectando = new JLabel("Conectando a la Base de datos");
		//lblConectando.setHorizontalAlignment(SwingConstants.CENTER);
		//lblConectando.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//lblConectando.setBounds(171, 178, 317, 54);
		//frame.getContentPane().add(lblConectando);
		
		JOptionPane.showMessageDialog(null, "Bienvenido!\n\nMateria: Programacion Orientada a Objetos\n\nTrabajo Practico de programa Clinica - UNPAZ                                     "
				+ "\n\n\nProfesor: Ing. Lucas Guaycochea\nProfesor: Lic. Cristian Ciarallo\n\nAlumno: Anahi Aguirre\nAlumno: Ariel Fernandez\nAlumno: Ricardo Gonzalez\nAlumno: Walter Suarez\n\n",
				"TP-POO - Programa clinica", JOptionPane.INFORMATION_MESSAGE);

		
		
	}
	
	public static void habilitar() {
		for(int x = 0; x<3; x++) {
			button[x].setVisible(true);
		}
		lblConectando.setVisible(false);
	}
}
