package it.unibs.fp.tamazoo;

public class Tamagotchi {

	private static final String MSG_TO_STRING = "Nome: %s %nAffetto: %.1f %nSazieta: %.1f%n";
	private static final String MSG_MORTE = "Purtroppo il tuo Tamagotchi è morto..";
	private static final String MSG_TRISTEZZA = "Il tuo Tamagotchi è triste..";
	
	public static final int MIN_ZERO = 0;
	public static final int MAX_AFFETTO = 100;
	public static final int MAX_SAZIETA = 100;
	
	private static final double SOGLIA_AFFETTO_BASSO = 30;
	private static final double SOGLIA_SAZIETA_BASSA = 30;
	private static final double SOGLIA_SAZIETA_ALTA = 90;
	
	public static final double INCREMENTO_BISCOTTO = 1.2;
	public static final int FATTORE_CAREZZE = 2;
	public static final int FATTORE_BISCOTTI = 4;
	
	
	
	//ATTRIBUTI PRIVATE
	private String nome;
	private double gradoAffetto;
	private double gradoSazieta;
	
	public Tamagotchi(String _nome, double _gradoAffetto, double _gradoSazieta) {
		this.nome = _nome;
		this.gradoAffetto = _gradoAffetto;
		this.gradoSazieta = _gradoSazieta;
	}
	
	public Tamagotchi(String _nome) {
		this.nome = _nome;
	}
	
	public void riceviCarezze(int numCarezze) {
		this.gradoAffetto = Math.min(this.gradoAffetto + numCarezze, MAX_AFFETTO);
		this.gradoSazieta = Math.max(MIN_ZERO, this.gradoSazieta - numCarezze/FATTORE_CAREZZE);
	}

	public void riceviBiscotti(int numBiscotti) {
		for(int i = MIN_ZERO; i < numBiscotti; i++) 
		{
			this.gradoSazieta = Math.min(this.gradoSazieta * INCREMENTO_BISCOTTO, MAX_SAZIETA);
		}
		
		this.gradoAffetto = Math.max(MIN_ZERO, this.gradoAffetto - numBiscotti/FATTORE_BISCOTTI);
	}
	
	public boolean sonoTriste() {
		boolean checkAffetto = this.gradoAffetto < SOGLIA_AFFETTO_BASSO;
		boolean checkSazieta = this.gradoSazieta < SOGLIA_SAZIETA_BASSA || this.gradoSazieta > SOGLIA_SAZIETA_ALTA;
		
		return checkAffetto || checkSazieta;
	}
	
	public boolean sonoMorto() {
		
		return this.gradoSazieta == MIN_ZERO || this.gradoAffetto == MIN_ZERO || this.gradoSazieta == MAX_SAZIETA;
	}

	
	@Override
	public String toString() {
		
		StringBuffer message = new StringBuffer();
		
		//Per visualizzare i valori interni, rimuovere il commento sottostante.
		message.append(String.format(MSG_TO_STRING, nome, gradoAffetto, gradoSazieta));
		
		if(sonoMorto())
		{
			message.append(MSG_MORTE);
		}
		else
		{
			if(sonoTriste())
			{
				message.append(MSG_TRISTEZZA);
			}
		}
			
		return message.toString();
	}

	
	//GETTERS & SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getGradoAffettivo() {
		
		return gradoAffetto;
	}

	public void setGradoAffettivo(double _gradoAffetto) {
		
		this.gradoAffetto = rangeValore(_gradoAffetto, MIN_ZERO, MAX_AFFETTO);
	}

	public double getGradoSazieta() {

		return gradoSazieta;
	}

	public void setGradoSazieta(double _gradoSazieta) {
		
		this.gradoSazieta = rangeValore(_gradoSazieta, MIN_ZERO, MAX_SAZIETA);
	}
	
	public void setSazietaAffetivita(double gradoSazieta, double gradoAffetto) {
		setGradoSazieta(gradoSazieta);
		setGradoAffettivo(gradoAffetto);
	}
	
	//Metodo che non permette ad Affetto e Sazieta ad eccedere il range dei valori validi
	public double rangeValore(double valore, int min, int max)
	{
		if(valore < min)
		{
			return min;
		}
		else if(valore > max)
		{
			return max;
		}
		
		return valore;
	}
}
