import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -빵집-
 * 1. DFS이용하여 도착하는 경로 찾기
 * 2. DFS도중 방문 해본 곳은 다시 밟지 않기
 */

//출처 : https://www.acmicpc.net/problem/3109
public class Solution_3109 {
	static int ROW, COL;

	static int[] drow = {-1,0,1};
	static int[] dcol = {1,1,1};
	static boolean isFind;
	
	static boolean[][] isVisted;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		isVisted = new boolean[ROW][COL+1];
		
		String tempString;
		for (int row = 0; row < ROW; ++row) {
			tempString = br.readLine();
			for (int col = 0; col < COL; ++col) {
				switch (tempString.charAt(col)) {
				case 'x':
					isVisted[row][col] = true;
					break;
				}
			}
		}
		
		int result = 0;
		for(int row = 0; row < ROW; ++row) {
			isFind = false;
			isVisted[row][0] = true;
			dfs(row, 0);
			if(isFind)
				result++;
		}
		System.out.println(result);
		
	}
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>COL)
			return true;
		return false;
	}
	
	public static void dfs(int row, int col) {
		//도착 했을 경우
		if(col >= COL) {
			isFind = true;
		}
		
		//3방향
		int nr,nc;
		for(int i = 0; i < 3; ++i) {
			nr = row+drow[i];	nc = col+dcol[i];
			//이미 연결 되어있거나 벽인경우 pass
			if(isOut(nr, nc) || isVisted[nr][nc])
				continue;
			isVisted[nr][nc] = true;
			dfs(nr, nc);
			if(isFind)
				return;
			//찾았을 경우는 바로 return
		}
	}
	
}