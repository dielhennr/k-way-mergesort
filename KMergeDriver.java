import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class KMergeDriver {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java KMergeDriver <file.txt>");
			System.exit(0);
		}

		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("File Not Found");
			System.exit(0);
		}

		try (Scanner scan = new Scanner(file)) {
			scan.nextLine();
			int count = 0;
			while (scan.hasNextLine()) {
				count++;
				scan.nextLine();
			}
			scan.close();
			Scanner scan1 = new Scanner(file);

			int k = scan1.nextInt();
			int[] numbers = new int[count];
			String strNum;
			for (int i = 0; i < count; i++) {
				numbers[i] = scan1.nextInt();
			}
			scan1.close();

			KMerge merger = new KMerge();
			merger.sort(numbers, k);
			try{
				File outFile = new File(args[0] + "_sorted");
				outFile.createNewFile();
				FileWriter writer = new FileWriter(outFile); 
				writer.write(merger.toString(numbers)); 
				writer.flush();
				writer.close();
			}catch (IOException ioe) {
				System.out.println("Error when writing output to file");
			}



		} catch (FileNotFoundException fnf) {

			System.out.println("File not found");
			System.exit(0);
		}
	}
}
