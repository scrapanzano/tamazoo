package it.unibs.fp.tamazoo;

public class TamaGordo extends Tamagotchi{

	private String specie;
	
	public TamaGordo(String _nome, double _gradoAffetto, double _gradoSazieta) {
		super(_nome, _gradoAffetto, _gradoSazieta);
		this.specie = "TamaGordo";
	}
	
	
	
	@Override
	public boolean sonoTriste() {
		return this.getGradoSazieta() < SOGLIA_SAZIETA_BASSA;
	}

}
