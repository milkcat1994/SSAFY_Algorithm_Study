import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* -미세먼지 안녕!-
 * 각좌표에는 추가될 먼지량과 총 먼지량을 저장한다.
 * 공기청정기의 정보는 따로 보관하여 공기청정기의 순환시 위,아래 나누어 진행한다.
 * └공기 청정기 순환방향의 반대 방향으로 이동하면서 먼지를 옮겨나간다.
 *  └─먼지를 밀어내는것이 아닌 당기는 방법을 사용하였다.
 */

//출처 : https://www.acmicpc.net/problem/17144
public class Main_B_G5_17144_미세먼지안녕 {
	static int R, C, T;
	static Point[][] board;
	// 공기 청정기 좌표
	static Point[] product = new Point[2];
	
	//상우하좌
	static int[] drow = {-1, 0, 1, 0};
	static int[] dcol = {0, 1, 0, -1};
	
	public static class Point {
		// 확산으로 인해 추가될 먼지량
		Queue<Integer> que = new LinkedList<Integer>();
		
		//좌표, 해당 좌표의 먼지량
		int row, col, sum;
		//sum이 -1이라면 공기청정기다.
		
		Point(){ }
		
		Point(int row, int col, int sum){
			this.row = row;
			this.col = col;
			this.sum = sum;
		}
		
		//확산이후 현재자리 먼지량 수정
		public void setPostDiffuSum(int count) {
			this.sum = this.sum - (this.sum/5)*count;
		}
		//먼지량 설정
		public void setSum(int sum) {
			this.sum = sum;
		}
		//먼지량 증가
		public void addSum(int sum) {
			this.sum += sum;
		}
		//좌표 같은지 확인
		public boolean equals(int row, int col) {
			if(this.row == row && this.col == col)
				return true;
			return false;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// R,C _ 행,열
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 진행될 시간
		T = Integer.parseInt(st.nextToken());

		int tempInt = 0;
		board = new Point[R][C];
		for(int row = 0; row < R; ++row) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < C; ++col) {
				board[row][col] = new Point(row, col, Integer.parseInt(st.nextToken()));
				//공기청정기라면 Point에 추가
				if(board[row][col].sum == -1) {
					product[tempInt++] = board[row][col];
				}
			}
		} //end for(row)
	
