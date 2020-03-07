import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -오! 나의 여신님-
 */

/*
 * 메모리 : 24620KB 
 * 시간 : 114ms 
 * 코드길이 : 2249B 
 * 소요시간 : 20M
 */

//150P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWsBQpPqMNMDFARG
public class Solution_SWE_7793_오나의여신님 {
	static int ROW,COL, sr,sc,er,ec;
	static char[][] board;
	static boolean[][] isVisited;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static Queue<int[]> que;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			ROW = Integer.parseInt(st.nextToken());
			COL = Integer.parseInt(st.nextToken());
			board = new char[ROW][COL];
			isVisited = new boolean[ROW][COL];
			que = new LinkedList<>();
			
			String ts;
			for (int row = 0; row < ROW; ++row) {
				ts = br.readLine();
				for (int col = 0; col < COL; ++col) {
					board[row][col] = ts.charAt(col);
					switch (board[row][col]) {
					case 'S':
						sr=row;	sc=col;
						break;
					case 'D':
						er=row;	ec=col;
						break;
					case '*':
						que.offer(new int[] {row,col,'*'});
					}
				}
			}
			int res = bfs();
			System.out.println("#"+t+" "+ (res != -1 ? res : "GAME OVER"));
		} //end TestCase;
	}
	
	public static int bfs() {
		que.offer(new int[] {sr,sc,'S'});
		int qs,cr,cc,cs, nr,nc,ns, time=0;
		int[] tIntArr;
		
		while(!que.isEmpty()) {
			qs=que.size();
			while(--qs>=0) {
				tIntArr=que.poll();
				cr=tIntArr[0];	cc=tIntArr[1];	cs=tIntArr[2];
				if(er==cr && ec==cc && cs=='S') {
					return time;
				}
				
				for(int dir = 0; dir < 4; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];
					if(isOut(nr, nc) || isVisited[nr][nc] || board[nr][nc]=='X') continue;
					ns=board[nr][nc];
					//수지는 .과 D만 밟을 수 있다.
					if(cs=='S') {
						if(ns!='.' && ns!='D') continue;
						que.offer(new int[] {nr,nc,'S'});
						board[nr][nc]='S';
					}
					//악마는 .과 'S'를 밟을 수 있다.
					else if (cs=='*'){
						if(ns!='.' && ns!='S') continue;
						que.offer(new int[] {nr,nc,'*'});
						board[nr][nc]='*';
					}
					isVisited[nr][nc]=true;
				}
			}
			time++;
		}
		return -1;
	}
	
	public static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
}
