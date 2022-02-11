package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadAndWrite {
	//skriver en linje som overskriver alt som ligger i filen
	public static void write(String filename, String text) {
		try {
			PrintWriter writer = new PrintWriter(filename);
			writer.println(text);
			writer.flush();
			writer.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static String read(String filename) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File(filename));
		String line = scanner.nextLine();
		scanner.close();
		return line;
	}
}
