import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_1-
 * 메모리 : 38972KB
 * 시간 : 256ms
 * 코드길이 : 999B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15649
public class Main_B_S3_15649_N과M_1 {
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static Stack<Integer> stack = new Stack<>();
	static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N+1];

		getPermu(0);
		
		System.out.print(sb.toString());
	}
	
	public static void getPermu(int count) {
		if(count == M) {
			for(Integer ti : stack) {
				sb.append(ti).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 1; i <= N; ++i) {
			if(isVisited[i]) continue;
			
			isVisited[i] = true;
			stack.push(i);
			getPermu(count+1);
			isVisited[i] = false;
			stack.pop();
		}
	}
}
