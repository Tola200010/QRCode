import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static void printCommon(int[] firstArray, int[] secondArray) {
        // Enter your Code Here
        // Finding intersection using HashMap
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : firstArray) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        ArrayList<Integer> intersection = new ArrayList<>();
        for (int num : secondArray) {
            if (map.containsKey(num) && map.get(num) > 0) {
                intersection.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        // Sorting the intersection list
        Collections.sort(intersection);

        // Printing the result
        if (intersection.isEmpty()) {
            System.out.print("");
        } else {
            for (int num : intersection) {
                System.out.print(num + " ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] firstArray = new int[n];
        int[] secondArray = new int[n];
        for (int i = 0; i < n; ++i) {
            firstArray[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; ++i) {
            secondArray[i] = scanner.nextInt();
        }

        printCommon(firstArray, secondArray);
    }
}