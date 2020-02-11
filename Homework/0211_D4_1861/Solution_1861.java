import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -정사각형방-
 * 1. 모든 좌표를 확인해나갈것이다.
 * 2. 이때 인접 부근에 내려가는 곳이 있다면 해당 좌표는 최장이 될 수 없기에 확인하지 않는다.
 * 3. 만약 위 조건에 걸리지 않고 올라가는 곳이 있다면 해당 좌표에서 BFS를 시작한다.
 * 4. BFS로 가장 최장의 길이를 구하고 이미 최장 답이 있다면 해당 좌표의 숫자가 작은것을 저장한다.
 * 5. 위의 BFS를 통해 확인 한곳은 visited로 관리하여 1번에서 4방향을 확인하기 전에 걸러낸다.
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LtJYKDzsDFAXc
public class Solution_1861 {
	//각 좌표의 좌표값과 내부값을 저장하는 Class이다.
	public static class Point {
		int row,col,sum;
		
		Point(Point p) {
			this.row = p.row;
			this.col = p.col;
			this.sum = p.sum;
		}
		
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
		
		void setSum(int sum) {
			this.sum = sum;
		}
		
		void setObject(Point p) {
			this.row = p.row;
			this.col = p.col;
			this.sum = p.sum;
		}
	}

	static int N;
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static Point[][] board;
	static boolean[][] isVisited;
	
	//BFS를 위한 Queue
	static Queue<Point> que = new LinkedList<Point>();
	
	static int maxResult = Integer.MIN_VALUE;
	// 가장 최장의 길이를 가지는 메인 좌표
	static Point resPoint = null;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		boolean isFind = false;
		int tempResult=0;
		int nr,nc;
		board = new Point[1000][1000];
		for (int row = 0; row < 1000; ++row) {
			for (int col = 0; col < 1000; ++col) {
				board[row][col] = new Point(row,col);
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			isVisited = new boolean[N][N];
			maxResult = Integer.MIN_VALUE;
			
			for (int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; ++col) {
					board[row][col].setSum(Integer.parseInt(st.nextToken()));
				}
			}
			
			//모든 칸에 대해서 찾기
			for (int row = 0; row < N; ++row) {
				C:for (int col = 0; col < N; ++col) {
					//현재 좌표 이미 확인 했다면 다음 값
					if(isVisited[row][col])
						continue;
					
					isFind = false;
					
					for(int i = 0; i < 4; ++i) {
						nr = row+drow[i];	nc = col+dcol[i];
						if(isOut(nr,nc))
							continue;
						//내려가는 곳 있는경우 해당은 최장이 될 수 없음.
						if(board[row][col].sum == board[row+drow[i]][col+dcol[i]].sum+1)
	                            continue C;
						//올라가는거 발견할 경우 true
						if(board[row][col].sum+1 == board[row+drow[i]][col+dcol[i]].sum)
							isFind = true;
					}
					
					// 올라가는 방향만 있을때 bfs시작
					if(isFind) {
						if(resPoint == null)
							resPoint = new Point(board[row][col]);
							tempResult = bfs(row,col);
							//최대길이 갱신 필요하다면.
							if(maxResult < tempResult) {
								resPoint.setObject(board[row][col]);
								maxResult = tempResult;
							}
							//만일 값이 같다면, 현재 List의 수와 비교해서 작은걸로 교체
							else if(maxResult == tempResult) {
								if(resPoint.sum > board[row][col].sum) {
									resPoint.setObject(board[row][col]);
								}
							}
					} //end if(isFind)
				} //end C:for(col)
			} //end for(row)
			
			sb.append("#"+t+" "+resPoint.sum + " "+ maxResult + "\n");
			resPoint = null;
		} //end TestCase
		
		System.out.print(sb.toString());
	}
	
	//해당좌표 밖인지 확인
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
	
	//해당 좌표에서 bfs시작
	/**
	 * 
	 * @param row		행
	 * @param col		열
	 * @param dir		진행방향
	 */
	public static int bfs(int row, int col) {
		int cr,cc,nr,nc;
		//해당 좌표 넣기
		que.offer(board[row][col]);
		isVisited[row][col] = true;
		
		int count = 0, qSize = 0;;
		cr = row;	cc = col;
		Point tp;
		
		while(!que.isEmpty()) {
			qSize = que.size();
			while(--qSize >= 0) {
				tp = que.poll();
				cr = tp.row;	cc = tp.col;
				
				//4방향 탐색 -> 
				for(int i = 0; i < 4; ++i) {
					nr = cr+drow[i];	nc = cc+dcol[i];
					if(isOut(nr,nc))
						continue;
					//올라가는것 모두 que에 넣기
					if(tp.sum+1 == board[nr][nc].sum) {
						que.offer(board[nr][nc]);
						//해당 지점 방문 했음을 표시
						isVisited[nr][nc] = true;
					}
				}
			} //end while(--qSize>=0)
			count++;
			
		} //end while(!que.isEmpty())
		
		return count;
	} //end bfs()
}