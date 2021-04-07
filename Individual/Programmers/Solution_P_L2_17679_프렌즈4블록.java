import java.util.ArrayList;
import java.util.List;

/*
 * -프렌즈 4블록-
 * 
 * 테스트 1 〉	통과 (0.17ms, 52.3MB)
 * 테스트 2 〉	통과 (0.22ms, 52.1MB)
 * 테스트 3 〉	통과 (0.15ms, 54MB)
 * 테스트 4 〉	통과 (1.24ms, 52.2MB)
 * 테스트 5 〉	통과 (38.50ms, 57.8MB)
 * 테스트 6 〉	통과 (6.22ms, 53.2MB)
 * 테스트 7 〉	통과 (0.89ms, 52.6MB)
 * 테스트 8 〉	통과 (2.46ms, 52.9MB)
 * 테스트 9 〉	통과 (0.18ms, 52.6MB)
 * 테스트 10 〉	통과 (1.16ms, 52.5MB)
 * 테스트 11 〉	통과 (3.27ms, 53.2MB)
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17679
public class Solution_P_L2_17679_프렌즈4블록 {
	
	public int solution(int m, int n, String[] board) {
		int answer = 0;
		
		char[][] charBoard = new char[m][n];
		for (int r = 0; r < m; ++r) {
			for (int c = 0; c < n; ++c) {
				charBoard[r][c] = board[r].charAt(c);
			}
		}
		
		boolean isFind;
		char ch;
		do {
			isFind = false;
			boolean[][] isSelected = new boolean[m][n];
			for(int r=0; r<m-1; ++r) {
				for(int c=0; c<n-1; ++c) {
					ch = charBoard[r][c];
					if(ch == ' ') continue;
					
					if(ch == charBoard[r][c+1] &&
						ch == charBoard[r+1][c] &&
						ch == charBoard[r+1][c+1]) {
						isFind = true;
						isSelected[r][c] = true;
						isSelected[r][c+1] = true;
						isSelected[r+1][c] = true;
						isSelected[r+1][c+1] = true;
					}
				}
			}
			
			answer += getSelectedBlock(isSelected);
			
			charBoard = getNewBoard(charBoard, isSelected, m, n);
		}while(isFind);
		
		return answer;
	}

	int getSelectedBlock(boolean[][] isSelected) {
		int cnt=0;
		for (int row = 0; row < isSelected.length; ++row) {
			for (int col = 0; col < isSelected[row].length; ++col) {
				if(isSelected[row][col])
					cnt++;
			}
		}
		return cnt;
	}
	
	char[][] getNewBoard(char[][] charBoard, boolean[][] isSelected, int m, int n){
		char[][] newBoard = new char[m][n];
		
		List<Character> list;
		int listSize;
		for(int c=0; c<n; ++c) {
			list = new ArrayList<>();
			for(int r=m-1; r>=0; --r) {
				if(!isSelected[r][c])
					list.add(charBoard[r][c]);
			}
			
			listSize = list.size();
			for(int idx=0; idx<m; ++idx) {
					newBoard[m-idx-1][c] = (idx < listSize) ? list.get(idx) : ' ';
			}
		}
		
		return newBoard;
	}
	
	public static void main(String[] args) {
		Solution_P_L2_17679_프렌즈4블록 sol = new Solution_P_L2_17679_프렌즈4블록();
		int m=4;
		int n=5;
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		int answer = sol.solution(m,n,board);
		System.out.println(answer);
	}
}