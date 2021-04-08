import java.util.Arrays;

/*
 * -경주로 건설-
 * 
 * 테스트 1 〉	통과 (0.08ms, 52.7MB)
 * 테스트 2 〉	통과 (0.08ms, 52.8MB)
 * 테스트 3 〉	통과 (0.07ms, 52.3MB)
 * 테스트 4 〉	통과 (0.11ms, 52.2MB)
 * 테스트 5 〉	통과 (0.12ms, 53.1MB)
 * 테스트 6 〉	통과 (1.94ms, 53.9MB)
 * 테스트 7 〉	통과 (2.55ms, 53.5MB)
 * 테스트 8 〉	통과 (6.57ms, 53.6MB)
 * 테스트 9 〉	통과 (3.20ms, 53.4MB)
 * 테스트 10 〉	통과 (7.85ms, 53.4MB)
 * 테스트 11 〉	통과 (49.65ms, 53.9MB)
 * 테스트 12 〉	통과 (111.08ms, 53.7MB)
 * 테스트 13 〉	통과 (1.04ms, 52.6MB)
 * 테스트 14 〉	통과 (1.32ms, 53.4MB)
 * 테스트 15 〉	통과 (8.47ms, 52.9MB)
 * 테스트 16 〉	통과 (31.19ms, 53.5MB)
 * 테스트 17 〉	통과 (24.06ms, 54.1MB)
 * 테스트 18 〉	통과 (63.52ms, 54MB)
 * 테스트 19 〉	통과 (24.36ms, 53.2MB)
 * 테스트 20 〉	통과 (3.82ms, 53.5MB)
 * 테스트 21 〉	통과 (3.64ms, 54MB)
 * 테스트 22 〉	통과 (0.41ms, 53.2MB)
 * 테스트 23 〉	통과 (0.25ms, 52.1MB)
 * 
 * 다익스트라 이용해서 풀이 하는 방식도 있음. dfs, bfs 모두 사용가능
 * 
 * 풀이 시간 : 1H 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/67259
public class Solution_P_L3_67259_경주로건설 {
	int minRes;
	int boardLength;
	int[] drow = new int[] {0,0,-1,1};
	int[] dcol = new int[] {-1,1,0,0};

	public int solution(int[][] board) {
		boardLength = board.length;
		
		int[][][] resArr = new int[boardLength][boardLength][4];
		for(int r=0; r<boardLength; ++r) {
			for(int c=0; c<boardLength; ++c) {
				if(r==0 && c==0) continue;
				Arrays.fill(resArr[r][c], Integer.MAX_VALUE);
			}
		}
		
		minRes = Integer.MAX_VALUE;
		
		if(board[0][1] != 1)
			dfs(board, resArr, 1, 0, 1, 100);
		if(board[1][0] != 1)
			dfs(board, resArr, 3, 1, 0, 100);
		
		return minRes;
	}
	
	void dfs(int[][] board, int[][][] resArr, int exDirection, int r, int c, int sum) {
		if(resArr[r][c][exDirection] <= sum)
			return;
		
		resArr[r][c][exDirection] = sum;
		
		if(r==boardLength-1 && c==boardLength-1) {
			minRes = Math.min(minRes, sum);
			return;
		}
		
		int nr,nc;
		for(int d=0; d<4; ++d) {
			nr=r+drow[d]; nc=c+dcol[d];
			if(isOut(nr,nc) || board[nr][nc]==1) continue;
			
			if(d == exDirection)
				dfs(board, resArr, d, nr, nc, sum+100);
			else
				dfs(board, resArr, d, nr, nc, sum+600);
		}
	}
	
	boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=boardLength || c>=boardLength)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Solution_P_L3_67259_경주로건설 sol = new Solution_P_L3_67259_경주로건설();
		int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		int answer = sol.solution(board);
		System.out.println(answer);
	}
}