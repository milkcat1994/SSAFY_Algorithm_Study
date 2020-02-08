import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -연구소-
 * 0인 곳에서 벽을 세개 설치하고, 2인곳에서 BFS로 바이러스를 모두 퍼트린뒤
 * 빈칸갯수 - 퍼진 virus갯수 -3(새롭게 설치된 벽)
 */

//출력 : 안전영역의 최대 크기를 출력

// 출처 : https://www.acmicpc.net/problem/14502
public class Solution_14502 {
	//지도 크기 N,M
	static int N,M;
	static char[][] board;
	
	static List<Point> bList = new ArrayList<Point>();	//빈칸 저장 List
	static int blSize;
	
	static List<Point> vList = new ArrayList<Point>();	//virus 저장 List
	static Queue<Point> que = new LinkedList<Point>();	//virus확산(BFS)위한 Queue
	
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static int maxResult = Integer.MIN_VALUE;
	
	//new Point()로 인한 메모리 낭비 막기위해 HashMap사용
	static Map<Integer,Point> hm = new HashMap<Integer,Point>();
	static Point tempP;
	public static class Point{
		int row,col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//(3 ≤ N, M ≤ 8)
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		// 0:빈칸, 1:벽, 2:바이러스
		// 2 ≤ virus ≤ 10
		String tempStr;
		for (int row = 0; row < N; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < M; ++col) {
				board[row][col] = tempStr.charAt(col*2);
				hm.put(M*row + col, new Point(row,col));
				
				switch (board[row][col]) {
				case '0':
					bList.add(hm.get(M*row + col));
					break;
				case '2':
					vList.add(hm.get(M*row + col));
					break;
				}
			}
		}
		blSize = bList.size();
		pickWall(0, 0);
		
		System.out.println(maxResult);
	}
	
	/**
	 * @param index		현재 뽑을 빈칸 index
	 * @param count		현재까지 설치한 벽의 수
	 */
	public static void pickWall(int index, int count) {
		//벽 설치 3개라면 bfs시작
		if(count >= 3) {
			bfs();
			initBoard();
			return;
		}
		
		Point tempP;
		for(int i = index; i < blSize; ++i) {
			tempP = bList.get(i);
			board[tempP.row][tempP.col] = '1';
			pickWall(i+1,count+1);
			board[tempP.row][tempP.col] = '0';
		}
	}
	
	public static void bfs() {
		//virus List 삽입
		for(Point p : vList)
			que.offer(p);
		
		//bfs시작
		int crow,ccol,nrow,ncol, tempRes = 0;
		while(!que.isEmpty()) {
			tempP = que.poll();
			crow = tempP.row;	ccol = tempP.col;
			
			//4방향 순회
			for(int i = 0; i < 4; ++i) {
				nrow = crow+drow[i];
				ncol = ccol+dcol[i];
				//범위 벗어날경우, 해당 자리 1일경우, 2일경우
				if(nrow<0 || ncol<0 || nrow>=N || ncol>=M || board[nrow][ncol] == '2' || board[nrow][ncol] == '1')
					continue;
				
				//해당 방향으로 퍼질수 있다면 que에 삽입.
				board[nrow][ncol] = '2';
				que.offer(hm.get(M*nrow + ncol));
				tempRes++;
			}
		} //end while(!que.isEmpty())
		
		maxResult = Math.max(maxResult, blSize -(3 + tempRes));
	}
	
	//virus퍼진 부분 모두 삭제
	public static void initBoard() {
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				if(board[row][col] == '2')
					board[row][col] = '0';
			}
		}
		for(Point p : vList)
			board[p.row][p.col] = '2';
	}
	
}
