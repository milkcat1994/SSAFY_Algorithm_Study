import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -적록색약-
 */

//출처 : https://www.acmicpc.net/problem/10026
public class Main_B_G5_10026_적록색약 {
	static char[][] board;
	static boolean[][] isVisited;
	static int N;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static class Point{
		int r,c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		board = new char[N][N];
		
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = str.charAt(col);
			}
		}
		isVisited = new boolean[N][N];
		int res1 = 0, res2=0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(isVisited[row][col]) continue;
				bfs(row, col, false);
				res1++;
			}
		}

		isVisited = new boolean[N][N];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(isVisited[row][col]) continue;
				bfs(row, col, true);
				res2++;
			}
		}
		System.out.println(res1+" "+res2);
	}
	
	static void bfs(int row, int col, boolean flag) {
		Queue<Point> que = new LinkedList<>();
		
		que.offer(new Point(row, col));
		char ch = board[row][col];
		Point tp;
		int cr,cc,nr,nc;
		while(!que.isEmpty()) {
			tp = que.poll();
			cr=tp.r; cc=tp.c;
			for(int d=0; d<4; ++d) {
				nr=cr+drow[d]; nc=cc+dcol[d];
				if(isOut(nr, nc) || isVisited[nr][nc] || !isTrue(board[nr][nc], ch, flag)) continue;
				
				isVisited[nr][nc] = true;
				que.offer(new Point(nr, nc));
			}
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static boolean isTrue(char target, char origin, boolean flag) {
		if(flag) {
			switch (origin) {
			case 'R':
			case 'G':
				if(target == 'R' || target == 'G')
					return true;
				else
					return false;
			case 'B':
			default:
				if(target == 'R' || target == 'G')
					return false;
				else
					return true;
			}
		}
		else {
			if(target != origin)
				return false;
			return true;
		}
	}
}
