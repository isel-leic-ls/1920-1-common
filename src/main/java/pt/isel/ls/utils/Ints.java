package pt.isel.ls.utils;

import java.util.Optional;

public class Ints {

    /**
     * Returns the maximum integer between two integer operands.
     *
     * @param a the first operand.
     * @param b the second operand.
     * @return the greatest of the two integer operands.
     */
    public static int max(int a, int b) {
        return a >= b ? a : b;
    }

    /**
     * Looks for an integer in an sub-array and returns its index, if found.
     * Other returns -1;
     *
     * @param a         the array containing the sub-array to search.
     * @param fromIndex the first index of the sub-array.
     * @param toIndex   the index after the last index of the sub-array.
     * @param n         the integer to find.
     * @return the index of an occurrence of {@code n}, if {@code n} exists in the sub-array;
     * -1 otherwise.
     */

    public static int indexOfBinary(int[] a, int fromIndex, int toIndex, int n) {

        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("from(" + fromIndex + ") > to(" + toIndex + ")");
        }
        if (fromIndex < 0 || toIndex > a.length) {
            throw new IllegalArgumentException("sub-array exceeds the array bounds");
        }

        int low = fromIndex;
        int high = toIndex - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (n > a[mid]) {
                low = mid + 1;
            } else if (n < a[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static Optional<Integer> parse(String value) {
        try {
            return Optional.of(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
