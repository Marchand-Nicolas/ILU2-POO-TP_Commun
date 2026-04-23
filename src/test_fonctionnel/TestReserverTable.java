package test_fonctionnel;

import control.ControlConnecterClient;
import control.ControlCreerClient;
import control.ControlReserverTable;
import frontiere.BoundaryConnecterClient;
import frontiere.BoundaryCreerClient;
import frontiere.BoundaryReserverTable;
import model.CarnetClientele;
import model.Client;
import model.Reservation;
import model.Restaurant;

public class TestReserverTable {
	public static void main(String[] args) {
		System.out.println("---------- CREATION CLIENT ----------");
		CarnetClientele carnetClientele = new CarnetClientele();
		Restaurant restaurant = new Restaurant();

		// Deux tables compatibles pour 3 convives: tables 1 et 2.
		restaurant.ajouterTable(3);
		restaurant.ajouterTable(4);

		ControlCreerClient controlCreerClient = new ControlCreerClient(carnetClientele);
		BoundaryCreerClient boundaryCreerClient = new BoundaryCreerClient(controlCreerClient);
		boundaryCreerClient.creerClient();

		System.out.println("\n---------- CONNECTION CLIENT ----------");
		ControlConnecterClient controlConnecterClient = new ControlConnecterClient(carnetClientele);
		BoundaryConnecterClient boundaryConnecterClient = new BoundaryConnecterClient(controlConnecterClient);
		int numClient = boundaryConnecterClient.connecterClient();

		if (numClient != -1) {
			System.out.println("\n---------- RESERVER TABLE ----------");
			ControlReserverTable controlReserverTable = new ControlReserverTable(restaurant, carnetClientele);
			BoundaryReserverTable boundaryReserverTable = new BoundaryReserverTable(controlReserverTable);
			boundaryReserverTable.reserverTable(numClient);
		}

		System.out.println("\n---------- CONTROL DES DONNEES ----------");
		if (numClient != -1) {
			Client client = carnetClientele.getClient(numClient);
			System.out.println("Client :");
			System.out.println(client);
			System.out.println("Reservation :");
			Reservation reservation = client.getDerniereReservation();
			if (reservation != null) {
				String reservationAffichee = reservation.toString().replace("Table ", "table n\u00b0");
				System.out.println(reservationAffichee);
			}
		}
	}
}
