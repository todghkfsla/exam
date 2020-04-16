import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
public class Main {

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }

        int left = a[0];
        for(int i=0; i<a.length; i++) {
            if (left > a[i]) left = a[i];
        }
        System.out.println(compare(left, sum, n, m, a));


    }


    private static int compare(int left, int right, int n, int m, int[] a) {
        if (Math.abs(right-left) <=1) return left;
        int point = (left + right) / 2;
        int count = counting(point, a, n);

        if (count <= m) return compare(left, point, n, m, a);
        else return compare(point, right, n, m, a);
    }

    private static int sum(Stack<Integer> stack) {
        if (stack.isEmpty()) return 0;
        else {
            int sum = 0;
            for (Integer i : stack) {
                sum += i;
            }

            return sum;
        }
    }

    private static int counting(float point, int[] a, int n)
    {
        int count=1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++)
        {
            if (point <= sum(stack) + a[i]) {
                stack.clear();
                count++;
                stack.push(a[i]);
            }
            else stack.push(a[i]);
        }
        return count;
    }

    public static void main(String[] args)
    {
        solve();
    }
}
