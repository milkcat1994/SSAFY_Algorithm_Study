import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -드래곤 커브-
 * 1. 각 세대별 이동 방향을 역순으로 반시계방향으로 회전하여 더해주면 해당 드래곤 커브이동 방향이 된다.
 * 2. 이 패턴은 List에 순차적으로 담아 시작 지점에서 이동하여 좌표를 true로 만들어 준다.
 * 3. 4꼭지점이 모두 true인 경우를 더한다.
 */

// 출처 : https://www.acmicpc.net/problem/15685
public class Main_B_G4_15685_드래곤커브 {
	final static int BOARD_SIZE = 100;
	//map은 상하좌우 한칸씩 늘려 bound체크 X
	static boolean[][] board = new boolean[BOARD_SIZE+2][BOARD_SIZE+2];
	//우,상,좌,하
	static int[] drow = {0,-1, 0, 1};
	static int[] dcol = {1, 0, -1, 0};
	static List<Integer[]> dList = new ArrayList<Integer[]>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 드래곤 커브 갯수
		int N = Integer.parseInt(br.readLine()); // (1 ≤ N ≤ 20)
		
		//x,y,d,g (0 ≤ x, y ≤ 100, 0 ≤ d ≤ 3, 0 ≤ g ≤ 10)
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			// 시작 좌표, 시작 방향, 세대
			dList.add(
					new Integer[]{
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							});
		} //end for(i)
		
		int listSize = dList.size();
		
		// 각 드래곤 커브 정보 임시 저장용 변수
		Integer[] tempInteger = new Integer[4];
		List<Integer> tempList = new ArrayList<Integer>();
		int tempSize, curRow, curCol;
		// 각각의 드래곤 커브의 좌표를 찍을것이다.
		for(int dIndex = 0; dIndex < listSize; ++dIndex) {
			tempInteger = dList.get(dIndex);
			// 첫 방향 삽입
			tempList.add(tempInteger[2]);
			// 시작 좌표에서 시작하며, 해당 세대까지 for문 돌것
			for(int ages = 1; ages <= tempInteger[3]; ++ages) {
				// 세대를 반복하며 해당 list에 방향을 추가할것이다
				tempSize = tempList.size();
				// list를 끝부터 처음으로 도면서 각각의 방향을 돌리면서 add해줄것.
				for(int i = tempSize-1; i >= 0;--i) {
					tempList.add((tempList.get(i)+1)%4);
				}
			} //end for(ages)
			// 하나의 드래곤 커브를 다 만들었다.
			
			// 해당 드래곤 커브 시작 지점에서 진행 방향에 따라 이동하며 좌표를 찍어준다.
			curRow = tempInteger[1];	curCol = tempInteger[0];
			board[curRow][curCol] = true;
			for(Integer dir : tempList) {
				curRow += drow[dir];
				curCol += dcol[dir];
				board[curRow][curCol] = true;
			} //end for(방향)
			
			tempList.clear();
		} //end for(dIndex)
		
		//board를 다 돌며 꼭짓점이 4개 다 true 인것을 찾기
		int result = 0;
		for(int row = 0; row < BOARD_SIZE; ++row) {
			for(int col = 0; col < BOARD_SIZE; ++col) {
				if(board[row][col] && board[row][col+1] && board[row+1][col] && board[row+1][col+1])
					result++;
			}
		}
		System.out.println(result);
	}
}