package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class algorytm {
	static int liczbaEl = create.tab.length;
	static Map<String, Integer> popRozw = new HashMap<String, Integer>(); // poczatkowa mapa populacji potencjalnych
																			// rozwiązań
	static Map<String, Integer> mapRozw = new HashMap<String, Integer>(); // mapa populacji potencjalnych rozwiązań
	static String osobnik = "";
	static ArrayList<String> alphabet = new ArrayList<String>(
			Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"));

	public static void doIteracje(int populacja, int iter, int R) {
		generPopPoczat(populacja); // generacja populacji początkowej
		float MFC = MFC(); // oblicz uśrednionej dla wszystkich osobników wartość
		float MFC_FC = 0;
		for (Map.Entry entry : popRozw.entrySet()) {
			// System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
			MFC_FC = MFC / (int) entry.getValue();
			int czesc = (populacja - R) / 4;
			int buffer1 = 0;
			int buffer2 = 0;
			if (MFC_FC > 1.1 && buffer1 > 3 * czesc) {
				mutacja((String) entry.getKey(), MFC_FC, MFC);
				buffer1++;
			} else {
				if (MFC_FC > 0.9 && buffer2 > 1 * czesc) {
					mutacja((String) entry.getKey(), MFC_FC, MFC);
					buffer2++;
				}
			}
			generPopPoczat(R);
		}
	}

	public static void generPopPoczat(int populacja) { // metoda generuje populacje poczatkawa
		for (int p = 1; p <= populacja; p++) {
			Random rnd = new Random();
			// int liczbaEl = create.tab.length;
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

	private static void mutacja(String key, float MFC_FC, float MFC) {
		float newMFC_FC = 0;
		String newOsobnik = "";
		Random rnd = new Random();
		int randomIndex1 = rnd.nextInt(liczbaEl - 1);
		int randomIndex2 = rnd.nextInt(liczbaEl - 1);
		while (randomIndex1 == randomIndex2)
			randomIndex2 = rnd.nextInt(liczbaEl - 1);
		newOsobnik = key.replace(alphabet.get(randomIndex1), alphabet.get(randomIndex2));
		int newFC = FC(newOsobnik);
		newMFC_FC = MFC / newFC;

		if (newMFC_FC > MFC_FC)
			mapRozw.put(newOsobnik, newFC);
		mapRozw.put(key, (int) (MFC_FC * MFC));

	}

	static void contin(int iter) {

	}

}
