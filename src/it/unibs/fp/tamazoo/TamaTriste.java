package it.unibs.fp.tamazoo;

/**
 * <p>
 * Il TamaTriste e' un tamagotchi che e' sempre infelice, indipendentemente 
 * da quante carezze o biscotti riceve (pero' non muore mai di infelicita').
 * </p>
 * @author Davide Leone - 723335.
 *
 */
public class TamaTriste extends Tamagotchi{
	
	private static final String NOME_SPECIE = "triste";
	
	public TamaTriste(String _nome,  double _gradoSazieta) {
		super(_nome, MIN_ZERO, _gradoSazieta);
		this.specie = NOME_SPECIE;
	}
	
	/**
	 * <p>
	 * Modifica dei valori interni se il tamagotchi riceve carezze.
	 * Le modifiche avvengono come segue:
	 * <ul>
	 * <li>Grado di affetto: nessuna modifica (il TamaTriste e' ininfluente al numero di carezze ricevute);</li>
	 * <li>Grado di sazieta': diminuisce della meta' delle carezze ricevute (rispettando il suo valore minimo).</li>
	 * </ul>
	 * </p>
	 * @param numCarezze
	 */
	@Override
	public void riceviCarezze(int numCarezze) {
		this.setGradoSazieta(Math.max(MIN_ZERO, this.getGradoSazieta() - numCarezze/FATTORE_CAREZZE));
	}
	
	/**
	 * <p>
	 * Modifica dei valori interni se il tamagotchi riceve biscotti.
	 * Le modifiche avvengono come segue:
	 * <ul>
	 * <li>Grado di sazieta': in questo caso aumenta del 10% per ogni biscotti ricevuto;</li>
	 * <li>Grado di affetto: nessuna modifica (il TamaTriste e' ininfluente al numero di carezze ricevute).</li>
	 * </ul>
	 * </p>
	 * @param numBiscotti
	 */
	@Override
	public void riceviBiscotti(int numBiscotti) {
		for(int i = MIN_ZERO; i < numBiscotti; i++) 
		{
			this.setGradoSazieta(Math.min(this.getGradoSazieta() * INCREMENTO_BISCOTTO, MAX_SAZIETA));
		}
	}
	
	/**
	 * <p>Verifica lo stato di tristezza del TamaTriste.</p>
	 * @return Sempre True, in quanto TamaTriste ha la caratteristica di essere sempre triste.
	 */
	@Override
	public boolean sonoTriste() {
		return true;
	}
	
	/**
	 * <p>
	 * Verifica lo stato di morte del TamaTriste.
	 * Il TamaTriste muore solo se non mangia abbastanza o se mangia troppo
	 * (come nel caso base).
	 * </p>
	 * return True se viene rispettata almeno una delle condizioni di tristezza, False altrimenti. 
	 */
	@Override
	public boolean sonoMorto() {
		return this.getGradoSazieta() == MIN_ZERO || this.getGradoSazieta() == MAX_SAZIETA;
	}
	
	@Override
	public String toString() {
		StringBuffer message = new StringBuffer();
		
		message.append(String.format(MSG_TO_STRING, this.getNome(), this.getGradoAffettivo(), this.getGradoSazieta(), this.specie));
	
		if(sonoMorto())
		{
			message.append(String.format(MSG_MORTE, this.getNome()));
		}
		else
		{
			message.append(String.format(MSG_TRISTEZZA, this.getNome()));
		}
		
		return message.toString();
	}
	
	
}
