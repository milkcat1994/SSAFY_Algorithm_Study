import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -RGB거리2-
 * 첫번째 집의 색을 정한뒤 다음집의 색을 정해간다.
 * 마지막집의 색은 첫째집의 색과 다른 것을 고른다.
 * 첫집의 색을 3가지 모두 확인해보면 된다.
 */

//출처 : https://www.acmicpc.net/problem/17404
public class Main_B_G4_17404_RGB거리2 {
	static int[][] arr, dp;
	static int N, minRes = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][3];
		
		for(int n=0; n<N; ++n) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[n][0] = Integer.parseInt(st.nextToken());
			arr[n][1] = Integer.parseInt(st.nextToken());
			arr[n][2] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N][3];
		dp[0][0] = arr[0][0];
		dp[1][0] = 10000000;
		dp[1][1] = arr[0][0]+arr[1][1];
		dp[1][2] = arr[0][0]+arr[1][2];
		for(int i=2; i<N; ++i) {
			dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
			dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
			dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
		}
		minRes = min(minRes,dp[N-1][1]);
		minRes = min(minRes,dp[N-1][2]);

		dp = new int[N][3];
		dp[0][1] = arr[0][1];
		dp[1][0] = arr[0][1]+arr[1][0];
		dp[1][1] = 10000000;
		dp[1][2] = arr[0][1]+arr[1][2];
		for(int i=2; i<N; ++i) {
			dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
			dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
			dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
		}
		minRes = min(minRes,dp[N-1][0]);
		minRes = min(minRes,dp[N-1][2]);

		dp = new int[N][3];
		dp[0][2] = arr[0][2];
		dp[1][0] = arr[0][2]+arr[1][0];
		dp[1][1] = arr[0][2]+arr[1][1];
		dp[1][2] = 10000000;
		for(int i=2; i<N; ++i) {
			dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
			dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
			dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
		}
		minRes = min(minRes,dp[N-1][0]);
		minRes = min(minRes,dp[N-1][1]);
		
		System.out.println(minRes);
	}
	
	static int min(int a, int b) {
		return a > b ? b : a;
	}
}
