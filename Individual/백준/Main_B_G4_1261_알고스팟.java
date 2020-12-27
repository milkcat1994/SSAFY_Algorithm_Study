import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -알고스팟-
 */

//출처 : https://www.acmicpc.net/problem/1261
public class Main_B_G4_1261_알고스팟 {
	static int N,M;
	static boolean[][] board;
	static int[][] isVisited;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		init();
		
		find();
		
		System.out.print(isVisited[N-1][M-1]);
	}
	
	static void find() {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0,0,0));
		
		int cr,cc,cw, nr,nc,nw;
		Vertex tv;
		while(!pq.isEmpty()) {
			tv = pq.poll();
			
			cr=tv.r; cc=tv.c; cw=tv.w;
			for(int d=0; d<4; ++d) {
				nr=cr+drow[d]; nc=cc+dcol[d];
				if(isOut(nr, nc)) continue;
				
				nw=cw;
				if(!board[nr][nc]) nw++;
				if(nw < isVisited[nr][nc]) {
					pq.offer(new Vertex(nr,nc,nw));
					isVisited[nr][nc] = nw;
					if(nr==N-1 && nc==M-1)
						return;
				}
			}
		}
	}
	
	static boolean isOut(int r, int c){
		if(r<0 || c<0 || r>=N || c>=M)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		board = new boolean[N][M];
		isVisited = new int[N][M];
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < M; ++col) {
				board[row][col] = str.charAt(col)-'0' == 0 ? true : false;
				isVisited[row][col] = Integer.MAX_VALUE;
			}
		}
		
		// 시작점은 0부터 시작
		isVisited[0][0] = 0;
	}
	
	static class Vertex implements Comparable<Vertex>{
		int r,c,w;
		
		Vertex(int r, int c, int w){
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
	}
}
