import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_10-
 * 메모리 : 13036KB
 * 시간 : 76ms
 * 코드길이 : 1380B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15664
public class Main_B_S2_15664_N과M_10 {
	static int N,M;
	static Stack<Integer> stack = new Stack<>();
	static int[] intArr;
	static Set<String> set = new HashSet<String>();
	static StringBuilder sb;
	static StringBuilder sb2 = new StringBuilder();
	
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
		
		getAscCombi(0, 0);
		System.out.print(sb2.toString());
	}
	
	public static void getAscCombi(int index, int count) {
		if(count == M) {
			sb = new StringBuilder();
			for(Integer ti : stack) {
				sb.append(ti).append(' ');
			}
			sb.append('\n');
			if(!set.contains(sb.toString())) {
				sb2.append(sb.toString());
				set.add(sb.toString());
			}
			return;
		}
		
		for(int i = index; i < N; ++i) {
			stack.push(intArr[i]);
			getAscCombi(i+1, count+1);
			stack.pop();
		}
	}
}
