package Clinica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;

public class Especialidades {
	
	private int idEsp;
	private String nomEsp;
	private int IdAreaMedica;
	private int idMed;
	private int [][] diaHorEsp = new int[2][180];

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

	public int getIdMed() {
		return idMed;
	}
	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}
	public int getIdAreaMedica() {
		return IdAreaMedica;
	}
	public void setIdAreaMedica(int idAreaMedica) {
		IdAreaMedica = idAreaMedica;
	}

	public void setDiaHorEsp(int r, int d, int h) { //r es la linea - d es el dia de la semana - h es la hora 
		if(idEsp>0) {
			this.diaHorEsp[0][r] =  d;
			this.diaHorEsp[1][r] =  h;
		}
	}
	
	public void setDiaHorEsp2() { //r es la linea - d es el dia de la semana - h es la hora 
		Dao.agregarBorrar("DELETE FROM `EspAbierta` WHERE `idAbiEsp` = " + this.idEsp + " AND `DiaAbiEsp` > 0");
		//System.out.println("Se borraron los horarios de: " + this.nomEsp);
		String sql = "INSERT INTO `EspAbierta`(`IdAbiEsp`, `DiaAbiEsp`, `HorAbiEsp`) VALUES ";
		for(int x=0; x<180; x++) {
			if(diaHorEsp[0][x]==0) x = 180;
			else {
			if(idEsp>0) {
				int d = this.diaHorEsp[0][x];
				int h = this.diaHorEsp[1][x];
				sql = sql +  "(" + this.idEsp + "," + d + "," + h + ") ,";
				//Dao.agregarBorrar(sql);
			}}
		}
		sql = sql.substring(0, sql.length() -1);
		Dao.agregarBorrar(sql);
		//System.out.println("se agrego a la base de datos");
	}
	
	public boolean AgregarEspecialidad() {
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
		datCom.addElement(null);
		rs = Dao.consulta(sql);
			try {
				while(rs.next()) datCom.addElement(rs.getString(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return datCom;
	}
	
	public void cargarIdEspPorNombre() {	// cargar id a la instacia de acuerdo al nombre cargado
		if(this.nomEsp!=null) {
			boolean b = true;
			String sql = "SELECT idEsp FROM Especializacion WHERE nombreEsp = '" + this.nomEsp + "'";
			ResultSet rs = null;
			rs = Dao.consulta(sql);
			try {
				while(rs.next()) {
					idEsp=(rs.getInt(1));
					b = false;
				}	if(b) this.idEsp = 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}} else {
				this.idEsp = 0;
			}
	}
	
	
	public void carDiaHorEsp() {
		limpiarDiasHoras();
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
	
	private void limpiarDiasHoras() {
		for (int x = 0; x<180; x++) {
			this.diaHorEsp[0][x] = 0;
			this.diaHorEsp[1][x] = 0;	
		}
	}
	
	public int [] devolverHorario(int dia) {
		int max = 0, min = 25;
		for(int x = 0; x<180; x++) {
			if(this.diaHorEsp[0][x]==dia) {
				if(this.diaHorEsp[1][x]<min) min = this.diaHorEsp[1][x];
				if(this.diaHorEsp[1][x]>max) max = this.diaHorEsp[1][x];
			}
		}
		int[] a = {min, max};
		return a;
	}
	
	public boolean exiEsp() {
		String sql = "SELECT idEsp FROM Especializacion WHERE nombreEsp = '" + this.nomEsp + "'";
		return Dao.ifExists(sql);
	}
	
	
	public void mostrar () {
		for(int x=1; x<180;x++) {
			System.out.println("Id: "+this.idEsp+"\tDias: "+this.diaHorEsp[0][x]+"\tHoras: "+this.diaHorEsp[1][x]);
		}
	}
	
	public void inhabilitarDias(Calendar d) {
		if(idEsp!=0) {
			java.util.Date a;
			a = d.getTime();
			java.sql.Date sqlDate = new java.sql.Date(a.getTime());
			String sql = "SELECT `IdAbiEsp` FROM `EspAbierta` WHERE `DiaCerrado` = '" + sqlDate + "' AND `IdAbiEsp` = " + idEsp;
			if(Dao.ifExists(sql));
			else {
				sql = "INSERT INTO `EspAbierta`(`IdAbiEsp`, `DiaCerrado`) VALUES ('" + idEsp + "' , '" + sqlDate + "')";
				Dao.agregarBorrar(sql);
				
			}}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel listadoDiasInhabilitados() {
		DefaultComboBoxModel datCom = new DefaultComboBoxModel();
		String sql = "SELECT `DiaCerrado` FROM EspAbierta WHERE `IdAbiEsp` = " + idEsp + " AND `DiaCerrado` >= now() ";
		ResultSet rs = null;
		rs = Dao.consulta(sql);
			try {
				while(rs.next()) {
					datCom.addElement(rs.getDate(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return datCom;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel comboAreaMedica() {
		DefaultComboBoxModel datCom = new DefaultComboBoxModel();
		String sql = "SELECT `DiaCerrado` FROM EspAbierta WHERE `IdAbiEsp` = " + idEsp + " AND `DiaCerrado` >= now() ";
		ResultSet rs = null;
		rs = Dao.consulta(sql);
			try {
				while(rs.next()) {
					datCom.addElement(rs.getDate(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return datCom;
	}
	
	
	
	public void habilitarDias(java.sql.Date dia) {
		String sql = "DELETE FROM `EspAbierta` WHERE `idAbiEsp` = " + idEsp + " AND  `DiaCerrado` ='" + dia + "'";
		Dao.agregarBorrar(sql);
	}
	
	public void eliminarEspecializacion() {				// Agregar verificador de si hay turnos abiertos en caso de haber enviar mensaje de error 
		String sql =  "SELECT idEsp FROM Especializacion WHERE idEsp = " + this.idEsp ;
		
		if(Dao.ifExists(sql)){
			sql = "DELETE FROM Especializacion WHERE idEsp = " + this.idEsp ;
			Dao.agregarBorrar(sql);
			sql = "DELETE FROM EspAbierta WHERE idAbiEsp = " + this.idEsp ;
			}
		}

	public ResultSet especialidadSinArea () {
		String consSQL = "SELECT nombreEsp FROM Especializacion WHERE IdAreaMedica IS NULL OR IdAreaMedica = 0";
		return Dao.consulta(consSQL);
	}
	
	public ResultSet especialidadConArea (int idArea) {
		if(idArea==0) { idArea = -1; } 
		String consSQL = "SELECT nombreEsp FROM Especializacion WHERE IdAreaMedica = " + idArea;
		return Dao.consulta(consSQL);
	}

	public void establecerAreaMedica() {
		String consSQL = "UPDATE Especializacion SET IdAreaMedica =" + IdAreaMedica + " WHERE idEsp =" + idEsp;
		Dao.agregarBorrar(consSQL);
	}
	
	public void borrarAreaMedica() {
		String consSQL = "UPDATE Especializacion SET IdAreaMedica = 0 WHERE idEsp =" + idEsp;
		System.out.println(consSQL);
		Dao.agregarBorrar(consSQL);
	
	}
	
}
