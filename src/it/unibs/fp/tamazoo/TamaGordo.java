package it.unibs.fp.tamazoo;

/**
 * <p>
 * Il TamaGordo e' un tamagotchi che pensa solo a mangiare ed e' insensibile alle carezze.
 * </p>
 * @author Davide Leone - 72335
 *
 */
public class TamaGordo extends Tamagotchi{

	private static final String NOME_SPECIE = "ingordo";

	public TamaGordo(String _nome, double _gradoSazieta) {
		super(_nome, MAX_AFFETTO, _gradoSazieta);
		this.specie = NOME_SPECIE;
	}
	
	/**
	 * <p>
	 * Modifica dei valori interni se il tamagotchi riceve carezze.
	 * Le modifiche avvengono come segue:
	 * <ul>
	 * <li>Grado di affetto: nessuna modifica (il grado di affetto e' ininfluente e sempre massimo);</li>
	 * <li>Grado di sazieta': diminuisce del doppio rispetto alle altre specie di tamagotchi.</li>
	 * </ul>
	 * </p>
	 * @param numCarezze
	 */
	@Override
	public void riceviCarezze(int numCarezze) {
		this.setGradoSazieta(Math.max(MIN_ZERO, this.getGradoSazieta() - (2 * numCarezze/FATTORE_CAREZZE)));
	}
	
	/**
	 * <p>
	 * Modifica dei valori interni se il tamagotchi riceve biscotti.
	 * Le modifiche avvengono come segue:
	 * <ul>
	 * <li>Grado di sazieta': in questo caso aumenta del 10% per ogni biscotti ricevuto;</li>
	 * <li>Grado di affetto: nessuna modifica (il grado di affetto e' ininfluente e sempre massimo).</li>
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
	 * <p>
	 * Verifica lo stato di tristezza del TamaGordo.
	 * Il TamaGordo e' triste quando:
	 * <ul>
	 * <li>Il grado di sazieta' e' sotto ad una certa soglia (in questo caso minore di 30).</li>
	 * </ul>
	 * </p>
	 * @return True se viene rispettata la condizione di tristezza, False altrimenti.
	 */
	@Override
	public boolean sonoTriste() {
		return this.getGradoSazieta() < SOGLIA_SAZIETA_BASSA;
	}
	
	/**
	 * <p>
	 * Verifica lo stato di morte del TamaGordo.
	 * Il Tamagordo muore solo se mangia poco, mai se mangia troppo.
	 * </p>
	 * @return True se viene rispettata la condizione di morte, False altrimenti.
	 */
	@Override
	public boolean sonoMorto() {
		return this.getGradoSazieta() == MIN_ZERO;
	}

}
