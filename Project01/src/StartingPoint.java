
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StartingPoint {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please insert the size of Array : ");
        int n = sc.nextInt();

        while (n < 1 || n > 300_000) {
            n = sc.nextInt();
        }

        List<Integer> numbers = new ArrayList<>(n);
        List<Integer> oNumbers = new ArrayList<>(n);

        System.out.println("please insert the numbers : ");
        for (int i = 0; i < n; i++) {  // getting data from scanner
            int num = sc.nextInt();
            numbers.add(num);
            oNumbers.add(num);
        }
        numbers.sort(Comparator.comparingInt(o -> o));


        int counter = 0;
        for (int k = 0; k < oNumbers.size(); k++) {  // Comparing numbers in the places
            if (!numbers.get(k).equals(oNumbers.get(k))) {
                counter++;
            }
        }
        for (int num : numbers) {   // printing the sorted list
            System.out.print(num + " ");
        }

        System.out.println();
        System.out.println("The number of places that changed : " + counter);
    }
}




