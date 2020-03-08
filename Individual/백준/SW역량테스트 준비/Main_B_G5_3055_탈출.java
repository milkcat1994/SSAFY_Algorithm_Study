import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -탈출-
 * 1. 물을 먼저 Queue에 넣고 마지막에 고슴도치 넣는다.
 * 2. BFS이용하여 물을 먼저 퍼트리고 고슴도치를 이동 시킨다.
 * 3. 물과 고슴도치는 같이 움직이므로 물은 빈칸, 고슴도치일때
 * 4. 고슴도치는 빈칸 일때만 이동 할 수 있다.
 */

//출처 : https://www.acmicpc.net/problem/3055
public class Main_B_G5_3055_탈출 {
	public static class Point {
		int row, col;
		char c;
		Point(int row, int col, char c) {
			this.row = row;
			this.col = col;
			this.c = c;
		}
	}
	
	public static int ROW,COL;
	public static char[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		board = new char[ROW][COL];
		Queue<Point> que = new LinkedList<>();
		String tempStr;
		int sr=0,sc=0;
		for (int row = 0; row < ROW; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = tempStr.charAt(col);
				switch (board[row][col]) {
				case '*':
				que.offer(new Point(row,col,'*'));
					break;
				case 'S':	//고슴도치
					sr = row;	sc = col;
					break;
				}
			}
		}
		que.offer(new Point(sr,sc,'S'));
		
		int[] drow = {-1,1,0,0};
		int[] dcol = {0,0,-1,1};
		
		int qsize,cr,cc,nr,nc,time=1;
		Point tp;
		while(!que.isEmpty()) {
			qsize = que.size();
			while(--qsize >= 0) {
				tp = que.poll();
				cr = tp.row;	cc = tp.col;
				
				for(int dir = 0; dir < 4; ++dir) {
					nr = cr+drow[dir];	nc = cc+dcol[dir];
					if(isOut(nr, nc)) continue;
					switch (tp.c) {
					//물이라면 빈칸, 고슴도치로 이동하여 해당 자리 덮어 씌운다.
					case '*':
						if(isWAble(board[nr][nc])) {
							board[nr][nc] = '*';
							que.offer(new Point(nr,nc,'*'));
						}
						break;
					//고슴도치라면 빈칸으로 이동하여 해당 자리 덮어 씌운다.
					case 'S':
						//도착했다면 시간 출력하고 종료
						if(isDFind(board[nr][nc])) {
							System.out.println(time);
							return;
						}
						if(isDAble(board[nr][nc])) {
							board[nr][nc] = 'S';
							que.offer(new Point(nr,nc,'S'));
						}
						break;
					}
					
				} //end for(dir)
			} //end while(--qsize>=0)
			time++;
		}
		System.out.println("KAKTUS");
	}
	
	public static boolean isDFind(char c) {
		if(c == 'D')
			return true;
		return false;
	}
	
	public static boolean isWAble(char c) {
		if(c == '.' || c == 'S')
			return true;
		return false;
	}
	
	public static boolean isDAble(char c) {
		if(c == '.')
			return true;
		return false;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
}
