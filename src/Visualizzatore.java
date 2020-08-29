import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Visualizzatore extends JFrame{
	
	private Container contentPane;
	private JPanel pnlCentrale;
	private JTable tabella;
	
	public Visualizzatore(String intestazione) {
		super(intestazione);
		contentPane = getContentPane();
	}
	
	public void visualizza() {
		creaPannelli();
		this.setBounds(50, 50, 1200, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void creaPannelli() {
		pnlCentrale = new JPanel();
		pnlCentrale.add(tabella);
		contentPane.add(pnlCentrale);
	}
	
	public void aggiungiTabellaDaMappa(Map<Date, Integer> map,int rows,int columns) {
		Set<Date> s= map.keySet();
		Iterator<Date> sIt= s.iterator();
		ArrayList<Object> ar=new ArrayList<>();
		for(Object o:s) {
			ar.add(map.get(o));
		}
		
		Object[][] rowsTable = new Object[rows][columns];
		for(int i=0;i<columns;i++) {
			for (int j = 0; j < rows; j++) {
				if(sIt.hasNext())
					rowsTable[j][i]= sIt.next();
				else
					rowsTable[j][i]=ar.get(i);
			}
		}
		
		Object[] titles= new Object[] {"Data","NumeroContagi"};
		tabella = new JTable(rowsTable,titles);
	}
	
	
}
