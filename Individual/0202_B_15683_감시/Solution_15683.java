import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -감시-
 * 1. cctv를 List형태로 관리한다.
 * 2. 2차원 배열의 초기화를 해주지 않고 1차원 배열의 clone을 사용하여 재귀함수의 인자로 넣어주었다.
 * └──초기화 해주는 것이 메모리 사용측면에서 좋을듯 싶다.
 * 3. 각 cctv번호에 따라 확인하는 방향이 다르므로 switch문을 이용하였다.
 * └──cctv의 번호에 따른 방향을 2차원 배열로 저장한다면 더 간결한 코드가 될것이라 예상된다.
 */

// 출처 : https://www.acmicpc.net/problem/15683
public class Solution_15683 {
	static int ROW, COL;
	static List<Integer[]> cctvList = new ArrayList<Integer[]>();
	static int cctvNum = 0;
	// 상 우 하 좌
	static int[] drow= {-1, 0, 1, 0};
	static int[] dcol= {0, 1, 0, -1};
	
	static int minResult = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// Board 크기 저장
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());

		// board 초기화
		int[] board = new int[ROW*COL];
		
		String tempString;
		// 0 : 빈칸, 1~5:CCTV, 6:벽
		for (int row = 0; row < ROW; ++row) {
			tempString = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row*COL+col] = tempString.charAt(col*2)-'0';
				switch (board[row*COL + col]) {
				// CCTV 인경우 CCTV List에 추가
				case 1:	case 2: case 3: case 4: case 5:
					cctvList.add(new Integer[]{board[row*COL + col], row, col});
					cctvNum++;
					break;

				default:
					break;
				}
			}
		} // end for(row)
		
		// 각자 4방향 모두 Searching 해야한다._cctv모두.
		for(int i = 0; i < 4; ++i) {
			locateCCTV(0, board.clone(), i);
		}
		System.out.println(minResult);
	}
	// cctv Index, board, 회전량(0~3)
	private static void locateCCTV(int index, int[]board, int rotate) {
		// 모든 cctv 다 확인 했을때
		if(index >= cctvNum) {
			int tempResult = 0;
			for (int row = 0; row < ROW; ++row) {
				for (int col = 0; col < COL; ++col) {
					if(board[row*COL + col] == 0) {
						tempResult++;
					}
				}
			} //end for(row)
			// 최소 값 갱신
			minResult = minResult > tempResult ? tempResult : minResult;
			return;
		}
		int cctvType = cctvList.get(index)[0];
		// 다음 cctv 감시구간
		// 현재 CCTV 위치
		int originRow = cctvList.get(index)[1], originCol = cctvList.get(index)[2];
		
		int nextRow = originRow, nextCol = originCol;
		// 1번 CCTV - 1방향
		while(true) {
			nextRow += drow[rotate];
			nextCol += dcol[rotate];
			// 다음칸이 밖이거나 벽일경우 그만 칠하기
			if(nextRow < 0 || nextCol < 0 || nextRow >= ROW ||nextCol >= COL || board[nextRow*COL + nextCol] == 6) {
				break;
			}
		
			//0일 경우에만 해당 구간에 칠해주기 7로
			if(board[nextRow*COL+nextCol] == 0) {
				board[nextRow*COL+nextCol] = 7;
			}
			//1~5인 경우는 그냥 진행
		}
		
		nextRow = originRow; nextCol = originCol;
		switch (cctvType) {
		case 2:
			// 0,1일때만 수행해도됨
			while(rotate <= 1) {
				nextRow += drow[rotate+2];
				nextCol += dcol[rotate+2];
				// 다음칸이 밖이거나 벽일경우 그만 칠하기
				if(nextRow < 0 || nextCol < 0 || nextRow >= ROW ||nextCol >= COL || board[nextRow*COL+nextCol] == 6) {
					break;
				}
			
				//0일 경우에만 해당 구간에 칠해주기 7로
				if(board[nextRow*COL+nextCol] == 0) {
					board[nextRow*COL+nextCol] = 7;
				}
				//1~5인 경우는 그냥 진행
			}
			break;
		case 3:
			// 시계방향 1칸만 보기
			while(true) {
				nextRow += drow[(rotate+1)%4];
				nextCol += dcol[(rotate+1)%4];
				// 다음칸이 밖이거나 벽일경우 그만 칠하기
				if(nextRow < 0 || nextCol < 0 || nextRow >= ROW ||nextCol >= COL || board[nextRow*COL+nextCol] == 6) {
					break;
				}
			
				//0일 경우에만 해당 구간에 칠해주기 7로
				if(board[nextRow*COL+nextCol] == 0) {
					board[nextRow*COL+nextCol] = 7;
				}
				//1~5인 경우는 그냥 진행
			}
			break;
		case 4:
			// 시계방향 1칸
			while(true) {
				nextRow += drow[(rotate+1)%4];
				nextCol += dcol[(rotate+1)%4];
				// 다음칸이 밖이거나 벽일경우 그만 칠하기
				if(nextRow < 0 || nextCol < 0 || nextRow >= ROW ||nextCol >= COL || board[nextRow*COL+nextCol] == 6) {
					break;
				}
			
				//0일 경우에만 해당 구간에 칠해주기 7로
				if(board[nextRow*COL+nextCol] == 0) {
					board[nextRow*COL+nextCol] = 7;
				}
				//1~5인 경우는 그냥 진행
			}

			nextRow = originRow; nextCol = originCol;
			// 시계방향 2칸
			while(true) {
				nextRow += drow[(rotate+2)%4];
				nextCol += dcol[(rotate+2)%4];
				// 다음칸이 밖이거나 벽일경우 그만 칠하기
				if(nextRow < 0 || nextCol < 0 || nextRow >= ROW ||nextCol >= COL || board[nextRow*COL+nextCol] == 6) {
					break;
				}
			
				//0일 경우에만 해당 구간에 칠해주기 7로
				if(board[nextRow*COL+nextCol] == 0) {
					board[nextRow*COL+nextCol] = 7;
				}
				//1~5인 경우는 그냥 진행
			}
			break;
		case 5:
			for(int i = 1; i < 4; ++i) {
				nextRow = originRow; nextCol = originCol;
				// 시계방향 3칸다보기
				while(true) {
					nextRow += drow[(rotate+i)%4];
					nextCol += dcol[(rotate+i)%4];
					// 다음칸이 밖이거나 벽일경우 그만 칠하기
					if(nextRow < 0 || nextCol < 0 || nextRow >= ROW ||nextCol >= COL || board[nextRow*COL+nextCol] == 6) {
						break;
					}
				
					//0일 경우에만 해당 구간에 칠해주기 7로
					if(board[nextRow*COL+nextCol] == 0) {
						board[nextRow*COL+nextCol] = 7;
					}
					//1~5인 경우는 그냥 진행
				}
			}
			break;
		} //end switch(cctvType)
		
		//다음 cctv 확인하기
		for(int i = 0; i < 4; ++i) {
			locateCCTV(index+1, board.clone(), i);
		}
	}
}
