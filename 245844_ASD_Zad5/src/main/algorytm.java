package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class algorytm {
	static Map<String, Integer> popRozw = new HashMap<String, Integer>(); // mapa populacji potencjalnych rozwiązań
	static String osobnik = "";
	static ArrayList<String> alphabet = new ArrayList<String>(
			Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"));

	public static void doDziela(int populacja, int liter) {
		// generacja populacji początkowej
		generPopPoczat();
		System.out.println(osobnik);

	}

	public static void generPopPoczat() { // metoda generuje populacje poczatkawa
		Random rnd = new Random();
		int liczbaEl = create.tab.length;
		int k = 1;
		ArrayList<String> alphabetCopy = new ArrayList<String>();
		alphabetCopy.addAll(alphabet);
		for (int i = 0; i < liczbaEl; i++) {
			int randomIndex;
			if (k != liczbaEl) {
				randomIndex = rnd.nextInt(liczbaEl - k);
			} else {
				randomIndex = 0;
			}
			osobnik = osobnik + alphabetCopy.get(randomIndex);
			alphabetCopy.remove(randomIndex);
			k++;
			System.out.println(osobnik);
			funkcjaCelu(osobnik);
		}
	}

	private static void funkcjaCelu(String osobnik2) { // metoda obliczania jakośći każdego potencjalnego rozwiązania
		System.out.println(alphabet.indexOf("a"));

	}
}
