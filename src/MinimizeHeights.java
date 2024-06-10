import com.slava.utilities.Reader;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

//https://www.geeksforgeeks.org/problems/minimize-the-heights3351/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class MinimizeHeights {

    //Minimize the Heights II
//https://www.geeksforgeeks.org/problems/minimize-the-heights3351/1?page=1&difficulty=Medium,Hard&sortBy=submissions
    int getMinDiff_II(int[] arr, int n, int k) {
        int res = 0;
        Arrays.sort(arr);
        if (arr[arr.length - 1] < k) {
            return arr[arr.length - 1] - arr[0];
        }
        res = arr[arr.length - 1] - arr[0];
        if (res < 1) return res;

        int startIndex = getMaxIndArray(arr, k - 1);
        startIndex = startIndex >= 0 ? startIndex : Math.abs(startIndex) - 1;
        if (startIndex>0) startIndex--;
        for (int i = startIndex; i < arr.length - 1; i++) {
            int aMin = Integer.min(arr[0] + k, arr[i + 1] - k);
            int aMax = Integer.max(arr[arr.length - 1] - k, arr[i] + k);
            res = Integer.min(aMax - aMin, res);
        }
        return res;
    }

    public static int getMaxIndArray(int[] numsWithDupSorted, int key) {
        int curInd = Arrays.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && curInd < numsWithDupSorted.length - 1 && numsWithDupSorted[curInd + 1] == key) {
            curInd = Arrays.binarySearch(numsWithDupSorted, curInd + 1, numsWithDupSorted.length, key);
        }
        return curInd;
    }

    //Minimize the Heights I
    // https://www.geeksforgeeks.org/problems/minimize-the-heights-i/1
    int getMinDiff_I(int[] arr, int n, int k) {
        int res = 0;
        Arrays.sort(arr);
        res = arr[arr.length - 1] - arr[0];
        if (res < 1) return res;
        for (int i = 0; i < arr.length - 1; i++) {
            int aMin = Integer.min(arr[0] + k, arr[i + 1] - k);
            int aMax = Integer.max(arr[arr.length - 1] - k, arr[i] + k);
            res = Integer.min(aMax - aMin, res);
        }
        return res;
    }

    int getMinDiff_I_(int[] arr, int n, int k) {
        int res = 0;
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int minPossible = arr[0] + k;
        int maxPossible = arr[0] - k;
        for (int a : arr) {
            minPossible = Integer.min(minPossible, a + k);
            maxPossible = Integer.max(maxPossible, a - k);
        }
        if (maxPossible < minPossible) {
            int tmp = maxPossible;
            maxPossible = minPossible;
            minPossible = tmp;
        }
        res = maxPossible - minPossible;
        int maxExceedUp = 0;
        int maxExceedDown = 0;
        for (int a : arr) {
            int exceedUp = a + k - maxPossible;
            int exceedDown = a - k - minPossible;

            if (exceedUp > 0 && exceedDown < 0) // exceed limits
            {
                System.out.println("a = " + a + " minPossible = " + minPossible + " maxPossible = " + maxPossible + " exceedUp = " + exceedUp + " exceedDown = " + exceedDown);
                if (exceedUp > Math.abs(exceedDown)) {
                    maxExceedDown = Integer.max(maxExceedDown, Math.abs(exceedDown));  //new min
                } else {
                    maxExceedUp = Integer.max(maxExceedUp, exceedUp);  //new min  //new max
                }
            }
        }
        return res + maxExceedDown + maxExceedUp;
    }


    @Test
    public void test1() {
        int K = 2, N = 4;
        int[] arr = {1, 5, 8, 10};
        int res = getMinDiff_II(arr, N, K);
        Assert.assertEquals(5, res);
    }

    @Test
    public void test1_I() {
        int K = 4, N = 10;
        int[] arr = {1, 5, 9, 5, 7, 3, 1, 4, 4, 8};
        int res = getMinDiff_I(arr, N, K);
        Assert.assertEquals(6, res);
    }

    @Test
    public void test2_I() {
        int K = 5, N = 10;
        int[] arr = {2, 6, 3, 4, 7, 2, 10, 3, 2, 1};
        int res = getMinDiff_I(arr, N, K);
        Assert.assertEquals(7, res);
    }


    @Test
    public void test2() {
        int K = 7, N = 8;
        int[] arr = {1, 8, 10, 6, 4, 6, 9, 1};
        //   8  15 17   13 11 13 16 8
        int res = getMinDiff_II(arr, N, K);
        Assert.assertEquals(9, res);
    }

    @Test
    public void test3() {
        int K = 1, N = 4;
        int[] arr = {7, 7, 3, 5};
        int res = getMinDiff_II(arr, N, K);
        Assert.assertEquals(2, res);
    }

    @Test
    public void test4() {
        int K = 4, N = 10;
        int[] arr = {2, 4, 3, 9, 9, 10, 9, 7, 1, 2};
        //    6   8  7  5  5  6   5  3  5  7
        int res = getMinDiff_II(arr, N, K);
        Assert.assertEquals(5, res);
    }

    @Test
    public void test4A() {
        int K = 37, N = 3;
        int[] arr = {21, 293, 292};
        //    6   8  7  5  5  6   5  3  5  7
        int res = getMinDiff_II(arr, N, K);
        Assert.assertEquals(198, res);
    }


    @Test
    public void test5() throws FileNotFoundException {
        int K = 494, N = 318;
        int[] arr = Reader.readArrayFromFile("resources/MinimizeHeightsInput.txt");
        int res = getMinDiff_II(arr, N, K);
        Assert.assertEquals(973, res);
    }
}
