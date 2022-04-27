package algofinal;

public class dynamicProgramming {

	// first just our recursive implementation, which even so is still very
	// inefficient due to the amount of times
	// our program has to compute the fibRecursive() of previous numbers just to get
	// to our
	// current fibRecursive(n), the timing complexity of this algorithm is T(n) and
	// is
	// linear.

	public static int fibRecursive(final int n) {

		if (n <= 1) {
			return n;
		}

		return fibRecursive(n - 1) + fibRecursive(n - 2);

	}

	// a much more efficient version using dynamic programming.
	// the approach we are implementing is a bottom up approach, starting from the
	// simplest problem, and working up to the more complicated ones.
	// we are solving each of the subproblems only once giving a time complexity of
	// O(n), and our space being O(1) since we are only using two variables.

	// After running time tests on both of these algorithms, the dynamic approach is
	// faster by approximately 4 times, which is a huge difference.

	public static int fibBottomUp(final int n) {
		// has to be +2 rather than +1 or will not run in case of n = 0.
		int[] f = new int[n + 2];

		f[0] = 0;
		f[1] = 1;

		for (int i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}

		return f[n];

	}

	// we can uncomment this if we want to be able to print out the actual substring
	// and not just the length
	// static String subString = "";

	// The time complexity of this program is O(sOne.length * sTwo.length) using
	// placeholders x,y for sOne and sTwo lengths respectively we get O(x*y),
	// the same holds for the auxiliary space complexity of the algorithm, also
	// being O(x*y).

	public static int LCS(final String sOne, final String sTwo) {

		int[][] lcsTable = new int[sOne.length()][sTwo.length()];
		int max = 0;
		int end = -1;
		// subString = "";

		for (int i = 0; i < sOne.length(); i++) {
			for (int j = 0; j < sTwo.length(); j++) {
				if (sOne.charAt(i) == sTwo.charAt(j)) {

					if (i == 0 || j == 0) {
						lcsTable[i][j] = 1;
					} else {
						lcsTable[i][j] = lcsTable[i - 1][j - 1] + 1;

					}

					if (lcsTable[i][j] > max) {
						max = lcsTable[i][j];
						end = i;

					}

				}
			}
		}
		// we can use this to store the actual substring as opposed to just the
		// length
		// This returns the actual substring itself
		// subString = sOne.substring(end - max + 1, end + 1);

		return sOne.substring(end - max + 1, end + 1).length();

	}

	// the time complexity of this solution is O(2^n)
	// the auxiliary space is O(1) since we are not using any data structures for
	// storing our values, we must add int c to the parameters to avoid an infinite
	// loop in our recursion, int c will act as our current index and will help
	// avoid a java.lang.StackOverflow error
	public static int knapsackBrute(final int W, final int[] w, final int[] vals, int c) {

		if (c == 0 || W == 0)
			return 0;

		else if (w[c - 1] > W)
			return knapsackBrute(W, w, vals, c - 1);

		else
			return Math.max(vals[c - 1] + knapsackBrute(W - w[c - 1], w, vals, c - 1),
					knapsackBrute(W, w, vals, c - 1));

	}

	// knapsack using dynamic programming with a bottom up approach
	// since we have a nested loop over the vals.length lets call it v, and a weight
	// limit W the
	// running time is O(vW) (2d array of vals and Weight) and for auxiliary space
	// complexity also O(vW) since we
	// are using a 2d array of size v*W
	public static int knapsackDynamic(final int W, final int[] w, final int[] vals) {
		// base case for no item present or capacity of knapsack being 0

		if (vals.length <= 0 || W <= 0) {
			return 0;
		}

		int[][] K = new int[vals.length + 1][W + 1];

		for (int j = 0; j <= W; j++) {
			K[0][j] = 0;
		}

		for (int i = 1; i <= vals.length; i++) {
			for (int j = i; j <= W; j++) {
				if (w[i - 1] > j) {
					K[i][j] = K[i - 1][j];
				} else {
					K[i][j] = Math.max(K[i - 1][j], K[i - 1][j - w[i - 1]] + vals[i - 1]);
				}
			}

		}

		return K[vals.length][W];

	}

	public static void main(String[] args) {
		// fibonacci test

		long fibRecursStartTime = 0;
		long fibRecursEndTime = 0;
		long fibRecursTotal = fibRecursEndTime - fibRecursStartTime;
		long fibDynStartTime = 0;
		long fibDynEndTime = 0;
		long fibDynTotal = fibDynEndTime - fibDynStartTime;

		int n = 9;

		// if we want to print the nth fibonacci number
		fibRecursStartTime = System.nanoTime();
		System.out.println(fibRecursive(n));
		fibRecursEndTime = System.nanoTime();
		fibRecursTotal = fibRecursEndTime - fibRecursStartTime;
		System.out.println(
				"The recursive algorithm for the nth fibonacci number has a time of " + fibRecursTotal + " ns");

		fibRecursStartTime = System.nanoTime();
		// if we want to print the whole sequence up to the nth number.
		for (int i = 0; i < n; i++) {

			System.out.print(fibRecursive(i) + " ");
		}
		fibRecursEndTime = System.nanoTime();
		fibRecursTotal = fibRecursEndTime - fibRecursStartTime;
		System.out.println("");
		System.out.println("///////////");
		// System.out.println("The recursive algorithm for the WHOLE SEQUENCE has a time
		// of " + fibRecursTotal + " ns");

		fibDynStartTime = System.nanoTime();
		System.out.println(fibBottomUp(9));
		fibDynEndTime = System.nanoTime();
		fibDynTotal = fibDynEndTime - fibDynStartTime;
		System.out.println("The dynamic algorithm for the nth fibonacci number has a time of " + fibDynTotal + " ns");

		fibDynStartTime = System.nanoTime();
		// if we want to print the whole sequence up to the nth number.
		for (int i = 0; i < n; i++) {

			System.out.print(fibBottomUp(i) + " ");
		}
		fibDynEndTime = System.nanoTime();
		fibDynTotal = fibDynEndTime - fibDynStartTime;
		System.out.println("");
		System.out.println("///////////");
		// System.out.println("The dynamic algorithm for the WHOLE SEQUENCE has a time
		// of " + fibRecursTotal + " ns");

		// LCS test
		System.out.println("///////////");

		System.out.println("Substring length: " + LCS("asdsadsa", "asdsadsadasdasdsa"));

		// Knapsack test
		System.out.println("///////////");

		int vals[] = { 55, 95, 115 };
		int w[] = { 5, 30, 40 };
		int W = 600;
		int c = vals.length;

		System.out.println(knapsackBrute(W, w, vals, c));
		System.out.println(knapsackDynamic(W, w, vals));

	}

}
