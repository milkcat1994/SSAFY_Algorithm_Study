import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -키 순서-
 * 플로이드 워셜 이용한 이동 가능성 판단 (단 방향으로 생각하여 풀이)
 */

//출처 : https://www.acmicpc.net/problem/2458
public class Main_B_G4_2458_키순서 {
	static final int INF = Integer.MAX_VALUE;
	static int N,M;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		init();
		
		fluid();
		
		System.out.print(isAble());
	}
	
	static int isAble() {
		int res=0;
		
		R:for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(row == col) continue;
				if(Math.min(board[row][col], board[col][row]) == INF) continue R;
			}
			res++;
		}
		
		return res;
	}
	
	static void fluid() {
		for(int k = 0; k < N; ++k) {
			for (int i = 0; i< N; ++i) {
				for (int j = 0; j < N; ++j) {
					if(board[i][k] == INF || board[k][j] == INF) continue;
					
					board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
				}
			}
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				board[row][col] = INF;
			}
		}
		
		int s,e;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken()) - 1;
			e = Integer.parseInt(st.nextToken()) - 1;
			
			board[s][e] = 1;
		}
	}
	
}
