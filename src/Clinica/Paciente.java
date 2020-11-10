package Clinica;

import java.sql.*;

public class Paciente {
	
	private int idPac;
	private String nombrePac;
	private int dniPac;
	private String telefonoPac;
	private String coberturaPac;
	
	public int getIdPac() {
		return idPac;
	}
	public void setIdPac(int idPac) {
		this.idPac = idPac;
	}
	public String getNombrePac() {
		return nombrePac;
	}
	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}
	public int getDniPac() {
		return dniPac;
	}
	public void setDniPac(int dniPac) {
		this.dniPac = dniPac;
	}
	public String getTelefonoPac() {
		return telefonoPac;
	}
	public void setTelefonoPac(String telefonoPac) {
		this.telefonoPac = telefonoPac;
	}
	public String getCoberturaPac() {
		return coberturaPac;
	}
	public void setCoberturaPac(String coberturaPac) {
		this.coberturaPac = coberturaPac;
	}
	
	public boolean darAltaPaciente() {
		if(pacienteCompleto()) {
			String sqlingreso;
			sqlingreso = "INSERT INTO `Paciente` (`idPac`, `nomPac`, `dniPac`, `telPac`, `coberturaPac`) VALUES (NULL, '" + nombrePac 
			+ "', " + dniPac + ", '" + telefonoPac +  "', '" + coberturaPac +"')"; 
			Dao.agregarBorrar(sqlingreso);
			return true;
		} else return false;
	}
	
	private boolean pacienteCompleto() {
		if(coberturaPac.length()<5&&dniPac>99999&&nombrePac.length()<5&&telefonoPac.length()<4) {
			pacienteActivo();
				return false;
			} else	return true;

	}
	
	public boolean editarPaciente() {
		boolean resultado = false;
		if(pacienteCompleto()) {
			String sqlEditar;
			sqlEditar = "UPDATE `Paciente` SET `nomPac`='" + nombrePac + "',`dniPac`='" + dniPac + "',`telPac`='" + telefonoPac + "',`coberturaPac`='" + coberturaPac + "' WHERE `idPac` = " + idPac;
			resultado = Dao.agregarBorrar(sqlEditar);
		}
		return resultado;
	}
	
	
	public boolean eliminarPaciente() {
		if (existeDNI()) {
			String sql = "DELETE FROM `Paciente` WHERE `idPac` = " + idPac;
			return Dao.agregarBorrar(sql);
		}
		return false;
	}
	
	public boolean existeDNI() {
		String sql = "SELECT idPac FROM Paciente WHERE dniPac = " + dniPac;
		return Dao.ifExists(sql);
	}
	
	
	public static ResultSet listadoPacientes() {
		String sql = "SELECT * FROM Paciente";
		return Dao.consulta(sql);
	}
	
	public ResultSet pacienteConDNI() {
		String sql = "SELECT * FROM Paciente WHERE dniPac = " + dniPac;
		ResultSet rs = Dao.consulta(sql);
		
		try {
			while(rs.next()) {
				idPac = rs.getInt(1);
				nombrePac = rs.getString(2);
				telefonoPac = rs.getString(4);
				coberturaPac = rs.getString(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs = null;
		rs = Dao.consulta(sql);
		return rs;
	}
	
	public ResultSet pacienteConNombre() {
		String sql = "SELECT * FROM `Paciente` WHERE `nomPac` LIKE '%" + nombrePac + "%'";
		return Dao.consulta(sql);
	}
	
	
	public void pacienteActivo() {
		System.out.println(idPac+" - "+nombrePac+" - "+dniPac+" - "+telefonoPac+" - "+getCoberturaPac());
	}
	
	public void borDatPac() {
		idPac = 0;
		nombrePac = "";
		coberturaPac= "";
		telefonoPac = "";
	}
	

}
