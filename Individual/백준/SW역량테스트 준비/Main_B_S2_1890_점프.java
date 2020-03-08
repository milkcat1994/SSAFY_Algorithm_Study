import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -점프-
 * 1. DFS와 메모이제이션 이용하였다.
 * 2. 이미 방문한 자리는 해당 자리에서 갈 수 있는 가능성의 개수를 memo배열로 가지고있다.
 * 3. 가능성 수가 2^63-1 보다 작다고 했으므로 long형 사용하였다.
 */

//출처 : https://www.acmicpc.net/problem/1890
public class Main_B_S2_1890_점프 {
	static int N;
	static int[][] board;
	static long[][] memo;
	static int[] drow = {1,0};
	static int[] dcol = {0,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		memo = new long[N][N];
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = str.charAt(col*2)-'0';
			}
		}
		System.out.print(dfs(0,0));
	}
	
	static long dfs(int r, int c) {
		if(memo[r][c] > 0) return memo[r][c];
		if(r==N-1 && c==N-1) return 1;
		if(board[r][c]==0) return 0;
		
		int nr,nc;
		for(int dir = 0; dir < 2; ++dir) {
			nr=r+drow[dir]*board[r][c];	nc=c+dcol[dir]*board[r][c];
			if(isOut(nr, nc)) continue;
			memo[r][c] += dfs(nr,nc);
		}
		
		return memo[r][c];
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
}