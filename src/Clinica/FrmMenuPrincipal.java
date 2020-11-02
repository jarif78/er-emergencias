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
	private static JButton [] button = new JButton[2];
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
		

		
		button[0] = new JButton("Pacientes");
		button[0].setVisible(false);
		button[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FrmPaciente FP = new FrmPaciente();
				FP.setVisible(true);
			}
		});
		button[0].setBounds(310, 148, 156, 63);
		frame.getContentPane().add(button[0]);
		
		
		button[1] = new JButton ("Especialidades");
		button[1].setVisible(false);
		button[1].addMouseListener(new MouseAdapter() {
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
		button[1].setBounds(116, 148, 156, 63);
		frame.getContentPane().add(button[1]);
		
		//lblConectando = new JLabel("Conectando a la Base de datos");
		lblConectando.setHorizontalAlignment(SwingConstants.CENTER);
		lblConectando.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConectando.setBounds(171, 178, 317, 54);
		frame.getContentPane().add(lblConectando);
		
		JOptionPane.showMessageDialog(null, "Bienvenido!\n\nMateria: Programacion Orientada a Objetos\n\nTrabajo Practico de programa Clinica - UNPAZ                                     "
				+ "\n\n\nProfesor: Ing. Lucas Guaycochea\nProfesor: Lic. Cristian Ciarallo\n\nAlumno: Anahi Aguirre\nAlumno: Ariel Fernandez\nAlumno: Ricardo Gonzalez\nAlumno: Walter Suarez\n\n",
				"TP-POO - Programa clinica", JOptionPane.INFORMATION_MESSAGE);

		
		
	}
	
	public static void habilitar() {
		for(int x = 0; x<2; x++) {
			button[x].setVisible(true);
		}
		lblConectando.setVisible(false);
	}
}
