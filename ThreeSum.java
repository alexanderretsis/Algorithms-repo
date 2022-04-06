import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    private static final List<String> filePaths = new ArrayList<>();

    static {
        filePaths.add("resources/1Kints.txt");
        filePaths.add("resources/2Kints.txt");
        filePaths.add("resources/4Kints.txt");
        filePaths.add("resources/8Kints.txt");
        filePaths.add("resources/16Kints.txt");
    }

    public static void main(final String[] args) {
        for (final String path : filePaths) {
        	// do something
        	

        	// ALGORITHM A

        	long start = System.currentTimeMillis();
        	
        	threeSumA(loadFileAsIntArray(path));

        	long end = System.currentTimeMillis();

        	System.out.println("Algorithm A took " + (end - start) + " ms");

        	// ALGORITHM B

        	start = System.currentTimeMillis();

        	threeSumB(loadFileAsIntArray(path));

        	end = System.currentTimeMillis();

        	System.out.println("Algorithm B took " + (end - start) + " ms");
        	
        }
    }

    public static int threeSumA(final int[] array) {
        final int length = array.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (array[i] + array[j] + array[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int threeSumB(final int[] array) {
        final int length = array.length;
        Arrays.sort(array);
        if (Arrays.stream(array).distinct().toArray().length != length) {
            throw new IllegalArgumentException("Input array contains duplicates");
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int k = Arrays.binarySearch(array, -(array[i] + array[j]));
                if (k > j) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int[] loadFileAsIntArray(final String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath)).stream().mapToInt(Integer::parseInt).toArray();
    }
}
