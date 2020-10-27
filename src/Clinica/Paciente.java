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
			sqlingreso = "INSERT INTO `Paciente` (`idPac`, `nomPac`, `dniPac`, `telPac`, `coberturaPac`) VALUES (NULL, '" + this.nombrePac 
			+ "', " + this.dniPac + ", '" + this.telefonoPac +  "', '" + this.coberturaPac +"')"; 
			Dao.agregarBorrar(sqlingreso);
			return true;
		} else return false;
	}
	
	private boolean pacienteCompleto() {
		if(this.coberturaPac.length()<5&&this.dniPac>99999&&this.nombrePac.length()<5&&this.telefonoPac.length()<4) {
			pacienteActivo();
				return false;
			} else	return true;

	}
	
	public boolean editarPaciente() {
		boolean resultado = false;
		if(pacienteCompleto()) {
			String sqlEditar;
			sqlEditar = "UPDATE `Paciente` SET `nomPac`='" + this.nombrePac + "',`dniPac`='" + this.dniPac + "',`telPac`='" + this.telefonoPac + "',`coberturaPac`='" + this.coberturaPac + "' WHERE `idPac` = " + this.idPac;
			resultado = Dao.agregarBorrar(sqlEditar);
		}
		return resultado;
	}
	
	
	public boolean eliminarPaciente() {
		if (existeDNI()) {
			String sql = "DELETE FROM `Paciente` WHERE `idPac` = " + this.idPac;
			return Dao.agregarBorrar(sql);
		}
		return false;
	}
	
	public boolean existeDNI() {
		String sql = "SELECT idPac FROM Paciente WHERE dniPac = " + this.dniPac;
		return Dao.ifExists(sql);
	}
	
	
	public static ResultSet listadoPacientes() {
		String sql = "SELECT * FROM Paciente";
		return Dao.consulta(sql);
	}
	
	public ResultSet pacienteConDNI() {
		String sql = "SELECT * FROM Paciente WHERE dniPac = " + this.dniPac;
		return Dao.consulta(sql);
	}
	
	public ResultSet pacienteConNombre() {
		String sql = "SELECT * FROM `Paciente` WHERE `nomPac` LIKE '%" + this.nombrePac + "%'";
		return Dao.consulta(sql);
	}
	
	
	public void pacienteActivo() {
		System.out.println(this.idPac+" - "+this.nombrePac+" - "+this.dniPac+" - "+this.telefonoPac+" - "+this.getCoberturaPac());
	}

}
