import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -치즈-
 * 1. 첫 빈칸으로 부터 빈칸 찾으며 사라질 치즈 찾아 dq에 넣기
 * 2. dq이용해 BFS이용, 빈칸의 경우 dq의 맨앞에 넣어준다.
 * 3. 위 3가지 반복.
 * 4. echeeS, techeeS 변수 통해 마지막 전의 남은 치즈 개수 저장.
 */

//출처 : https://www.acmicpc.net/problem/2636
public class Main_B_G5_2636_치즈 {
	static int ROW,COL;
	static int[][] board;
	static boolean[][] isVisited;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static int echeeS;
	
	static class Point {
		int r,c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		board = new int[ROW][COL];
		isVisited = new boolean[ROW][COL];
		for (int row = 0; row < ROW; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
		System.out.print(echeeS);
	}
	
	public static int bfs() {
		//가장 겉면 치즈 담기
		Queue<Point> boundQue = new LinkedList<>();
		//해당 겉면 치즈와 연결되는 빈칸 모두 찾아 위에 넣어주기
		Deque<Point> dq = new LinkedList<>();
		
		boundQue.offer(new Point(0,0));
		isVisited[0][0] = true;
		int qs, cr,cc,nr,nc,time=0, techeeS;
		Point tp;
		
		//첫 빈칸으로부터 사라질 치즈들 찾기
		qs = boundQue.size();
		while(--qs>=0) {
			tp=boundQue.poll();
			cr=tp.r;	cc=tp.c;
			
			for(int dir = 0; dir < 4; ++dir) {
				nr=cr+drow[dir];	nc=cc+dcol[dir];
				if(isOut(nr,nc) || isVisited[nr][nc]) continue;
				isVisited[nr][nc] = true;
				//빈 곳이면 맨 앞에 넣어 계속 찾아보기
				if(board[nr][nc]==0) {
					qs++;
					boundQue.offer(new Point(nr,nc));
				}
				else {
					dq.offer(new Point(nr,nc));
					echeeS++;
				}
			}
		}

		//사라질 치즈로 부터 BFS시작
		while(!dq.isEmpty()) {
			techeeS = 0;
			qs = dq.size();
			while(--qs>=0) {
				tp=dq.poll();
				cr=tp.r;	cc=tp.c;
				
				for(int dir = 0; dir < 4; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];
					if(isOut(nr,nc) || isVisited[nr][nc]) continue;
					isVisited[nr][nc] = true;
					//빈 곳은 가장 앞으로 집어넣어 주변 다시 탐색
					if(board[nr][nc]==0) {
						qs++;
						dq.offerFirst(new Point(nr,nc));
					}
					else {
						dq.offer(new Point(nr,nc));
						techeeS++;
					}
				}
			}
			if(techeeS!=0)
				echeeS = techeeS;
			time++;
		}
		return time;
	}
	
	public static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
}