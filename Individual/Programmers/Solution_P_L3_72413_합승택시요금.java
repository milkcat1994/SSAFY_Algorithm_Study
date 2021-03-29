/*
 * -합승 택시 요금-
 * 1. 플로이드-워셜 알고리즘 이용
 * 
 * 정확성  테스트
 * 테스트 1 〉	통과 (0.04ms, 52.1MB)
 * 테스트 2 〉	통과 (0.09ms, 54MB)
 * 테스트 3 〉	통과 (0.06ms, 52.4MB)
 * 테스트 4 〉	통과 (0.12ms, 52.3MB)
 * 테스트 5 〉	통과 (0.17ms, 52.6MB)
 * 테스트 6 〉	통과 (0.26ms, 54.3MB)
 * 테스트 7 〉	통과 (0.20ms, 53.1MB)
 * 테스트 8 〉	통과 (0.37ms, 52.7MB)
 * 테스트 9 〉	통과 (0.56ms, 53MB)
 * 테스트 10 〉	통과 (0.71ms, 52.7MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (18.84ms, 54.4MB)
 * 테스트 2 〉	통과 (28.51ms, 53.9MB)
 * 테스트 3 〉	통과 (34.36ms, 52.4MB)
 * 테스트 4 〉	통과 (25.37ms, 53.6MB)
 * 테스트 5 〉	통과 (25.75ms, 52.4MB)
 * 테스트 6 〉	통과 (26.20ms, 53.1MB)
 * 테스트 7 〉	통과 (35.98ms, 63.8MB)
 * 테스트 8 〉	통과 (65.25ms, 64.8MB)
 * 테스트 9 〉	통과 (37.52ms, 64.5MB)
 * 테스트 10 〉	통과 (36.98ms, 64.4MB)
 * 테스트 11 〉	통과 (62.72ms, 64.6MB)
 * 테스트 12 〉	통과 (39.23ms, 60.2MB)
 * 테스트 13 〉	통과 (69.04ms, 59.8MB)
 * 테스트 14 〉	통과 (70.00ms, 60.4MB)
 * 테스트 15 〉	통과 (77.80ms, 62.1MB)
 * 테스트 16 〉	통과 (27.08ms, 53.9MB)
 * 테스트 17 〉	통과 (33.63ms, 53.4MB)
 * 테스트 18 〉	통과 (24.79ms, 53MB)
 * 테스트 19 〉	통과 (23.55ms, 53.1MB)
 * 테스트 20 〉	통과 (39.13ms, 54.2MB)
 * 테스트 21 〉	통과 (27.69ms, 53.6MB)
 * 테스트 22 〉	통과 (56.05ms, 59.4MB)
 * 테스트 23 〉	통과 (76.98ms, 63.3MB)
 * 테스트 24 〉	통과 (39.36ms, 57.6MB)
 * 테스트 25 〉	통과 (25.29ms, 53.7MB)
 * 테스트 26 〉	통과 (25.01ms, 53.4MB)
 * 테스트 27 〉	통과 (25.53ms, 54.3MB)
 * 테스트 28 〉	통과 (27.43ms, 53.7MB)
 * 테스트 29 〉	통과 (19.36ms, 52.7MB)
 * 테스트 30 〉	통과 (17.96ms, 52.6MB)
 * 
 * 풀이 시간 : 10M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/72413
public class Solution_P_L3_72413_합승택시요금 {
	static final int INF = 10000000;
	
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] board = new int[n+1][n+1];
		for (int row = 1; row <= n; ++row) {
			for (int col = 1; col <= n; ++col) {
				if(row==col)
					board[row][col] = 0;
				else
					board[row][col] = INF;
			}
		}
		
		int start,end,weight;
		for(int[] intArr : fares) {
			start = intArr[0];
			end = intArr[1];
			weight = intArr[2];
			
			board[start][end] = weight;
			board[end][start] = weight;
		}
		
		for(int j=1; j<=n; ++j) {
			for(start=1; start<=n; ++start) {
				for(end=1; end<=n; ++end) {
					board[start][end] = Math.min(board[start][end], board[start][j] + board[j][end]);
				}
			}
		}
		
		// 중간지점 확인
		int minRes=Integer.MAX_VALUE;
		for(int mid=1; mid<=n; ++mid) {
			minRes = Math.min(minRes, board[a][mid] + board[b][mid] + board[mid][s]);
		}
		
		return minRes;
	}

	public static void main(String[] args) {
		Solution_P_L3_72413_합승택시요금 sol = new Solution_P_L3_72413_합승택시요금();
		int n=6;
		int s=4;
		int a=6;
		int b=2;
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		int answer = sol.solution(n, s, a, b, fares);
		System.out.println(answer);
	}
}