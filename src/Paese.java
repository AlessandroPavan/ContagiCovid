import java.util.Date;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import java.util.Objects;

public class Paese implements Comparable<Paese>{
	
	private String nome;
	private Map<Date, Integer> morti = new TreeMap<>();
	private Map<Date, Integer> contagi = new TreeMap<>();
	
	/**
	 * crea un paese
	 * @param nome il nome del paese
	 * @param d la data
	 * @param nMorti il numero delle morti
	 * @param nContagi il numero dei contagi
	 */
	public Paese(String nome, Date d, Integer nMorti, Integer nContagi) {
		this.nome = nome;
		morti.put(d,nMorti);
		contagi.put(d,nContagi);
		
	}
	
	public Paese(String nome) {
		this.nome = nome;
	}
	
	/**
	 * aggiunge il numero dei morti di un determinato giorno
	 * @param d la data 
	 * @param nMorti il numero di morti di quel giorno
	 */
	public void addMorti(Date d, int nMorti) {
		morti.put(d,nMorti);
	}
	
	/**
	 * aggiunge il numero di contagi di un determinato giorno
	 * @param d la data
	 * @param nContagi il numero di contagi di quel giorno
	 */
	public void addContagi(Date d, int nContagi) {
		contagi.put(d,nContagi);
	}
	
	/**
	 * restituisce
	 * @param d
	 * @return
	 */
	public int getNContagi(Date d) {
		
		if(contagi.containsKey(d)) {
			return contagi.get(d);
		}
		throw new IllegalArgumentException("Nessun dato trovato per la data richiesta");
		
		
	}
	
	public int compareTo(Paese o) {
		return this.nome.compareTo(o.nome);
	}
	
	public int hashcode() {
		return Objects.hash(nome);
	}
	
	public String toString() {
		String s = "";
		Set<Date> date = morti.keySet();
		s+=nome + "\n morti: \n";
		for(Date d: date) {
			s+=d.toString() + "    " + morti.get(d) + "\n";
		}
		s+="\n \n \n contagi:";
		for(Date d: date) {
			s+=d.toString() + "    " + contagi.get(d)+ "\n";
	}
	
		return s;
	}
}
