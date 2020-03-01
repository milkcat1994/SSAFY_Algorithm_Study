import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -욕심쟁이 판다-
 * 1. DFS이용 Memoization
 * 2. 이미 방문하여 시간을 적은 곳이라면 해당 시간을 가져온다.
 * 3. 모든 좌표에 대해 위 방식 실행
 * 4. 가장 큰 값을 출력
 */

//출처 : https://www.acmicpc.net/problem/1937
public class Main_B_G3_1937_욕심쟁이판다 {
	static int N;
	static int[][] board;
	static int[][] timeArr;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		timeArr = new int[N][N];
		
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				timeArr[row][col] = 1;
			}
		}
		
		int maxRes = 0,tr;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				tr = dfs(row,col);
				if(maxRes < tr) maxRes = tr;
			}
		}
		
		System.out.println(maxRes);
	} //end main
	
	public static int dfs(int row, int col) {
		//이미 방문 한적 있다면 해당 시간 반환
		if(timeArr[row][col] > 1) return timeArr[row][col];
		
		int nr,nc, time;
		for(int dir = 0; dir < 4; ++dir) {
			nr=row+drow[dir];	nc=col+dcol[dir];
			//나아갈 수 없다면 다음 방향 확인
			if(isOut(nr,nc) || board[row][col] >= board[nr][nc]) continue;
			time = dfs(nr,nc)+1;
			//시간 더 크다면 갱신
			if(timeArr[row][col] < time)
				timeArr[row][col] = time;
		}
		return timeArr[row][col];
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
}
