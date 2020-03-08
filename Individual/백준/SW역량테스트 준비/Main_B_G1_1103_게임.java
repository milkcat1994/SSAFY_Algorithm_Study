import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -게임-
 * 1. 사이클 판단 해야하므로 DFS이용
 * 2. 밖으로 나간다면 1을 반환
 * 3. 방문하여 해당 자리에 횟수가 저장되어있다면 해당 횟수 반환
 * 4. 반환값+1 이 현재 자리의 최대 횟수이다.
 * 5. timeArr이용 DP
 */

//출처 : https://www.acmicpc.net/problem/1103
public class Main_B_G1_1103_게임 {
	static int ROW,COL;
	static int[][] board;
	static int[][] timeArr;
	static boolean[][] isVisited;
	static boolean isCycle = false;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());

		String tempStr;
		char tc;
		board = new int[ROW][COL];
		timeArr = new int[ROW][COL];
		isVisited = new boolean[ROW][COL];
		for (int row = 0; row < ROW; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < COL; ++col) {
				tc = tempStr.charAt(col);
				if(tc =='H')
					board[row][col] = -1;
				else
					board[row][col] = tc-'0';
			}
		}
		
		dfs(0,0);
		
		if(isCycle)
			System.out.println(-1);
		else {
			System.out.println(setMax());
		}
	}
	
	public static int dfs(int row, int col) {
		//나갔다면 0반환하여 이전자리 1가질수있도록.
		if(isOut(row, col))	return 0;
		//방문 했다면 Cycle이 있다는 것.
		if(isVisited[row][col]) {
			isCycle=true;
			return 0;
		}
		//이미 갱신된 자리라면 해당 값 가져오기
		if(timeArr[row][col] > 0) return timeArr[row][col];
		
		int nr, nc,jump=board[row][col], tt;
		isVisited[row][col] = true;
		for(int dir = 0; dir < 4; ++dir) {
			nr=row+drow[dir]*jump;	nc=col+dcol[dir]*jump;

			tt = dfs(nr,nc)+1;
			//큰값으로 갱신
			if(timeArr[row][col] < tt)
				timeArr[row][col] = tt;
			
			if(isCycle) return 0;
		}
		
		isVisited[row][col] = false;
		return timeArr[row][col];
	}
	
	public static int setMax() {
		int maxTime=0;
		for (int row = 0; row < ROW; ++row) {
			for (int col = 0; col < COL; ++col) {
				if(maxTime<timeArr[row][col])
					maxTime=timeArr[row][col];
			}
		}
		return maxTime;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL || board[row][col]==-1)
			return true;
		return false;
	}
}
