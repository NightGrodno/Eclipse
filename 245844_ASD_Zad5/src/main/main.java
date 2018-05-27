package main;

import java.util.Scanner;

public class main {
	static int populacja;
	static int iter;
	static int odrzuc;

	public static void main(String[] args) {
		create.create("z5data.csv"); // wczytywanie wprost z pliku tekstowego

		System.out.println("Populacja potencjalnych rozwiązań o rozmiarze (P) z przedziału 30-1000: ");
		Scanner odczyt = new Scanner(System.in); // obiekt do odebrania danych od użytkownika
		populacja = Integer.parseInt(odczyt.nextLine());

		System.out.println("Liczba iteracji (I): ");
		iter = Integer.parseInt(odczyt.nextLine());
		
		System.out.println("Liczba najgorszych rozwiązań odrzucanych przez algorytm (R): ");
		odrzuc = Integer.parseInt(odczyt.nextLine());

		algorytm.doDziela(populacja, iter, odrzuc);

		System.out.println("Zbiór rozwiązań najlepszych: ");


		System.out.println("Wykonanic kolejną serie iteracji (1) albo zakończyc działanie programu (0): ");
		int walue = Integer.parseInt(odczyt.nextLine());
		if (walue == 1) {
			System.out.println("Liczba iteracji I: ");
			iter = Integer.parseInt(odczyt.nextLine());
			algorytm.contin(iter);
		}
		odczyt.close();

	}

}
