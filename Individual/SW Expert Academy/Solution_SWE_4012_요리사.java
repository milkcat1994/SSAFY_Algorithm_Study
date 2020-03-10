import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -요리사-
 * 1. 1번 무조건 선택하는 조합 작성
 */

/*
 * 메모리 : 30324KB 
 * 시간 : 259ms 
 * 코드길이 : 1410B 
 * 소요시간 : 25M
 */

//200P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH
public class Solution_SWE_4012_요리사 {
	static int N;
	static int[][] board;
	static int minRes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; ++t) {
			minRes=Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			
			int ti;
			board=new int[N][N];
			for (int r = 0; r < N; ++r) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; ++c) {
					ti=Integer.parseInt(st.nextToken());
					board[r][c] += ti;
					board[c][r] += ti;
				}
			}
			
			//1번째 무조건 선택
			dfs(1, 1, 1<<0);
			System.out.println("#"+t+" "+minRes);
		} //end TestCase
	}
	
	static void dfs(int index, int count, int isSelected) {
		//N-2개 보다 많이 선택했다면 볼 필요 없다.
		if(count>N-2)
			return;
		//1개 이상일때 확인
		if(count>1)
			getMinRes(isSelected);
		//index부터 선택 시작
		for(int i = index; i<N; ++i) {
			dfs(i+1, count+1, isSelected|1<<i);
		}
	}
	
	static void getMinRes(int isSelected) {
		int a=0,b=0, tRes=0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < row; ++col) {
				//둘다 선택 된거면 a++
				if((isSelected & 1<<row)!=0 && (isSelected & 1<<col)!=0)
					a+=board[row][col];
				//둘다 선택 되지 않았다면 b++
				else if((isSelected & 1<<row)==0 && (isSelected & 1<<col)==0)
					b+=board[row][col];
			}
		}
		tRes=Math.abs(a-b);
		minRes = minRes<tRes ? minRes : tRes;
	}
}