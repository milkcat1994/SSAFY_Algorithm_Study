import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_2-
 * 메모리 : 12980KB
 * 시간 : 84ms
 * 코드길이 : 878B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15650
public class Main_B_S3_15650_N과M_2 {
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		getCombi(1, 0);
		
		System.out.print(sb.toString());
	}
	
	public static void getCombi(int index, int count) {
		if(count == M) {
			for(Integer ti : stack) {
				sb.append(ti).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = index; i <= N; ++i) {
			stack.push(i);
			getCombi(i+1, count+1);
			stack.pop();
		}
	}
}
