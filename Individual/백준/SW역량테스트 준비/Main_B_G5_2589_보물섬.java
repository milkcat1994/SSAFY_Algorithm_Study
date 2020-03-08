import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -보물섬-
 * 1. BFS이용한 완전탐색
 */

//출처 : https://www.acmicpc.net/problem/2589
public class Main_B_G5_2589_보물섬 {
	static int ROW,COL;
	static boolean[][] isVisited;
	static char[][] board;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		board = new char[ROW][COL];
		isVisited = new boolean[ROW][COL];
		//육지(L)나 바다(W)
		String tempStr;
		for (int row = 0; row < ROW; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = tempStr.charAt(col);
			}
		}
		
		int tempRes, maxRes=0;
		for (int row = 0; row < ROW; ++row) {
			for (int col = 0; col < COL; ++col) {
				//땅일 경우 BFS시작
				if(board[row][col] == 'L') {
					tempRes = bfs(row, col);
					maxRes = maxRes > tempRes ? maxRes : tempRes;
				}
			}
		}
		System.out.println(maxRes);
	}
	
	public static int bfs(int row, int col) {
		initVisited();
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {row,col});
		isVisited[row][col] = true;
		int[] tIntArr;
		int cr,cc,nr,nc, qSize, time = -1;
		while(!que.isEmpty()) {
			qSize = que.size();
			while(--qSize >= 0) {
				tIntArr = que.poll();
				cr = tIntArr[0];	cc = tIntArr[1];
				for(int dir = 0; dir < 4; ++dir) {
					nr = cr+drow[dir];	nc = cc+dcol[dir];
					if(isOut(nr, nc) || board[nr][nc] == 'W' || isVisited[nr][nc]) continue;
					
					que.offer(new int[] {nr,nc});
					isVisited[nr][nc] = true;
				}
			}
			time++;
		}
		return time;
	}
	
	public static void initVisited() {
		for (int row = 0; row < ROW; ++row) {
			for (int col = 0; col < COL; ++col) {
				isVisited[row][col] = false;
			}
		}
	}
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
}