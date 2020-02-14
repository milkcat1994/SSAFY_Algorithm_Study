import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_5432_쇠막대기자르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		Stack<Character> st = new Stack<Character>();
		
		String tempString;
		int inputSize, result;
		char exChar = ' ', curChar;
		
		for(int t = 1; t <= T; ++t) {
			result = 0;
			tempString = br.readLine();
			inputSize = tempString.length();
			
			for(int index = 0; index < inputSize; ++index) {
				curChar = tempString.charAt(index);
				switch (curChar) {
				case '(':
					//쇠막대기야
					if(exChar == ')') {
						st.push(curChar);
					}
					//쇠막대기일걸요?
					else {
						st.push(curChar);
					}
					break;
				// case ')':
				default:
					//레이저일때
					if(exChar == '(') {
						st.pop();
						result += st.size();
					}
					//쇠막대기가 끝났어요 +1
					else if(exChar == ')') {
						result++;
						st.pop();
					}
					break;
				}
				exChar = curChar;
			} //end for(index)
			
			System.out.println("#"+t+" "+result);
		}
	}
}
