package hw1.util;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayUtil {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Object[] mergeSort(Object[] array, Comparator comparator) {
        if (array.length <= 1) {
            return array;
        }

        Object[] result = new Object[array.length];
        Object[] left = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2), comparator);
        Object[] right = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length), comparator);
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            var leftObject = left[leftIndex];
            var rightObject = right[rightIndex];

            if (comparator.compare(leftObject, rightObject) <= 0) {
                result[resultIndex] = leftObject;
                leftIndex++;
            } else {
                result[resultIndex] = rightObject;
                rightIndex++;
            }
            resultIndex++;
        }

        while (leftIndex < left.length) {
            result[resultIndex] = left[leftIndex];
            leftIndex++;
            resultIndex++;
        }

        while (rightIndex < right.length) {
            result[resultIndex] = right[rightIndex];
            rightIndex++;
            resultIndex++;
        }
        return result;
    }
}
