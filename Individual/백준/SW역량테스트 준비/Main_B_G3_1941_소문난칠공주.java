import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * -소문난 칠공주-
 * 1. 조합으로 7자리 선택
 * 2. BFS로 해당 자리 모두 이어져 있는지 확인
 */

//출처 : https://www.acmicpc.net/problem/1941
public class Main_B_G3_1941_소문난칠공주 {
	static final int N = 5;
	static char[][] board = new char[N][N];
	static boolean[][] isSelected = new boolean[N][N];
	static boolean[][] tempVisited = new boolean[N][N];
	
	static Queue<int[]> que = new LinkedList<>();
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr;
		
		for (int row = 0; row < N; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = tempStr.charAt(col);
			}
		}
		
		dfs(0,0,0);
		
		System.out.println(result);
	}
	
	public static void dfs(int index, int count, int res) {
		if(count == 7) {
			if(!bfs(count)) return;
			if(res>=4) {
				result++;
			}
			return;
		}
		
		int nr,nc;
		for(int i = index; i < 25; ++i) {
			nr = i/N;	nc = i%N;
			isSelected[nr][nc] = true;
			dfs(i+1, count+1, board[nr][nc] == 'S' ? res+1 : res);
			isSelected[nr][nc] = false;
		}
	}
	
	public static void initVisited() {
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				tempVisited[row][col] = false;
			}
		}
	}
	
	//현재 선택한 count만큼 true가 나오지 않으면 실패임
	public static boolean bfs(int count) {
		int[] tInt;
		int nr,nc, tc=0;
		initVisited();
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(isSelected[row][col]) {
					que.offer(new int[]{row, col});
					tempVisited[row][col] = true;
					tc++;
					while(!que.isEmpty()) {
						tInt = que.poll();
						
						for(int dir = 0; dir < 4; ++dir) {
							nr = tInt[0]+drow[dir];	nc = tInt[1]+dcol[dir];
							//나가거나 선택 되지 않는다면 나갈 수 없다.
							if(isOut(nr, nc) || !isSelected[nr][nc] || tempVisited[nr][nc]) continue;
							tempVisited[nr][nc] = true;
							tc++;
							que.offer(new int[] {nr,nc});
						}
					}
					//하나로 연결 되어있다면 true 아니라면 false
					if(count == tc) return true;
					return false;
				} // end if(isSelected[][])
			}
		}
		return true;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
}
