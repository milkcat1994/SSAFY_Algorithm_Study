import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -토마토-
 */

//출처 : https://www.acmicpc.net/problem/7569
public class Main_B_S1_7569_토마토 {
	static int R, C, H;
	static Tomato[][][] tomato;
	static boolean[][][] isVisited;
	static Queue<Tomato> que;
	static int targetTomato;
	
	// 순서대로 row, col, hei 변경
	static int[] drow = {-1,1, 0,0, 0,0};
	static int[] dcol = {0,0, -1,1, 0,0};
	static int[] dhei = {0,0, 0,0, -1,1};
	
	public static void main(String[] args) throws Exception {
		init();
		int res=bfs();
		System.out.print(targetTomato == 0 ? res : -1);
	}
	
	static int bfs() {
		int time = -1, size, cr,cc,ch, nr,nc,nh;
		Tomato tt;
		
		while(!que.isEmpty()) {
			size = que.size();
			while(--size >= 0) {
				tt = que.poll();
				cr=tt.r; cc=tt.c; ch=tt.h;
				for(int d=0; d<6; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d]; nh=ch+dhei[d];
					if(isOut(nr, nc, nh) || isVisited[nh][nr][nc] || tomato[nh][nr][nc].flag == -1) continue;
					
					tomato[nh][nr][nc].changeFlag();
					que.offer(tomato[nh][nr][nc]);
					isVisited[nh][nr][nc] = true;
					targetTomato--;
				}
			}
			time++;
		}
		
		return time;
	}
	
	static boolean isOut(int r, int c, int h) {
		if(r<0 || c<0 || h<0 || r>=R || c>=C || h>=H)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		que = new LinkedList<>();
		tomato = new Tomato[H][R][C];
		isVisited = new boolean[H][R][C];
		int ti;
		for (int height = 0; height < H; ++height) {
			for (int row = 0; row < R; ++row) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 0; col < C; ++col) {
					ti = Integer.parseInt(st.nextToken());
					tomato[height][row][col] = new Tomato(row, col, height, ti);
					if(ti == 1) {
						isVisited[height][row][col] = true;
						que.offer(tomato[height][row][col]);
					}
					else if(ti == 0) {
						targetTomato++;
					}
				}
			}
		}
	}
	
	static class Tomato{
		// flag : -1, 0, 1
		int r,c,h, flag;
		
		Tomato(int r, int c, int h, int flag){
			this.r = r;
			this.c = c;
			this.h = h;
			this.flag = flag;
		}
		
		void changeFlag() {
			this.flag = 1;
		}
	}
}
