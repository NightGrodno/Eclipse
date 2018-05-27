package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class algorytm {
	static Map<String, Integer> popRozw = new HashMap<String, Integer>(); // poczatkowa mapa populacji potencjalnych
																			// rozwiązań
	static Map<String, Integer> mapRozw = new HashMap<String, Integer>(); // mapa populacji potencjalnych rozwiązań
	static String osobnik = "";
	static ArrayList<String> alphabet = new ArrayList<String>(
			Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"));

	public static void doDziela(int populacja, int iter, int R) {
		generPopPoczat(populacja); // generacja populacji początkowej
		doIteracje(iter, R);

	}

	private static void doIteracje(float iter, int R) {
		float MFC = MFC(); // oblicz uśrednionej dla wszystkich osobników wartość
		float MFC_FC = 0;
		for (int value : popRozw.values()) {	
			//System.out.println(value);
			MFC_FC = MFC / value;
			if (MFC_FC > 1.2) {
				if(mutacja(MFC_FC)>MFC_FC)

			} else {
				if (MFC_FC > 1) {

				}

			}

			System.out.println(MFC_FC);
		}

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

	private static float MFC() { // metoda obliczya uśrednioną dla wszystkich osobników wartość
		int suma = 0;
		int dzielnik = 0;
		for (int value : popRozw.values()) {
			suma = suma + value;
			dzielnik++;
		}
		return suma / dzielnik;
	}

	private static float mutacja(float MFC_FC) {
		float newMFC_FC;
			
		return newMFC_FC;
	}

	static void contin(int iter) {

	}

}
