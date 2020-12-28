import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -불-
 */

//출처 : https://www.acmicpc.net/problem/5427
public class Main_B_G4_5427_불 {
	static BufferedReader br;
	static int ROW, COL, TC;
	static char[][] board;
	static Queue<Point> que;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		int time;
		
		for(int t=0; t<TC; ++t) {
			init();
			time = bfs();
			System.out.println((time == -1) ? "IMPOSSIBLE" : time);
		}
	}
	
	static int bfs() {
		int time=0,size, cr,cc, nr,nc;
		
		Point tp;
		while(!que.isEmpty()) {
			size = que.size();
			while(--size>=0) {
				tp = que.poll();
				
				cr=tp.r; cc=tp.c;
				if(tp.isFire) {
					for(int d=0; d<4; ++d) {
						nr=cr+drow[d]; nc=cc+dcol[d];
						if(isOut(nr, nc) || board[nr][nc] == '#' || board[nr][nc] == '*') continue;
						que.offer(new Point(nr, nc, true));
						board[nr][nc] = '*';
					}
				}
				else {
					for(int d=0; d<4; ++d) {
						nr=cr+drow[d]; nc=cc+dcol[d];
						if(isOut(nr, nc)) return time+1;
						if(board[nr][nc] == '#' || board[nr][nc] == '*' || board[nr][nc] == '@') continue;
						que.offer(new Point(nr, nc, false));
						board[nr][nc] = '@';
					}
				}
			}
			time++;
		}
		
		return -1;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		COL = Integer.parseInt(st.nextToken());
		ROW = Integer.parseInt(st.nextToken());
		
		que = new LinkedList<>();
		board = new char[ROW][COL];
		String str;
		Point player = null;
		for (int row = 0; row < ROW; ++row) {
			str = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = str.charAt(col);
				if(board[row][col] == '@')
					player = new Point(row, col, false);
			}
		}
		
		for (int row = 0; row < ROW; ++row) {
			for (int col = 0; col < COL; ++col) {
				if(board[row][col] == '*')
					que.offer(new Point(row, col, true));
			}
		}
		que.offer(player);
	}
	
	static class Point {
		int r, c;
		boolean isFire;
		
		Point(int r, int c, boolean isFire){
			this.r = r;
			this.c = c;
			this.isFire = isFire;
		}
	}
}
