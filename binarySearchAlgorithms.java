package algopracts;

import java.util.Comparator;
import java.util.List;

public class binarySearchAlgorithms {

	// ITERATIVE APPROACH

	public static <T extends Comparable<T>> int binarySearch(final T[] array, final T elem,
			final Comparator<T> comparator) {

		int l = 0;
		int r = array.length - 1;
		int mid;

		while (l <= r) {
			mid = (l + r) / 2;

			if (elem == array[mid]) {
				return mid;
			} else if (elem.compareTo(array[mid]) > 0) {
				r = mid - 1;
			} else {
				r = mid + 1;
			}
		}

		return -1;

	}
// FOR THE RECURSIVE BINARY SEARCH IT WILL BE MORE EFFICIENT TO USE A HELPER ALGORITHM.

	public static <T extends Comparable<T>> int binarySearchHelper(final T[] array, final T elem, int l, int r) {

		if (l > r) {
			return -1;
		}

		int mid = (l + r) / 2;
		if (elem == array[mid]) {
			return mid;
		} else if (elem.compareTo(array[mid]) > 0) {
			return binarySearchHelper(array, elem, l, mid - 1);
		} else {
			return binarySearchHelper(array, elem, mid + 1, r);
		}

	}

	public static <T extends Comparable<T>> int binarySearchRecursive(final T[] array, final T elem,
			final Comparator<T> comparator) {
//Now all we have to do is call the helper function.

		return binarySearchHelper(array, elem, 0, array.length - 1);

	}

	//

	public static <T extends Comparable<T>> int binarySearch(final List<T> list, final T elem,
			final Comparator<T> comparator) {

		int l = 0;
		int r = list.size() - 1;
		int mid;

		while (l <= r) {
			mid = (l + r) / 2;

			if (elem == list.get(mid)) {
				return mid;
			} else if (elem.compareTo(list.get(mid)) > 0) {
				r = mid - 1;
			} else {
				r = mid + 1;
			}
		}

		return -1;

	}

	public static void main(String[] args) {

	}

}
