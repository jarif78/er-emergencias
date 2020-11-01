package Clinica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class FrmMenuPrincipal {
	public static Date fecha = new Date();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
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
		
		
		JOptionPane.showMessageDialog(null, "Bienvenido!\n\nMateria: Programacion Orientada a Objetos\n\nTrabajo Practico de programa Clinica - UNPAZ                                     "
				+ "\n\n\nProfesor: Ing. Lucas Guaycochea\nProfesor: Lic. Cristian Ciarallo\n\nAlumno: Anahi Aguirre\nAlumno: Ariel Fernandez\nAlumno: Ricardo Gonzalez\nAlumno: Walter Suarez\n\n",
				"TP-POO - Programa clinica", JOptionPane.INFORMATION_MESSAGE);

		
		JButton button = new JButton("Pacientes");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmPaciente FP = new FrmPaciente();
				FP.setVisible(true);
			}
		});
		button.setBounds(310, 148, 156, 63);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Especialidades");
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FrmEspecialidades FE = new FrmEspecialidades();
				FE.setVisible(true);
			}
		});
		button_1.setBounds(116, 148, 156, 63);
		frame.getContentPane().add(button_1);
		
		//Dao.conexion();
		
	}
}
