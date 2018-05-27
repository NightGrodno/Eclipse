package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class create {

	static int[][] tab = new int[12][12];
	static int tablength = 0;

	public static void create(String FileName) {
		File myCSVfile = new File(FileName);
		String line = "";
		String CSVsplitter = ","; // separator pol

		// zapis elematy do tablicy
		try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {
			int j = 0;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split(CSVsplitter);
				for (int i = 0; i < lineTokens.length; i++) {
					if (lineTokens[i].equals("X"))
						tab[j][i] = 0;
					else
						tab[j][i] = Integer.parseInt(lineTokens[i]);
				}
				tablength = lineTokens.length;
				j++;
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}