import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -상범 빌딩-
 */

//출처 : https://www.acmicpc.net/problem/6593
public class Main_B_G5_6593_상범빌딩 {
	static BufferedReader br;
	static int H,R,C;
	static Tile[][][] board;
	static boolean[][][] isVisited;
	
	static Queue<Tile> que;
	
	static int[] dhei = {-1,1, 0,0, 0,0};
	static int[] drow = {0,0, -1,1, 0,0};
	static int[] dcol = {0,0, 0,0, -1,1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(init()) {
			int res = bfs();
			
			if(res == -1)
				sb.append("Trapped!");
			else
				sb.append("Escaped in " + res + " minute(s).");
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
	
	static int bfs() {
		int time = 0, size, cr,cc,ch, nr,nc,nh;
		
		Tile tt;
		while(!que.isEmpty()) {
			size = que.size();
			while(--size >= 0) {
				tt = que.poll();
				cr=tt.r; cc=tt.c; ch=tt.h;
				if(tt.flag == 3) return time;

				for(int d=0; d<6; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d]; nh=ch+dhei[d];
					if(isOut(nr,nc,nh) || isVisited[nh][nr][nc] || board[nh][nr][nc].flag == 1) continue;
					
					que.offer(board[nh][nr][nc]);
					isVisited[nh][nr][nc] = true;
				}
			}
			time++;
		}
		
		return -1;
	}
	
	static boolean isOut(int r, int c, int h) {
		if(r<0 || c<0 || h<0 || r>=R || c>=C || h>=H)
			return true;
		return false;
	}
	
	static boolean init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		if(H == 0 && R == 0 && C == 0) return false;
		
		board = new Tile[H][R][C];
		isVisited = new boolean[H][R][C];
		
		que = new LinkedList<>();
		String str;
		for(int height = 0; height < H; ++height) {
			for (int row = 0; row < R; ++row) {
				str = br.readLine();
				for (int col = 0; col < C; ++col) {
					switch (str.charAt(col)) {
					case '.':
						board[height][row][col] = new Tile(height, row, col, 0);
						break;
					case '#':
						board[height][row][col] = new Tile(height, row, col, 1);
						break;
					case 'S':
						board[height][row][col] = new Tile(height, row, col, 2);
						que.offer(board[height][row][col]);
						isVisited[height][row][col] = true;
						break;
					case 'E':
						board[height][row][col] = new Tile(height, row, col, 3);
						break;
					}
				}
			}
			str = br.readLine();
		}
		
		return true;
	}
	
	static class Tile {
		int h,r,c;
		// blank : 0, wall : 1, start : 2, end : 3
		int flag;
		
		Tile(int h, int r, int c, int flag){
			this.h = h;
			this.r = r;
			this.c = c;
			this.flag = flag;
		}
	}
}
