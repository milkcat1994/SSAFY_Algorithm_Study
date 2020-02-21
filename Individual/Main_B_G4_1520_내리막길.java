import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -내리막길-
 * 1. DFS를 이용해 먼저 목적지를 찾는다.
 * 2. 목적지의 가짓수에 1로 만들고 '1'반환 => 다시 목적지 도착시에는 '3번'진행
 * 3. 거꾸로 돌아가며 방문한 적이 있었던 땅은 해당 땅의 가짓수를 가져오기
 * 4. 방문하지 않았다면 해당 땅으로 들어가 DFS계속 진행
 * 5. 4방향 다 확인하고 현재 좌표에 가짓수 저장했다면 현재 좌표의 가짓수 반환
 */

//출처 : https://www.acmicpc.net/problem/1520
public class Main_B_G4_1520_내리막길 {
	static int ROW, COL;
	static int[][] board;
	static boolean[][] isVisited;
	static int[][] sum;
	
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static class Point {
		int row, col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		board = new int[ROW][COL];
		isVisited = new boolean[ROW][COL];
		sum = new int[ROW][COL];
		
		for (int row = 0; row < ROW; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		isVisited[0][0] = true;
		System.out.println(dfs(0,0));
	}
	
	public static int dfs(int row, int col) {
		//도착 했다면 도착 지점에 1더해주기
		//돌아가면서 가짓수를 가져올것이기 때문
		if(isFind(row,col)) {
			sum[row][col]=1;
			return 1;
		}
		
		int cr=row,cc=col, nr,nc,res=0;
		for(int i = 0; i < 4; ++i) {
			nr = cr+drow[i];	nc = cc+dcol[i];
			//갈수 없는 곳이라면 pass
			if(isAble(nr, nc, board[cr][cc]))
				continue;
			//이미 방문 한적이 있는 곳이라면 해당 좌표 가능성 갯수 가져오기.
			if(isVisited[nr][nc]) {
				res += sum[nr][nc];
				continue;
			}
			isVisited[nr][nc] = true;
			res += dfs(nr,nc);
		} //end for(0~3)
		//현재 좌표에 가짓수 더해주고 해당 가짓수 반환
		sum[row][col] += res;
		return sum[row][col];
	}
	
	public static boolean isFind(int row, int col) {
		if(row == ROW-1 && col == COL-1)
			return true;
		return false;
	}
	
	//밖으로 나가거나 내리막길이 아닌경우
	public static boolean isAble(int row, int col, int exInt) {
		if(row<0 || col<0 || row>=ROW || col>=COL || exInt<=board[row][col])
			return true;
		return false;
	}
}