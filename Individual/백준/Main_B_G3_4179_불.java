import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -불-
 */

//출처 : https://www.acmicpc.net/problem/4179
public class Main_B_G3_4179_불 {
	static int R,C;
	static char[][] board;
	static boolean[][] isVisited;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	//지훈이 리스트, 불 리스트
	static Queue<Point> pQue, fQue;
	
	static public class Point{
		int r,c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		isVisited = new boolean[R][C];
		pQue = new LinkedList<>();
		fQue = new LinkedList<>();
		
		String str;
		for (int row = 0; row < R; ++row) {
			str = br.readLine();
			for (int col = 0; col < C; ++col) {
				board[row][col] = str.charAt(col);
				switch (board[row][col]) {
				case 'J':
					pQue.offer(new Point(row, col));
					isVisited[row][col] = true;
					break;
				case 'F':
					fQue.offer(new Point(row, col));
					isVisited[row][col] = true;
				default:
					break;
				}
			}
		}
		
		int cr,cc, nr,nc;
		int pSize, fSize, time = 0;
		Point tp;
		//지훈이 먼저 이동
		while(!pQue.isEmpty()) {
			pSize = pQue.size();
			time++;
			while(--pSize >= 0) {
				tp = pQue.poll();
				cr = tp.r;	cc = tp.c;
				
				//현재 위치 불일 경우 패스
				if(board[cr][cc] == 'F')
					continue;
				
				for(int d=0; d<4; ++d) {
					nr = cr+drow[d];	nc = cc+dcol[d];
					//나가면 바로 끝
					if(isOut(nr, nc)) {
						System.out.println(time);
						return;
					}
					//벽이나 불일경우 패스
					if(isWall(nr,nc) || isFire(nr,nc) || isVisited[nr][nc])
						continue;
					
					//.일경우 해당 부분으로 이동
					pQue.offer(new Point(nr,nc));
					isVisited[nr][nc] = true;
					board[nr][nc] = 'J';
				}
			} //Player 이동 끝
			
			//불 이동
			fSize = fQue.size();
			while(--fSize >= 0) {
				tp = fQue.poll();
				cr = tp.r; cc = tp.c;
				
				for(int d=0; d<4; ++d) {
					nr = cr+drow[d];	nc = cc+dcol[d];
					//나가거나, 방문했거나, 벽이거나
					if(isOut(nr, nc) || isFire(nr, nc) || isWall(nr, nc))
						continue;
					
					fQue.offer(new Point(nr,nc));
					isVisited[nr][nc] = true;
					board[nr][nc] = 'F';
				}
			}
		} // end while(pQue.isEmpty())
		System.out.println("IMPOSSIBLE");
	}
	
	//탈출 여부 판단
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=R || c>=C)
			return true;
		return false;
	}
	
	static boolean isWall(int r, int c) {
		if(board[r][c] == '#')
			return true;
		return false;
	}
	
	static boolean isFire(int r, int c) {
		if(board[r][c] == 'F')
			return true;
		return false;
	}
}
