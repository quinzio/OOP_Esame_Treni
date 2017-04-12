package orari;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 R1 - Definizione Percorsi
 Il sistema agisce tramite la classe Orari. 
 La prima fase dell'uso consiste nel definire i percorsi dei treni.
 */

public class Percorso {

	private String codice;
	private String categoria;
	private List<Fermata> fermate = new ArrayList<>();
	private List<Treno> treniSuPercorso = new ArrayList<>();
	private int numFermate;

	public void setTreniSuPercorso(Treno treno) {
		this.treniSuPercorso.add(treno);
	}

	/*
	 * Un percorso per default non è straordinario
	 */
	private boolean straordinario = false;

	/*
	 * La categoria di un treno può essere "Intercity", "Eurostar",
	 * "Interregionale", o "Regionale".
	 */
	private enum Categoria {
		Intercity, Eurostar, Interregionale, Regionale;
	}

	public Percorso(String codice, String categoria) {
		super();
		this.codice = codice;
		boolean found = false;
		for (Categoria c : Categoria.values())
			if (categoria.equals(c.toString()))
				found = true;
		this.categoria = categoria;
	}

	/*
	 * La classe Percorso che offre i metodi getCodice(), getCategoria() per
	 * leggere il codice e la categoria
	 */
	public String getCodice() {
		return codice;
	}

	public String getCategoria() {
		return categoria;
	}

	/*
	 * inoltre è a disposizione il metodo isStraordinario() per conoscere il
	 * tipo
	 */
	public boolean isStraordinario() {
		return straordinario;
	}

	/*
	 * I treni possono essere ordinari oppure straordinari, per definire il tipo
	 * si usa il metodo setStraordinario() che riceve un parametro boolean
	 */
	public void setStraordinario(boolean b) {
		this.straordinario = b;
	}

	/*
	 * La classe Percorso fornisce il metodo aggiungiFermata() che riceve come
	 * parametri il nome della stazione, e l'orario previsto in ore e minuti.
	 * Questo metodo restituisce l'oggetto Fermata corrispondente.
	 */
	public Fermata aggiungiFermata(String nomeStazione, int ore, int minuti) {
		numFermate++;
		Fermata f = new Fermata(nomeStazione, ore, minuti);
		fermate.add(f);
		return f;
	}

	public int getNumFermate() {
		return numFermate;
	}

	/*
	 * Per ottenere l'elenco di tutte le fermate di un percorso si usa il metodo
	 * getFermate() della classe Percorso che restituisce la lista degli oggetti
	 * Fermata ordinati in base all'orario.
	 */
	public List getFermate() {
		List<Fermata> fermate_byh = new ArrayList<>(fermate);
		fermate_byh.sort(Comparator.comparing(Fermata::getOre).thenComparing(
				Fermata::getMinuti));
		return fermate_byh;
	}

	public int ritardoMassimo() {
		Optional<Integer> ritardo = null;
		ritardo = treniSuPercorso.stream().map(Treno::ritardoFinale)
				.collect(Collectors.maxBy(Comparator.naturalOrder()));
		return ritardo.isPresent() ? ritardo.get() : 0;
	}

	public int ritardoMedio() {
		Double ritardo = null;
		ritardo = treniSuPercorso.stream().map(Treno::ritardoFinale)
				.collect(Collectors.averagingInt(Integer::new));
		Integer intrit = ritardo.intValue();
		return intrit;
	}

	public List getTreni() {
		treniSuPercorso.sort(Comparator
				.comparing(Treno::getAnno, Comparator.reverseOrder())
				.thenComparing(
						Comparator.comparing(Treno::getMese,
								Comparator.reverseOrder()))
				.thenComparing(
						Comparator.comparing(Treno::getGiorno,
								Comparator.reverseOrder())));
		return treniSuPercorso;
	}

}
