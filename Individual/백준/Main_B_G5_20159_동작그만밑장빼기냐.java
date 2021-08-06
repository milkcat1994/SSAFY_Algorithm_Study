import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -동작 그만. 밑장 빼기냐?-
 * 1. 밑장은 카드뭉치의 가장 밑을 의미한다.
 * 2. 세가지 경우로 생각하여 풀이한다.
 * ├ 밑장을 빼지 않을 때
 * ├ 상대에게 밑장을 줄 때
 * └ 나에게 밑장을 줄 때
 * 
 */

//출처 : https://www.acmicpc.net/problem/20159
public class Main_B_G5_20159_동작그만밑장빼기냐 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 원본, 상대에게 밑장을 줄때, 자신에게 밑장을 줄때
		int[][] memo = new int[N/2][3];
		memo[0][0] = arr[0];
		memo[0][1] = arr[0];
		memo[0][2] = arr[1];
		
		for(int i=2; i<N; i+=2) {
			memo[i/2][0] = memo[i/2-1][0] + arr[i];
			memo[i/2][1] = max(memo[i/2-1][0] + arr[i-1], memo[i/2-1][1] + arr[i-1]);
			memo[i/2][2] = max(memo[i/2-1][0] + arr[i+1], memo[i/2-1][2] + arr[i+1]);
		}
		
		System.out.print(max(memo[N/2-1][0], memo[N/2-1][1], memo[N/2-1][2]));
	}

	static int max(int a, int b) {
		return Math.max(a, b);
	}
	
	static int max(int a, int b, int c) {
		return max(a, max(b, c));
	}
}