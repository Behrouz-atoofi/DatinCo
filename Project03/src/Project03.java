import java.util.Scanner;

public class Project03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while (n < 1 || n > 1000) {
            n = scanner.nextInt();
        }

        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        int maxProfit = 0;
        int profit = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n - 1; j++) {
                profit = prices[j + 1] - prices[i];

                if (profit >= maxProfit) {
                    maxProfit = profit;
                } else if (profit < 0)
                    profit = 0;
            }

        }
        System.out.println(maxProfit);
    }
}
