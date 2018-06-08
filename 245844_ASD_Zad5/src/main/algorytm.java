package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class algorytm {
	static int liczbaEl = create.tab.length; // liczba szaf
	static Map<String, Integer> buffor = new LinkedHashMap<String, Integer>(); // mapa buffor
	static Map<String, Integer> popRozw = new LinkedHashMap<String, Integer>(); // poczatkowa popul rozw.
	static Map<String, Integer> popDoKlon = new LinkedHashMap<String, Integer>(); // populacja do klonowania
	static String osobnik = "";
	static ArrayList<String> alphabet = new ArrayList<String>(
			Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"));

	public static void doIteracje(int populacja, int iter, int R) {

		generPopPoczat(populacja); // generacja populacji początkowej

		for (int iteracja = 1; iteracja <= iter; iteracja++) {
			float MFC = MFC(); // oblicz uśrednionej dla wszystkich osobników wartość
			float MFC_FC = 0;
			int czesc = (populacja - R) / 4;
			int buffer1 = 0; // rozwiązania znacząco lepsze 75% sklonowanych
			int buffer2 = 0; // lepsze jedynie nieznacznie 25% sklonowaneych

			for (Map.Entry entry : popRozw.entrySet()) { // klonowanie do nowej populacji i mutacja
				int FC = (int) entry.getValue();
				MFC_FC = MFC / FC;
				if (MFC_FC > (1.1) && buffer1 < 3 * czesc) {
					mutacja((String) entry.getKey(), FC);
					buffer1++;
				} else {
					if (MFC_FC > (0.9) && buffer2 < 1 * czesc) {
						mutacja((String) entry.getKey(), FC);
						buffer2++;
					}
				}
			}

			if (popDoKlon.size() != 0) {
				popRozw.clear();
				popRozw.putAll(popDoKlon);
				popDoKlon.clear();
			}
			// generacja nowych odobnikow i sortowanie mapy
			generPopPoczat(popRozw.size() + R);
			buffor.clear();
			sortByValue(popRozw);
			popRozw.clear();
			popRozw.putAll(buffor);

			// interakcji z użytkownikiem po 10 iteracjach
			if (iteracja % 10 == 0) {
				System.out.println("Zbiór najlepszych  rozwiązań po 10 iteracjach: ");
				for (int i = 0; i <= 3; i++)
					System.out.println("Element " + (i + 1) + ": " + popRozw.keySet().toArray()[i] + " – "
							+ +popRozw.get(popRozw.keySet().toArray()[i]));
				System.out.println("");
			}
			// interakcji z użytkownikiem po wszystkich iter
			if (iteracja == iter) {
				Scanner odczyt = new Scanner(System.in);
				System.out.println("Wykonanic kolejną serie iteracji (1) albo zakończyc działanie programu (0): ");
				int walue = Integer.parseInt(odczyt.nextLine());
				if (walue == 1) {
					System.out.println("Liczba iteracji I: ");
					iter = Integer.parseInt(odczyt.nextLine());
					iteracja = 1;
				} else {
					end();
					odczyt.close();
					System.exit(0);
				}
			}

		}
		end();
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
		return FC / 2;
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
			popDoKlon.put(newOsobnik, newFC);
		else
			popDoKlon.put(key, FC);

	}

	public static <K, V extends Comparable<? super V>> void sortByValue(Map<String, Integer> popRozw2) {
		HashMap<String, Integer> result = new LinkedHashMap<>();
		Stream<Entry<String, Integer>> st = popRozw2.entrySet().stream();

		st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
		buffor.putAll(result);
	}

	public static void end() {

		System.out.println("Zbiór najlepszych  rozwiązań: ");
		for (int i = 0; i < 4; i++)
			System.out.println("Element " + i + ": " + popRozw.keySet().toArray()[i] + " – "
					+ +popRozw.get(popRozw.keySet().toArray()[i]));

		System.out.println("\nRozwiązanie najlepsze: " + popRozw.keySet().toArray()[0] + " – "
				+ +popRozw.get(popRozw.keySet().toArray()[0]));

		System.out.println("Koniec programu...");
	}
}
