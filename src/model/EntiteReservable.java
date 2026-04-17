package model;

public abstract class EntiteReservable<F extends Formulaire> {
	protected CalendrierAnnuel calendrier;
	private int numero;

	protected EntiteReservable() {
		calendrier = new CalendrierAnnuel();
	}

	public boolean estLibre(F formulaire) {
		return calendrier.estLibre(formulaire.getJour(), formulaire.getMois());
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public abstract boolean compatible(F formulaire);

	public abstract Reservation reserver(F formulaire);
}