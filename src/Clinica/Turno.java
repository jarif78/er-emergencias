package Clinica;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	

	public int[] turnosHora(String dia, int hora) {
		String ad = null;
		String ah = null;
		int minm = 61;
		int maxm = 0;
		if(hora<10) {
			ad = dia.substring(6,10) + "/" + dia.substring(3,5) + "/" + dia.substring(0,2) + " 0" + hora + ":00:00";
			ah = dia.substring(6,10) + "/" + dia.substring(3,5) + "/" + dia.substring(0,2) + " 0" + hora + ":59:59";
		} else {
			ad = dia.substring(6,10) + "/" + dia.substring(3,5) + "/" + dia.substring(0,2) + " " + hora + ":00:00";
			ah = dia.substring(6,10) + "/" + dia.substring(3,5) + "/" + dia.substring(0,2) + " " + hora + ":59:59";
		}
		String consSQL = "SELECT * FROM Turnos WHERE idEspecialidad = " + idEspecialidad + " AND FechaDesde BETWEEN '" + ad + "' AND '" + ah + "' ORDER BY FechaDesde ASC";
		ResultSet rs = Dao.consulta(consSQL);
		
		
		try {
			while(rs.next()) {
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int a[] = {minm, maxm};
		
		return a;
			
	}

	

}
