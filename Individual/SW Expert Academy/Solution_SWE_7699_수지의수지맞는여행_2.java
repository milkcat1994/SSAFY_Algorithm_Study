import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -수지의 수지 맞는 여행-
 * 1. DFS이용한 완전탐색
 * 2. bitMasking 이용한 관람한 명소 관리
 * 3. 최대 명소 관람시 종료
 */

/*
 * 메모리 : 25172KB 
 * 시간 : 133ms 
 * 코드길이 : 1726B 
 * 소요시간 : 40M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWqUzj0arpkDFARG
public class Solution_SWE_7699_수지의수지맞는여행_2 {
	static final int MAXRES = 'Z'-'A'+1;
	static int ROW,COL,maxResult;
	static boolean isMax;
	static int[][] board;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			ROW = Integer.parseInt(st.nextToken());
			COL = Integer.parseInt(st.nextToken());
			
			board = new int[ROW][COL];
			String tempStr;
			for (int row = 0; row < ROW; ++row) {
				tempStr = br.readLine();
				for (int col = 0; col < COL; ++col) {
					board[row][col] = tempStr.charAt(col)-'A';
				}
			}
			
			maxResult=1;
			isMax=false;
			dfs(0, 0, 1<<board[0][0], 1);
			sb.append('#').append(t).append(' ').append(maxResult).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	public static void dfs(int row, int col, int visited, int count) {
		if(count == MAXRES) {
			isMax=true;
			maxResult=MAXRES;
			return;
		}
		int nr,nc;
		for(int dir = 0; dir < 4; ++dir) {
			nr=row+drow[dir];   nc=col+dcol[dir];
			if(isOut(nr, nc)) continue;
			if((visited & (1<<board[nr][nc]))> 0) {
				maxResult = maxResult > count ? maxResult : count;
				continue;
			}
			dfs(nr, nc, visited | (1<<board[nr][nc]), count+1);
			if(isMax)
				return;
		}
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
}