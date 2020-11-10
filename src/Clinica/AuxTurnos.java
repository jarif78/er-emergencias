package Clinica;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class AuxTurnos extends JTable
	{
	
	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int ColumnIndex) 
	{
		Component componente = super.prepareRenderer(renderer, rowIndex, ColumnIndex);
		
		if(getValueAt(rowIndex, ColumnIndex).getClass().equals(Integer.class))
		{
			int valor = Integer.parseInt(this.getValueAt(rowIndex, ColumnIndex)+"");
			if(valor == 60) {
				componente.setBackground(Color.gray);
				componente.setForeground(Color.gray);
			} else if(valor == 0) {
				componente.setBackground(Color.green);
				componente.setForeground(Color.black);
			} else if(valor < 3) {
				componente.setBackground(Color.yellow);
				componente.setForeground(Color.black);
			} else if(valor == 4) {
				componente.setBackground(Color.orange);
				componente.setForeground(Color.black);
			} else if(valor>=5&&valor<60) {
				componente.setBackground(Color.red);
				componente.setForeground(Color.black);

			}
		}
		
		return componente;
	}
	
	
}


	