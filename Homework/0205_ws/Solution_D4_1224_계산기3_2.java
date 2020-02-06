import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 후위 연산 방식으로 먼저 교환이후 다시 계산한다.
public class Solution_D4_1224_계산기3_2 {
	static Stack<Character> stackO;
	static Stack<Integer> stackI;
	static Queue<Character> queueTotal;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tLength;
		String tempStr;
		//각 stack 초기화
		stackO = new Stack<Character>();
		stackI = new Stack<Integer>();
		queueTotal = new LinkedList<Character>();

		for(int t = 1; t <= 10; ++t) {
			
			tLength = Integer.parseInt(br.readLine());
			
			tempStr = br.readLine();
			
			for(int i = 0; i < tLength; ++i) {
				makePost(tempStr.charAt(i));
			}
			
			//남은 것 빼기
			while(!stackO.isEmpty()) {
				queueTotal.offer(stackO.pop());
			}
			
			// 후위 표기식 빌때까지 앞부터 진행
			while(!queueTotal.isEmpty()) {
				pick(queueTotal.poll());
			}
			System.out.println("#"+t+" "+stackI.pop());
		}
	}
	
	private static int cal(char c) {
		int x,y;
		y = stackI.pop();
		x = stackI.pop();
		
		switch (c) {
		case '+':
			return x+y;
		case '-':
			return x-y;
		case '*':
			return x*y;
		//case '/'
		default:
			return x/y;
		}
	}
	
	
	private static void makePost(char c) {
		switch (c) {
			// 여는 괄호는 무조건 넣는다.
		case '(':
			stackO.push(c);
			break;
			// 닫는 괄호는 여는괄호가 나올때 까지 pop하여 계산한다.
		case ')':
			while(stackO.peek() != '(') {
				queueTotal.offer(stackO.pop());
			}
			stackO.pop();
			break;
			//+,-라면 연산자 stack의 top이 *,/이라면 stack내부먼저 처리
			// 아니라면 push
		case '+':
		case '-':
		case '*':
		case '/':
			//만약 넣을 수 없다면 stackO에서 하나 빼고 계속 확인
			while(!isPush(stackO.peek(), c)) {
				queueTotal.offer(stackO.pop());
			}
			stackO.push(c);
			break;

		//숫자일 경우에
		default:
			queueTotal.offer(c);
			break;
		}
	}

	private static void pick(char c) {
		switch (c) {
			// 여는 괄호는 무조건 넣는다.
		case '+':
		case '-':
		case '*':
		case '/':
			stackI.push(cal(c));
			break;

		//숫자일 경우에
		default:
			stackI.push(c-'0');
			break;
		}
	}
	
	// stack에 넣을지
	// stack.peek(), 넣을 연산자
	private static boolean isPush(char s, char c){
		if(s == '(')
			return true;
		if(c == '+' || c== '-') {
			return false;
		}
		return true;
	
	}
}
