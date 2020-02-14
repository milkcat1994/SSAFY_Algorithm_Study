import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *-배열돌리기4-
 * 1. 각 회전 정보를 이용하여 가장 바깥 사각형 부터 순차적으로 회전
 * 2. 역회전도 따로 구현하여 따로 보드판 저장하지 않음.
 */

//출처 : https://www.acmicpc.net/problem/17406
public class Solution_17406 {
	static int ROW,COL,K;
	
	static boolean[] isUsed;	//순열 생성 위한 used배열
	static int board[][];
	static int rotate[][];		//회전 정보
	static int minResult = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[ROW+2][COL+2];
		for (int row = 1; row <= ROW; ++row) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		isUsed = new boolean[K];
		rotate = new int[K][3];
		for(int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			rotate[i][0] = Integer.parseInt(st.nextToken());
			rotate[i][1] = Integer.parseInt(st.nextToken());
			rotate[i][2] = Integer.parseInt(st.nextToken());
		}
		
		rotateIdx(0);
		System.out.println(minResult);
	}
	
	//회전 배열 선택
	public static void rotateIdx(int count) {
		if(count == K) {
			getMin();
			return;
		}
		
		//순열 생성
		for(int i = 0; i < K; ++i) {
			if(isUsed[i])
				continue;
			isUsed[i] = true;
			//해당 회전으로 배열 변경
			rotate(i);
			rotateIdx(count+1);
			isUsed[i] = false;
			//해당 회전의 역회전
			reverse(i);
		}
	}
	
	//각 행의 값을 비교하여 최소값 갱신
	public static void getMin() {
		int result = 0;
		for (int row = 1; row <= ROW; ++row) {
			result = 0;
			for (int col = 1; col <= COL; ++col) {
				result += board[row][col];
			}
			minResult = Math.min(result, minResult);
		}
	}
	
	//해당 회전번호정보로 회전
	public static void rotate(int idx) {
		//(r,c,s)
		//가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)
		int ur,dr,lc,rc;
		ur = rotate[idx][0] - rotate[idx][2];
		dr = rotate[idx][0] + rotate[idx][2];
		lc = rotate[idx][1] - rotate[idx][2];
		rc = rotate[idx][1] + rotate[idx][2];
		
		//다음과 현재 좌표내 값, 회전되는 사각형의 수
		int next,cur,limit = (rc-lc)/2;
		for(int t = 0; t < limit; ++t) {
			//위
			cur = board[ur][lc];
			for (int col = lc; col < rc; ++col) {
				next = board[ur][col+1];
				board[ur][col+1] = cur;
				cur = next;
			}
			
			//오른쪽
			for (int row = ur; row < dr; ++row) {
				next = board[row+1][rc];
				board[row+1][rc] = cur;
				cur = next;
			}
			
			//아래
			for (int col = rc; col > lc; --col) {
				next = board[dr][col-1];
				board[dr][col-1] = cur;
				cur = next;
			}
			
			//왼쪽
			for (int row = dr; row > ur; --row) {
				next = board[row-1][lc];
				board[row-1][lc] = cur;
				cur = next;
			}
			//안쪽 사각형 만들기 위한 구간 조정
			ur++;	dr--;	lc++;	rc--;
		}
	}
	
	//rotate(idx)의 역회전
	public static void reverse(int idx) {
		int ur,dr,lc,rc;
		ur = rotate[idx][0] - rotate[idx][2];
		dr = rotate[idx][0] + rotate[idx][2];
		lc = rotate[idx][1] - rotate[idx][2];
		rc = rotate[idx][1] + rotate[idx][2];
		
		int next,cur,limit = (rc-lc)/2;
		for(int t = 0; t < limit; ++t) {
			//아래
			cur = board[dr][lc];
			for (int col = lc; col < rc; ++col) {
				next = board[dr][col+1];
				board[dr][col+1] = cur;
				cur = next;
			}

			//오른쪽
			for (int row = dr; row > ur; --row) {
				next = board[row-1][rc];
				board[row-1][rc] = cur;
				cur = next;
			}
			
			//위
			for (int col = rc; col > lc; --col) {
				next = board[ur][col-1];
				board[ur][col-1] = cur;
				cur = next;
			}
			
			//왼쪽
			for (int row = ur; row < dr; ++row) {
				next = board[row+1][lc];
				board[row+1][lc] = cur;
				cur = next;
			}
			ur++;	dr--;	lc++;	rc--;
		}
	}
}
