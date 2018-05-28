package main;

import java.util.Scanner;

public class main {
	static int populacja;
	static int iter;
	static int odrzuc;

	public static void main(String[] args) {

		create.create("z5data_beta.csv"); // wczytywanie wprost z pliku tekstowego

		System.out.println("Populacja potencjalnych rozwiązań o rozmiarze (P) z przedziału 30-1000: ");
		Scanner odczyt = new Scanner(System.in); // obiekt do odebrania danych od użytkownika
		populacja = Integer.parseInt(odczyt.nextLine());
		while (populacja > 1000 || populacja < 30) {
			System.out.println("Wpisz P z przedziału 30-1000: ");
			populacja = Integer.parseInt(odczyt.nextLine());
		}
		System.out.println("Liczba iteracji (I): ");
		iter = Integer.parseInt(odczyt.nextLine());

		System.out.println("Liczba najgorszych rozwiązań odrzucanych przez algorytm (R): ");
		odrzuc = Integer.parseInt(odczyt.nextLine());
		while (odrzuc >= populacja) {
			System.out.println("Wpisz R < P: ");
			odrzuc = Integer.parseInt(odczyt.nextLine());
		}

		algorytm.doIteracje(populacja, iter, odrzuc);

		odczyt.close();

	}

}
