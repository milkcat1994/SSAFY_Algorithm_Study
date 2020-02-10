import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -원판돌리기-
 * 실수 : 배열 직접 돌리지 않고 pivot(index)를 수정하여 파악하고자 하였음.
 * 		└디버깅 하기 어려워지고, 코드가 더 복잡해진 느낌.
 * 		└─그냥 쉽게쉽게 배열이용하여 회전 시키고 BFS이용했다면 쉬웠을 것 같은 문제
 * 
 * 1. 각 index는 12시 방향부터 시계방향 순으로 돌아가며 받는다.
 * 2. 각 idnex를 배열에 저장하고 회전방향, 회전 수 만큼 해당 배열의 시작 pivot을 수정해준다.
 * 3. 원형 LinkedList형태임으로 마지막 index에서는 첫 index로 넘어갈 수있어야한다.
 * 4. 모든 좌표를 선택해 돌면서 BFS를 이용하여 주변에 같은 숫자가 있는지 파악한다.
 * 5. 이동할 숫자가 없을경우 평균을 구해야 하며, 평균 초과,미만 의 경우에만 +-1을 해준다.
 * 6. 만약 모두 0이라면 평균은 0이다.
 * 
 */

//출처 : https://www.acmicpc.net/problem/17822
public class Solution_17822 {
	static int N,M,T;
	static int[][] board;
	
	//해당 원판 pivot
	static int[] pivot;
	
	//0:시계 1:반시계
	static int[] drot = new int[]{-1,1};
	
	//남은 숫자 갯수
	static int count = 0;
	
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	//같은 숫자 찾기 위한 BFS용 Queue
	static Queue<Point> que = new LinkedList<Point>();
	
	//Queue에 넣을 Point
	public static class Point {
		int row, col;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	// 같은 숫자를 찾았다면 true
	static boolean isSame = false;
	
	//bfs이용해서 주변 같으면 다 지워주자.
	public static void bfs() {
		int cr,cc,nr,nc;
		Point tp;
		
		//현재 값
		int cn = 0;
		//모든 좌표 돌면서 같은 곳 찾기
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				//현재 값
				cn = board[row][col];
				if(cn == 0)
					continue;
				que.offer(new Point(row,col));
				
				//해당 좌표와 붙은 곳 다 찾기
				while(!que.isEmpty()) {
					tp = que.poll();
					cr = tp.row;
					cc = tp.col;
					
					//4방향 모두 순회
					for(int i = 0; i < 4; ++i) {
						nr = cr+drow[i];
						if(!inBound(nr))
							continue;
						//같은 라인이면 pivot 더해줄 필요 없음.
						if(nr == cr)
							nc = (cc+dcol[i] +M)%M;
						//같은 row가 아니라면 pivot 차이를 이용해 인접한지 파악하기
						else
							nc = (cc+dcol[i] +pivot[nr]-pivot[cr] +M)%M;
						/*
						 * 현재 col값 pivot이용해 구하기
						 * (col + pivot[row])%M
						 */

						//만일 현재 자리 값과 값이 같다면 queue에 넣을것.
						//그리고 0으로 값 바꿔줄것.
						if(cn != 0 && cn == board[nr][nc]) {
							que.offer(new Point(nr,nc));
							//현재 자리 다음 자리 0
							board[cr][cc] = 0;
							board[nr][nc] = 0;
							isSame = true;
						}
						
					} //end for(i)
					
				} //end while(!que.isEmpty())
				que.clear();
			} //end for(col)
		} //end for(row)
	}
	
	//col >= M, col < 0 은 원형이기에 보지않는다.
	public static boolean inBound(int row) {
		if(row<0 || row>=N)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
	
		board = new int[N][M];
		pivot = new int[N];
		
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		} //end for(row)
		
		//T번 회전 정보 받기
		int rx,rd,rk;
		for(int i = 0; i < T; ++i) {
			isSame = false;
			st = new StringTokenizer(br.readLine());
			rx = Integer.parseInt(st.nextToken());
			rd = Integer.parseInt(st.nextToken());
			rk = Integer.parseInt(st.nextToken());
			
			//rx의 배수인 row 모두 회전
			for(int k = rx-1; k < N; k+=rx) {
				//해당 방향으로 pivot을 이동시킨다.
				pivot[k] = (pivot[k] + drot[rd]*rk + M)%M;
			}
			
			//인접한 같은 값 찾기
			bfs();
			
			//만일 위에서 같은 부분이 없었다면 아래 코드 실행
			if(!isSame) {
				Average aver = getAverage();
				// 0이 아닌 모든 곳 증감 실행
				for (int row = 0; row < N; ++row) {
					for (int col = 0; col < M; ++col) {
						if(board[row][col] != 0) {
							//딱 나누어 떨어진다면 같은 값 제외 증감.
							if(aver.c == 0) {
								if(board[row][col] < aver.a)
									board[row][col]++;
								else if(board[row][col] > aver.a)
									board[row][col]--;
							}
							// 나누어 떨어지지 않는다면
							else {
								if(board[row][col] <= aver.a)
									board[row][col]++;
								else if(board[row][col] > aver.a)
									board[row][col]--;
							}
						} //end if()
					}
				} //end for(row)
			} //end if(!isSame)
			
		} //end for(i)_회전

		System.out.println(getSum());
	} //end main
	
	//남아있는 총 합계 구하기
	public static int getSum() {
		int sum = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
					sum+=board[row][col];
			}
		}
		return sum;
	}
	
	public static class Average{
		//몫, 나머지
		int a,c;
		Average(int a, int c){
			this.a = a;
			this.c = c;
		}
	}
	
	public static Average getAverage() {
		count = 0;
		int sum = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				if(board[row][col] != 0) {
					count++;
					sum+=board[row][col];
				} 
			}
		}
		
		//분모 0이면 나누지 않기.
		if(count != 0)
			return new Average(sum/count, sum%count);
		else
			return new Average(0, 0);
	}
}