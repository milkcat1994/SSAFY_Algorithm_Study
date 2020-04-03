import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -다항식 계산-
 * 1. N,M의 범위가 50이하이기에 완전 탐색 해도 가능하다.
 * 2. memo에 이전에 구한 f_i(x_m)을 저장해둔다.
 */

/*
 * 메모리 : 27872KB 
 * 시간 : 102ms 
 * 코드길이 : 1447B 
 * 소요시간 : 50M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXCjsn0KJzcDFAX0
public class Solution_SWE_9659_다항식계산 {
	static final int MOD = 998244353;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; ++t) {
			int N = Integer.parseInt(br.readLine());
			int[][] func = new int[N+1][3];
			for(int n = 2; n <=N; ++n) {
				st = new StringTokenizer(br.readLine(), " ");
				func[n][0] = Integer.parseInt(st.nextToken());
				func[n][1] = Integer.parseInt(st.nextToken());
				func[n][2] = Integer.parseInt(st.nextToken());
			}
			sb.append("#"+t+" ");
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			long x;
			int a,b;
			for(int m = 0; m < M; ++m) {
				x = Integer.parseInt(st.nextToken());
				long[] memo = new long[N+1];
				memo[0] = 1;
				memo[1] = x;
				for(int n = 2; n <= N; ++n) {
					a = func[n][1];	b = func[n][2];
					switch (func[n][0]) {
					case 1:
						memo[n] = (memo[a] + memo[b])%MOD;
						break;
					case 2:
						memo[n] = (a * memo[b])%MOD;
						break;
					case 3:
						memo[n] = (memo[a] * memo[b])%MOD;
						break;
					}
				}
				sb.append(memo[N]+" ");
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
