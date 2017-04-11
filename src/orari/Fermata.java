package orari;

public class Fermata {
	private String stazione;
	private Integer ora;
	private Integer minuto;

	public Fermata(String stazione, Integer ora, Integer minuto) {
		super();
		this.stazione = stazione;
		this.ora = ora;
		this.minuto = minuto;
	}

	/*
	 * La classe Fermata offre i metodi getStazione(), getOra(), getMinuti().
	 */
	public String getStazione() {
		return stazione;
	}

	public int getOre() {
		return ora;
	}

	public int getMinuti() {
		return minuto;
	}

}
