import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -N과 M_12-
 * 메모리 : 15352KB
 * 시간 : 104ms
 * 코드길이 : 1364B
 * 소요 시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/15666
public class Main_B_S2_15666_N과M_12 {
	static int N,M;
	static Stack<Integer> stack = new Stack<>();
	static List<Integer> intList = new ArrayList<Integer>();
	static Set<Integer> set = new HashSet<Integer>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; ++i)
			set.add(Integer.parseInt(st.nextToken()));
		
		intList.addAll(set);
		Collections.sort(intList);
		N = set.size();
		
		getDupAscCombi(0, 0);
		System.out.print(sb.toString());
	}
	
	public static void getDupAscCombi(int index, int count) {
		if(count == M) {
			for(Integer ti : stack) {
				sb.append(ti).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = index; i < N; ++i) {
			stack.push(intList.get(i));
			getDupAscCombi(i, count+1);
			stack.pop();
		}
	}
}
