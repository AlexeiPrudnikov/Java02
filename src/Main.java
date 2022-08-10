import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] array = {2, 14, 56, 6, 74, 87, 3, 5, 7, 1, 9, 4, 10, -1, -2};
        printArray(array);
        array = sortArray(array);
        printArray(array);
        System.out.println(combine(4, 2));

    }
// Задача № 1
    public static void nextCortege(ArrayList list, int start, int n, int k) {

        for (int i = 0; i < list.size(); i++) {
            ArrayList currentList = new ArrayList((ArrayList) list.get(i));

            if (currentList.size() < k) {
                list.remove(i);
                for (int j = start; j <= n; j++) {
                    ArrayList newList = new ArrayList((ArrayList) currentList);
                    newList.add(j);
                    if (!list.contains(newList)) list.add(newList);
                    nextCortege(list, j + 1, n, k);
                }
            }
        }
        if (start > (n - k + 1)) return;
        ArrayList newItemList = new ArrayList();
        newItemList.add(start);
        list.add(newItemList);
        nextCortege(list, start + 1, n, k);
        return;
    }

    public static List<List<Integer>> combine(int n, int k) {
        ArrayList result = new ArrayList();
        if (k > n) return null;
        nextCortege(result, 1, n, k);
        return result;
    }

    // Задание № 2 (№ 6 из списка)
    public static void printArray(int[] array) {
        for (int item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // Слияние частей массива
    public static int[] mergeArray(int[] arrayFirst, int[] arraySecond) {
        int indexFirst = 0;
        int indexSecond = 0;
        int[] result = new int[arrayFirst.length + arraySecond.length];
        for (int i = 0; i < result.length; i++) {
            if (indexFirst == arrayFirst.length) {
                result[i] = arraySecond[indexSecond];
                indexSecond++;
            } else if (indexSecond == arraySecond.length) {
                result[i] = arrayFirst[indexFirst];
                indexFirst++;
            } else if (arrayFirst[indexFirst] < arraySecond[indexSecond]) {
                result[i] = arrayFirst[indexFirst];
                indexFirst++;
            } else {
                result[i] = arraySecond[indexSecond];
                indexSecond++;
            }
        }
        return result;
    }

    // Сортировка слиянием
    public static int[] sortArray(int[] array) {
        if (array == null) return null;
        if (array.length < 2) return array;
        int halfIndex = array.length / 2;
        int[] arrayFirst = new int[halfIndex];
        System.arraycopy(array, 0, arrayFirst, 0, halfIndex);
        int[] arraySecond = new int[array.length - halfIndex];
        System.arraycopy(array, halfIndex, arraySecond, 0, array.length - halfIndex);
        arrayFirst = sortArray(arrayFirst);
        arraySecond = sortArray(arraySecond);
        return mergeArray(arrayFirst, arraySecond);
    }

}