package orari;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Treno {
	private String codicePercorso;
	private int giorno;
	private int mese;
	private int anno;
	private List<Passaggio> passaggi;
	private int numPassaggi;

	public Treno(String codice, int giorno, int mese, int anno) {
		super();
		this.codicePercorso = codice;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}

	public Percorso getPercorso() {
		for (Percorso p : Orari.percorsi) {
			if (p.getCodice().equals(codicePercorso))
				return p;
		}
		return null;
	}

	public int getGiorno() {
		return giorno;
	}

	public int getMese() {
		return mese;
	}

	public int getAnno() {
		return anno;
	}

	public Passaggio registraPassaggio(String string, int i, int j) throws StazioneNonValida {
		boolean found = false;
		Percorso res = null;
		for (Percorso p : Orari.percorsi) {
			if (p.getCodice().equals(codicePercorso)) {
				found = true;
				res = p;
			}
		}
		if (!found)
			throw new StazioneNonValida();

		found = false;
		@SuppressWarnings("unchecked")
		List<Fermata> fermatequi = res.getFermate();

		Fermata fRes = null;

		for (Fermata f : fermatequi) {
			if (f.getStazione().equals(string))
				found = true;
			fRes = f;
		}
		if (!found)
			throw new StazioneNonValida();

		int ritardo = 60 * (i - fRes.getOre()) + (j - fRes.getMinuti());

		numPassaggi++;
		Passaggio pass = null;
		passaggi.add(pass = new Passaggio(string, i, j, ritardo));
		return pass;

	}

	public boolean arrivato() {
		Percorso res = null;
		for (Percorso p : Orari.percorsi) {
			if (p.getCodice().equals(codicePercorso))
				res = p;
		}
		if (res.getNumFermate() == numPassaggi)
			return true;
		return false;
	}

	public int ritardoMassimo() {
		Optional<Integer> maxrit = passaggi.stream().map(Passaggio::ritardo).max(Comparator.naturalOrder());
		return maxrit.isPresent() ? maxrit.get() : 0;

	}

	public int ritardoFinale() {
		return passaggi.get(numPassaggi - 1).ritardo();
	}

}
