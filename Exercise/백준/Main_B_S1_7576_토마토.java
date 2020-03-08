import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -토마토-
 * 1. 익은 토마토, 익지 않은 토마토, 공백을 각각 HashMap에 저장
 * 2. 익은 토마토를 모두 Queue에 넣고 BFS이용하여 모두 탐색,
 * 3. 이떄 범위를 벗어나거나, 익은 토마토, 공백 이면 해당 부분 탐색 x
 * 4. 확인한 깊이를 통해 시간 도출
 */

//출처 : https://www.acmicpc.net/problem/7576
public class Main_B_S1_7576_토마토 {
	static int ROW, COL;
	static int[][] board;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static Map<Integer, Point> wallMap = new HashMap<Integer, Point>();
	static Map<Integer, Point> unTomaMap = new HashMap<Integer, Point>();
	static Map<Integer, Point> tomaMap = new HashMap<Integer, Point>();
	
	public static class Point {
		int row,col;
		
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		COL = Integer.parseInt(st.nextToken());
		ROW = Integer.parseInt(st.nextToken());
		
		//익은 토마토
		Queue<Point> que = new LinkedList<Point>();
		
		board = new int[ROW][COL];
		
		for (int row = 0; row < ROW; ++row) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				switch (board[row][col]) {
				//토마토 없음
				case -1:
					wallMap.put(getIndex(row,col), new Point(row,col));
					break;
				//익은 토마토
				case 1:
					tomaMap.put(getIndex(row,col), new Point(row,col));
					que.offer(new Point(row,col));
					break;
				//안익은 토마토
				case 0:
					unTomaMap.put(getIndex(row,col), new Point(row,col));
				}
			}
		} //end for(row)
		
		Point tp;
		int qsize, cr,cc,nr,nc, ti;
		int depth = 0;
		
		while(!que.isEmpty()) {
			if(unTomaMap.isEmpty()) {
				System.out.println(depth);
				return;
			}
			qsize = que.size();
			
			while(--qsize >= 0) {
				tp = que.poll();
				cr = tp.row;	cc = tp.col;
				for(int i = 0; i < 4; ++i) {
					nr = cr+drow[i];
					nc = cc+dcol[i];
					if(isOut(nr,nc))
						continue;
					
					//해당 토마토 익은토마토에 넣고 안익은 토마토 삭제
					ti = getIndex(nr, nc);
					que.offer(unTomaMap.get(ti));
					tomaMap.put(ti, unTomaMap.get(ti));
					unTomaMap.remove(ti);
				}
			} //end while(--qsize>=0)
			depth++;
		}
		
		if(!unTomaMap.isEmpty()) {
			System.out.println(-1);
		}
	}
	
	public static int getIndex(int row, int col) {
		return row*COL+col;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL
				|| wallMap.containsKey(getIndex(row,col)) || tomaMap.containsKey(getIndex(row,col)))
			return true;
		return false;
	}
}