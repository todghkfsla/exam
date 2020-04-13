import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Stack;
public class Main {
    private static Object Stack;

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < m; i++) {
            a[i] = sc.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += a[i];
        }

        System.out.println(compare(0, sum, m, a));


    }


    private static int compare(int left, int right, int m, int a[]) {
        if (left == right) return left;
        int count = counting(point, a, m);

        if (count < m) return compare(left, point);
        else return compare(point, right, m);
    }


    private static int counting(float point, int a[], int m)
    {
        int count=1;
        int sum=0;
        for (int i : Stack<i>)
        {
            sum+=i;
        }

            for (i = 0; i < m; i++)
            {
                if (point <= sum + a[i]) stack.clear;count++;
                else stack.push(a[i]);
            }
        return count;
    }

}
    public static void main(String[] args)
    {
        solve();
    }
}
