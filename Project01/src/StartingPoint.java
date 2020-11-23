
import java.util.Scanner;

public class StartingPoint {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the size of Array : ");
        int n = scanner.nextInt();
        while (n < 1 || n > 300_000) {
            n = scanner.nextInt();
        }
        int[] numbers = new int[n];
        int[] oNumbers = new int[n];

        System.out.println("please insert the numbers : ");
        for (int i = 0; i < n; i++) {  // getting data from scanner
            numbers[i] = scanner.nextInt();
            oNumbers[i] = numbers[i];
        }

        int middle_Variable;
        for (int j = 1; j < numbers.length; j++) { // Moving numbers
            for (int k = j; k > 0; k--) {
                if (numbers[k] < numbers[k - 1]) {
                    middle_Variable = numbers[k];
                    numbers[k] = numbers[k - 1];
                    numbers[k - 1] = middle_Variable;
                }

            }

        }
        int counter = 0;
        for (int k = 0; k < oNumbers.length; k++) {  // Comparing numbers
            if (numbers[k] != oNumbers[k]) {
                counter++;
            }
        }

        System.out.println("The number of places that changed : " +counter);
    }
}




