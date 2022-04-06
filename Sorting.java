import java.util.Comparator;
import java.util.List;

public class Sorting {

	public static <T extends Comparable<T>> void bogoSort(final List<T> list, final Comparator<T> comparator) {
		// TODO

	}

	public static <T extends Comparable<T>> void bogoSort(final T[] array, final Comparator<T> comparator) {
		// TODO
	}

	public static <T extends Comparable<T>> void bubbleSort(final List<T> list, final Comparator<T> comparator) {
		// TODO
		for (int i = 0; i < list.size() - 1; i++) {
			int flag = 0;
			for (int j = 0; j < list.size() - 1 - i; j++) {
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {

					flag = 1;

					list.set(i, list.set(i + 1, list.get(i)));

				}

			}
			if (flag == 0)
				break;

		}
	}

	public static <T extends Comparable<T>> void bubbleSort(final T[] array, final Comparator<T> comparator) {
		// TODO
		/**
		 * In the best case(if our array is already sorted in ascending order) the first
		 * for loop will be executed only once and the second will be executed n-1
		 * times, therefore a best case time complexity of O(n) in the worst case our
		 * outer loop is being executed n-1 times as well as the inner loop, therefore
		 * we get a polynomial (n-1)(n-1) which gives gives us a power of n^2 therefore
		 * worst case time complexity is O(n^2). The space complexity is O(1) as its an
		 * in place aglorithm.
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		for (int i = 0; i < array.length - 1; i++) {
			int flag = 0;
			for (int j = 0; j < (array.length - 1) - i; j++) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					T temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					flag = 1;

				}

			}
			if (flag == 0)
				break;

		}
	}

	public static <T extends Comparable<T>> void selectionSort(final List<T> list, final Comparator<T> comparator) {
		// TODO
		/**
		 * The time complexity for selectionSort is the same in all
		 * cases(best-average-worst), O(n^2) This is because inner loop is being
		 * computed n^2 times in any case regardless of the value of n As for space
		 * complexity selection sort is an in-place algorithm and needs no extra space
		 * therefore the complexity is O(1)
		 */

		int min_index;

		for (int i = 0; i < list.size(); i++) {
			min_index = i;
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j).compareTo(list.get(min_index)) > 0) {
					min_index = j;

				}

				list.set(i, list.set(j, list.get(i)));
			}

		}
	}

	public static <T extends Comparable<T>> void selectionSort(final T[] array, final Comparator<T> comparator) {
		// TODO
		T temp;
		int min_index;

		for (int i = 0; i < array.length - 1; i++) {
			min_index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[min_index]) < 0) {
					min_index = j;

				}
				temp = array[i];
				array[i] = array[min_index];
				array[min_index] = temp;

			}
		}

	}

	private static <T extends Comparable<T>> void merge(Object[] a, T[] left, T[] right) {
		// index l,r,a respectively.
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < right.length) {
			if (left[i].compareTo(right[j]) < 0) {
				a[k++] = left[i++];
			} else {
				a[k++] = right[j++];
			}
		}
		while (i < left.length) {
			a[k++] = left[i++];
		}
		while (j < right.length) {
			a[k++] = right[j++];
		}

	}

	public static <T extends Comparable<T>> void mergeSort(final List<T> list, final Comparator<T> comparator) {
		// TODO
		/**
		 * mergesort has a time complexity of O(nlogn) best, average and worst case. The
		 * space complexity is O(n) as additional storage may be required depending on
		 * the size of our array.
		 * 
		 */

		int n = list.size();
		if (n <= 1) {
			return;
		}
		int mid = n / 2;

		T[] l = (T[]) new Comparable[mid];
		T[] r = (T[]) new Comparable[n - mid];

		// need to modify parameter to merge
		mergeSort(l, comparator);
		mergeSort(r, comparator);
		merge(list.toArray(), l, r);

	}

	public static <T extends Comparable<T>> void mergeSort(final T[] array, final Comparator<T> comparator) {
		// TODO
		int n = array.length;
		if (n <= 1) {
			return;
		}
		int mid = n / 2;

		T[] l = (T[]) new Comparable[mid];
		T[] r = (T[]) new Comparable[n - mid];

		// need to modify parameter to merge
		mergeSort(l, comparator);
		mergeSort(r, comparator);
		merge(array, l, r);

	}
	// simple exchange class to swap 2 objects.

	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static int partition(Comparable[] a, int low, int high) {
		// T pivot = a[low];
		int i = low;
		int j = high + 1;

		// Comparable pivot = a[low];

		while (true) {
			while (a[++i].compareTo(a[low]) < 0) {
				if (i == high)
					break;

			}
			while (a[low].compareTo(a[--j]) < 0) {
				if (j == low)
					break;
			}
			if (i >= j)
				break;
			exch(a, i, j);

		}

		exch(a, low, j);
		return j;

	}

	private static <T extends Comparable<T>> void sort(Comparable[] a, int low, int high) {
		if (low < high) {
			int j = partition(a, low, high);
			sort(a, low, j - 1);
			sort(a, j + 1, high);
		}

	}

	public static <T extends Comparable<T>> void quickSort(final List<T> list, final Comparator<T> comparator) {
		// TODO
		/**
		 * In the best case we are splitting our array into 2 partitions of equal size
		 * as we would only need one additional partition level, As the array size
		 * doubles so does the partitioning therefore we have log2n partitioning levels
		 * for n elements giving us a best case of O(nlogn), The average case is also
		 * O(nlogn), while in the worst case which occurs when our pivot element happens
		 * to be the largest or smallest element of our array, which would mean our
		 * array does not get "partinioned" into 2 equal ones but one with only our
		 * pivot and one with everything but our pivot therefore requiring n partioninsg
		 * levels and a decreasing partitioning effort of n which is aproaching towards
		 * 0 (1/2n ). Meaning the worst case time complexity is O(n^2). As for space
		 * complexity it is an inplace recursive algorithm of depth n therefore O(log n)
		 * in all cases.
		 * 
		 * One of the issues with this algorithm is the chance of choosing a bad pivot
		 * as described above this risk is minimized by choosing the pivot randomly.
		 * 
		 * Secondly another issue is this algorithms instability, it is highly unstable
		 * this could possibly be reduced by implementing the algorithm differenlty or
		 * with additional logic
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		Comparable[] array = (Comparable[]) list.toArray();

		sort(array, 0, array.length - 1);

	}

	public static <T extends Comparable<T>> void quickSort(final T[] array, final Comparator<T> comparator) {
		// TODO
		sort(array, 0, array.length - 1);

	}
}
