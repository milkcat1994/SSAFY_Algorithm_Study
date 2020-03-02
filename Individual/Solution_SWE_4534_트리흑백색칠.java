import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -트리 흑백 색칠-
 * 1. rootNode가 흑, 백 일때 따로 계산
 * 2. 동일한 계산 반복 될 수 있으므로 메모이제이션 이용필요
 * 3. dp[w]=dp[w]+dp[b]; dp[b]=dp[w]; 점화식 알고도 못푼문제
 * 4. 각 경우의 수들을 곱해주어야 한다는 점 알아두기
 */

/*
 * 메모리 : 119596KB 
 * 시간 : 595ms 
 * 코드길이 : 1605B 
 * 소요시간 : 1D+
 */

//150P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWO6esOKOKQDFAWw
public class Solution_SWE_4534_트리흑백색칠 {
	static final int BLACK=0;
	static final int WHITE=1;
	static int N;
	static final int MOD = 1000000007;
	static List<Integer>[] list;
	static long[][] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		int u,v;
		for(int t = 1; t <=T; ++t) {
			N = Integer.parseInt(br.readLine());
			memo = new long[2][N+1];
			list = new ArrayList[N+1];
			for(int n = 0; n <= N; ++n)
				list[n] = new ArrayList<Integer>();
			
			for(int n = 1; n < N; ++n) {
				st = new StringTokenizer(br.readLine(), " ");
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				list[u].add(v);
				list[v].add(u);
			}
			
			sb.append('#').append(t).append(' ').append((dfs(1,BLACK,-1)+dfs(1,WHITE,-1))%MOD).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	public static long dfs(int v, int color, int parent) {
		//memo[color][v]값이 존재한다면, 해당 값 리턴.
		if(memo[color][v] > 0) return memo[color][v];
		
		long ret = 1;
		//color가 흑(0)인 경우.
		//자식 노드들을 백(1)으로 칠한 경우의 경우의 수들의 곱
		if(color==BLACK) {
			for(int nu : list[v]) {
				if(nu == parent) continue;
				ret *= dfs(nu, WHITE, v);
				ret%=MOD;
			}
		}
		//color가 백(1)인 경우.
		//자식 노드들을 흑(1)으로 칠한 경우의 경우의 수들의 곱
		//+
		//자식 노드들을 백(0)으로 칠한 경우의 경우의 수들의 곱
		else if(color==WHITE){
			for(int nu : list[v]) {
				if(nu == parent) continue;
				ret *= (dfs(nu, BLACK, v) + dfs(nu, WHITE, v));
				ret%=MOD;
			}
		}
		//memo[color][v]에 기록
		memo[color][v] = ret;
		return ret;
	}
}