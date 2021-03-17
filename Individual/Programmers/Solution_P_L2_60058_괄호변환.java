/*
 * -괄호변환-
 * 테스트 1 〉	통과 (1.64ms, 52.4MB)
 * 테스트 2 〉	통과 (0.05ms, 53.1MB)
 * 테스트 3 〉	통과 (1.63ms, 53.1MB)
 * 테스트 4 〉	통과 (1.65ms, 52.2MB)
 * 테스트 5 〉	통과 (1.58ms, 52MB)
 * 테스트 6 〉	통과 (1.60ms, 52.6MB)
 * 테스트 7 〉	통과 (1.73ms, 52.2MB)
 * 테스트 8 〉	통과 (1.60ms, 52.7MB)
 * 테스트 9 〉	통과 (1.76ms, 52.5MB)
 * 테스트 10 〉	통과 (1.65ms, 53.7MB)
 * 테스트 11 〉	통과 (1.65ms, 52.1MB)
 * 테스트 12 〉	통과 (1.70ms, 52MB)
 * 테스트 13 〉	통과 (1.93ms, 52.2MB)
 * 테스트 14 〉	통과 (1.74ms, 51.7MB)
 * 테스트 15 〉	통과 (1.70ms, 55.4MB)
 * 테스트 16 〉	통과 (2.06ms, 52.9MB)
 * 테스트 17 〉	통과 (3.25ms, 52.3MB)
 * 테스트 18 〉	통과 (2.81ms, 52.5MB)
 * 테스트 19 〉	통과 (2.07ms, 53.7MB)
 * 테스트 20 〉	통과 (1.93ms, 53.5MB)
 * 테스트 21 〉	통과 (1.80ms, 52MB)
 * 테스트 22 〉	통과 (1.66ms, 52.2MB)
 * 테스트 23 〉	통과 (2.32ms, 52.9MB)
 * 테스트 24 〉	통과 (1.74ms, 52.5MB)
 * 테스트 25 〉	통과 (1.73ms, 52.2MB)
 * 
 * 풀이 시간 : 20M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/60058
public class Solution_P_L2_60058_괄호변환 {
	
	public String solution(String p) {
		return find(p);
	}
	
	public String find(String w) {
		int leftCnt=0;
		int idx;
		String u,v;
		
		for(int i=0; i<w.length(); ++i) {
			// 균형 부분 찾기
			switch (w.charAt(i)) {
			case '(':
				leftCnt++;
				break;
			case ')':
				leftCnt--;
				break;
			default:
				// do nothing
				break;
			}
			
			// 균형 잡힌 괄호를 추출하기위해 idx 이용하여 나누기
			if(leftCnt==0) {
				idx = i;
				u = w.substring(0,idx+1);
				v = w.substring(idx+1);
				
				// u가 올바른 괄호 문자열이라면 문자열 v에 대해 1단계 다시 수행
				// u에 수행된 값 더해서 반환
				if(isCorrect(u)) {
					w = v;
					return u+find(v);
				}
				// 올바른 괄호 문자열이 아니라면
				else {
					// u에 대해 처리하는 구간 => 4-4
					String str = u.substring(1,u.length()-1);
					StringBuilder sb2 = new StringBuilder();
					for(int j=0; j<str.length(); ++j) {
						sb2.append(str.charAt(j) == '(' ? ')' : '(');
					}
					
					// 4-1 ~ 4-3, 4-5
					StringBuilder sb = new StringBuilder();
					sb.append('(').append(find(v)).append(')').append(sb2.toString());
					return sb.toString();
				}
			}
		}
		return "";
	}
	
	// 올바른 괄호 문자열인지 판단
	public boolean isCorrect(String u) {
		int left=0;
		for(int i=0; i<u.length(); ++i) {
			switch (u.charAt(i)) {
			case '(':
				left++;
				break;
			case ')':
				left--;
				break;
			default:
				break;
			}
			
			if(left<0)
				return false;
		}
		return true;
	}


	public static void main(String[] args) {
		Solution_P_L2_60058_괄호변환 sol = new Solution_P_L2_60058_괄호변환();
		String p = "(()())()";
		String answer = sol.solution(p);
		System.out.println(answer);
	}
}