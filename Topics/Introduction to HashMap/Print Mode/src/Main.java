import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static void printMode( int[] map) {
        // Enter your Code Here
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        // Populate the frequency map
        for (int num : map) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Find the mode (element with the highest frequency)
        int mode = map[0];
        int maxFrequency = frequencyMap.get(mode);

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mode = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        // Print the mode and its frequency
        System.out.println(mode + " " + maxFrequency);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] map = new int [n];
        for (int i = 0; i < n; ++i) { 
            map[i] = scanner.nextInt();
        }

        printMode(map);
    }
}