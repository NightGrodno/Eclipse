package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;

public class database {
	static int km = 0;
	static HashMap<String, HashMap<String, Integer>> outerHZCW = new HashMap<String, HashMap<String, Integer>>();

	public static void search(String departure, String arrival) {

		if (outerHZCW.containsKey(departure)) {
			if (outerHZCW.get(departure).containsKey(arrival)) {
				sysout(departure, arrival, 0, "-", outerHZCW.get(departure).get(arrival).intValue());
			}

			for (String key1 : outerHZCW.get(departure).keySet()) {
				if (outerHZCW.get(key1).containsKey(arrival)) {
					km = outerHZCW.get(departure).get(key1).intValue() + outerHZCW.get(key1).get(arrival).intValue();
					sysout(departure, arrival, 1, key1, km);
				}
			}

			for (String key1 : outerHZCW.get(departure).keySet()) {
				for (String key2 : outerHZCW.get(key1).keySet()) {
					if (outerHZCW.get(key2).containsKey(arrival) && !key1.equals(arrival) && !key2.equals(departure)) {
						km = outerHZCW.get(departure).get(key1).intValue() + outerHZCW.get(key1).get(key2).intValue()
								+ outerHZCW.get(key2).get(arrival).intValue();
						sysout(departure, arrival, 2, key1 + " --> " + key2, km);
					}
				}
			}

		} else {
			System.out.println("Oops... Wpisz poprawne miasto");
		}

	}

	public static void sysout(String departure, String arrival, int przesiadka, String przez, int odleglodc) {
		System.out.printf("%-15s%-15s%-15s%-35s%-10s%n", departure, arrival, przesiadka, przez, odleglodc);

	}

	public static void create(String FileName) {
		File myCSVfile = new File(FileName);
		String line = "";
		String CSVsplitter = "-"; // myslnik jako separator pol

		// zapis elematuw do map
		try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {

			while ((line = br.readLine()) != null) {

				String[] lineTokens = line.split(CSVsplitter);

				if (!outerHZCW.containsKey(lineTokens[0])) {
					outerHZCW.put(lineTokens[0], new HashMap<String, Integer>() {
						{
							put(lineTokens[1], Integer.parseInt(lineTokens[2]));
						}
					});

				} else {
					outerHZCW.get(lineTokens[0]).put(lineTokens[1], Integer.parseInt(lineTokens[2]));

				}

				if (!outerHZCW.containsKey(lineTokens[1])) {
					outerHZCW.put(lineTokens[1], new HashMap<String, Integer>() {
						{
							put(lineTokens[0], Integer.parseInt(lineTokens[2]));
						}
					});

				} else {
					outerHZCW.get(lineTokens[1]).put(lineTokens[0], Integer.parseInt(lineTokens[2]));

				}

			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
