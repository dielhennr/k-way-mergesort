import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that reads in a file of (\n) separated integers, sorts them using K Way Merge Sort, 
 * and writes the output to a new file.
 * @author dielhennr
 */

public class KMergeDriver {

	public static void main(String[] args) {

		//Validate input
		if (args.length != 1) {
			System.out.println("Usage: java KMergeDriver <file.txt>");
			System.exit(0);
		}

		File file = new File(args[0]);

		//Process the file
		try (Scanner scan = new Scanner(file)) {
			//count values
			scan.nextLine();
			int count = 0;
			while (scan.hasNextLine()) {
				count++;
				scan.nextLine();
			}

			Scanner scan1 = new Scanner(file);
			//read values into array
			int k = scan1.nextInt();
			int[] numbers = new int[count];
			String strNum;
			for (int i = 0; i < count; i++) {
				numbers[i] = scan1.nextInt();
			}
			scan1.close();
			

			//sort array
			KMerge merger = new KMerge();
			merger.sort(numbers, k);

			//write output to a new file
			try{
				File outFile = new File(args[0] + "_sorted");
				outFile.createNewFile();
				FileWriter writer = new FileWriter(outFile); 
				writer.write(merger.toString(numbers)); 
				writer.flush();
				writer.close();
			}catch (IOException ioe) {
				System.out.println("Error: Writing output to file");
				ioe.printStackTrace();
			}



		} catch (FileNotFoundException | InputMismatchException e) {
			if (e instanceof InputMismatchException){
				System.out.println("Error: Non-Integer element in file.");
			}else{
				System.out.println("File not found.");
			}
			System.out.print("\nStack Trace:\n");
			e.printStackTrace();
			System.exit(0);
		}
	}
}
