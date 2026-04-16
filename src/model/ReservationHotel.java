package model;

public class ReservationHotel extends Reservation {
	int nbLitsSimples;
	int nbLitsDoubles;
	int numeroChambre;
	public ReservationHotel(int jour, int mois, int nbLitsSimples, int nbLitsDoubles, int numeroChambre) {
		super(jour, mois);
		this.nbLitsSimples = nbLitsSimples;
		this.nbLitsDoubles = nbLitsDoubles;
		this.numeroChambre = numeroChambre;
	}
	
	@Override
	public String toString() {
		return super.toString() + nbLitsSimples + " lits simples et " + nbLitsDoubles + " lits doubles, chambre N°" + numeroChambre;
	}
}
