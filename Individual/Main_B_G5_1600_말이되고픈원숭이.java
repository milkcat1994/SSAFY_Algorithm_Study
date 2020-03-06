import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -말이 되고픈 원숭이-
 * 1. visit배열에 방문시 점프 남은 횟수 저장하기 -> 남은 점프가 많을 경우만 재방문 가능
 * 2. BFS통해 최소 시간 구하기
 * 
 * ++ 입력은 열,행 순으로 받는다. => 항상 행,열 순은 아니니 주의
 */

//출처 : https://www.acmicpc.net/problem/1600
public class Main_B_G5_1600_말이되고픈원숭이 {
	static class Point {
		int row,col,jump;

		public Point(int row, int col, int jump) {
			this.row = row;
			this.col = col;
			this.jump = jump;
		}
	}
	
	static int ROW,COL;
	static int[][] board;
	static int[][] isVisited;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static int[] dhrow = {-2,-2,-1,1,2,2,1,-1};
	static int[] dhcol = {-1,1,2,2,1,-1,-2,-2};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		COL = Integer.parseInt(st.nextToken());
		ROW = Integer.parseInt(st.nextToken());
		
		board = new int[ROW][COL];
		isVisited = new int[ROW][COL];
		for (int row = 0; row < ROW; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(isVisited[row], -1);
		}
		
		isVisited[0][0]=K;
		System.out.print(bfs(new Point(0,0,K)));
	}
	
	public static int bfs(Point cp) {
		Queue<Point> que = new LinkedList<>();
		que.offer(cp);
		Point tp;
		int qs, cr,cc,cj, nr,nc,nj, time=0;
		
		while(!que.isEmpty()) {
			qs = que.size();
			
			while(--qs>=0) {
				tp = que.poll();
				cr=tp.row;	cc=tp.col;	cj=tp.jump;
				
				if(cr==ROW-1 && cc==COL-1)
					return time;
				//말의 움직임
				if(cj>0) {
					nj=cj-1;
					for(int dir = 0; dir < 8; ++dir) {
						nr=cr+dhrow[dir];	nc=cc+dhcol[dir];
						//나가거나 해당 위치가 더 적은 점프를 가지고 방문했을 경우
						if(isOut(nr, nc) || isVisited[nr][nc] >= nj || board[nr][nc]==1) continue;
						isVisited[nr][nc]=nj;
						que.offer(new Point(nr,nc,nj));
					}
				}
				
				//상하좌우 이동
				for(int dir = 0; dir < 4; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];
					//나가거나 해당 위치가 더 적은 점프를 가지고 방문했을 경우
					if(isOut(nr, nc) || isVisited[nr][nc] >= cj || board[nr][nc]==1) continue;
					isVisited[nr][nc]=cj;
					que.offer(new Point(nr,nc,cj));
				}
			}
			time++;
		}
		return -1;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
}
