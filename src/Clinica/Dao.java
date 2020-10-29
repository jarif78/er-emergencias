package Clinica;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class Dao extends SwingWorker<Void, Void>{
	
	private static boolean baseConectada = false;
	private static java.sql.Connection base = null;
	
    private static void conexion(){
    	if (baseConectada) ;
    	else {
    		java.sql.Connection cnx = null;
	        String driver = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://85.10.205.173/tpunpazclinica";
	        String user = "grupo6unpaz";
	        String password = "grupo6unpaz";
	
	        try {
	            Class.forName(driver);
	            System.out.println("Estableciendo conexion con el servidor d4free.net");
	            cnx = DriverManager.getConnection(url, user, password);
	            System.out.println("Se conectó correctamente a la base");
	            Dao.baseConectada = true;
	            Dao.setBase(cnx);
	            baseConectada = true;
	        } catch (Exception e) {
	        	e.printStackTrace();            
        	}
    	}
    }

	private static void setBase(java.sql.Connection base) {
		Dao.base = base;
	}
	
	public static ResultSet consulta(String consSQL) {
		Statement s = null;
		ResultSet rs = null;
		if(Dao.baseConectada) {
			try {
				s = base.createStatement();
				rs = s.executeQuery (consSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				conexion();
				s = base.createStatement();
				rs = s.executeQuery (consSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return rs;
	}
	
	public static boolean agregarBorrar (String consSQL) {
		Statement s = null;
		boolean resultado = false;
		if(Dao.baseConectada) {
			try {
				s = base.createStatement();
				s.executeUpdate(consSQL);
				resultado = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				conexion();
				s = base.createStatement();
				s.executeUpdate(consSQL);
				resultado = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return resultado;
		
	}
	
	public static boolean ifExists(String sSQL) {
        Statement s = null;
		boolean resultado = false;
		try {
			s = base.createStatement();
	        ResultSet rs = s.executeQuery(sSQL);
	        if(rs.next()) resultado = true;					// Si resultset puede ejecutar la accion next significa que existe registro, cambio resultado a true
	        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;

	}


	public static java.sql.Connection getBase() {
		return base;
	}
	
	
	public static DefaultTableModel rellenoJTable(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // nombres de columnas
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // datos
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}

	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
    	if (baseConectada) ;
    	else {
    		java.sql.Connection cnx = null;
	        String driver = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://85.10.205.173/tpunpazclinica";
	        String user = "grupo6unpaz";
	        String password = "grupo6unpaz";
	
	        try {
	            Class.forName(driver);
	            System.out.println("Estableciendo conexion con el servidor d4free.net");
	            cnx = DriverManager.getConnection(url, user, password);
	            System.out.println("Se conectó correctamente a la base");
	            JOptionPane.showMessageDialog(null, "Se encuentra conectado al servidor", "Programa clinica", JOptionPane.INFORMATION_MESSAGE);
	            Dao.setBase(cnx);
	            baseConectada = true;
	        } catch (Exception e) {
	        	e.printStackTrace();            
        	}
    	}
		
		
		return null;
	}

	
    
    
    
    
    
}