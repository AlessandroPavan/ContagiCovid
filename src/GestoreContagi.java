import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class GestoreContagi {
	
	public static final String PATH=".data";

	public static void main(String[] args) throws MalformedURLException, IOException {
		Date dataAttuale= new Date();
		Date dataUltimaEstrazione= (Date)readObject();
		if(!compareDates(dataAttuale,dataUltimaEstrazione)) {
			FileUtils.copyURLToFile(new URL("https://opendata.ecdc.europa.eu/covid19/casedistribution/xml/"), new File("dati.xml"));
			writeObject(dataAttuale);
			System.out.println("Scaricato dal sito");
		}
		EstrattoreDati d = new EstrattoreDati("dati.xml");
		Set<Paese> paesi =d.getPaesi();
		Visualizzatore v = new Visualizzatore("Tabella contagi covid");
		Paese desiderato = trovaPaese(paesi,"italy");
		v.aggiungiTabellaDaMappa(desiderato.getMapContagi(),desiderato.getGiorniRegistrati() , 2);
		v.visualizza();
	}
	
	static void writeObject(Object o) {
		try {
			FileOutputStream f = new FileOutputStream(PATH);
			ObjectOutputStream out=new ObjectOutputStream(f);
			out.writeObject(o);
			out.close();
		}catch(IOException e) {
			System.err.println("Non è stato possibile salvare l'oggetto");
		}
	}
	
	static Object readObject() {
		Object o=null;
		try {
			FileInputStream fi=new FileInputStream(PATH);
			ObjectInputStream oFi=new ObjectInputStream(fi);
			o=oFi.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	static boolean compareDates(Date d1, Date d2) {
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		String sD1= sdf.format(d1);
		String sD2= sdf.format(d2);
		return sD1.equals(sD2);
	}
	
	static Paese trovaPaese(Set<Paese> p,String cercato) {
		Paese trovato = null;
		Iterator<Paese> it= p.iterator();
		while(it.hasNext()) {
			Paese pEstratto=it.next();
			if(pEstratto.getNome().equalsIgnoreCase(cercato)) {
				trovato=pEstratto;
				break;
			}
		}
		return trovato;
	}

}
