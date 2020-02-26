import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_7-
 * 메모리 : 302596KB
 * 시간 : 988ms
 * 코드길이 : 1093B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15656
public class Main_B_S3_15656_N과M_7 {
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static Stack<Integer> stack = new Stack<>();
	static int[] intArr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		intArr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; ++i)
			intArr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(intArr);
		
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
		
		for(int i = 0; i < N; ++i) {
			stack.push(intArr[i]);
			getDupPermu(count+1);
			stack.pop();
		}
	}
}
