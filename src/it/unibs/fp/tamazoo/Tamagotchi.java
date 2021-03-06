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

	public static final String MSG_TO_STRING = "Nome: %s %nAffetto: %.1f %nSazieta': %.1f %nSpecie: %s%n";
	public static final String MSG_MORTE = "%s: morto..%n";
	public static final String MSG_TRISTEZZA = "%s dice: \"Sono triste..\".%n";
	
	private static final String NOME_SPECIE = "base";
	
	public static final int MIN_ZERO = 0;
	protected static final int MAX_AFFETTO = 100;
	protected static final int MAX_SAZIETA = 100;
	
	protected static final double SOGLIA_AFFETTO_BASSO = 30;
	protected static final double SOGLIA_SAZIETA_BASSA = 30;
	protected static final double SOGLIA_SAZIETA_ALTA = 90;
	
	protected static final double INCREMENTO_BISCOTTO = 1.2;
	protected static final int FATTORE_CAREZZE = 2;
	protected static final int FATTORE_BISCOTTI = 4;
	
	
	
	//ATTRIBUTI PRIVATE
	private String nome;
	private double gradoAffetto;
	private double gradoSazieta;
	protected String specie;
	
	public Tamagotchi(String _nome, double _gradoAffetto, double _gradoSazieta) {
		this.nome = _nome;
		this.gradoAffetto = _gradoAffetto;
		this.gradoSazieta = _gradoSazieta;
		this.specie = NOME_SPECIE;
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
	 * <li>Grado di sazieta': diminuisce della meta' delle carezze ricevute (rispettando il suo valore minimo).</li>
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
	 * Le modifiche avvengono come segue:
	 * <ul>
	 * <li>Grado di sazieta': in questo caso aumenta del 10% per ogni biscotti ricevuto;</li>
	 * <li>Grado di affetto: in questo caso diminuisce dell' 1/4 dei biscotti ricevuti.</li>
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
	 * @return True se viene rispettata almeno una delle due condizioni di tristezza, False altrimenti.
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
	 * @return True se viene rispettata almeno una delle condizioni di morte, False altrimenti.
	 */
	public boolean sonoMorto() {
		
		return this.gradoSazieta == MIN_ZERO || this.gradoAffetto == MIN_ZERO || this.gradoSazieta == MAX_SAZIETA;
	}

	
	@Override
	public String toString() {
		
		StringBuffer message = new StringBuffer();
		
		//Per visualizzare i valori interni, rimuovere il commento sottostante.
		message.append(String.format(MSG_TO_STRING, this.nome, this.gradoAffetto, this.gradoSazieta, this.specie));
		
		if(sonoMorto())
		{
			message.append(String.format(MSG_MORTE, this.nome));
		}
		else
		{
			if(sonoTriste())
			{
				message.append(String.format(MSG_TRISTEZZA, this.nome));
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
		
		return this.gradoAffetto;
	}

	public void setGradoAffettivo(double _gradoAffetto) {
		
		this.gradoAffetto = rangeValore(_gradoAffetto, MIN_ZERO, MAX_AFFETTO);
	}

	public double getGradoSazieta() {

		return this.gradoSazieta;
	}

	public void setGradoSazieta(double _gradoSazieta) {
		
		this.gradoSazieta = rangeValore(_gradoSazieta, MIN_ZERO, MAX_SAZIETA);
	}
	
	public void setSazietaAffetivita(double gradoSazieta, double gradoAffetto) {
		setGradoSazieta(gradoSazieta);
		setGradoAffettivo(gradoAffetto);
	}
	
	public String getSpecie() {
		
		return specie;
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
