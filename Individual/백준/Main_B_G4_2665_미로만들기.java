import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -미로만들기-
 */

//출처 : https://www.acmicpc.net/problem/2665
public class Main_B_G4_2665_미로만들기 {
	static int N;
	static boolean[][] board;
	static int[][] arr;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(bfs());
	}
	
	static int bfs() {
		PriorityQueue<Tile> pq = new PriorityQueue<>();
		
		pq.offer(new Tile(0,0,0));
		
		Tile tt;
		int cr,cc,ccnt, nr,nc,ncnt;
		while(!pq.isEmpty()) {
			
			tt = pq.poll();
			cr=tt.r; cc=tt.c; ccnt=tt.cnt;
			for(int d=0; d<4; ++d) {
				nr=cr+drow[d]; nc=cc+dcol[d];
				if(isOut(nr, nc)) continue;
				
				ncnt=ccnt + (!board[nr][nc] ? 1 : 0);
				if(arr[nr][nc] <= ncnt) continue;
				
				if(nr == N-1 && nc == N-1)
					return ncnt;
				
				pq.offer(new Tile(nr,nc, ncnt));
				arr[nr][nc] = ncnt;
			}
		}
		
		return 0;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		board = new boolean[N][N];
		arr = new int[N][N];
		
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = str.charAt(col) == '1' ? true : false;
				arr[row][col] = 123456;
			}
		}
	}
	
	static class Tile implements Comparable<Tile>{
		int r,c, cnt;
		
		Tile(int r, int c, int cnt){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Tile o) {
			return this.cnt - o.cnt;
		}
	}
}
