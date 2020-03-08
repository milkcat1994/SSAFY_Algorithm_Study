import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -치즈-
 * 1. 모든 빈칸을 찾아나가면서 주위 값을 -1씩 시키며 해당 좌표가 치즈일경우
 * 2. 해당 Point의 s값이 2 이하라면 2군데 이상 빈 공간이므로 dq에 담아준다.
 * 3. dq에 담은 치즈는 주위 값들을 다시 줄여가며, 빈 공간이 나올 경우 dq의 앞에 넣는다.
 */

//출처 : https://www.acmicpc.net/problem/2638
public class Main_B_G4_2638_치즈 {
	static int ROW,COL;
	static boolean[][] isVisited, board;
	static Point[][] cheeze;
	static List<Point> list = new LinkedList<>();
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static class Point {
		int r,c,s;
		public Point(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW=Integer.parseInt(st.nextToken());
		COL=Integer.parseInt(st.nextToken());
		board = new boolean[ROW][COL];
		isVisited = new boolean[ROW][COL];
		cheeze = new Point[ROW][COL];
		
		String str;
		for (int row = 0; row < ROW; ++row) {
			str = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = str.charAt(col*2) == '1';
				//치즈라면 4를 빈공간이라면 0을 넣어준다.
				if(board[row][col])
					cheeze[row][col] = new Point(row,col,4);
				else
					cheeze[row][col] = new Point(row,col,0);
			}
		}
		System.out.print(bfs());
	}
	
	public static int bfs() {
		Deque<Point> dq = new LinkedList<>();
		Queue<Point> que = new LinkedList<>();
		int qs, cr,cc, nr,nc, time=0;
		Point tp, tc;
		que.offer(cheeze[0][0]);
		while(!que.isEmpty()) {
			tp=que.poll();
			cr=tp.r;	cc=tp.c;
			for(int dir = 0; dir < 4; ++dir) {
				nr=cr+drow[dir];	nc=cc+dcol[dir];
				if(isOut(nr, nc) || isVisited[nr][nc]) continue;
				//빈공간은 다시 방문 안하기 위해 true로 변경해주기
				if(!board[nr][nc]) {
					isVisited[nr][nc]=true;
					que.offer(cheeze[nr][nc]);
				}
				//치즈 일경우에는 2군데 접촉인지 파악해주고 dq에 넣기
				else {
					tc=cheeze[nr][nc];
					tc.s--;
					//2군데 이상 접촉 하고 있다면 dq에 넣어주기
					if(tc.s<=2) {
						dq.offer(tc);
						//더이상 방문할 필요가 없다.
						isVisited[nr][nc]=true;
					}
				}
			}
		}
		
		while(!dq.isEmpty()) {
			qs=dq.size();
			while(--qs>=0) {
				tp=dq.poll();
				cr=tp.r;	cc=tp.c;
				for(int dir = 0; dir < 4; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];
					if(isOut(nr, nc) || isVisited[nr][nc]) continue;
					//빈공간이라면 가장 앞으로 집어넣어서 주변 치즈 다시 줄여주기
					if(!board[nr][nc]) {
						isVisited[nr][nc]=true;
						qs++;
						dq.offerFirst(cheeze[nr][nc]);
					}
					//치즈 일경우에는 2군데 접촉인지 파악해주고 dq에 넣기
					else {
						tc=cheeze[nr][nc];
						tc.s--;
						//2군데 이상 접촉 하고 있다면 dq에 넣어주기
						if(tc.s<=2) {
							dq.offer(tc);
							//더이상 방문할 필요가 없다.
							isVisited[nr][nc]=true;
						}
					}
				}
			}
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
