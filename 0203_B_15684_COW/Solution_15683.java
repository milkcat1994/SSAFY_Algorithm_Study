import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -입력-
 * 첫째 줄에 세로선의 개수 N, 가로선의 개수 M, 세로선마다 가로선을 놓을 수 있는 위치의 개수 H가 주어진다.
 * (2 ≤ N ≤ 10, 1 ≤ H ≤ 30, 0 ≤ M ≤ (N-1)×H)
 * 둘째 줄부터 M개의 줄에는 가로선의 정보가 한 줄에 하나씩 주어진다.
 * 가로선의 정보는 두 정수 a과 b로 나타낸다. (1 ≤ a ≤ H, 1 ≤ b ≤ N-1)
 * b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결했다는 의미이다.
 * 가장 위에 있는 점선의 번호는 1번이고, 아래로 내려갈 때마다 1이 증가한다.
 * 세로선은 가장 왼쪽에 있는 것의 번호가 1번이고, 오른쪽으로 갈 때마다 1이 증가한다.
 * 입력으로 주어지는 가로선이 서로 연속하는 경우는 없다.
 */

/*
 * -출력-
 * i번 세로선의 결과가 i번이 나오도록 사다리 게임을 조작하려면, 추가해야 하는 가로선 개수의 최솟값을 출력한다.
 * 만약, 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.
 */

/*
 * 생각...
 * 각 세로줄 사이 마다 가지는 가로줄은 %2==0 && >0 이어야함. -> 홀수개인 세로줄만 골라 1개씩 설치해나가면됨
 * └> 만약 중간이 0개라면 2개를 설치해야함.
 * 예제 5번과 같다면 2배로 설치해야됨.
 * 모든 경우의 수를 보는것이 가능할까..?
 * 최악의 경우 : 모두 1개씩 가지고있을 경우
 * 27^10 가지?
 * 정답이 3보다 크면 -1출력..?
 * └> 3개 설치해보고 안되면 끝내면됨
 * index 1부터 n-3까지 도는식 * 3
 * 일단 설치 안했을때, 1개 설치 할때 2개 설치 할때 3개 설치 할때 각각 돌릴것
 * 각각에서 답 나오면 바로 출력
 * 
 * 사다리를 타서 해당 사다리가 i->i로 도달하는게 맞는지 확인하는 함수 하나 필요
 * board에서 왼쪽이 true라면 좌측 이동, 자신이 true라면 우측 이동
 */

// 출처 : https://www.acmicpc.net/problem/15684
public class Solution_15683 {
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
		
		
		//각 col별로 1,2,3개씩 확인하는 함수 필요
		//설치할 가로선 개수는 0~3개이다.
		//세로선 개수만큼 가로선설치를 확인 할 것이다.
		//가로선을 놓을 수 있는 위치 만큼 해당 col에 가로선을 설치 해볼것이다.
		for(int line = 0; line <= 3; ++line) {
			// 하나라도 true가 나오면 연결 성공, line이 최소 line갯수
			if(makeLine(0, line)) {
				System.out.println(line);
				return;
			}
		}
		// 구하지 못했을 경우 -1출력
		System.out.println(-1);
		
	}
	
	// 몇개생성했는지 몇개까지 생성할지
	public static boolean makeLine(int count, int limit) {
		boolean result = false;
		// 생성하고자 하는 만큼 생성했을때
		if(count == limit) {
			// findDest(); 해보고 모두 true라면 결과값 도출
			for(int col = 1; col <= N; ++col) {
				if(!findDest(col))
					return false;
			}
			return true;
		}
		
		// count번부터 N-limit+count만큼 확인 해볼것. => 각 세로줄 확인하는 것임
		for(int col = count+1;  col <= N-limit+count; ++col) {
			// 해당 세로줄에서 생성 할 수 있는 가로줄 위치를 모두 확인할것이다.
			for(int row = 1; row <= M; ++row) {
				//현재 위치, 또는 왼쪽 오른쪽이 true라면 해당 row에는 설치하지 못함
				if(board[row][col] || board[row][col-1] || board[row][col+1]) {
					continue;//break row
				}
				// 해당 row에 설치 가능하다면
				// 다음 col에 설치 시도
				board[row][col] = true;
				if(makeLine(count+1, limit))
					return true;
				board[row][col] = false;
			} //for(row)
		} //for(col)
		
		return result;
	}
	
	// 각 출발점에서 도착점이 자신과 같은 col값인지 판단
	public static boolean findDest(int col) {
		int curCol = col;
		int curRow = 1;
		// 현재 row가 H에 도달했다면 도착한것임
		while(curRow <= M) {
			// board에서 왼쪽이 true라면 좌측 이동, 자신이 true라면 우측 이동
			if(board[curRow][curCol-1]) {
				// 좌측 이동
				curCol--;
			}
			else if(board[curRow][curCol]){
				// 우측 이동
				curCol++;
			}
			//아무것도 없다면 row만 증가
				curRow++;
		} //end while(curRow<H)
		
		// 다 내려왔을 때 위치 같다면 true 리턴
		if(col == curCol)
			return true;
		return false;
	}
}











