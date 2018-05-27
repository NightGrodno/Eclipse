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
		generPopPoczat(populacja); // generacja populacji początkowej
		System.out.println(popRozw.size());
		MFC(); // oblicz uśrednionej dla wszystkich osobników wartość

	}

	public static void generPopPoczat(int populacja) { // metoda generuje populacje poczatkawa
		for (int p = 1; p <= populacja; p++) {
			Random rnd = new Random();
			int liczbaEl = create.tab.length;
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

	private static void MFC() { // metoda obliczya uśrednioną dla wszystkich osobników wartość

	}
}
