package main;

import java.util.Scanner;

public class main {
	static int populacja;
	static int iter; // liczba iteracji
	static int odrzuc; // liczbą najgorszych rozwiązań odrzucanych przez algorytm

	public static void main(String[] args) {

		create.create("z5data_beta.csv"); // wczytywanie wprost z pliku tekstowego

		Scanner odczyt = new Scanner(System.in); // obiekt do odebrania danych od użytkownika

		System.out.println("Populacja potencjalnych rozwiązań o rozmiarze (P) z przedziału 30-1000: ");
		populacja = Integer.parseInt(odczyt.nextLine());

		while (populacja > 1000 || populacja < 30) { // powinien operować na P z przedziału 30-1000
			System.out.println("Wpisz P z przedziału 30-1000: ");
			populacja = Integer.parseInt(odczyt.nextLine());
		}

		System.out.println("Liczba iteracji (I): ");
		iter = Integer.parseInt(odczyt.nextLine());

		System.out.println("Liczba najgorszych rozwiązań odrzucanych przez algorytm (R): ");
		odrzuc = Integer.parseInt(odczyt.nextLine());

		while (odrzuc >= populacja) { // liczba odrzucanych nie wieksza od populacj
			System.out.println("Wpisz R < P: ");
			odrzuc = Integer.parseInt(odczyt.nextLine());
		}

		algorytm.doIteracje(populacja, iter, odrzuc);
		odczyt.close();

	}

}
