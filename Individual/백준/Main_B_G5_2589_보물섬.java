import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -보물섬-
 * 기존 코드에서 객체 재생성하였던 부분을 Land[][]로 바꾸어 재사용하여 풀이
 */

//출처 : https://www.acmicpc.net/problem/2589
public class Main_B_G5_2589_보물섬 {
	static int R,C;
	static Land[][] board;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(find());
	}
	
	static int find() {
		int time, res=0;
		for (int row = 0; row < R; ++row) {
			for (int col = 0; col < C; ++col) {
				if(!board[row][col].isLand) continue;
				time = bfs(row, col);
				if(time > res) res = time;
				initVisited();
			}
		}
		return res-1;
	}
	
	static int bfs(int r, int c) {
		Queue<Land> que = new LinkedList<>();
		que.offer(board[r][c]);
		isVisited[r][c] = true;
		
		int time=0, size, nr,nc;
		Land tl;
		while(!que.isEmpty()) {
			size = que.size();
			while(--size >= 0) {
				tl = que.poll();
				
				for(int d = 0; d < 4; ++d) {
					nr=tl.r+drow[d]; nc=tl.c+dcol[d];
					if(isOut(nr, nc) || isVisited[nr][nc]) continue;
					que.offer(board[nr][nc]);
					isVisited[nr][nc] = true;
				}
			}
			time++;
		}
		
		return time;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=R || c>=C || !board[r][c].isLand)
			return true;
		return false;
	}
	
	static void initVisited() {
		for (int row = 0; row < R; ++row) {
			for (int col = 0; col < C; ++col) {
				isVisited[row][col] = false;
			}
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new Land[R][C];
		isVisited = new boolean[R][C];
		String str;
		for (int row = 0; row < R; ++row) {
			str = br.readLine();
			for (int col = 0; col < C; ++col) {
				board[row][col] = new Land(row, col, str.charAt(col) == 'L' ? true : false);
			}
		}
	}
	
	static class Land {
		int r,c;
		boolean isLand;
		Land(int r, int c, boolean isLand){
			this.r = r;
			this.c = c;
			this.isLand = isLand;
		}
	}
}
