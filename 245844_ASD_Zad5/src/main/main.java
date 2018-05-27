package main;

import java.util.Scanner;

public class main {
	static int populacja;
	static int liter;

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		create.create("z5data.csv");

		System.out.println("Populacja potencjalnych rozwiązań o rozmiarze P z przedziału 30-1000: ");
		Scanner odczyt = new Scanner(System.in); // obiekt do odebrania danych od użytkownika
		populacja = Integer.parseInt(odczyt.nextLine());

		System.out.println("Liczba iteracji I: ");
		liter = Integer.parseInt(odczyt.nextLine());
		odczyt.close();

		algorytm.doDziela(populacja, liter);

	}

}
