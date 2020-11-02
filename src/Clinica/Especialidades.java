package Clinica;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

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

	public void setDiaHorEsp(int r, int d, int h) { //r es la linea - d es el dia de la semana - h es la hora 
		if(idEsp>0) {
			this.diaHorEsp[0][r] =  d;
			this.diaHorEsp[1][r] =  h;
			String sql = "INSERT INTO `EspAbierta`(`IdAbiEsp`, `DiaAbiEsp`, `HorAbiEsp`) VALUES (" + this.idEsp + "," + d + "," + h + ")";
			Dao.agregarBorrar(sql);
		}
	}
	
	public boolean ingEsp() {
		boolean resultado = false;
		if(this.nomEsp.length()>5) {
			String sql = "INSERT INTO `Especializacion`(`nombreEsp`) VALUES ('" + this.nomEsp +"')";
			ResultSet rs = null;
			resultado = Dao.agregarBorrar(sql);
			sql = "SELECT idEsp FROM Especializacion WHERE nombreEsp = '" + this.nomEsp + "'";
			rs = Dao.consulta(sql);
			try {
				rs.next();
				int nro;
				nro = rs.getInt (1);
				this.idEsp=nro;
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return resultado;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel LisEsp() {
		DefaultComboBoxModel datCom = new DefaultComboBoxModel();
		String sql = "SELECT `nombreEsp` FROM Especializacion";
		ResultSet rs = null;
		rs = Dao.consulta(sql);
			try {
				while(rs.next()) datCom.addElement(rs.getString(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return datCom;
	}
	
	public void carIdEspConNom() {	// cargar id a la instacia de acuerdo al nombre cargado
		String sql = "SELECT idEsp FROM Especializacion WHERE nombreEsp = '" + this.nomEsp + "'";
		ResultSet rs = null;
		rs = Dao.consulta(sql);
		try {
			while(rs.next()) {
				setIdEsp(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void carDiaHorEsp() {
		
		String sSQL = "SELECT idAbiEsp FROM EspAbierta WHERE IdAbiEsp = " + this.idEsp;
		if(Dao.ifExists(sSQL)) {
			int x = 1;
			String sql = "SELECT * FROM EspAbierta WHERE IdAbiEsp = " + this.idEsp;
			ResultSet rs = null;
			rs = Dao.consulta(sql);
			try {
				while(rs.next()) {
					this.diaHorEsp[0][x] =  rs.getInt(2);
					this.diaHorEsp[1][x] =  rs.getInt(3);
					x = x + 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean exiEsp() {
		String sql = "SELECT idEsp FROM Especializacion WHERE nombreEsp = '" + this.nomEsp + "'";
		return Dao.ifExists(sql);
	}
	
	
	public void mostrar () {
		for(int x=1; x<168;x++) {
			System.out.println("Id: "+this.idEsp+"\tDias: "+this.diaHorEsp[0][x]+"\tHoras: "+this.diaHorEsp[1][x]);
		}
	}
	
	
}
