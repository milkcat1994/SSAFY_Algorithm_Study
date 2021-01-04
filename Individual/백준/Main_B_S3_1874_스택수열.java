import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * -스택수열-
 */

//출처 : https://www.acmicpc.net/problem/1874
public class Main_B_S3_1874_스택수열 {
	static int N;
	
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		init();
		
		find();
		
		System.out.println(sb.toString());
	}
	
	static void find() throws Exception {
		Stack<Integer> stack = new Stack<>();
		// idx는 최대로 들어온 숫자를 의미한다.
		int ti, idx=1;
		while(--N >= 0) {
			ti = Integer.parseInt(br.readLine());
			// 입력받은 숫자가 도달하지 못한 숫자라면 해당 숫자까지 stack에 넣어준다.
			while(ti >= idx) {
				stack.push(idx++);
				sb.append("+\n");
			}
			
			// 뽑힐 숫자가 없거나 뽑히는 숫자가 바라는 숫자와 다르다면 '수열'을 만들 수 없다.
			if(stack.isEmpty() || stack.peek() != ti) {
				sb.setLength(0);
				sb.append("NO");
				return;
			}
			
			stack.pop();
			sb.append("-\n");
		}
	}
	
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
	}
}
