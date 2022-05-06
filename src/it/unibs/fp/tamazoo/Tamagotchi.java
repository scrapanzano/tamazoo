package it.unibs.fp.tamazoo;

/**
 * <p>
 * Un Tamagotchi e' un'entita' software in grado di recepire stimoli dall'esterno 
 * che determinano la sua sopravvivenza e il suo grado di benessere: specie 
 * diverse di Tamagotchi reagiscono in modo diverso a diversi stimoli.
 * </p>
 * @author Davide Leone - 723335.
 *
 */
public class Tamagotchi {

	private static final String MSG_TO_STRING = "Nome: %s %nAffetto: %.1f %nSazieta': %.1f%n";
	private static final String MSG_MORTE = "Purtroppo il tuo Tamagotchi e' morto..";
	private static final String MSG_TRISTEZZA = "Sono triste..";
	
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
	
	/**
	 * <p>
	 * Modifica dei valori interni se il tamagotchi riceve carezze.
	 * Le modifiche avvengono come segue:
	 * <ul>
	 * <li>Grado di affetto: aumenta in base alle carezze ricevute (rispettando il suo valore massimo);</li>
	 * <li>Grado di sazieta': diminuisce della meta delle carezze ricevute (rispettando il suo valore minimo).</li>
	 * </ul>
	 * </p>
	 * @param numCarezze
	 */
	public void riceviCarezze(int numCarezze) {
		this.gradoAffetto = Math.min(this.gradoAffetto + numCarezze, MAX_AFFETTO);
		this.gradoSazieta = Math.max(MIN_ZERO, this.gradoSazieta - numCarezze/FATTORE_CAREZZE);
	}

	/**
	 * <p>
	 * Modifica dei valori interni se il tamagotchi riceve biscotti.
	 * Le modigiche avvengono come segue:
	 * <ul>
	 * <li>Grado di sazieta': in questo caso aumenta del 10% per ogni biscotti ricevuto;</li>
	 * <li>Gradi di affetto: in questo caso diminuisce dell' 1/4 dei biscotti ricevuti.</li>
	 * </ul>
	 * </p>
	 * @param numBiscotti
	 */
	public void riceviBiscotti(int numBiscotti) {
		for(int i = MIN_ZERO; i < numBiscotti; i++) 
		{
			this.gradoSazieta = Math.min(this.gradoSazieta * INCREMENTO_BISCOTTO, MAX_SAZIETA);
		}
		
		this.gradoAffetto = Math.max(MIN_ZERO, this.gradoAffetto - numBiscotti/FATTORE_BISCOTTI);
	}
	
	/**
	 * <p>
	 * Verifica lo stato di tristezza del tamagotchi.
	 * Il tamagotchi e' triste quando:
	 * <ul>
	 * <li>Il grado di affetto e' sotto una certa soglia (in questo caso minore di 30); </li>
	 * <li>Il grado di sazieta' e' troppo basso o troppo alto (in questo caso minore di 30 o maggiore di 90).</li>
	 * </ul>
	 * </p>
	 * @return
	 */
	public boolean sonoTriste() {
		boolean checkAffetto = this.gradoAffetto < SOGLIA_AFFETTO_BASSO;
		boolean checkSazieta = this.gradoSazieta < SOGLIA_SAZIETA_BASSA || this.gradoSazieta > SOGLIA_SAZIETA_ALTA;
		
		return checkAffetto || checkSazieta;
	}
	
	/**
	 * <p>
	 * Verifica lo stato di morte del tamagotchi.
	 * Il tamagotchi e' morto quando:
	 * <ul>
	 * <li>Uno dei due valori interni raggiunge 0;</li>
	 * <li>Il grado di sazieta' raggiunge il massimo.</li>
	 * </ul>
	 * </p>
	 * @return
	 */
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
	
	/**
	 * <p>Metodo che non permette al grado di affetto e di sazieta' 
	 * di eccedere il range dei valori validi.
	 * </p>
	 * @param valore
	 * @param min
	 * @param max
	 * @return valore che rispetta il range
	 */
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
