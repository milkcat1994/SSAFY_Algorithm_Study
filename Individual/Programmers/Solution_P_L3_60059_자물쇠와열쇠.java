/*
 * -자물쇠와 열쇠-
 * 1. 너무 어렵게 생각한 문제... 그냥 key를 좌측 끝부터 우측 끝까지 이동 시키고, 회전한 경우도 고려해주면 끝.
 * 2. 좌측 끝은 key의 우측 끝과 lock의 좌측 끝이 맞닿는 부분부터 시작이다.
 * 
 * 테스트 1 〉	통과 (0.40ms, 52.1MB)
 * 테스트 2 〉	통과 (0.04ms, 51.8MB)
 * 테스트 3 〉	통과 (8.62ms, 53.6MB)
 * 테스트 4 〉	통과 (0.04ms, 52.5MB)
 * 테스트 5 〉	통과 (30.21ms, 59MB)
 * 테스트 6 〉	통과 (16.84ms, 53.3MB)
 * 테스트 7 〉	통과 (4.20ms, 54MB)
 * 테스트 8 〉	통과 (23.00ms, 56.2MB)
 * 테스트 9 〉	통과 (188.65ms, 86.9MB)
 * 테스트 10 〉	통과 (62.10ms, 79.6MB)
 * 테스트 11 〉	통과 (20.40ms, 56.3MB)
 * 테스트 12 〉	통과 (0.04ms, 53MB)
 * 테스트 13 〉	통과 (8.93ms, 52.6MB)
 * 테스트 14 〉	통과 (8.80ms, 53.2MB)
 * 테스트 15 〉	통과 (1.48ms, 52.1MB)
 * 테스트 16 〉	통과 (17.47ms, 54.1MB)
 * 테스트 17 〉	통과 (148.65ms, 77.6MB)
 * 테스트 18 〉	통과 (0.61ms, 51.9MB)
 * 테스트 19 〉	통과 (0.26ms, 53MB)
 * 테스트 20 〉	통과 (30.42ms, 53.6MB)
 * 테스트 21 〉	통과 (23.28ms, 56.1MB)
 * 테스트 22 〉	통과 (1.27ms, 53.1MB)
 * 테스트 23 〉	통과 (10.68ms, 53.2MB)
 * 테스트 24 〉	통과 (5.81ms, 53.6MB)
 * 테스트 25 〉	통과 (111.17ms, 71.8MB)
 * 테스트 26 〉	통과 (264.27ms, 98.4MB)
 * 테스트 27 〉	통과 (104.64ms, 72.1MB)
 * 테스트 28 〉	통과 (70.26ms, 67.5MB)
 * 테스트 29 〉	통과 (10.62ms, 54.6MB)
 * 테스트 30 〉	통과 (59.36ms, 65.7MB)
 * 테스트 31 〉	통과 (6.83ms, 53.2MB)
 * 테스트 32 〉	통과 (8.65ms, 53.7MB)
 * 테스트 33 〉	통과 (2.10ms, 53MB)
 * 테스트 34 〉	통과 (4.22ms, 52.8MB)
 * 테스트 35 〉	통과 (1.21ms, 53MB)
 * 테스트 36 〉	통과 (1.27ms, 51.9MB)
 * 테스트 37 〉	통과 (0.98ms, 53.2MB)
 * 테스트 38 〉	통과 (0.24ms, 52.1MB)
 * 
 * 풀이 시간 : 5H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/60059
public class Solution_P_L3_60059_자물쇠와열쇠 {
	int N,M, newM;
	int targetKeyCnt;
	
	// right, down, left, up
	int[] drow = {0,1,0,-1};
	int[] dcol = {1,0,-1,0};
	boolean[][][] isVisited;
	int visitSize;
	
	public boolean solution(int[][] key, int[][] lock) {
		// 3 <= M <= N <= 20
		N = lock.length;
		M = key.length;
		visitSize = (M-1)*2 + 1;
		isVisited = new boolean[4][visitSize][visitSize];
		
		targetKeyCnt = getLockNonBlockCnt(lock);

		if(targetKeyCnt == 0) return true;

		newM = (M-1)*2+M;
		int[][] newKey = new int[newM][newM];
		for (int row = M-1; row < M+M-1; ++row) {
			for (int col = M-1; col < M+M-1; ++col) {
				newKey[row][col] = key[row-M+1][col-M+1];
			}
		}
		
		return dfs(newKey, lock, M-1, M-1, 0);
	}
	
	boolean dfs(int[][] key, int[][] lock, int cr, int cc, int rotate) {
		if(rotate==4)
			return false;
		// 이미 방문한 적이 있다면 실패
		if(isVisited[rotate][cr][cc])
			return false;
		isVisited[rotate][cr][cc] = true;
		
		int originKeyCnt = getKeyBlockCnt(key);
		// 키가 없다면 실패
		if(originKeyCnt == 0)
			return false;
		
		// 현재 가진 key의 개수가 목표치보다 작다면 실패
		if(originKeyCnt < targetKeyCnt)
			return false;

		// 다 채워진다면 true 반환
		if(isBoardFull(key, lock, targetKeyCnt))
			return true;
		
		// 상하좌우 이동
		for(int d=0; d<4; ++d) {
			if(isOut(cr+drow[d], cc+dcol[d], visitSize))
				continue;
			
			// 성공 시 true 반환
			if(dfs(moveBoard(key, d), lock, cr+drow[d], cc+dcol[d], rotate))
				return true;
		}
		
		// 회전
		int nr,nc;
		//1번만 회전시켜서 주면됨
		nr = cc;
		nc = (visitSize-1) - (cr);
		if(dfs(rotateBoard(key), lock, nr, nc, rotate+1))
			return true;
		
		return false;
	}
	
	// 시계 방향 90도 회전
	int[][] rotateBoard(int[][] board) {
		int size = board.length;
		int moveSize = size-1;
		
		int targetRotateCnt = size/2;
		int rotateCnt=0;
		int sr,sc;
		
		int[][] arr = new int[size][size];
		resetBoard(board, arr);
		
		while(rotateCnt < targetRotateCnt) {
			sr = sc = rotateCnt;
			
			// 왼쪽 -> 위
			for(int i=0; i<moveSize; ++i) {
				arr[sr][sc+i] = board[sr+moveSize-i][sc];
			}
			
			// 아래 -> 왼쪽
			for(int i=0; i<moveSize; ++i) {
				arr[sr+moveSize-i][sc] = board[sr+moveSize][sc+moveSize-i];
			}

			// 오른쪽 -> 아래
			for(int i=0; i<moveSize; ++i) {
				arr[sr+moveSize][sc+moveSize-i] = board[sr+i][sc+moveSize];
			}
			
			// 위 -> 오른쪽
			for(int i=0; i<moveSize; ++i) {
				arr[sr+i][sc+moveSize] = board[sr][sc+i];
			}

			moveSize -=2;
			rotateCnt++;
		}
		return arr;
	}

	void resetBoard(int[][] originBoard, int[][] targetBoard) {
		int size = originBoard.length;
		for(int i=0; i<size; ++i)
			targetBoard[i] = originBoard[i].clone();
	}
	
	int[][] moveBoard(int[][] board, int d) {
		int nr,nc;
		int size = board.length;
		int[][] arr = new int[size][size];
		
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				nr=row+drow[d]; nc=col+dcol[d];
				if(isOut(nr,nc, size))
					continue;
				
				arr[nr][nc] = board[row][col];
			}
		}
		return arr;
	}
	
	boolean isOut(int r, int c, int size) {
		if(r<0 || c<0 || r>=size || c>=size)
			return true;
		return false;
	}
	
	int getLockNonBlockCnt(int[][] board) {
		int size = board.length;
		int cnt=0;
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				if(board[row][col] == 0) cnt++;
			}
		}
		return cnt;
	}
	
	int getKeyBlockCnt(int[][] board) {
		int cnt=0;

		for (int row = M-1; row < M+M-1; ++row) {
			for (int col = M-1; col < M+M-1; ++col) {
				if(board[row][col]==1) cnt++;
			}
		}
		return cnt;
	}
	
	boolean isBoardFull(int[][] key, int[][] lock, int targetBlockCnt) {
		// keySize : M , lockSize : N
		int diff = N-M;
		int blockCnt;
		int keyNum, lockNum;
		for(int lockRow=0; lockRow<=diff; ++lockRow) {
			F:for(int lockCol=0; lockCol<=diff; ++lockCol) {
				blockCnt = 0;
				// 만일 한 곳이라도 빈자리 있다면 다음 차례
				for(int keyRow=0; keyRow<M; ++keyRow) {
					for(int keyCol=0; keyCol<M; ++keyCol) {
						keyNum = key[keyRow+M-1][keyCol+M-1];
						lockNum = lock[keyRow+lockRow][keyCol+lockCol];
						if(keyNum == 1 && lockNum == 0)
							blockCnt++;
						if(keyNum == 1 && lockNum == 1)
							continue F;
					}
				}
				// block을 다 끼워맞췄다면
				if(blockCnt == targetBlockCnt)
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Solution_P_L3_60059_자물쇠와열쇠 sol = new Solution_P_L3_60059_자물쇠와열쇠();
		int[][] key = {{0,0,0}, {1,0,0}, {0,1,1}};
		int[][] lock = {{1,1,1}, {1,1,0}, {1,0,1}};
		boolean answer = sol.solution(key, lock);
		System.out.println(answer);
	}
}