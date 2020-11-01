package Clinica;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Especialidades {
	
	private int idEsp;
	private String nomEsp;
	private boolean estEsp;
	private int idMed;
	private int [][] diaHorEsp = new int[2][168];

	public int getIdEsp() {
		return idEsp;
	}
	public void setIdEsp(int idEsp) {
		this.idEsp = idEsp;
	}
	public String getNomEsp() {
		return nomEsp;
	}
	public void setNomEsp(String nomEsp) {
		this.nomEsp = nomEsp;
	}
	public boolean isEstEsp() {
		return estEsp;
	}
	public void setEstEsp(boolean estEsp) {
		this.estEsp = estEsp;
	}
	public int getIdMed() {
		return idMed;
	}
	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}

	public void setDiaHorEsp(int r, int d, int h) {
		this.diaHorEsp[0][r] =  d;
		this.diaHorEsp[1][r] =  h;
	}
	
	public boolean ingEsp() {
		String sql = "INSERT INTO `Especializacion`(`nombreEsp`) VALUES ('" + this.nomEsp +"')";
		ResultSet rs = null;
				
		boolean resultado = Dao.agregarBorrar(sql);
		sql = "SELECT idEsp FROM Especializacion WHERE nombreEsp = '" + this.nomEsp + "'";
		rs = Dao.consulta(sql);
		
		try {
	    	rs.next();
			int nro;
			nro = rs.getInt (1);
			this.idEsp=nro;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	
	
	
	
}
