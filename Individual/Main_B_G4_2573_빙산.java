import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -빙산-
 * 1. 모든 빙산은 Point Class의 Set으로 관리한다.
 * 2. 모든 빙산의 4방향을 확인하여 물의 갯수 파악, 다음 예상 얼음량을 저장한다.
 * 3. 다시 모든 빙산을 돌면서 board에 해당 얼음량 갱신, 0일경우 Set에서 제거한다.
 * 4. 두개로 나뉘어져있는지 BFS를 이용하여 판단하고
 * 5. 만일 그냥 다녹았을 경우 Set이 비어있으므로 0을 출력하고 종료시킨다.
 */

// 출처 : https://www.acmicpc.net/problem/2573
public class Main_B_G4_2573_빙산 {
	static int ROW,COL;
	static int[][] board;
	static boolean[][] isVisited;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static Set<Point> set = new HashSet<Point>();
	
	public static class Point {
		int row, col, sum;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		Point(int row, int col, int sum) {
			this.row = row;
			this.col = col;
			this.sum = sum;
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
			st = new StringTokenizer(br.readLine()," ");
			for (int col = 0; col < COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				//빙산일 경우 Set에 추가
				if(board[row][col] != 0) {
					set.add(new Point(row,col,board[row][col]));
				}
			}
		}
		
		//각 빙산 돌면서 주위의 0 갯수 만큼 sum줄이기
		//sum이 0이 되었다면 Set에서 제거
		Iterator<Point> iter;
		Point tp;
		int cr,cc,nr,nc, nw, cs,ns, time = 0;
		do {
			//2개로 나뉘지 않고 다 녹았을 경우
			if(set.isEmpty()) {
				System.out.println(0);
				return;
			}
			iter = set.iterator();
			while(iter.hasNext()) {
				tp = iter.next();
				cr = tp.row;	cc = tp.col;	cs = tp.sum;
				nw = 0;
				for(int dir = 0; dir < 4; ++dir) {
					nr = cr+drow[dir];	nc = cc+dcol[dir];
					if(isOut(nr, nc)) continue;
					if(board[nr][nc] == 0) nw++;
				}
				//주변 물 갯수 만큼 해당 물 줄이기
				ns = cs - nw;
				if(ns<0) ns = 0;
				tp.sum = ns;
			}
			
			//0이라면 Set에서 삭제 하고 board 갱신
			iter = set.iterator();
			while(iter.hasNext()) {
				tp = iter.next();
				cr = tp.row;	cc = tp.col;	cs = tp.sum;
				board[cr][cc] = cs;
				//0이라면 삭제하고 다음 것 보기
				if(cs == 0) {
					iter.remove();
					continue;
				}
			}
			time++;
			//두개로 나뉠때 까지
		} while(isDivided());
		
		System.out.println(time);
	}
	
	public static boolean isDivided() {
		initVisited();
		
		Iterator<Point> iter = set.iterator();
		Queue<Point> que = new LinkedList<>();
		int sSize = set.size();
		Point tp;
		int cr,cc,nr,nc, ts=0;
		if(iter.hasNext()) {
			tp = iter.next();
			que.offer(tp);
			isVisited[tp.row][tp.col] = true;
			ts++;
			
			//빙산의 갯수 BFS이용 세기
			while(!que.isEmpty()) {
				tp = que.poll();
				cr = tp.row;	cc = tp.col;
				for(int dir = 0; dir < 4; ++dir) {
					nr = cr+drow[dir];	nc = cc+dcol[dir];
					if(isOut(nr, nc) || board[nr][nc] == 0 || isVisited[nr][nc]) continue;
					
					que.offer(new Point(nr,nc));
					isVisited[nr][nc] = true;
					ts++;
				}
				
			}
			//확인 한 곳 == setSize라면 아직 1덩어리인 것이다.
			if(sSize == ts) return true;
			else	return false;
		}
		return true;
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
