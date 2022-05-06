package it.unibs.fp.tamazoo;

public class TamaTriste extends Tamagotchi{
	
	public TamaTriste(String _nome, double _gradoAffetto, double _gradoSazieta) {
		super(_nome, _gradoAffetto, _gradoSazieta);
	}
	
	/**
	 * @return Sempre True, in quanto TamaTriste ha la caratteristica di essere sempre triste.
	 */
	@Override
	public boolean sonoTriste() {
		return true;
	}
	
	/**
	 * <p>
	 * Il TamaTriste muore solo se non mangia abbastanza o se mangia troppo
	 * (come nel caso base).
	 * </p>
	 * return True se vengono rispettate le condizioni della morte, False altrimenti. 
	 */
	@Override
	public boolean sonoMorto() {
		return this.getGradoAffettivo() == MIN_ZERO || this.getGradoAffettivo() == MAX_SAZIETA;
	}
	
	@Override
	public String toString() {
		StringBuffer message = new StringBuffer();
		
		message.append(String.format(MSG_TO_STRING, this.getNome(), this.getGradoAffettivo(), this.getGradoSazieta()));
	
		if(sonoMorto())
		{
			message.append(MSG_MORTE);
		}
		else
		{
			message.append(String.format(MSG_TRISTEZZA, this.getNome()));
		}
		
		return message.toString();
	}
	
	
}
