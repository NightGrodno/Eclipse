package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class algorytm {
	static int FC_min = 0; // najliepsza całkowita przebyta droga
	static String key_min = ""; // najliepsza ciąg
	static int liczbaEl = create.tab.length; // liczba szaf
	static Map<String, Integer> popRozw = new HashMap<String, Integer>(); // poczatkowa mapa populacji potencjalnych
																			// rozwiązań
	static Map<String, Integer> mapRozw = new HashMap<String, Integer>(); // mapa populacji potencjalnych rozwiązań
	static String osobnik = "";
	static ArrayList<String> alphabet = new ArrayList<String>(
			Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"));

	public static void doIteracje(int populacja, int iter, int R) {

		generPopPoczat(populacja); // generacja populacji początkowej

		for (int iteracja = 1; iteracja <= iter; iteracja++) {
			float MFC = MFC(); // oblicz uśrednionej dla wszystkich osobników wartość
			float MFC_FC = 0;
			int czesc = (popRozw.size() - R) / 4;
			int buffer1 = 0; // rozwiązania znacząco lepsze 75% sklonowanych
			int buffer2 = 0; // lepsze jedynie nieznacznie 25% sklonowaneych

			for (Map.Entry entry : popRozw.entrySet()) { // klonowanie do nowej populacji i mutacja
				int FC = (int) entry.getValue();
				MFC_FC = MFC / FC;
				if (MFC_FC > (1.15) && buffer1 < 3 * czesc) {
					mutacja((String) entry.getKey(), FC);
					buffer1++;
				} else {
					if (MFC_FC > (1.1) && buffer2 < 1 * czesc) {
						mutacja((String) entry.getKey(), FC);
						buffer2++;
					}
				}
			}
			// interakcji z użytkownikiem
			if (iteracja % 10 == 0) {
				System.out.println("Zbiór najlepszych  rozwiązań po 10 iteracjach: \n");
				System.out.println(mapRozw + "\n");

				if (iteracja == iter) {
					Scanner odczyt = new Scanner(System.in);
					System.out.println("Wykonanic kolejną serie iteracji (1) albo zakończyc działanie programu (0): ");
					int walue = Integer.parseInt(odczyt.nextLine());
					if (walue == 1) {
						System.out.println("Liczba iteracji I: ");
						iter = Integer.parseInt(odczyt.nextLine());
						iteracja = 1;
					} else {
						for (Map.Entry entry : mapRozw.entrySet()) {
							if (FC_min > (int) entry.getValue()) {
								FC_min = (int) entry.getValue();
								key_min = (String) entry.getKey();
							}
						}
						System.out.println("Rozwiązanie najlepsze: " + key_min + "– " + FC_min);
						System.out.println("Koniec programu...");
						odczyt.close();
						System.exit(0);
					}
				}
			}
			if (mapRozw.size() != 0) {
				popRozw.clear();
				popRozw.putAll(mapRozw);
				mapRozw.clear();
			}
			// generacja nowych odobnikow
			generPopPoczat(popRozw.size() + R);

		}
		System.out.println("Zbiór najlepszych  rozwiązań: \n");
		System.out.println(mapRozw + "\n");
		System.out.println("Rozwiązanie najlepsze: " + key_min + "– " + FC_min);
		System.out.println("Koniec programu...");
	}

	public static void generPopPoczat(int populacja) { // metoda generuje populacje poczatkawa
		while (popRozw.size() < populacja) {
			Random rnd = new Random();
			int k = 0;
			ArrayList<String> alphabetCopy = new ArrayList<String>();
			alphabetCopy.addAll(alphabet);
			for (int i = 0; i < liczbaEl; i++) {
				int randomIndex = rnd.nextInt(liczbaEl - k);
				osobnik = osobnik + alphabetCopy.get(randomIndex) + " ";
				alphabetCopy.remove(randomIndex);
				k++;
			}
			popRozw.put(osobnik, FC(osobnik));// oblicz jakośći każdego potencjalnego rozwiązania
			osobnik = "";
		}
	}

	private static int FC(String osobnik) { // metoda obliczania jakośći każdego potencjalnego rozwiązania
		String CSVsplitter = " "; // separator
		String[] lineTokens = osobnik.split(CSVsplitter);
		int FC = 0;
		for (int i = 0; i < lineTokens.length; i++) {
			for (int j = 0; j < lineTokens.length; j++) {
				if (i == j)
					continue;
				if (i > j)
					FC = FC + ((i - j) * create.tab[alphabet.indexOf(lineTokens[i])][alphabet.indexOf(lineTokens[j])]);
				else
					FC = FC + ((j - i) * create.tab[alphabet.indexOf(lineTokens[i])][alphabet.indexOf(lineTokens[j])]);
			}
		}
		return FC;
	}

	private static float MFC() { // metoda obliczya uśrednioną dla wszystkich osobników wartość
		int suma = 0;
		int dzielnik = 0;
		for (int value : popRozw.values()) {
			suma = suma + value;
			dzielnik++;
		}
		return suma / dzielnik;
	}

	private static void mutacja(String key, int FC) {
		String newOsobnik = "";
		Random rnd = new Random();
		int randomIndex1 = rnd.nextInt(liczbaEl - 1);
		int randomIndex2 = rnd.nextInt(liczbaEl - 1);
		while (randomIndex1 == randomIndex2) {
			randomIndex2 = rnd.nextInt(liczbaEl - 1);
		}
		int index = key.indexOf(alphabet.get(randomIndex2));
		newOsobnik = key.replace(alphabet.get(randomIndex1), alphabet.get(randomIndex2));
		newOsobnik = newOsobnik.substring(0, index) + alphabet.get(randomIndex1) + newOsobnik.substring(index + 1);
		int newFC = FC(newOsobnik);
		if (newFC < FC)
			mapRozw.put(newOsobnik, newFC);
		else
			mapRozw.put(key, FC);

	}
}
