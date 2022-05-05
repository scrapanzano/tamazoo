package it.unibs.fp.tamazoo;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;


public class TamaMain {

	public static final String NUMERAZIONE_TAMAGOTCHI = "%d Tamagotchi:";
	public static final String MSG_NOME = "Nome Tamagotchi: ";
	public static final String MSG_AFFETTO = "Affetto iniziale di %s: ";
	public static final String MSG_SAZIETA = "Sazietà iniziale di %s: ";
	public static final String MSG_NUM_TAMAGOTCHI = "Inserisci il numero di Tamagotchi da creare: ";
	public static final String MSG_BENVENUTO = "Benvenuto nel fantastico mondo dei Tamagotchi!";
	public final static String MSG_AZIONE = "Cosa vuoi fare?";
	public final static String [] VOCI_MENU = {"Dai carezze.", "Dai biscotti."};
	
	public final static int MIN_ZERO = 0;
	public static final int MAX_AFFETTO = 100;
	public static final int MAX_SAZIETA = 100;
	
	public static void main(String[] args) {
		
		int numTamagotchi;
		ArrayList<Tamagotchi> listaTamagotchi = new ArrayList<>();
		Tamagotchi tamagotchi;
		
		System.out.println(MSG_BENVENUTO);
		
		numTamagotchi = InputDati.leggiInteroConMinimo(MSG_NUM_TAMAGOTCHI, MIN_ZERO);
		
		for(int i = MIN_ZERO; i < numTamagotchi; i++) {
			System.out.println(NUMERAZIONE_TAMAGOTCHI);
			listaTamagotchi.add(tamagotchi = creaTamagotchi());
		}
		
		for(Tamagotchi mioTamagotchi : listaTamagotchi) {
			System.out.println(mioTamagotchi.toString());
		}
		
		MyMenu menu = new MyMenu(MSG_AZIONE, VOCI_MENU);
		
		int scelta;
		
		do {
			
		}while scelta()

	}
	
	public static Tamagotchi creaTamagotchi() {
		String nome = InputDati.leggiStringaNonVuota(MSG_NOME);
		double gradoAffetto = InputDati.leggiIntero(String.format(MSG_AFFETTO, nome), MIN_ZERO, MAX_AFFETTO);
		double gradoSazieta = InputDati.leggiIntero(String.format(MSG_SAZIETA, nome), MIN_ZERO, MAX_SAZIETA);
				
		return new Tamagotchi(nome, gradoAffetto, gradoSazieta);
	}

}
