import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -외판원순회2-
 * 1. 순열 이용하여 연결될 수 있는 구간의 비용 합하기
 * 2. 마지막으로 뽑힌 장소와 첫 장소와의 연결
 * 3. 해당 비용이 0 이라면 지나갈 수 없으므로 패스
 */

//출처 : https://www.acmicpc.net/problem/10971
public class Solution_10971 {
	static int N;
	static int[][] board;
	static boolean[] isVisited;
	static int minResult = Integer.MAX_VALUE;
	static int first;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		isVisited = new boolean[N];

		board = new int[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; ++i) {
			first = i;
			isVisited[i] = true;
			solution(1,i,0);
			isVisited[i] = false;
		}
		System.out.println(minResult);
	}
	
	/**
	 * 
	 * @param count		현재까지 선택한 정점 갯수
	 * @param index		현재 선택한 정점 번호
	 * @param sum		현재까지 선택된 비용
	 */
	public static void solution(int count, int index, int sum) {
		if(count == N) {
			//마지막 도착점과 시작점이 이어져있다면 비용 계산
			if(board[index][first] != 0)
				minResult = Math.min(minResult, sum+board[index][first]);
			return;
		}
		
		for(int i = 0; i < N; ++i) {
			//방문했거나 이어져 있지 않다면 pass
			if(isVisited[i] || board[index][i] == 0)
				continue;
			isVisited[i] = true;
			solution(count+1, i, sum+board[index][i]);
			isVisited[i] = false;
		}
	}
}
