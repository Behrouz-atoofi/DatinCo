
import java.util.*;
// Behrouz Atoofi .
public class StartingPoint {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[3][3];
        System.out.println("input [3][3] matrix : ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Minimum Number change is : " + findMinimumTotalChange(matrix));


    }

    public static int findTotalChange(int[][] matrix, int[][] data) {
        int totalChange = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                totalChange += Math.abs(matrix[i][j] - data[i][j]);
            }
        }
        return totalChange;
    }

    public static int findMinimumTotalChange(int[][] matrix) {
        int[][][] data = {
                {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
                {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
                {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
                {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
                {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}},
        };
        int bestMatrix = 0;
        List<Integer> num = new ArrayList<>();
        List<Integer> num1 = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int x = findTotalChange(matrix, data[i]);
            num.add(x);
        }
        num1.addAll(num);
        num.sort((o1, o2) -> o1 - o2);
        int min = num.get(0);
        bestMatrix = num1.indexOf(min);

        System.out.println("The most similar matrix is : ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(data[bestMatrix][i][j] + " ");
            }
            System.out.println();
        }

        return min;
    }


}


