import java.io.BufferedReader;
import java.io.InputStreamReader;

// 출처 : https://www.acmicpc.net/problem/14503
public class Solution_14503 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tempStringArr;
		String tempString;
		
		char[][] board;
		// board의 가로, 세로 Size
		int rowSize;
		int colSize;
		
		// 로봇의 현재 위치, 방향
		int curRow;	int curCol;
		// 가고자 하는 위치
		int postRow; int postCol;
		// 0:상, 2:하, 1:우, 3:좌
		int curDir; // 
		// curDir에 따른 이동함수
		int[] dRow = {-1, 0, 1, 0};
		int[] dCol = {0, 1, 0, -1};
		
		// 청소한 구간
		int result=1;
		
		// 세로, 가로 크기 받기
		tempStringArr = br.readLine().split(" ");
		rowSize = Integer.parseInt(tempStringArr[0]);
		colSize = Integer.parseInt(tempStringArr[1]);
		
		board = new char[rowSize][colSize];

		tempStringArr = br.readLine().split(" ");
		curRow = Integer.parseInt(tempStringArr[0]);
		curCol = Integer.parseInt(tempStringArr[1]);
		curDir = Integer.parseInt(tempStringArr[2]);
		
		// 배열 초기화
		for(int row = 0; row < rowSize; ++row) {
			tempString = br.readLine();
			for(int col = 0; col < colSize; ++col) {
				board[row][col] = tempString.charAt(col*2);
			}
		}
		
		//
		boolean needBack = false;
		boolean flag = true;
		
		// 기본 위치 청소
		board[curRow][curCol] = '2';
		while(flag) {
			// 현재 위치 청소하기
			for(int i = 0; i < 4; ++i) {
				// 일단 방향전환 하기
				curDir = (curDir+3)%4;
				postRow = curRow+dRow[curDir];
				postCol = curCol+dCol[curDir];
				// 왼쪽방향이 청소가 되어있지 않다면 위치 이동하고 청소하기
				if(board[postRow][postCol] == '0') {
					curRow = postRow;
					curCol = postCol;
					++result;
					//청소 했다면 2로 채우기
					board[curRow][curCol] = '2';
					
					break;
				}
				// 네 방향 모두 청소가 되어있다면 needBack = true;
				if(i == 3) {
					needBack = true;
				}
			}
			// end for

			// 네 방향 모두 청소가 되어있다면 원래 방향으로 한 칸 후진
			if(needBack) {
				postRow = curRow-dRow[curDir];
				postCol = curCol-dCol[curDir];
				
				// 후진 못할 경우에는 작동 멈추기
				// 해당 좌표 벽일경우
				if(board[postRow][postCol] == '1') {
					System.out.println(result);
					flag = false;
				}
				// '0' or '2' 일경우 이동
				else {
					needBack = false;
					curRow = postRow;
					curCol = postCol;
				}
			}
			// end if(needBack)
		}
	}
}
