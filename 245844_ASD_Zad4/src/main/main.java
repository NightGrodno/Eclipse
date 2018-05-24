package main;

import java.util.Scanner;

public class main {
	static String separator = "-----------------------------------------------------------------------------------------";
	static String departure; // miejsca początku
	static String arrival; // miejsca końca

	public static void main(String[] args) {

		database.create("z4dataHZCW.csv"); // uzupelnienie bazy danych polaczen

		System.out.println("Wprowadz miejsca początku podróży: ");

		Scanner odczyt = new Scanner(System.in); // obiekt do odebrania danych od użytkownika
		departure = odczyt.nextLine();

		System.out.println("Wprowadz miejsca końca podróży: ");
		arrival = odczyt.nextLine();

		odczyt.close();

		System.out.println(separator);
		System.out.printf("%-15s%-15s%-15s%-35s%-10s%n", "Poczatek", "Koniec", "Przesiadek", "Przez", "Odleglosc");
		System.out.println(separator);

		database.search(departure, arrival);
		System.out.println(separator);

		// Bree -> Roke
		// Trondheim-->Rivendell
	}

}
