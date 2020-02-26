import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_5-
 * 메모리 : 53684KB
 * 시간 : 356ms
 * 코드길이 : 1244B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15654
public class Main_B_S3_15654_N과M_5 {
	static int N,M;
	static StringBuilder sb = new StringBuilder();
	static Stack<Integer> stack = new Stack<>();
	static boolean[] isSelected;
	static int[] intArr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		intArr = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; ++i)
			intArr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(intArr);
		
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
		
		for(int i = 0; i < N; ++i) {
			if(isSelected[i]) continue;
			stack.push(intArr[i]);
			isSelected[i] = true;
			getPermu(count+1);
			isSelected[i] = false;
			stack.pop();
		}
	}
}
