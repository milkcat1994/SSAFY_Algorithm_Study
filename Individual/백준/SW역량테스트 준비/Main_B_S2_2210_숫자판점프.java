import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
 * -숫자판 점프-
 * 1. Set이용하여 중복 제거한 완전탐색
 */

//출처 : https://www.acmicpc.net/problem/2210
public class Main_B_S2_2210_숫자판점프 {
	static final int N=5;
	static char[][] board = new char[N][N];
	static int[] drow={-1,1,0,0};
	static int[] dcol={0,0,-1,1};
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = str.charAt(col*2);
			}
		}
		
		char[] res = new char[6];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				dfs(row, col, 0,res);
			}
		}
		System.out.print(set.size());
	}
	
	static void dfs(int r, int c, int index, char[] res) {
		if(index==6) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<index; ++i)
				sb.append(res[i]);
			set.add(sb.toString());
			return;
		}
		
		int nr,nc;
		for(int dir = 0; dir < 4; ++dir) {
			nr=r+drow[dir];	nc=c+dcol[dir];
			if(isOut(nr, nc)) continue;
			res[index]=board[nr][nc];
			dfs(nr,nc,index+1, res);
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
}