public class russianTimer {

	public static int russianPeasantMult(int x, int y) {

		int total = 0;

		while (x != 0) {

			if ((x & 1) == 1) {
				total += y;

			}
			x = x / 2;
			y = y * 2;

		}

		return total;

	}

	public static void main(final String[] args) {

		long start = System.nanoTime();
		russianPeasantMult(142, 263);
		long end = System.nanoTime();
		System.out.println("Algorithm took " + (end - start) + " ns");

	}
}
