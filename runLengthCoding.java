package algopracts;

public class runLengthCoding {

	public static void printRLE(final String input) {
		for (int i = 0; i < input.length(); i++) {
			int count = 1;

			while (i < input.length() - 1 && input.charAt(i) == input.charAt(i + 1)) {
				count++;
				i++;
			}
			System.out.print(input.charAt(i));
			System.out.print(count);

		}

	}

	public static void main(String[] args) {

		// Simple test
		System.out.println("Test 1");

		String input = "aaaaaaaaabbbbbbbbbbbccccccccccddddddd";
		System.out.println("Original input: " + input);

		System.out.println("Compressed String: ");

		printRLE(input);
		System.out.println(" ");
		System.out.println("//////////////");
		System.out.println(" ");

		System.out.println("Test 2");
		input = "aaacccbbeeeexxxxxtttttt";
		System.out.println("Original input: " + input);

		System.out.println("Compressed String: ");

		printRLE(input);
		System.out.println(" ");
		System.out.println("//////////////");
		System.out.println(" ");

		System.out.println("Test 3");
		input = "ooaooaaacc";
		System.out.println("Original input: " + input);

		System.out.println("Compressed String: ");

		printRLE(input);

		System.out.println(" ");
		System.out.println("//////////////");
		System.out.println(" ");

		System.out.println("Test 4");
		input = "howhowowowwwooaacdd";
		System.out.println("Original input: " + input);

		System.out.println("Compressed String: ");

		printRLE(input);

		System.out.println(" ");
		System.out.println("//////////////");
		System.out.println(" ");

		System.out.println("Test 5");
		input = "NotSoUsefulHere :(";
		System.out.println("Original input: " + input);

		System.out.println("Compressed String: ");

		printRLE(input);

	}

}
