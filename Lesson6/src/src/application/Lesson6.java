package src.application;

import java.util.Arrays;

public class Lesson6 {

    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7 };
        System.out.println(Arrays.toString(trimFroMLastFour(array)));

        int[] x1 = new int[] { 1, 1, 1, 4, 4, 1, 4, 4 };
        System.out.println(hasOneOrFour(x1));
    }

    public static int[] trimFroMLastFour(int[] arr){
        if (arr != null) {
            for (int i = arr.length - 1; i >= 0; i--) {
                if(arr[i] == 4) return Arrays.copyOfRange(arr, i+1, arr.length);
            }
        }
        throw new RuntimeException();
    }

    public static boolean hasOneOrFour(int[] arr){
        int factor1 = 0, factor2 = 0;
        for(int i : arr){
            if(i == 1) factor1 += 1;
            if(i == 4) factor2 += 1;
        }
        if(factor1 == 0 || factor2 == 0) return false;
        return (factor1 + factor2 == arr.length);
    }
}
