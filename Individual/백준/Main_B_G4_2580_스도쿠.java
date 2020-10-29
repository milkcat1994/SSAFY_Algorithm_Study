import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -스도쿠-
 */

//출처 : https://www.acmicpc.net/problem/2580
public class Main_B_G4_2580_스도쿠 {
	static final int N = 9;
	static int[][] board;
	static int[] isRow;
	static int[] isCol;
	static int[][] isSquare;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		board = new int[N][N];
		isRow = new int[N];
		isCol = new int[N];
		isSquare = new int[3][3];
		
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				isRow[row] |= (1<<board[row][col]);
				isCol[col] |= (1<<board[row][col]);
				isSquare[row/3][col/3] |= (1<<board[row][col]);
			}
		}
		
		dfs(0);
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				sb.append(board[row][col]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
	
	static boolean dfs(int cnt) {
		//다 찾았다면 성공했다는것.
		if(81 == cnt) {
			return true;
		}
		
		int row=cnt/N;
		int col=cnt%N;
		if(board[row][col] != 0) {
			if(dfs(cnt+1)) return true;
		}
		else {
			int flag,bit;
			for(int i=1; i<=N; ++i) {
				flag=(isRow[row] | isCol[col] | isSquare[row/3][col/3]);
				bit=(1<<i);
				if((flag & bit) > 0) continue;
				
				board[row][col] = i;
				isRow[row] |= bit;
				isCol[col] |= bit;
				isSquare[row/3][col/3] |= bit;
				if(dfs(cnt+1)) return true;
				board[row][col] = 0;
				isRow[row] ^= bit;
				isCol[col] ^= bit;
				isSquare[row/3][col/3] ^= bit;
			}
		}
		return false;
	}
}
