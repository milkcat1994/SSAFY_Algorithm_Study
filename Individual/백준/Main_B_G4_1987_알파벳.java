import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -알파벳-
 * 현재까지 밟아온 알파벳을 기억한 완탐
 */

//출처 : https://www.acmicpc.net/problem/1987
public class Main_B_G4_1987_알파벳 {
	static int[][] board;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static int R,C,res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		String str;
		board = new int[R][C];
		for (int row = 0; row < R; ++row) {
			str = br.readLine();
			for (int col = 0; col < C; ++col) {
				board[row][col] = str.charAt(col)-'A';
			}
		}
		dfs(0,0,0,0);
		
		System.out.println(res);
	}
	
	static void dfs(int r, int c, int tot, int flag) {
		//지나갈 수 없으므로 갱신
		if((1<<board[r][c] & flag) > 0) {
			res = res > tot ? res : tot;
			return;
		}
		flag |= 1<<board[r][c];
		
		int nr,nc;
		for(int d=0; d<4; ++d) {
			nr=r+drow[d]; nc=c+dcol[d];
			if(isOut(nr,nc)) continue;
			dfs(nr, nc, tot+1, flag);
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=R || c>=C)
			return true;
		return false;
	}
}
