import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * -1로 만들기 2-
 * 
 * 
 */

//출처 : https://www.acmicpc.net/problem/12852
public class Main_B_S1_12852_1로만들기2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int X;
	
	public static void main(String[] args) throws Exception {
		init();
		
		solve();
	}
	
	static void solve() {
		int[] memo = new int[X+1];
		Arrays.fill(memo, -1);
		
		Queue<Integer> que = new LinkedList<>();
		que.offer(X);
		
		int ti, nextIdx;
		while(!que.isEmpty()) {
			ti = que.poll();
			if(ti == 1)
				break;
			
			if(ti%3 == 0) {
				nextIdx = ti/3;
				if(memo[nextIdx] == -1) {
					que.offer(nextIdx);
					memo[nextIdx] = ti;
				}
			}
			
			if(ti%2 == 0) {
				nextIdx = ti/2;
				if(memo[nextIdx] == -1) {
					que.offer(nextIdx);
					memo[nextIdx] = ti;
				}
			}
			
			if(ti > 0) {
				nextIdx = ti - 1;
				if(memo[nextIdx] == -1) {
					que.offer(nextIdx);
					memo[nextIdx] = ti;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Stack<Integer> answerStack = new Stack<>();
		answerStack.push(1);
		while(answerStack.peek() != X) {
			answerStack.push(memo[answerStack.peek()]);
		}
		
		int cnt = answerStack.size() - 1;
		while(!answerStack.isEmpty()) {
			sb.append(answerStack.pop()).append(' ');
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}

	static void init() throws Exception {
		X = Integer.parseInt(br.readLine());
	}
	
}
