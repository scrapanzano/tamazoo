package it.unibs.fp.tamazoo;

import java.util.ArrayList;

import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;


public class TamaMain {

	public static final String MSG_USCITA = "%nUscita dal Tamazoo in corso...";
	public static final String MSG_MORTE_TAMAGOTCHI = "%nPurtroppo tutti i tuoi tamagotchi sono morti...";
	public static final String MSG_CONFERMA_USCITA = "Sei sicuro di voler uscire? ";
	public static final String AZIONE_COMPIUTA_BISCOTTI = "Hai dato biscotti a %s.";
	public static final String AZIONE_COMPIUTA_CAREZZE = "Hai dato carezze a %s.";
	public static final String NUMERAZIONE_TAMAGOTCHI = "%n--Tamagotchi %d--";
	public static final String MSG_NOME = "Nome Tamagotchi: ";
	public static final String MSG_AFFETTO = "Affetto iniziale di %s: ";
	public static final String MSG_SAZIETA = "Sazieta' iniziale di %s: ";
	public static final String MSG_NUM_TAMAGOTCHI = "Inserisci il numero di Tamagotchi da creare: ";
	public static final String MSG_BENVENUTO = "Benvenuto nel fantastico mondo dei Tamagotchi!";
	public final static String MSG_AZIONE = "Cosa vuoi fare?";
	public final static String [] VOCI_MENU = {"Dai carezze.", "Dai biscotti."};
	
	public final static int MIN_ZERO = 0;
	public static final int MAX_AFFETTO = 100;
	public static final int MAX_SAZIETA = 100;
	public static final int MAX_CAREZZE = 10;
	public static final int MAX_BISCOTTI = 5;
	
	public static void main(String[] args) {
		
		int numTamagotchi;
		ArrayList<Tamagotchi> listaTamagotchi = new ArrayList<>();
		Tamagotchi tamagotchi;
		
		System.out.println(MSG_BENVENUTO);
		
		numTamagotchi = InputDati.leggiInteroConMinimo(MSG_NUM_TAMAGOTCHI, MIN_ZERO);
		
		for(int i = MIN_ZERO; i < numTamagotchi; i++) {
			System.out.println(String.format(NUMERAZIONE_TAMAGOTCHI, i+1));
			listaTamagotchi.add(tamagotchi = creaTamagotchi());
		}
		
		int indexScelta;
		MyMenu menu = new MyMenu(MSG_AZIONE, VOCI_MENU);
		boolean finito = false;
		do {
			
		System.out.println();	
		indexScelta = menu.scegli();
		System.out.println();
		
		finito = eseguiSceltaUtente(listaTamagotchi, indexScelta);
		
		finito = rimuoviTamagotchiMorti(listaTamagotchi);
		
		}while(!finito);
		
		
		
		if(listaTamagotchi.size() == 0)
		{
			System.out.println(String.format(MSG_MORTE_TAMAGOTCHI));
		}
		
		System.out.println(String.format(MSG_USCITA));

	}
	
	/**
	 * <p>
	 * Esegue diverse azioni in base alla scelta dell'utente.
	 * In particolare:
	 * <ul>
	 * <li>Uscita dal programma in seguito ad un messaggio di conferma;</li>
	 * <li>Dare carezze a tutti i tamagotchi;</li>
	 * <li>Dare biscotti a tutti i tamagotchi.</li>
	 * </ul>
	 * Tutti i tamagotchi morti vengono esclusi dalle iterazioni successive.
	 * In questo senso si visionino anche i metodi citati.
	 * </p>
	 * @param listaTamagotchi
	 * @param scelta
	 * @see daiCarezze(listaTamagotchi).
	 * @see daiBiscotti(listaTamagotchi).
	 * @return
	 */
	private static boolean eseguiSceltaUtente(ArrayList<Tamagotchi> listaTamagotchi, int scelta) {
		switch(scelta) {
		
		case 0: return InputDati.yesOrNo(MSG_CONFERMA_USCITA);
		
		case 1: daiCarezze(listaTamagotchi);	
				break;
				
		case 2: daiBiscotti(listaTamagotchi);
			    break;
		
		}
		
		return false;
	}

