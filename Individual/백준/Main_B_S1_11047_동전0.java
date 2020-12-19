import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -동전 0-
 */

//출처 : https://www.acmicpc.net/problem/11047
public class Main_B_S1_11047_동전0 {
	static int N,K;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(find());
	}
	
	static int find() {
		int res = 0, ti;
		
		for(int i=N-1; i>=0; --i) {
			ti = K/arr[i];
			if(ti != 0) {
				res+= ti;
				K-= ti * arr[i];
			}
		}
		
		return res;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(br.readLine());
	}
}
