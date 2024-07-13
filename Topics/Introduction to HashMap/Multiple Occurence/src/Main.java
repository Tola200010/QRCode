import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static void printMostFrequentWord(String[] words) {
        // write your code here
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        String mostFrequentWord = null;
        int maxFrequency = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            if (stringIntegerEntry.getValue() > maxFrequency) {
                mostFrequentWord = stringIntegerEntry.getKey();
                maxFrequency = stringIntegerEntry.getValue();
            }
        }
        if (mostFrequentWord != null) {
            System.out.println(mostFrequentWord + " " + maxFrequency);
        }
    }

    // don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        printMostFrequentWord(words);
    }
}