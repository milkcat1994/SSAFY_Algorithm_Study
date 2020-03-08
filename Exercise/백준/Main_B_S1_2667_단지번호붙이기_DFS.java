import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * -단지번호붙이기-
 * DFS이용한 풀이(재귀 사용)
 */

//출처 : https://www.acmicpc.net/problem/2667
public class Main_B_S1_2667_단지번호붙이기_DFS {
	static int[] drow = {-1, 1, 0, 0};
	static int[] dcol = {0, 0, -1, 1};
	static char[][] board;
	static boolean[][] visitable;
	
	static int nrow,ncol, count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		board = new char[N+2][N+2];
		//방문 정보 저장
		visitable = new boolean[N+2][N+2];
		String tempString;
		for(int row = 1; row <= N; ++row) {
			tempString = br.readLine();
			for(int col = 1; col <= N; ++col) {
				board[row][col] = tempString.charAt(col-1);
				visitable[row][col] = true;
			}
		} //end for(row)
		
		//오름 차순 정렬 필요
		List<Integer> resultList = new ArrayList<Integer>();
		
		//모든 곳 방문 예정
		for(int row = 1; row <= N; ++row) {
			for(int col = 1; col <= N; ++col) {
				//만일 방문 할 수 있다면 방문하고 해당 자리가 '1'이라면 dfs시작 '0'이라면 pass
				if(visitable[row][col] && board[row][col] == '1') {
					//dfs 시작
					count = 0;
					dfs(row, col);
					//저장한 단지 숫자 pq에 저장
					if(count != 0)
						resultList.add(count);
				}
			}
		} //end for(row)

		// 결과값 출력
		sb.append(resultList.size()+"\n");
		resultList.sort(null);
		for(Integer var : resultList)
			sb.append(var+"\n");
		System.out.println(sb.toString());
	}

	public static void dfs(int row, int col) {
		visitable[row][col] = false;
		
		//빈땅이면 나가기
		if(board[row][col] == '0')
			return;
		
		// 집이면 주변 집찾기
		count += 1;
		for(int i = 0; i < 4; ++i) {
			nrow = row + drow[i];
			ncol = col + dcol[i];
			//다음 자리 방문 가능하고, 1이라면 방문
			if(visitable[nrow][ncol] && board[nrow][ncol] == '1') {
				visitable[nrow][ncol] = false;
				dfs(nrow, ncol);
			}
		} //end for(i)
		
	}
	
}
