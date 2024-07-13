import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static void printMaxKey(HashMap<Integer, Integer> map) {
        // implement me
        int maxKey = Integer.MIN_VALUE;
        for (int key : map.keySet()) {
            if (key > maxKey) {
                maxKey = key;
            }
        }
        System.out.println(map.get(maxKey));
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            map.put(scanner.nextInt(), scanner.nextInt());
        }

        printMaxKey(map);
    }
}