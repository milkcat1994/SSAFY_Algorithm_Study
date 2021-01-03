import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -크게 만들기-
 * 후에 들어오는 수가 전의 수보다 크다면 순서대로 지워준다.
 */

//출처 : https://www.acmicpc.net/problem/2812
public class Main_B_G5_2812_크게만들기 {
	static int N,K;
	static String str;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve());
	}
	
	static String solve() {
		char tc;
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<N; ++i) {
			tc = str.charAt(i);
			if(stack.isEmpty()) {
				stack.push(tc);
			}
			else if(stack.peek() < tc) {
				while(!stack.isEmpty() && stack.peek() < tc && K>0) {
					stack.pop();
					K--;
				}
				stack.push(tc);
			}
			else {
				stack.push(tc);
			}
		}
		
		while(--K>=0) {
			stack.pop();
		}
		
		List<Character> list = new ArrayList<>();
		list.addAll(stack);
		
		StringBuilder sb = new StringBuilder();
		for(Character character : list) {
			sb.append(character -'0');
		}
		return sb.toString();
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		str = br.readLine();
	}
}