		// 1초씩 진행
		for(int t = 0; t < T; ++t) {
			// 먼지 확산
			diffusion();
			
			// 먼지 합치기
			mergeSum();

			//공기청정기 가동
			runProduct();	
		}
		//먼지 총량 구하기
		int result = 0;
		for(int row = 0; row < R; ++row) {
			for(int col = 0; col < C; ++col) {
				result += board[row][col].sum;
			}
		}
		// 공기청정기로 인해 -2 되어서 +2 필요
		System.out.println(result + 2);
	}
	
	// 먼지 확산
	public static void diffusion() {
		// 다음 좌표, 확산량, 확산수
		int nrow,ncol, diffuSum, count;
		for(int row = 0; row < R; ++row) {
			for(int col = 0; col < C; ++col) {
				//4방향 확산
				diffuSum = board[row][col].sum / 5;
				count = 0;
				for(int dir = 0; dir < 4; ++dir) {
					nrow = row + drow[dir];
					ncol = col + dcol[dir];
					
					//범위 벗어나거나 또는 공기청정기가 있다면 확산 X
					if(nrow < 0 || ncol < 0 || nrow >= R || ncol >= C
							|| product[0].equals(nrow, ncol) || product[1].equals(nrow, ncol))
						continue;
					
					//범위 벗어나지 않는다면 해당 방향으로 확산
					board[nrow][ncol].que.offer(diffuSum);
					
					count++;
				} //end for(dir)
				//확산 횟수만큼 현재자리 먼지량 재정의
				board[row][col].setPostDiffuSum(count);
			} //end for(col)
		} //end for(row)
		
	} //end diffusion()
	
	//먼지량 합치기
	public static void mergeSum() {
		for(int row = 0; row < R; ++row) {
			for(int col = 0; col < C; ++col) {
				// 확산으로 인해 생긴 먼지가 있다면 해당 좌표에 먼지량 추가
				while(!board[row][col].que.isEmpty()) {
					board[row][col].addSum(board[row][col].que.poll());
				}
			} //end for(col)
		} //end for(row)
	} //end mergeSum()
	
	// 공기청정기 가동
	public static void runProduct() {
		// 현재 좌표
		int crow, ccol;
		// 다음 좌표, 한계 행(공기청정기 위치)
		int nrow, ncol, lrow;
		int dir;
		
		//--------위 공기청정기--------//
		crow = product[0].row;
		ccol = product[0].col;
		lrow = crow;
		
		// 상우하좌
		dir = 0;
		nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
		
		//바로 위가 경계선일경우 방향을 틀어준다.
		if(!inBound(nrow, ncol, lrow, 0)) {
			dir++;
			// 다음 좌표를 다시 설정해준다.
			nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
		}
		
		//바로 다음 값은 삭제되어야 한다.
		board[nrow][ncol].sum = 0;
		//다음값 삭제하고 해당 좌표로 이동한다.
		crow = nrow;	ccol = ncol;
		nrow = crow+drow[dir];	ncol = ccol+dcol[dir];

		//다음 좌표가 공기청정기라면 다 돈것이다.
		while(!arrive(nrow, ncol)){
			//경계선을 넘거나 하면 방향을 시계방향으로 바꿔준다.
			if(!inBound(nrow, ncol, lrow, 0)) {
				dir++;
				nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
				continue;
			}
			//다음 좌표값을 현재 좌표값으로 이동
			board[crow][ccol].setSum(board[nrow][ncol].sum);
			
			// 다음 값으로 전환
			crow = nrow;	ccol = ncol;
			nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
		}
		//공기청정기 들어가기 전 값 0초기화
		board[crow][ccol].sum = 0;
		
		
		//--------아래 공기청정기--------//
		crow = product[1].row;
		ccol = product[1].col;
		lrow = crow;
		
		//하우상좌
		//원래는 상우하좌이다.0123순서
		// -> 이를 2103순서로
		dir = 2;
		nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
		
		//바로 위가 경계선일경우 방향을 틀어준다.
		if(!inBound(nrow, ncol, lrow, 1)) {
			dir--;
			// 다음 좌표를 다시 설정해준다.
			nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
		}
		
		//바로 다음 값은 삭제되어야 한다.
		board[nrow][ncol].sum = 0;
		//다음값 삭제하고 해당 좌표로 이동한다.
		crow = nrow;	ccol = ncol;
		nrow = crow+drow[dir];	ncol = ccol+dcol[dir];

		//다음 좌표가 공기청정기라면 다 돈것이다.
		while(!arrive(nrow, ncol)){
			//경계선을 넘거나 하면 방향을 시계방향으로 바꿔준다.
			if(!inBound(nrow, ncol, lrow, 1)) {
				dir = ((dir-1)+4)%4;
				nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
				continue;
			}
			//다음 좌표값을 현재 좌표값으로 이동
			board[crow][ccol].setSum(board[nrow][ncol].sum);
			
			// 다음 값으로 전환
			crow = nrow;	ccol = ncol;
			nrow = crow+drow[dir];	ncol = ccol+dcol[dir];
		}
		//공기청정기 들어가기 전 값 0초기화
		board[crow][ccol].sum = 0;
	}
	
	//공기청정기 까지 도달했는지
	/**
	 * 
	 * @param row	현재 행
	 * @param col	현재 열
	 * @return		공기청정기와 좌표 같다면 true
	 */
	public static boolean arrive(int row, int col) {
		if(product[0].equals(row, col))
			return true;
		if(product[1].equals(row, col))
			return true;
		return false;
	}
	
	// bound확인
	/**
	 * 
	 * @param row	현재 행
	 * @param col	현재 열
	 * @param lrow	행의 한계점
	 * @param flag	공기청정기 위치
	 * @return		벗어난다면 false, 이외 true
	 */
	public static boolean inBound(int row, int col, int lrow, int flag) {
		// 위 공기청정기
		if(flag == 0) {
			// 먼지를 이동할수없는 곳이다.
			if(row < 0 || col < 0 || row > lrow || col >= C
					|| product[0].equals(row, col)) {
				return false;
			}
			return true;
		}
		//아래 공기청정기
		else {
			if(row < lrow || col < 0 || row >= R || col >= C
					|| product[0].equals(row, col)) {
				return false;
			}
			return true;
		} //end if-else
	}
}