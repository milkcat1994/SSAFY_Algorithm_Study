import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -외판원 순회-
 * 1. 완탐의 경우 N!이기때문에 하지 말것.
 * 2. memo이용하여 현재위치와 방문했던 상황을 이용하여 중복 작업 하지않기.
 * 
 */

//출처 : https://www.acmicpc.net/problem/2098
public class Main_B_G1_2098_외판원순회 {
	static int N;
	static int INF = 123456789;
	static int[][] board;
	static int[][] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		memo = new int[N][1<<N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; ++i) {
			Arrays.fill(memo[i], -1);
		}
		
		System.out.println(dfs(0, 1));
	}
	
	public static int dfs(int cur, int isVisited) {
		if(isVisited == (1<<N)-1) {
			if(board[cur][0] != 0) {
				return board[cur][0];
			}
			return INF;
		}
		
		int min = memo[cur][isVisited];
		if(min != -1)
			return min;
		min = INF;
		
		for(int i=0; i<N; ++i) {
			if( board[cur][i] == 0 || (1<<i & isVisited) > 0) continue;
			min = Math.min(board[cur][i] + dfs(i, isVisited | (1<<i)), min);
		}
		
		if(min != INF)
			memo[cur][isVisited] = min;
		return min;
	}
}
