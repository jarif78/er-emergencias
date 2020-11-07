package Clinica;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

public class AreaMedica {
	private int idAreaMedica = 0;
	private String nombreAreaMedica;
	private int idMedicoCoordinador;

	public int getIdAreaMedica() {
		return idAreaMedica;
	}
	public void setIdAreaMedica(int idAreaMedica) {
		this.idAreaMedica = idAreaMedica;
	}
	public int getIdMedicoCoordinador() {
		return idMedicoCoordinador;
	}
	public void setIdMedicoCoordinador(int idMedicoCoordinador) {
		this.idMedicoCoordinador = idMedicoCoordinador;
	}
	public String getNombreAreaMedica() {
		return nombreAreaMedica;
	}
	public void setNombreAreaMedica(String nombreAreaMedica) {
		String consSQL = "SELECT * FROM AreaMedica WHERE nombreAreaMedica='" + nombreAreaMedica + "'";
		if(Dao.ifExists(consSQL)) {
			ResultSet rs = null; 				
			rs = Dao.consulta(consSQL);
			try {
				while(rs.next()) {
					idAreaMedica = (rs.getInt(1));
					nombreAreaMedica = (rs.getString(2));
					idMedicoCoordinador = (rs.getInt(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			idAreaMedica = 0;
			idMedicoCoordinador = 0;
		}
		this.nombreAreaMedica = nombreAreaMedica;
	}
	
	public boolean existeArea() {
		String consSQL = "SELECT * FROM AreaMedica WHERE nombreAreaMedica='" + nombreAreaMedica + "'";
		return Dao.ifExists(consSQL);
	}
	
	
	
	public ResultSet listadoAreaMedica() {
		String consSQL = "SELECT nombreAreaMedica FROM AreaMedica";
		return Dao.consulta(consSQL);
		
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel completaComboString(ResultSet rs) {
		DefaultComboBoxModel datCom = new DefaultComboBoxModel();
			try {
				while(rs.next()) {
					datCom.addElement(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return datCom;
	}
	
	public void agregarArea() {
		if(nombreAreaMedica.length()>5) {
			String consSQL = "INSERT INTO AreaMedica (nombreAreaMedica) VALUES ('"+ nombreAreaMedica + "')";
			Dao.agregarBorrar(consSQL);
			setNombreAreaMedica(nombreAreaMedica);
		}
	}
	

	public void agregarArea(Medico m) {
		if(nombreAreaMedica.length()>5) {
			String consSQL = "INSERT INTO AreaMedica (nombreAreaMedica, idMedicoCoordinador) VALUES ('"+ nombreAreaMedica + "' , " + m.getIdMed() + ")";
			Dao.agregarBorrar(consSQL);
			setNombreAreaMedica(nombreAreaMedica);
		}
	}

	
	
}
