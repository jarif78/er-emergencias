package Clinica;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

public class Medico {
	private int idMed;
	private String nombreMedico;
	private String especialidadMed;
	private int dniMedico;
	private String telMedico;
	
	
	
	public int getIdMed() {
		return idMed;
	}
	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	public String getEspecialidadMed() {
		return especialidadMed;
	}
	public void setEspecialidadMed(String especialidadMed) {
		this.especialidadMed = especialidadMed;
	}

	public int getDniMedico() {
		return dniMedico;
	}
	public void setDniMedico(int dniMedico) {
		this.dniMedico = dniMedico;
	}
	public String getTelMedico() {
		return telMedico;
	}
	public void setTelMedico(String telMedico) {
		this.telMedico = telMedico;
	}

	public ResultSet listadoMedicos() {
		String sql = "SELECT * FROM Medico";
		ResultSet rs = null;
		rs = Dao.consulta(sql);
		return rs;
	}
	
	public void cargarMedicoConId() {
		if(idMed!=0) {
			String consSQL = "SELECT * FROM Medico WHERE idMed = " + idMed;
			ResultSet rs = null;
			rs = Dao.consulta(consSQL);
			try {
				while(rs.next()) {
					nombreMedico = rs.getString(2);
					dniMedico = rs.getInt(3);
					especialidadMed = rs.getString(4);
					telMedico = rs.getString(5);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ResultSet listadoNombreMedicos() {
		String sql = "SELECT nombreMedico FROM Medico";
		ResultSet rs = null;
		rs = Dao.consulta(sql);
		return rs;
	}

	
	
	public void eliminarMedico() {
		if(idMed!=0) {
			String consSQL = "DELETE FROM Medico WHERE idMed = " + idMed;
			Dao.agregarBorrar(consSQL);
		}
	}
	
	public boolean existeDniMedico() {
		String sSQL = "SELECT idMed FROM Medico WHERE dniMedico = " + dniMedico;
		return Dao.ifExists(sSQL);
	}
	
	public void agregarMedico() {
		String consSQL = "INSERT INTO Medico (nombreMedico, dniMedico, especialidadMed, telMedico) VALUES ('" + nombreMedico + "' , " + dniMedico + " , '" + especialidadMed + "' , '" + telMedico + "')";
		Dao.agregarBorrar(consSQL);
	}
	
	public void modificarDatosMedico() {
		String consSQL = "UPDATE Medico SET nombreMedico ='" + nombreMedico + "', dniMedico=" + dniMedico + ", especialidadMed='" + especialidadMed + "', telMedico='" + telMedico + "' WHERE idMed = " + idMed;
		System.out.println(consSQL);
		Dao.agregarBorrar(consSQL);
	}
	
	public void cargarMedicoConNombre() {
			String consSQL = "SELECT * FROM Medico WHERE nombreMedico = '" + nombreMedico + "'";
			ResultSet rs = null;
			rs = Dao.consulta(consSQL);
			try {
				while(rs.next()) {
					idMed = rs.getInt(1);
					dniMedico = rs.getInt(3);
					especialidadMed = rs.getString(4);
					telMedico = rs.getString(5);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public ResultSet listadoMedicoPorEspecializacion(String esp) {
		String consSQL = "SELECT nombreMedico FROM Medico WHERE especialidadMed = '" + esp + "'";
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


}
