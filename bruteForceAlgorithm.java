package algopracts;

public class bruteForceAlgorithm {

	public static void bruteForce(String txt, String pattern) {
		final int n = txt.length();
		final int m = pattern.length();

		for (int i = 0; i <= n - m; i++) {

			int j;

			for (j = 0; j < m; j++) {
				if (txt.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}

			if (j == m) {
				// THIS RETURNS THE INDEX IN OF THE TEXT WHERE THE PATTERN BEGINS
				System.out.println("Match found at" + i);
			}

		}

	}

	static void KMPSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]
		// Preprocess the pattern (calculate lps[]
		// array)
		computeLPSArray(pat, M, lps);
		// insert your code here

		// this is our index
		int i = 0;
		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				System.out.println("Match found at " + (i - j));
				j = lps[j - 1];
			}

			else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i += 1;
				}
			}
		}
	}

	static void computeLPSArray(final String pat, final int M, final int lps[]) {
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0
		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {

				len++;
				lps[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar
				// to search step.
				if (len != 0) {
					len = lps[len - 1];
					// Also, note that we do not increment
					// i here
				} else // if (len == 0)
				{
					lps[i] = len;
					i++;
				}
			}
		}
	}

	public static void main(String args[]) {
		long KMPStartTime = 0;
		long KMPEndTime = 0;
		long KMPTotal = KMPEndTime - KMPStartTime;
		long BFStartTime = 0;
		long BFEndTime = 0;
		long BFTotal = BFEndTime - BFStartTime;
		// KMP TESTS
		System.out.println("Results for KMP: ");

		String txt = "ABABDABACDABABCABAB";
		String pat = "ABABCABAB";

		KMPStartTime = System.nanoTime();
		KMPSearch(pat, txt);
		KMPEndTime = System.nanoTime();
		KMPTotal = KMPEndTime - KMPStartTime;

		System.out.println("KMP Time: " + KMPTotal + " ns");
		// KMP TESTS END
		System.out.println(" ");
		System.out.println("////////////////");
		System.out.println(" ");

		// BF TESTS
		System.out.println("Results for BF: ");

		txt = "ABABDABACDABABCABAB";
		pat = "ABABCABAB";
		BFStartTime = System.nanoTime();
		bruteForce(txt, pat);
		BFEndTime = System.nanoTime();
		BFTotal = BFEndTime - BFStartTime;

		System.out.println("BF Time: " + BFTotal + " ns");

	}

}
