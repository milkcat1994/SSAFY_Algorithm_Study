import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWE_1949_등산로조성 {
	static int N,K;
	static int[][] board;
	static int[][] memo;
	static boolean[][] isVisited;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int maxNum = 0;
			board = new int[N][N];
			for (int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 0; col < N; ++col) {
					board[row][col] = Integer.parseInt(st.nextToken());
					maxNum = Math.max(maxNum, board[row][col]);
				}
			}
			
			int max = 0;
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < N; ++c) {
					for(int k = 0; k <=K; ++k) {
						board[r][c]-=k;
						isVisited = new boolean[N][N];
						memo = new int[N][N];
						for (int row = 0; row < N; ++row) {
							for (int col = 0; col < N; ++col) {
								if(board[row][col] == maxNum) {
									dfs(row,col);
									max = Math.max(getMax(), max);
								}
							}
						}
						board[r][c]+=k;
					} // end K
				}
			}
			sb.append("#"+t+" "+(max+1)+"\n");
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	static int getMax() {
		int max = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				max = Math.max(max, memo[row][col]);
			}
		}
		return max;
	}
	
	static int dfs(int r, int c) {
		if(memo[r][c] > 0) {
			return memo[r][c];
		}
		
		int nr, nc, max=0;
		for(int dir = 0; dir < 4; ++dir) {
			nr = r+drow[dir];
			nc = c+dcol[dir];
			if(isOut(nr,nc) || board[r][c] <= board[nr][nc] || isVisited[nr][nc]) continue;
			isVisited[nr][nc] = true;
			max = Math.max(max, dfs(nr,nc)+1);
			isVisited[nr][nc] = false;
		}
		memo[r][c] = Math.max(max, memo[r][c]);
		return memo[r][c];
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
}