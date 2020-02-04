import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -출력-
 * 인구이동이 일어나는 횟수 출력
 */

/*
 * 모든땅을 순차적으로 돌며, 해당 땅과 연결될 모든 땅을 하나의 연합(List<List<Integer[]>> 형식)으로 나타내었다.
 * 처음 List는 각 연합의 큰 집합이며
 * 두번째 List는 해당 연합에서의 땅좌표를 저장하는 List이다.
 * Integer[]는 해당 연합에서의 땅좌표이다.
 * 
 * 한번 연합이 된 땅은 true로 선택하여 다른 연합에서 보지 않도록 한다.
 * isVisitable 배열이 계속 초기화 되며 새로운 연합을 찾는다.
 */

// 출처 : https://www.acmicpc.net/problem/16234
public class Solution_16234 {
	static List<List<Integer[]>> coperList = new ArrayList<List<Integer[]>>();
	
	// false 라면 방문할수 없는곳
	static boolean isVisitable[][];
	static int board[][];
	
	//상하좌우
	static int drow[] = new int[] {-1, 1, 0, 0};
	static int dcol[] = new int[] {0, 0, -1, 1};
	
	//연합의 갯수
	static int coperNum = 0;
	
	static int N,L,R;
	static int result = 0;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 총 땅갯수
		int totalGrand = N*N;
		
		isVisitable = new boolean[N+2][N+2];

		board = new int[N+2][N+2];
		//N개의 줄에 각 나라의 인구수가 주어진다.
		for(int row = 1; row <= N; ++row) {
			st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		} //end for(row)
		
		//연합의 총합과 바꿀 값
		int coperSum = 0;
		int coperRes = 0;
		
		// 인구이동이 없을때까지 계속_2000보다 작거나 같은 입력주어짐
		while(result <= 2000)
		{
			setVisitArr();
			//모든 땅 한번씩 다 밟아보았을때까지 연합을 찾아라
			for(int row = 1; row <= N; ++row) {
				for(int col = 1; col <= N; ++col) {
					// 아직 방문하지 않은 곳이라면 방문할 예정이다.
					if(isVisitable[row][col]) {
						// 연합 만들 좌표 List 추가
						coperList.add(new ArrayList<Integer[]>());
						// 이곳을 시작으로 연합 생성시작
						findCoper(row, col);
						//연합 갯수 증가
						coperNum++;
					}
				}
			} // end for(row)
			
			// 만약 연합의 갯수가 총 땅 갯수 라면 인구이동이 일어날 수 없다.
			if(coperNum == totalGrand) {
				System.out.println(result);
				return;
			}
			
			// 인구 이동이 일어날 수 있다면 이동 시켜준다.
			for(int i = 0; i < coperList.size(); ++i) {
				// i번째 연합의 합 구하기
				for(Integer[] var : coperList.get(i)) {
					coperSum += board[var[0]][var[1]];
				}
				coperRes = coperSum / coperList.get(i).size();
				for(Integer[] var : coperList.get(i)) {
					board[var[0]][var[1]] = coperRes;
				}
				coperSum = 0;
			} //end for(i)
			result++;
			coperNum = 0;
			coperList.clear();
		} //end while(result <= 2000)
	} // end main
	
	/**
	 * @param curRow	현재 행
	 * @param curCol	현재 열
	 */
	// 해당 위치와 연결된 연합을 찾는 함수
	private static void findCoper(int curRow, int curCol) {
		// 현재 땅이 밟을 수 없는 땅(미리 밟은 땅)이라면 보지 않고 버린다.
		if(!isVisitable[curRow][curCol]) {
			return;
		}
		
		// 현재땅은 밟았기에 밟을 수 없다고 표시
		isVisitable[curRow][curCol] = false;
		coperList.get(coperNum).add(new Integer[]{curRow, curCol});
		// 4방향 모두 확인 할것.
		for(int i = 0; i < 4; ++i) {
			// 밟을 수 있는 땅이며 차이값이 L~R사이라면 연결 가능하다.
			// 지금칸과 다음 칸이 연결 될수있다면 findCoper호출
			if(isGood(curRow, curCol, curRow+drow[i], curCol+dcol[i])) {
				findCoper(curRow+drow[i], curCol+dcol[i]);
			}
		}// end for(i)
	}
	
	// 연합이 될 수 있다면
	/**
	 * 
	 * @param cr		현재 행
	 * @param cc		현재 열
	 * @param nr		연결 희망 행
	 * @param nc		연결 희망 열
	 * @return			L ≤ 차이값 ≤ R 이라면 true, 그 외 false
	 */
	private static boolean isGood(int cr, int cc, int nr, int nc) {
		int tmp = myabs(board[cr][cc] - board[nr][nc]);
		
		// L ≤ 차이값 ≤ R 이라면 true
		if(L <= tmp && tmp <= R) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param num		현재 숫자
	 * @return			인자'num'의 절대값 반환
	 */
	private static int myabs(int num) {
		return num > 0 ? num: -num;
	}

	// visitable배열을 true로 바꾸어 방문 가능하도록 전환
	private static void setVisitArr() {
		for (int row = 1; row <= N; ++row) {
			for (int col = 1; col <= N; ++col) {
				isVisitable[row][col] = true;
			}
		}
	}
}











