package Clinica;

import java.sql.ResultSet;
import java.util.Date;

public class Turno {
	private int IdTurno;
	private String FechaDesde;
	private String FechaHasta;
	private int idPac;
	private int idEspecialidad;
	
	
	public int getIdTurno() {
		return IdTurno;
	}
	public void setIdTurno(int idTurno) {
		IdTurno = idTurno;
	}
	public String getFechaDesde() {
		return FechaDesde;
	}
	public void setFechaDesde(String horaInicioTurno) {
		FechaDesde = horaInicioTurno;
	}
	public String getFechaHasta() {
		return FechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		FechaHasta = fechaHasta;
	}
	public int getIdPac() {
		return idPac;
	}
	public void setIdPac(int idPac) {
		this.idPac = idPac;
	}
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
	public void cargarTurno() {
		String consSQL = "INSERT INTO Turnos (FechaDesde, FechaHasta, idPac, idEspecialidad) VALUES ('" + FechaDesde + "', '" + FechaHasta + "', " + idPac + ", " + idEspecialidad + ")";
		Dao.agregarBorrar(consSQL);
	}

	public ResultSet turnosSemana() {
		String consSQL = "SELECT * FROM Turnos WHERE idEspecialidad = " + idEspecialidad + " AND FechaDesde BETWEEN '" + FechaDesde + "' AND '" + FechaHasta + "'";
		return Dao.consulta(consSQL);
	}
	

}
