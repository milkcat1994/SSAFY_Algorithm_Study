/*
 * -자물쇠와 열쇠-
 * 1. 너무 어렵게 생각한 문제... 그냥 key를 좌측 끝부터 우측 끝까지 이동 시키고, 회전한 경우도 고려해주면 끝.
 * 2. 좌측 끝은 key의 우측 끝과 lock의 좌측 끝이 맞닿는 부분부터 시작이다.
 * 
 * 3. move를 먼저 실행해서 좌측 끝부터 우측 끝까지 모두 순회 하였다.
 * 4. 그 뒤 rotate를 이용하여 key를 회전시키고 위의 방식을 반복하였다.
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
	public boolean solution(int[][] key, int[][] lock) {
		// 3 <= M <= N <= 20
		int[][] moveTemp;
		for(int d = 0; d<4; ++d) {
			for (int moveRow = -key.length+1; moveRow < lock.length; ++moveRow) {
				for (int moveCol = -key.length+1; moveCol < lock.length; ++moveCol) {
					moveTemp = move(key, moveRow, moveCol, lock.length);
					
					if(isBoardFull(moveTemp, lock)) return true;
				}
			}
			key = rotate(key);
		}
		return false;
	}
	
	// 시계방향 회전
	int[][] rotate(int[][] key){
		int size = key.length;
		int[][] arr = new int[size][size];
		
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				arr[row][col] = key[size-col-1][row];
			}
		}
		return arr;
	}
	
	int[][] move(int[][] key, int moveRow, int moveCol, int lockSize){
		int[][] arr = new int[lockSize][lockSize];
		
		int keyLength = key.length;
		int nr,nc;
		// key를 순회 하며 lock크기의 arr에 붙인다.
		for (int row = 0; row < keyLength; ++row) {
			for (int col = 0; col < keyLength; ++col) {
				nr=row+moveRow; nc=col+moveCol;
				if(isOut(nr,nc,lockSize)) continue;
				arr[nr][nc] = key[row][col];
			}
		}
		return arr;
	}
	
	boolean isBoardFull(int[][] key, int[][] lock) {
		for (int row = 0; row < lock.length; ++row) {
			for (int col = 0; col < lock.length; ++col) {
				if((key[row][col] ^ lock[row][col]) == 0) return false;
			}
		}
		return true;
	}
	
	boolean isOut(int r, int c, int size) {
		if(r<0 || c<0 || r>=size || c>=size)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Solution_P_L3_60059_자물쇠와열쇠 sol = new Solution_P_L3_60059_자물쇠와열쇠();
		int[][] key = {{0,0,0}, {1,0,0}, {0,1,1}};
//		int[][] key = {{0,1}, {1,0}};
		int[][] lock = {{1,1,1}, {1,1,0}, {1,0,1}};
		boolean answer = sol.solution(key, lock);
		System.out.println(answer);
	}
}