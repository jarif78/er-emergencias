package Clinica;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class AuxTurnos extends JTable
	{
		public String fecha = null;
		public int sumaminutos = 0;
		public int acumulado = 0;
		public int hora = 0;
	
	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int ColumnIndex) 
	{
		Component componente = super.prepareRenderer(renderer, rowIndex, ColumnIndex);
		
		if(getValueAt(rowIndex, ColumnIndex).getClass().equals(Integer.class))
		{
			int valor = Integer.parseInt(this.getValueAt(rowIndex, ColumnIndex)+"");
			if(valor == 100) {
				componente.setBackground(Color.gray);
				componente.setForeground(Color.gray);
			} else if(valor == 0) {
				componente.setBackground(Color.green);
				componente.setForeground(Color.black);
			} else if(valor < 21) {
				componente.setBackground(Color.yellow);
				componente.setForeground(Color.black);
			} else if(valor < 51) {
				componente.setBackground(Color.orange);
				componente.setForeground(Color.black);
			} else if(valor>=51&&valor<100) {
				componente.setBackground(Color.red);
				componente.setForeground(Color.black);

			}
		}
		
		return componente;
	}
	
	public void turnosTomados(String desde, String hasta) {
		//int diad = Integer.parseInt(desde.substring(8,10));
		//int diah = Integer.parseInt(hasta.substring(8,10));
		int horad = Integer.parseInt(desde.substring(11,13));
		int horah = Integer.parseInt(hasta.substring(11,13));
		int mind = Integer.parseInt(desde.substring(14,16));
		int minh = Integer.parseInt(hasta.substring(14,16));
		fecha = desde.substring(8,10) + "/" + desde.substring(5,7) +  "/" + desde.substring(0,4);
		hora = horad;
		if(horah==horad) {
			sumaminutos = minh - mind + 1;
		} else if(horah>horad) {
			sumaminutos = 60 - mind;
			acumulado = minh + 1;
		}
	}
	

	
	
}


	