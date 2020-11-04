import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * -후위표기식-
 */

//출처 : https://www.acmicpc.net/problem/1918
public class Main_B_G4_1918_후위표기식 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		
		//알파벳
		Queue<Character> que = new LinkedList<>();
		//기호
		Stack<Character> stack = new Stack<>();
		// 여는괄호, 닫는괄호
		int size=str.length();
		for(int i=0; i<size; ++i) {
			switch (str.charAt(i)) {
			// 여는괄호는 무조건 넣기
			case '(':
				stack.push('(');
				break;
				
			case ')':
				// 여는 괄호가 나올때 까지 stack.pop, que.poll
				while(!que.isEmpty())
					sb.append(que.poll());
				while(!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				//여는괄호 제거
				stack.pop();
				break;
				
			//덧셈, 뺄셈
			case '+':
			case '-':
				//이전 등호가 *,/,+,-라면 모든것 비워야함
				if(!stack.isEmpty() && stack.peek()!='(') {
					while(!que.isEmpty())
						sb.append(que.poll());
					while(!stack.isEmpty() && stack.peek() != '(')
						sb.append(stack.pop());
				}
				stack.push(str.charAt(i));
				break;
			// 곱셈, 나눗셈은 *,/ 라면 비워야함
			case '*':
			case '/':
				if(!stack.isEmpty() && stack.peek()!='(') {
					while(!que.isEmpty())
						sb.append(que.poll());
					while(!stack.isEmpty() && stack.peek() != '(' && stack.peek() != '+' && stack.peek() != '-')
						sb.append(stack.pop());
				}
				stack.push(str.charAt(i));
				break;
			// 알파벳은 그냥 담을것
			default:
				que.offer(str.charAt(i));
				break;
			}
		}
		//안이 차있다면 모두 제거하기
		while(!que.isEmpty())
			sb.append(que.poll());
		while(!stack.isEmpty() && stack.peek() != '(') {
			sb.append(stack.pop());
		}
		System.out.println(sb.toString());
	}
}
