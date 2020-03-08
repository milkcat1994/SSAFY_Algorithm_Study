import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -벽부수고 이동하기-
 * 1. 현재 자리가 도착 자리인지 파악,
 * 2. 0,0을 queue에 먼저 담는다.
 * 3. 상하좌우로 퍼져나가며, 벽을 부술수 있다면(wall==3) 해당 벽의 좌표를 que에 넣는다.
 * 4. 지나다닐 수 있는 길에는 벽을 부술수 있는 경로가 지나갔는지 표시하며 다닌다.
 * 5. 만일 벽을 부술 수 있는 경로가 벽을 이미 부순 경로위를 지나간다면 해당 경로를 덮어씌워 지나간다.
 */

//출처 : https://www.acmicpc.net/problem/2206
public class Main_B_G4_2206_벽부수고이동하기 {
	public static class Point {
		//벽을 부술수 있다면 wall=3
		//벽 이미 부수었다면 wall=2
		int row, col, wall;
		
		Point(int row, int col, int wall){
			this.row = row;
			this.col = col;
			this.wall = wall;
		}
	}
	
	static int ROW, COL;
	static Queue<Point> que = new LinkedList<Point>();
	static int[][] board;
	static boolean[][] isVisited;
	
	//상,하,좌,우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
	
		board = new int[ROW][COL];
		isVisited = new boolean[ROW][COL];
		
		String tempStr;
		for (int row = 0; row < ROW; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = tempStr.charAt(col)-'0';
			}
		}
		
		if(isFind(0,0)) {
			System.out.println(1);
			return;
		}

		que.offer(new Point(0, 0, 3));
		isVisited[0][0] = true;
		
		boolean isFind = false;
		int cr,cc,nr,nc, qsize, time=1;
		Point tp;
		while(!que.isEmpty()) {
			qsize = que.size();

			while(--qsize>=0) {
				tp = que.poll();
				cr = tp.row;	cc = tp.col;
				
				for(int i = 0; i < 4; ++i) {
					nr = cr+drow[i];
					nc = cc+dcol[i];
					if(isOut(nr,nc))
						continue;
					
					//벽이고, 뚫을 수 있다면 뚫기
					if(isWall(nr,nc)) {
						//뚫지 못한다면 que에 넣지 않기
						if(tp.wall == 3) {
							que.offer(new Point(nr,nc,tp.wall-1));
						}
					}
					//벽이 아니라면 que에 넣어주기
					else {
						//만일 방문했는데 해당 자리 벽 부순애가 들어왔다면, 내가 벽 부술수 있다면 덮어씌우기
						if(isVisited[nr][nc] && board[nr][nc] == 2 && tp.wall == 3) {
							que.offer(new Point(nr,nc,tp.wall));
							board[nr][nc] = 3;
						}
						//방문 하지 않은 자리라면 현재 tp.wall을 바닥에 새기기
						else if(!isVisited[nr][nc]) {
							que.offer(new Point(nr,nc,tp.wall));
							isVisited[nr][nc] = true;
							board[nr][nc] = tp.wall;
						}
						
						if(isFind(nr,nc)) {
							isFind = true;
						}
					}
				}
			} //end while(--qsize>=0)
			time++;
			
			//만일 찾았다면 시간 출력
			if(isFind) {
				System.out.println(time);
				return;
			}
		} //end while(!que.isEmpty())
		
		if(!isFind)
			System.out.println(-1);
	} //end main
	
	public static boolean isFind(int row, int col) {
		if(row == ROW-1 && col == COL-1)
			return true;
		return false;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
	
	public static boolean isWall(int row, int col) {
		if(board[row][col] == 1)
			return true;
		return false;
	}
}
