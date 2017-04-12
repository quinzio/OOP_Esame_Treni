package orari;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Orari {
	static Collection<Percorso> percorsi = new ArrayList<>();
	Collection<Treno> treni = new ArrayList<>();

	public Orari() {
		percorsi.clear();
	}

	/*
	 * 
	 * Il metodo creaPercorso() che accetta come parametro il codice (es.
	 * IC2345) e la categoria e crea un oggetto Percorso
	 **/
	public Percorso creaPercorso(String codice, String categoria) {
		Percorso res =new Percorso(codice, categoria);
		percorsi.add(res);
		return res;
	}

	/*
	 * E' possibile conoscere tutti i percorsi definiti nell'orario tramite il
	 * metodo getPercorsi() che restituisce una collezione di oggetti Percorso
	 */
	public Collection getPercorsi() {
		return percorsi;
	}

	/*
	 * Inoltre è possibile usare il metodo getPercorso() che riceve come
	 * parametro il codice di un percorso e restituisce l'oggetto
	 * corrispondente.
	 */
	public Percorso getPercorso(String codice) {
		Percorso res = null;
		for (Percorso p : percorsi) {
			if (p.getCodice().equals(codice))
				res = p;
		}
		return res;
	}

	/*
	 * La classe Orari offre il metodo nuovoTreno() che riceve come parametri il
	 * codice del percorso e la data in cui viaggia il treno e restituisce
	 * l'oggetto Treno corrsipondente.
	 */
	public Treno nuovoTreno(String codice, int giorno, int mese, int anno) throws PercorsoNonValido {
		boolean found = false;
		Percorso res = null;
		for (Percorso p : percorsi) {
			if (p.getCodice().equals(codice)) {
				res = p;
				found = true; }
		}
		if (!found)
			throw new PercorsoNonValido();
		Treno tre =new Treno(codice, giorno, mese, anno);
		treni.add(tre);
		res.setTreniSuPercorso(tre);
		return tre;
	}

	public List getTreni() {
		return (List)treni;
	}

}
