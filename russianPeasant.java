public class russianPeasant {

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

		System.out.println(russianPeasantMult(13, 238) + " " + System.currentTimeMillis());
		System.out.println(russianPeasantMult(5, 5) + System.currentTimeMillis());
		System.out.println(russianPeasantMult(2, 18) + System.currentTimeMillis());

	}
}
