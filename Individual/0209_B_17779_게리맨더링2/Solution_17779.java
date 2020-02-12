import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -게리맨더링2-
 * 경계선 식을 작성하는데에 많은 시간 소요
 * 경계선 식만 찾고 작성한다면 쉬웠던 문제
 */

//출처 : https://www.acmicpc.net/problem/17779
public class Solution_17779 {
	static int N;
	static int[][] board;
	
	static int minResult = Integer.MAX_VALUE;
	static List<Integer> resultList = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 5 ≤ N ≤ 20
		// 1 ≤ A[r][c] ≤ 100
		N = Integer.parseInt(st.nextToken());
		board = new int[N+1][N+1];
		
		for (int row = 1; row <= N; ++row) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		/*
		 * 주어진 범위 이용한 for문 작성
		 * (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
		 * => d1 : 1 ~ N-1, y-1
		 * => d2 : 1 ~ N-x-d1, N-y
		 */
		for (int x = 1; x < N; ++x) {
			for (int y = 2; y < N; ++y) {
				for (int d1 = 1; d1 < N && d1 <= y-1; ++d1) {
					for (int d2 = 1; d2 <= N-x-d1 && d2 <= N-y; ++d2) {
						makeTeam(x, y, d1, d2);
					}
				} //end for(d1)
			} //end for(y)
		} //end for(x)
		System.out.println(minResult);
	}

	/*
	 * -기본 범위(직사각형 범위)-
	 * 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
	 * 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
	 * 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
	 * 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
	 * 
	 * 각 선거구는 직사각형 형태에서 5번 선거구를 제외하는 식으로 작성하였다.
	 * 아래는 위 식을 제외한 추가적으로 잘라낸 부분만 작성한다.
	 * 1번 선거구 : row+col < x+y 부분 => '/' 방향으로 잘라내었다.
	 * 
	 * 2번 선거구 : '\'모양으로 잘라내기 위한 식이었으며, row >= x 일때만 적용하며,
	 * 			 (row+col) < (2*(row-x+1)+x+y-1) 라면 2번구역이 아닌 5번구역이다.
	 * 			 └─(2a + x+y-1)_초항(x+y-1이고 공차2인 점화식)
	 * 			 그리하여 !(not)연산자 통해 5번 구간으로 볼 수 있도록 하였다.
	 * 
	 * 3번 선거구 : '\'모양으로 잘라내기 위한 식이었으며, col >= y-d1 일때만 적용하여,
	 * 			 (row+col) < (2*(col-y+1+d1)+x+y-1) 라면 3번구역이 아닌 5번구역이다.
	 * 			 └─(2a + x+y-1)_초항(x+y-1이고 공차2인 점화식)
	 * 			 2번선거구와 같이 5번구간을 걸러내는데에 위식을 작성하였다.
	 * 
	 * 4번 선거구 : row+col > x+y+d2+d2 부분=>'/' 방향으로 잘라내었다.
	 */
	public static void makeTeam(int x, int y, int d1, int d2) {
		int r1=0,r2=0,r3=0,r4=0,r5=0;
		for (int row = 1; row <= N; ++row) {
			for (int col = 1; col <= N; ++col) {
				// 1번 선거구
				if(row < x+d1 && col <= y && row+col < x+y) {
					r1 += board[row][col];
				}
				// 2번 선거구
				else if(row <= x+d2 && y < col && col <= N && !(row >= x && (row+col) < (2*(row-x+1)+x+y-1))) {
					r2 += board[row][col];
				}
				// 3번 선거구
				else if(x+d1 <= row && row <= N && col < y-d1+d2 && !(col >= y-d1 && (row+col) < (2*(col-y+d1+1)+x+y-1))) {
					r3 += board[row][col];
				}
				//4번
				else if(x+d2 < row && row <= N && y-d1+d2 <= col && col <= N
						&& row+col > x+y+d2+d2) {
					r4 += board[row][col];
				}
				//5번
				else {
					r5 += board[row][col];
				}
			}
		} //end for(row)
		int tr = getDiff(r1, r2, r3, r4, r5);
		minResult = minResult < tr ? minResult : tr;
	}
	
	//오름차순 정렬하여 차이값을 반환한다.
	public static int getDiff(int r1, int r2, int r3, int r4, int r5) {
		resultList.clear();
		resultList.add(r1);
		resultList.add(r2);
		resultList.add(r3);
		resultList.add(r4);
		resultList.add(r5);
		resultList.sort(null);
		return resultList.get(4)-resultList.get(0);
	}
}