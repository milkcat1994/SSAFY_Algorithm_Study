import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -거울설치-
 */

//출처 : https://www.acmicpc.net/problem/2151
public class Main_B_G4_2151_거울설치 {
	static int N;
	static char[][] board;
	static int[][][] cntBoard;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};

	// 처음 방향 -1
	public static class Point implements Comparable<Point>{
		int r, c, d, w;
		Point(int r, int c, int d, int w){
			this.r = r;
			this.c = c;
			this.d = d;
			this.w = w;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		board = new char[N][N];
		cntBoard = new int[N][N][4];
		String str;
		PriorityQueue<Point> pq = new PriorityQueue<>();
		List<int[]> goal = new ArrayList<>();
		
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = str.charAt(col);
				if(board[row][col] == '#') {
					goal.add(new int[] {row,col});
				}
				for(int d = 0; d < 4; ++d)
					cntBoard[row][col][d] = 1000;
			}
		}

		int cr,cc, nr,nc, minRes=1000;
		Point tp;
		cr=goal.get(0)[0]; cc=goal.get(0)[1];
		for(int d =0; d<4; ++d) {
			nr=cr+drow[d]; nc=cc+dcol[d];
			if(isOut(nr, nc)) continue;
			pq.offer(new Point(nr, nc, d,0));
		}
		
		while(!pq.isEmpty()) {
			tp = pq.poll();
			if(tp.w >= minRes)
				continue;
			cr=tp.r; cc=tp.c;
			//현재 좌표 따라 진행
			switch (board[cr][cc]) {
			case '!':
				switch (tp.d) {
				case 0: case 1:
					nr=cr+drow[2]; nc=cc+dcol[2];
					if(!isOut(nr, nc) && cntBoard[nr][nc][2] > tp.w+1) {
						pq.offer(new Point(nr,nc,2,tp.w+1));
						cntBoard[nr][nc][2] = tp.w+1;
					}
					nr=cr+drow[3]; nc=cc+dcol[3];
					if(!isOut(nr, nc) && cntBoard[nr][nc][3] > tp.w+1) {
						pq.offer(new Point(nr,nc,3,tp.w+1));
						cntBoard[nr][nc][3] = tp.w+1;
					}
					break;
				case 2: case 3:
					nr=cr+drow[0]; nc=cc+dcol[0];
					if(!isOut(nr, nc) && cntBoard[nr][nc][0] > tp.w+1) {
						pq.offer(new Point(nr,nc,0,tp.w+1));
						cntBoard[nr][nc][0] = tp.w+1;
					}
					nr=cr+drow[1]; nc=cc+dcol[1];
					if(!isOut(nr, nc) && cntBoard[nr][nc][1] > tp.w+1) {
						pq.offer(new Point(nr,nc,1,tp.w+1));
						cntBoard[nr][nc][1] = tp.w+1;
					}
					break;
				}
			case '.':
				nr=cr+drow[tp.d]; nc=cc+dcol[tp.d];
				if(!isOut(nr, nc) && cntBoard[nr][nc][tp.d] > tp.w) {
					pq.offer(new Point(nr,nc,tp.d,tp.w));
					cntBoard[nr][nc][tp.d] = tp.w;
				}
				break;
			case '#':
				if(cr == goal.get(1)[0] && cc == goal.get(1)[1]) {
					System.out.println(tp.w);
					return;
				}
				break;
			}
		}
	}
	
	//나가거나 벽
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N || board[r][c] == '*')
			return true;
		return false;
	}
}
