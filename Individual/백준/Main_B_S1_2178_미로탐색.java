import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -미로탐색-
 */

//출처 : https://www.acmicpc.net/problem/2178
public class Main_B_S1_2178_미로탐색 {
	public static class Point{
		int r,c,w;
		Point(int r, int c, int w){
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
	static int N,M;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int ti;
		int[][] board;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < M; ++col) {
				ti = str.charAt(col) -'0';
				board[row][col] = ti == 1 ? Integer.MAX_VALUE : ti;
			}
		}
		
		Queue<Point> que = new LinkedList<>();
		int cr,cc, nr,nc, size, time=1;
		Point tp;
		que.offer(new Point(0,0,time));
		while(!que.isEmpty()) {
			size = que.size();
			while(--size >= 0) {
				tp = que.poll();
				cr=tp.r; cc=tp.c;
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr, nc) || tp.w+1 >= board[nr][nc]) continue;
					if(nr == N-1 && nc ==M-1) {
						System.out.println(time+1);
						return;
					}
					
					que.offer(new Point(nr,nc,time+1));
					board[nr][nc] = time+1;
				}
			}
			time++;
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=M)
			return true;
		return false;
	}
}
