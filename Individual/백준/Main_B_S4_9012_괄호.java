import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * -괄호-
 */

//출처 : https://www.acmicpc.net/problem/9012
public class Main_B_S4_9012_괄호 {
	static int TC;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		init();
		
		while(--TC >= 0) {
			find();
		}
	}
	
	static void find() throws Exception {
		String str = br.readLine();
		int size = str.length();
		
		Stack<Character> stack = new Stack<>();
		char tc;
		for(int i=0; i<size; ++i) {
			tc = str.charAt(i);
			
			switch (tc) {
			case '(':
				stack.push('(');
				break;

			case ')':
			default:
				if(stack.isEmpty()) {
					System.out.println("NO");
					return;
				}
				else {
					if(stack.peek() == '(') {
						stack.pop();
					}
					else {
						stack.push(')');
					}
				}
				break;
			}
		}
		if(stack.isEmpty()) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	
	static void init() throws Exception {
		TC = Integer.parseInt(br.readLine());
	}
}
