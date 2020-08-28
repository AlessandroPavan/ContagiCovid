
import java.util.Date;
import java.util.Iterator;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import jdk.nashorn.api.tree.ForInLoopTree;

public class EstrattoreDati extends DefaultHandler {

	private Paese paeseCorrente;
	private Set<Paese> paesi;
	private String pcData;
	private String nomePaese = "";
	private Date data;
	private int nContagi, nMorti;
	private boolean nuovoPaese = true;

	public EstrattoreDati(String nomeFile) {
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			parser.parse(new File(nomeFile), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startDocument() {
		paesi = new TreeSet<>();
	}

	public void characters(char[] ch, int start, int length) {
		pcData = new String(ch, start, length);
	}

	public void endElement(String uri, String name, String qName) {
		if (qName.equalsIgnoreCase("DateRep")) {
			try {
				data = new SimpleDateFormat("dd/MM/yyyy").parse(pcData);
			} catch (ParseException e) {

			}
		}

		if (qName.equalsIgnoreCase("CountriesAndTerritories")) {
			nuovoPaese = !nomePaese.equalsIgnoreCase(pcData);
			nomePaese = pcData;
			if (nuovoPaese) {
				paeseCorrente = new Paese(nomePaese);
				paesi.add(paeseCorrente);
			}
		}

		if (qName.equalsIgnoreCase("deaths")) {
			nMorti = Integer.valueOf(pcData);
		}

		if (qName.equalsIgnoreCase("cases")) {
			nContagi = Integer.valueOf(pcData);
		}

		if (qName.equalsIgnoreCase("record")) {
			paeseCorrente.addContagi(data, nContagi);
			paeseCorrente.addMorti(data, nMorti);
		}
	}

	public Set<Paese> getPaesi() {
		return paesi;
	}
}
