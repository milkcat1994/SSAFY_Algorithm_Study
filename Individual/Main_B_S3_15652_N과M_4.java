import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_4-
 * 메모리 : 18420KB
 * 시간 : 124ms
 * 코드길이 : 894B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15652
public class Main_B_S3_15652_N과M_4 {
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		getDupAscPermu(1, 0);
		
		System.out.print(sb.toString());
	}
	
	public static void getDupAscPermu(int index, int count) {
		if(count == M) {
			for(Integer ti : stack) {
				sb.append(ti).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = index; i <= N; ++i) {
			stack.push(i);
			getDupAscPermu(i, count+1);
			stack.pop();
		}
	}
}
