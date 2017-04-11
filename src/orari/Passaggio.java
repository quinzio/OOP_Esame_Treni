package orari;

public class Passaggio {
	private String stazione;
	private int ora, minuto, ritardo;

	public Passaggio(String stazione, int ora, int minuto, int ritardo) {
		super();
		this.stazione = stazione;
		this.ora = ora;
		this.minuto = minuto;
		this.ritardo = ritardo;
	}

	public String getStazione() {
		return stazione;
	}

	public int getOra() {
		return ora;
	}

	public int getMinuti() {
		return minuto;
	}

	public int ritardo() {
		return ritardo;
	}

}
