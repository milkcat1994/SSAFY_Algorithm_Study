import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_3-
 * 메모리 : 339364KB
 * 시간 : 732ms
 * 코드길이 : 864B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15651
public class Main_B_S3_15651_N과M_3 {
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		getDupPermu(0);
		
		System.out.print(sb.toString());
	}
	
	public static void getDupPermu(int count) {
		if(count == M) {
			for(Integer ti : stack) {
				sb.append(ti).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 1; i <= N; ++i) {
			stack.push(i);
			getDupPermu(count+1);
			stack.pop();
		}
	}
}
