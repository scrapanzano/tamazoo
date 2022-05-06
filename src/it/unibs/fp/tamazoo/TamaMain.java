package it.unibs.fp.tamazoo;

import java.util.ArrayList;

import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;


public class TamaMain {

	public static final String MSG_USCITA = "Uscita dal Tamazoo in corso...";
	public static final String MSG_MORTE_TAMAGOTCHI = "Purtroppo tutti i tuoi tamagotchi sono morti...";
	private static final String MSG_CONFERMA_USCITA = "Sei sicuro di voler uscire? ";
	public static final String AZIONE_COMPIUTA_BISCOTTI = "Hai dato biscotti a %s.";
	public static final String AZIONE_COMPIUTA_CAREZZE = "Hai dato carezze a %s.";
	public static final String NUMERAZIONE_TAMAGOTCHI = "%d Tamagotchi:";
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
			
		indexScelta = menu.scegli();
		
		escludiTamagotchiMorti(listaTamagotchi);
		finito = eseguiSceltaUtente(listaTamagotchi, indexScelta);
		
		
		}while(listaTamagotchi.size() != 0 && !finito);
		
		if(listaTamagotchi.size() == 0)
		{
			System.out.println(MSG_MORTE_TAMAGOTCHI);
		}
		
		System.out.println(MSG_USCITA);

	}
	
	private static boolean eseguiSceltaUtente(ArrayList<Tamagotchi> listaTamagotchi, int scelta) {
		switch(scelta) {
		
		case 0: return InputDati.yesOrNo(MSG_CONFERMA_USCITA);
		
		case 1: //Dai carezze
				int numCarezze = EstrazioniCasuali.estraiIntero(MIN_ZERO, MAX_CAREZZE);
			    
				for(Tamagotchi tamagotchi : listaTamagotchi) {
					tamagotchi.riceviCarezze(numCarezze);
					System.out.println(String.format(AZIONE_COMPIUTA_CAREZZE, tamagotchi.getNome()));
					System.out.println(tamagotchi.toString());
				}
				break;
				
		case 2: //Dai biscotti
				int numBiscotti = EstrazioniCasuali.estraiIntero(MIN_ZERO, MAX_BISCOTTI);
				
				for(Tamagotchi tamagotchi : listaTamagotchi) {
					tamagotchi.riceviBiscotti(numBiscotti);
					System.out.println(String.format(AZIONE_COMPIUTA_BISCOTTI, tamagotchi.getNome()));
					System.out.println(tamagotchi.toString());
				}
			   break;
		
		}
		
		return false;
	}

	/**
	 * <p> 
	 * Crea un tamagotchi inserendo: 
	 * <ul>
	 * <li>Nome del tamagotchi;</li>
	 * <li>Grado di affetto iniziale;</li>
	 * <li>Grado di sazieta iniziale.</li>
	 * </ul>
	 * </p>
	 * @return
	 */
	public static Tamagotchi creaTamagotchi() {
		String nome = InputDati.leggiStringaNonVuota(MSG_NOME);
		double gradoAffetto = InputDati.leggiIntero(String.format(MSG_AFFETTO, nome), MIN_ZERO, MAX_AFFETTO);
		double gradoSazieta = InputDati.leggiIntero(String.format(MSG_SAZIETA, nome), MIN_ZERO, MAX_SAZIETA);
				
		return new Tamagotchi(nome, gradoAffetto, gradoSazieta);
	}
	
	/**
	 * <p>Rimuove dalla lista dei tamagotchi tutti quelli morti.</p>
	 * @param listaTamagotchi
	 */
	public static void escludiTamagotchiMorti(ArrayList<Tamagotchi> listaTamagotchi) {
		for(int i = 0; i < listaTamagotchi.size(); i++) {
			Tamagotchi tamagotchi = listaTamagotchi.get(i);
			if(tamagotchi.sonoMorto())
			{
				listaTamagotchi.remove(i);
			}
		}
	}

}