	/**
	 * <p> 
	 * Crea un tamagotchi inserendo: 
	 * <ul>
	 * <li>Nome del tamagotchi;</li>
	 * <li>Grado di affetto iniziale stabilito in maniera casuale;</li>
	 * <li>Grado di sazieta iniziale stabilito in maniera casuale.</li>
	 * </ul>
	 * La specie del tamagotchi verra' scelta con un'estrazione casuale.
	 * Di seguito le casistiche sui diversi risultati dell'estrazione:
	 * <ol>
	 * <li>Il Tamagotchi sara' della specie base;</li>
	 * <li>Il Tamagotchi sara' della specie "TamaTriste".</li>
	 * </ol>
	 * </p>
	 * @return tamagotchi di diversa specie.
	 */
	public static Tamagotchi creaTamagotchi() {
		String nome = InputDati.leggiStringaNonVuota(MSG_NOME);
		double gradoAffetto = EstrazioniCasuali.estraiIntero(MIN_ZERO, MAX_AFFETTO);
		double gradoSazieta = EstrazioniCasuali.estraiIntero(MIN_ZERO, MAX_SAZIETA);
		
		int specie = EstrazioniCasuali.estraiIntero(1, 2);
		
		switch(specie) {
		case 0: return new Tamagotchi(nome, gradoAffetto, gradoSazieta);
		case 1: return new TamaTriste(nome, gradoAffetto, gradoSazieta);
		}
				
		return null;
	}
	
	/**
	 * <p>
	 * Da' ad ogni tamagotchi non morto lo stesso quantitativo di carezze.
	 * Il numero di carezze e' casuale. 
	 * L'utente viene informato circa lo stato di ogni tamagotchi.
	 * </p> 
	 * @param listaTamagotchi
	 */
	public static void daiCarezze(ArrayList<Tamagotchi> listaTamagotchi) {
		int numCarezze = EstrazioniCasuali.estraiIntero(MIN_ZERO, MAX_CAREZZE);
	    
		for(Tamagotchi tamagotchi : listaTamagotchi) {
			tamagotchi.riceviCarezze(numCarezze);
			System.out.println(String.format(AZIONE_COMPIUTA_CAREZZE, tamagotchi.getNome()));
			System.out.println(tamagotchi.toString());
		}
	}
	
	/**
	 * <p>
	 * Da' ad ogni tamagotchi non morto lo stesso quantitativo di biscotti.
	 * Il numero di biscotti e' casuale. 
	 * L'utente viene informato circa lo stato di ogni tamagotchi.
	 * </p> 
	 * @param listaTamagotchi
	 */
	public static void daiBiscotti(ArrayList<Tamagotchi> listaTamagotchi) {
		int numBiscotti = EstrazioniCasuali.estraiIntero(MIN_ZERO, MAX_BISCOTTI);
		
		for(Tamagotchi tamagotchi : listaTamagotchi) {
			tamagotchi.riceviBiscotti(numBiscotti);
			System.out.println(String.format(AZIONE_COMPIUTA_BISCOTTI, tamagotchi.getNome()));
			System.out.println(tamagotchi.toString());
		}
	}
	
	/**
	 * <p>
	 * Rimuove tutti i tamagotchi morti. 
	 * </p>
	 * @param listaTamagotchi
	 * @return True se non sono piu' presenti tamagotchi vivi, false altrimenti.
	 */
	public static boolean rimuoviTamagotchiMorti(ArrayList<Tamagotchi> listaTamagotchi) {
		for(int i = 0; i < listaTamagotchi.size(); i++) {
			Tamagotchi mioTamagotchi = listaTamagotchi.get(i);
			if(mioTamagotchi.sonoMorto())
			{
				listaTamagotchi.remove(i);
			}
		}
		
		if(listaTamagotchi.size() == 0)
		{
			return true;
		}
		
		return false;
	}
}
