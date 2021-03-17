/*
 * -문자열 압축-
 * 테스트 1 〉	통과 (0.05ms, 52.9MB)
 * 테스트 2 〉	통과 (0.30ms, 53.1MB)
 * 테스트 3 〉	통과 (0.19ms, 51.5MB)
 * 테스트 4 〉	통과 (0.06ms, 53.7MB)
 * 테스트 5 〉	통과 (0.02ms, 52.9MB)
 * 테스트 6 〉	통과 (0.06ms, 52.3MB)
 * 테스트 7 〉	통과 (0.45ms, 52.7MB)
 * 테스트 8 〉	통과 (0.21ms, 53.2MB)
 * 테스트 9 〉	통과 (0.45ms, 52.6MB)
 * 테스트 10 〉	통과 (1.32ms, 53.4MB)
 * 테스트 11 〉	통과 (0.09ms, 53.8MB)
 * 테스트 12 〉	통과 (0.09ms, 51.8MB)
 * 테스트 13 〉	통과 (0.11ms, 53MB)
 * 테스트 14 〉	통과 (0.42ms, 52MB)
 * 테스트 15 〉	통과 (0.12ms, 52.7MB)
 * 테스트 16 〉	통과 (0.06ms, 52.2MB)
 * 테스트 17 〉	통과 (0.46ms, 52.2MB)
 * 테스트 18 〉	통과 (0.79ms, 52.2MB)
 * 테스트 19 〉	통과 (0.68ms, 53.1MB)
 * 테스트 20 〉	통과 (1.59ms, 53.4MB)
 * 테스트 21 〉	통과 (1.57ms, 54.3MB)
 * 테스트 22 〉	통과 (1.57ms, 53.2MB)
 * 테스트 23 〉	통과 (1.72ms, 52.5MB)
 * 테스트 24 〉	통과 (1.02ms, 54.7MB)
 * 테스트 25 〉	통과 (2.03ms, 53.1MB)
 * 테스트 26 〉	통과 (1.30ms, 53.4MB)
 * 테스트 27 〉	통과 (1.49ms, 54.1MB)
 * 테스트 28 〉	통과 (0.04ms, 52.3MB)
 * 풀이 시간 : 20M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/60057
public class Solution_P_L2_60057_문자열압축 {
	
	public int solution(String s) {
		int answer = s.length();
		int half = s.length()/2;
		int totalLength = s.length();
		
		int beginIndex, endIndex;
		int curLength, exCnt;
		String exStr, curStr;
		
		for(int i=1; i<=half; ++i) {
			beginIndex = 0;
			endIndex = i;
			curLength = 0;
			exCnt=1;
			exStr="";
			
			while(endIndex<=totalLength) {
				curStr = s.substring(beginIndex, endIndex);
				if(exStr.equals(curStr)) {
					exCnt++;
				}
				else {
					curLength += exStr.length() + (exCnt==1 ? 0 : Integer.toString(exCnt).length());
					exCnt=1;
					exStr = curStr;
				}
				beginIndex=endIndex;
				endIndex+=i;
			}
			curLength += exStr.length() + (exCnt==1 ? 0 : Integer.toString(exCnt).length());
			
			if(beginIndex != totalLength) {
				curStr = s.substring(beginIndex);
				curLength += curStr.length();
			}
			answer = Integer.min(answer, curLength);
		}
		
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_L2_60057_문자열압축 sol = new Solution_P_L2_60057_문자열압축();
		String s = "aabbaccc";
		int answer = sol.solution(s);
		System.out.println(answer);
	}
}