import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -레이저 통신-
 * 1. PQ 이용하여 가장 적게 이동한 것 먼저 이동
 * 2. 이전 진행 방향에 따른 현재 좌표의 커브 횟수 계산
 * 3. Que의 모든 것 다 빠질 떄까지 진행
 */

//출처 : https://www.acmicpc.net/problem/6087
public class Main_B_G4_6087_레이저통신 {
	static int R,C;
	static char[][] board;
	static int[][] isVisited;
	//상우하좌
	static int[] drow = new int[] {-1,0,1,0};
	static int[] dcol = new int[] {0,1,0,-1};
	
	//시작점, 도착점
	static int sr,sc,er,ec;
	
	public static class Point implements Comparable<Point>{
		//row, col, 이전 방향,커브횟수
		int r, c, ed, cc;
		Point(int r, int c){
			this.r = r;
			this.c = c;
			this.ed = -1;
			this.cc = 0;
		}
		
		Point(int r, int c, int ed, int cc){
			this.r = r;
			this.c = c;
			this.ed = ed;
			this.cc = cc;
		}
		
		public int ifMoved(int cd) {
			//ed가 -1이 아니고 이전 방향과 다르다면 커브횟수 증가
			if(ed != -1 && ed != cd)
				return this.cc+1;
			return this.cc;
		}

		@Override
		public int compareTo(Point o) {
			return this.cc - o.cc;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		isVisited = new int[R][C];
		for(int r = 0; r < R; ++r)
			Arrays.fill(isVisited[r], Integer.MAX_VALUE);
		
		String ts;
		char tc;
		List<int[]> list = new ArrayList<>();
		
		for (int row = 0; row < R; ++row) {
			ts = br.readLine();
			for (int col = 0; col < C; ++col) {
				tc = ts.charAt(col);
				board[row][col] = tc;
				if(tc == 'C') {
					list.add(new int[]{row,col});
				}
			}
		}
		
		// 현재점, 다음점
		int cr=0,cc=0,ced=0,ccc=0, nr,nc;
		Point tp;
		
		sr = list.get(0)[0];	sc = list.get(0)[1];
		er = list.get(1)[0];	ec = list.get(1)[1];
		
		PriorityQueue<Point> que = new PriorityQueue<>();
		que.offer(new Point(sr, sc));
		
		int qs;
		boolean findGoal = false;
		while(!que.isEmpty()) {
				tp = que.poll();
				cr = tp.r; cc = tp.c; ced = tp.ed; ccc = tp.cc;
				
				//4방향 진행
				for(int d = 0; d < 4; ++d) {
					nr = cr+drow[d];	nc = cc+dcol[d];
					
					// 나가거나  이미 방문한 좌표라면 패스
					// 움직인다고 가정했을때 다음 좌표에 기록된 값보다 크다면 갈 필요 없음.
					if(isOut(nr,nc) ||  isVisited[nr][nc] < tp.ifMoved(d))
						continue;
					
					if(!findGoal && isGoal(nr,nc))
						findGoal = true;
					
					que.offer(new Point(nr,nc, d,tp.ifMoved(d)));
					isVisited[nr][nc] = tp.ifMoved(d);
				}
			
		} //end while(!que.isEmpty())
		
		System.out.println(isVisited[er][ec]);
	}
	
	static boolean isGoal(int r, int c) {
		if(r == er && c == ec)
			return true;
		return false;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=R || c>=C || board[r][c] == '*')
			return true;
		return false;
	}
}
