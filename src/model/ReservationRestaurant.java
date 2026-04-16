package model;

public class ReservationRestaurant extends Reservation {
	int numService;
	int numTable;
	public ReservationRestaurant(int jour, int mois, int numService, int numTable) {
		super(jour, mois);
		this.numService = numService;
		this.numTable = numTable;
	}
	
	@Override
	public String toString() {
		String numServiceString = "premier";
		if (numService == 2)
			numServiceString = "deuxieme";
		return super.toString() + "Table " + numTable + " pour le " + numServiceString + " service.";
	}
}
