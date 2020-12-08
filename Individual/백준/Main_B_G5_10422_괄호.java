import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * -괄호-
 * 카탈란 수열
 */

//출처 : https://www.acmicpc.net/problem/10422
public class Main_B_G5_10422_괄호 {
	static final int MAX = 5000/2;
	static final int MOD = 1000000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] arr = new long[MAX+1];
		
		arr[0] = 1;
		arr[1] = 1;
		
		for (int row = 2; row <= MAX; ++row) {
			if(row%2 == 1) {
				arr[row] = (arr[row] + (arr[(row-1)/2] * arr[(row-1)/2]) % MOD) % MOD;
			}
			for (int col = 0; col < row/2; ++col) {
				arr[row] = (arr[row] + (((arr[col] * arr[row-col-1]) % MOD) * 2) % MOD) % MOD;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			if(n%2 == 1) {
				System.out.println(0);
				continue;
			}
			System.out.println(arr[n/2]);
		}
		
	}
}
