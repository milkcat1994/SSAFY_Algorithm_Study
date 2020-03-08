import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * -모든 순열-
 * 1. 1부터 N까지 뽑지 않은 수를 뽑아간다.
 * 2. 뽑은 수는 Stack으로 관리하여 StringBuilder에 추가하고 한번에 출력한다.
 */

//출처 : https://www.acmicpc.net/problem/10974
public class Main_B_S3_10974_모든순열 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	static boolean[] isVisited;
	static Stack<Integer> st = new Stack<Integer>();
	static Integer[] intArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isVisited = new boolean[N+1];
		intArr = new Integer[]{0,1,2,3,4,5,6,7,8};
		
		printPermutation(0);
		
		System.out.print(sb.toString());
	}
	
	/**
	 * 
	 * @param count		현재까지 뽑은 갯수
	 */
	public static void printPermutation(int count) {
		// 다 뽑았을 경우 stack에 있는 수를 순차적으로 더해준다.
		if(count == N) {
			for(Integer i : st) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 모든 순열을 출력하기 위해 1부터 N까지 순회한다.
		for(int i = 1; i <= N; ++i) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			st.push(intArr[i]);
			printPermutation(count+1);
			st.pop();
			isVisited[i] = false;
		}
	}
}