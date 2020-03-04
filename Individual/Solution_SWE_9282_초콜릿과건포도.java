import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -초콜릿과 건포도-
 * 1. 모든 가로, 세로를 자르는 코드를 DFS 코드 내에 삽입하여 모든 경우의 수 확인
 * 2. 자르기 전의 합과 자른 후의 합을 합하여 최소 합을 찾아내고
 * 3. 행,열,행길이,열길이 에 따른 최소 합을 저장하여 반복 작업을 막는다.
 * 
 * memo에 dfs인 건 알았지만 어찌 자를지, 뭘 어떻게 저장해야할지 몰랐던 문제
 * 차근차근 다시 봐보기
 */

/*
 * 메모리 : 107928KB 
 * 시간 : 2987ms 
 * 코드길이 : 1845B 
 * 소요시간 : -
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW9j-qfacIEDFAUY
public class Solution_SWE_9282_초콜릿과건포도 {
	static int[][][][] memo;
	static int[][] board;
	static int ROW,COL;
	
	static int minRes;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			ROW=Integer.parseInt(st.nextToken());
			COL=Integer.parseInt(st.nextToken());
			
			board = new int[ROW][COL];
			memo = new int[ROW][COL][ROW+1][COL+1];
			for (int row = 0; row < ROW; ++row) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 0; col < COL; ++col) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			//반복되게 자르기
			minRes = dfs(0,0,ROW,COL, Integer.MAX_VALUE);
			sb.append('#').append(t).append(' ').append(minRes).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	public static int dfs(int row, int col, int rd, int cd, int minSum) {
		//단일 조각일 경우 더 볼 필요 없음, 자르는 비용은 없다.
		if(rd==1 && cd==1) return 0;
		
		//이미 저장된 최소값이 있다면 바로 반환
		if(memo[row][col][rd][cd] > 0) return memo[row][col][rd][cd];
		
		//기존에 있던 덩어리의 건포도 개수를 구하기
		int sum = 0;
		for (int r = row; r < row+rd; ++r) {
			for (int c = col; c < col+cd; ++c) {
				sum+=board[r][c];
			}
		}
		
		//가로로 나누어서 최소 비용 구하기
		for(int r = 1; r < rd; ++r) {
			//위쪽 비용과 아래쪽 비용, 현재 자신의 비용의 합
			int sum3 = sum+dfs(row,col,r,cd, minSum)+dfs(row+r,col,rd-r,cd, minSum);
			minSum = Math.min(minSum, sum3);
		}
		
		//세로로 나누어서 최소 비용 구하기
		for(int c = 1; c < cd; ++c) {
			//왼쪽 비용과 오른쪽 비용, 현재 자신의 비용의 합
			int sum3 = sum+dfs(row,col,rd,c, minSum)+dfs(row,col+c,rd,cd-c, minSum);
			minSum = Math.min(minSum, sum3);
		}
		
		memo[row][col][rd][cd] = minSum;
		return minSum;
	}
}