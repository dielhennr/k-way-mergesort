import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

/**
 * A class that reads in a file of newline separated integers, sorts them using
 * K Way Merge Sort, and writes the output to a new file.
 * 
 * @author Ryan Dielhenn
 */

public class KMergeDriver {

	/**
	 * Reads a file of newline separated integers, sorts them with K-Way merge sort
	 * and outputs the sorted integers to a file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/** Validate input */
		if (args.length != 1) {
			System.err.println("Usage: java KMergeDriver <file.txt>");
			System.exit(0);
		}

		Path file = Paths.get(args[0]);

		/** Process the file */
		try (BufferedReader counter = Files.newBufferedReader(file)) {
			/** Count values */
			counter.readLine();
			int count = 0;
			while (counter.readLine() != null) {
				count++;
			}
			
			BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
			/** Read values into array */
			int k = Integer.parseInt(reader.readLine().strip());
			int[] numbers = new int[count];
			for (int i = 0; i < count; i++) {
				numbers[i] = Integer.parseInt(reader.readLine().strip());
			}

			/** Sort Array */
			KMerge merger = new KMerge();
			merger.sort(numbers, k);

			/** Write output to a new file */
			Path outFile = Paths.get(file + "_sorted");
			try (BufferedWriter writer = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8)) {
				writer.write(merger.toString(numbers));
			} catch (IOException ioe) {
				System.err.println("Error: Writing output to file: " + outFile);
			}

		} catch (NoSuchElementException | IOException e) {
			if (e instanceof InputMismatchException) {
				System.err.println("Error: Non-Integer element in file.");
			} else if (e instanceof FileNotFoundException) {
				System.err.println("File not found.");
			} else {
				System.err.println("Error: whitespace at the end of file.");
				e.printStackTrace();
			}
		}
	}
}
