import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -평범한 배낭-
 */

//출처 : https://www.acmicpc.net/problem/12865
public class Main_B_G5_12865_평범한배낭 {
	static int N,K,maxResult;
	static final int maxK = 100001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		maxResult = 0;
		int[] memo = new int[maxK];
		int w, v;
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			w=Integer.parseInt(st.nextToken());
			v=Integer.parseInt(st.nextToken());
			
			for(int idx = K-w; idx > 0; --idx) {
				// 입력된 곳만.
				if(memo[idx] > 0) {
					if(idx+w <= K && memo[idx+w] < memo[idx]+v) {
						memo[idx+w] = memo[idx]+v;
					}
				}
			}
			
			if(memo[w] < v) {
				memo[w] = v;
			}			
		}
		for(int i = 0; i <= K; ++i) {
			if(maxResult < memo[i]) maxResult = memo[i];
		}
		System.out.println(maxResult);
		
	}
}
