import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -단지번호붙이기-
 * BFS이용한 풀이
 */

//출처 : https://www.acmicpc.net/problem/2667
public class Main_B_2667_BFS {
	static int[] drow = {-1, 1, 0, 0};
	static int[] dcol = {0, 0, -1, 1};
	
	public static class Point {
		public int row, col;
		
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		char[][] board = new char[N+2][N+2];
		//방문 정보 저장
		boolean[][] visitable = new boolean[N+2][N+2];
		
		String tempString;
		for(int row = 1; row <= N; ++row) {
			tempString = br.readLine();
			for(int col = 1; col <= N; ++col) {
				board[row][col] = tempString.charAt(col-1);
				visitable[row][col] = true;
			}			
		} //end for(row)
		
		//bfs로 풀것이다.
		Queue<Point> queue = new LinkedList<Point>();
		//오름 차순 정렬 필요
		List<Integer> resultList = new ArrayList<Integer>();
		
		Point curP;
		int nrow,ncol, count;
		
		//모든 곳 방문 예정
		for(int row = 1; row <= N; ++row) {
			for(int col = 1; col <= N; ++col) {
				//만일 방문 할 수 있다면 방문하고 해당 자리가 '1'이라면 bfs시작 '0'이라면 pass
				if(visitable[row][col]) {
					visitable[row][col] = false;
					switch (board[row][col]) {
					//bfs 시작
					case '1':
						//현재 위치 있으므로 저장
						count = 1;
						//현재 위치 삽입
						queue.offer(new Point(row,col));
						//queue가 빌때 까지 반복
						while(!queue.isEmpty()) {
							curP = queue.poll();
							// 4방향 모두 탐색
							for(int i = 0; i < 4; ++i) {
								nrow = curP.row + drow[i];
								ncol = curP.col + dcol[i];
								//다음 자리 방문 가능하고, 1이라면 방문
								if(visitable[nrow][ncol] && board[nrow][ncol] == '1') {
									count+=1;
									visitable[nrow][ncol] = false;
									queue.offer(new Point(nrow, ncol));
								}
							} //end for(i)
						} //end while(!queue.isEmpty())
						//저장한 단지 숫자 pq에 저장
						resultList.add(count);
						break;
					} // end switch(board[row][col]
				} //end if()
			}		
		} //end for(row)

		// 결과값 출력
		sb.append(resultList.size()+"\n");
		resultList.sort(null);
		for(Integer var : resultList)
			sb.append(var+"\n");
		System.out.println(sb.toString());
	}
}
