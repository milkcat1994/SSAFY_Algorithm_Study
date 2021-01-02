import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -N번째 큰수-
 * 다 넣지 않고 배열에 저장하고 마지막 행만 pq에 넣는다.
 * 가장 최대인 수를 poll하면, 해당 열의 이전 행의 값을 넣으면 열의 항상 최대인 값만 pq에 들어가게 된다.
 */

//출처 : https://www.acmicpc.net/problem/2075
public class Main_B_G4_2075_N번째큰수 {
	static int N;
	static int[][] board;
	static PriorityQueue<Point> pq;
	
	public static void main(String[] args) throws Exception {
		init();
		
		find();
		
		System.out.print(pq.poll().n);
	}
	
	static void find() {
		Point tp;
		for(int i=0; i<N-1; ++i) {
			tp = pq.poll();
			if(tp.r != 0) {
				pq.offer(new Point(tp.r-1, tp.c, board[tp.r-1][tp.c]));
			}
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>();
		board = new int[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				if(row == N-1)
					pq.offer(new Point(row, col, board[row][col]));
			}
		}
	}
	
	static class Point implements Comparable<Point>{
		int r,c,n;
		
		Point(int r, int c, int n){
			this.r = r;
			this.c = c;
			this.n = n;
		}

		@Override
		public int compareTo(Point o) {
			return o.n - this.n;
		}
	}
}
