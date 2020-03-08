import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -포도주 시식-
 * 1. 이전 포도주에서 0,1,2번 먹은 량에서 현재 포도주를 먹지 않는 량중 가장 큰 것을 골라 0번에 저장
 * 2. 이전 포도주에서 0번 먹은 량에서 현재 포도주를 먹은 량을 1번에 저장
 * 3. 이전 포도주에서 1번 먹은 량에서 현재 포도주를 먹은 량을 2번에 저장
 * 4. 마지막까지 구한 뒤 가장 큰것을 고르기
 */

//출처 : https://www.acmicpc.net/problem/2156
public class Main_B_S1_2156_포도주시식 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int[] grape = new int[N];
		
		for(int n=0; n<N; ++n)
			grape[n]=Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N][3];
		dp[0][0]=0;	dp[0][1]=grape[0];	dp[0][2]=grape[0];
		
		for(int n=1; n<N; ++n) {
			dp[n][0] = Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2]));
			dp[n][1] = dp[n-1][0]+grape[n];
			dp[n][2] = dp[n-1][1]+grape[n];
		}
		System.out.print(Math.max(dp[N-1][0], Math.max(dp[N-1][1], dp[N-1][2])));
	}
}