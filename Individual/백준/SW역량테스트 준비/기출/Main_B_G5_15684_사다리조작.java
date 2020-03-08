import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -사다리 조작-
 * 각 세로줄 사이 마다 가지는 가로줄은 %2==0 && >0 이어야함. -> 홀수개인 세로줄만 골라 1개씩 설치해나가면됨
 * └> 만약 중간이 0개라면 2개를 설치해야함.
 * └──위의 사항으로 가로줄이 홀수개인 세로줄이 4개 이상이라면 바로 실패함을 알 수있다.
 * 
 * 1. 가로선을 0,1,2,3개 순차적으로 설치해 볼것이다.
 * 2. 모든 설치 가능한 곳을 돌면서 설치해보고, 완성 유무를 판단한다.
 */

// 출처 : https://www.acmicpc.net/problem/15684
public class Main_B_G5_15684_사다리조작 {
	static int N,H,M;
	static boolean[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로선 개수
		H = Integer.parseInt(st.nextToken()); // 가로선 개수
		M = Integer.parseInt(st.nextToken()); // 가로선 놓을 수 있는 위치
		
		// 다음부터 가로선의 정보 - row는 1부터 시작
		// 좌, 우 한칸씩 크게 잡아서 out판단 방지
		board = new boolean[M+1][N+2];
		//사다리 정보 저장
		int tempRow, tempCol;
		for(int i = 0; i < H; ++i) {
			// b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
			st = new StringTokenizer(br.readLine());
			tempRow = Integer.parseInt(st.nextToken());
			tempCol = Integer.parseInt(st.nextToken());
			//board에 해당 정보 저장
			board[tempRow][tempCol] = true;
		}
		
		//설치할 가로선 개수는 0~3개이다.
		//세로선 개수만큼 가로선설치를 확인 할 것이다.
		//가로선을 놓을 수 있는 위치 만큼 해당 col에 가로선을 설치 해볼것이다.
		for(int line = 0; line <= 3; ++line) {
			// 하나라도 true가 나오면 연결 성공, line이 최소 line갯수
			if(makeLine(0, line, 1)) {
				System.out.println(line);
				return;
			}
		}
		// 구하지 못했을 경우 -1출력
		System.out.println(-1);
		
	}
	
	public static boolean makeLine(int count, int limit, int curCol) {
		// 생성하고자 하는 만큼 생성했을때
		if(count == limit) {
			// 모두 자신과 같은 index로 도착한다면 true라면 결과값 도출
			if(findDest())
				return true;
			return false;
		}
		
		// count번부터 N-limit+count만큼 확인 해볼것. => 각 세로줄 확인하는 것임
		for(int col = curCol;  col <= N-limit+count; ++col) {
			// 해당 세로줄에서 생성 할 수 있는 가로줄 위치를 모두 확인할것이다.
			for(int row = 1; row <= M; ++row) {
				//현재 위치, 또는 왼쪽 오른쪽이 true라면 해당 row에는 설치하지 못함
				if(board[row][col] || board[row][col-1] || board[row][col+1])
					continue;
				
				// 해당 row에 설치 가능하다면
				// 다음 col에 설치 시도
				board[row][col] = true;
				if(makeLine(count+1, limit, col))
					return true;
				board[row][col] = false;
			} //for(row)
		} //for(col)
		
		return false;
	}
	
	// 각 출발점에서 도착점이 자신과 같은 col값인지 판단
	public static boolean findDest() {
		int curCol;
		int curRow;
		for(int col = 1; col <= N; ++col) {
			curCol = col;
			curRow = 1;
			// 현재 row가 M에 도달했다면 도착한것임
			while(curRow <= M) {
				// board에서 왼쪽이 true라면 좌측 이동, 자신이 true라면 우측 이동
				if(board[curRow][curCol-1])
					curCol--;
				else if(board[curRow][curCol])
					curCol++;
				//아무것도 없다면 row만 증가
				curRow++;
			}
			// 다 내려왔을 때 위치 다르다면 false 리턴
			if(col != curCol)
				return false;
		}
		return true;
	}
}