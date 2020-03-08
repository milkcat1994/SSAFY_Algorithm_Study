import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -달이 차오른다, 가자.-
 * 1. 해당 좌표 방문상태 3차원으로 관리
 * 2. 열쇠상태에 따라 방문상태 조절하기 위함이다.
 * 3. BFS이용하여 목적지 도착하는 최단 거리 출력
 * 4. 열쇠 상태는 BitMask이용하여 관리하였다.
 */

//출처 : https://www.acmicpc.net/problem/1194
public class Main_B_G1_1194_달이차오른다가자 {
	static final int KEYNUM = 'F'-'A'+1;
	static int ROW,COL;
	static char[][] board;
	static boolean[][][] isVisited;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static class Point {
		int row,col,cond;
		Point(int row, int col, int cond){
			this.row = row;
			this.col = col;
			this.cond = cond;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW=Integer.parseInt(st.nextToken());
		COL=Integer.parseInt(st.nextToken());
		
		board=new char[ROW][COL];
		isVisited = new boolean[ROW][COL][1<<KEYNUM];
		String ts;
		int sr=0,sc=0;
		for (int row = 0; row < ROW; ++row) {
			ts=br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = ts.charAt(col);
				if(board[row][col]=='0') {
					sr=row; sc=col;
				}
			}
		}
		
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(sr,sc,0));
		isVisited[sr][sc][0] = true;
		
		int cr,cc,ccond, nr,nc,ncond, qs,time=0;
		Point tp;
		boolean isFind=false;
		W:while(!que.isEmpty()) {
			qs = que.size();
			while(--qs>=0) {
				tp=que.poll();
				cr=tp.row;	cc=tp.col;	ccond=tp.cond;
				if(isFind(cr, cc)) {
					isFind=true;
					break W;
				}
				for(int dir = 0; dir < 4; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];	ncond=ccond;
					//나가거나 이미 방문했거나 벽이라면 패스
					if(isOut(nr,nc) || isVisited[nr][nc][ncond] || board[nr][nc]=='#') continue;
					//문이라면
					if('A'<=board[nr][nc] && board[nr][nc]<='F') {
						//해당 열쇠가 없다면 가지 못한다.
						if((ccond & (1<<(board[nr][nc]-'A'))) == 0) {
							continue;
						}
					}
					//다음 자리가 열쇠라면
					else if('a'<=board[nr][nc] && board[nr][nc]<='f') {
						ncond = ccond | (1<<(board[nr][nc]-'a'));
					}
					isVisited[nr][nc][ncond] = true;
					que.offer(new Point(nr,nc,ncond));
				} //end for(dir)
			}
			time++;
		}
		
		if(isFind)
			System.out.println(time);
		else
			System.out.println(-1);
	}
	
	public static boolean isFind(int row, int col) {
		if(board[row][col] =='1')
			return true;
		return false;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
}
