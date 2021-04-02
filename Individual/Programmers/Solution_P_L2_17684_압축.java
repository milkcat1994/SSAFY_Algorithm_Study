import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * -압축-
 * 
 * 테스트 1 〉	통과 (2.80ms, 52.8MB)
 * 테스트 2 〉	통과 (4.87ms, 52MB)
 * 테스트 3 〉	통과 (2.45ms, 51.7MB)
 * 테스트 4 〉	통과 (6.27ms, 53.4MB)
 * 테스트 5 〉	통과 (3.29ms, 52.7MB)
 * 테스트 6 〉	통과 (3.31ms, 53MB)
 * 테스트 7 〉	통과 (3.18ms, 52MB)
 * 테스트 8 〉	통과 (3.36ms, 52.7MB)
 * 테스트 9 〉	통과 (3.70ms, 52.6MB)
 * 테스트 10 〉	통과 (2.70ms, 52.7MB)
 * 테스트 11 〉	통과 (3.30ms, 52.5MB)
 * 테스트 12 〉	통과 (2.93ms, 51.7MB)
 * 테스트 13 〉	통과 (4.40ms, 52.5MB)
 * 테스트 14 〉	통과 (7.97ms, 52.7MB)
 * 테스트 15 〉	통과 (5.37ms, 53.2MB)
 * 테스트 16 〉	통과 (3.52ms, 53.6MB)
 * 테스트 17 〉	통과 (3.21ms, 52.8MB)
 * 테스트 18 〉	통과 (6.24ms, 52.8MB)
 * 테스트 19 〉	통과 (3.69ms, 52.6MB)
 * 테스트 20 〉	통과 (4.28ms, 53.1MB)
 * 
 * 풀이 시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17684
public class Solution_P_L2_17684_압축 {
	public int[] solution(String msg) {
		List<Integer> answerList = new ArrayList<>();
		Map<String, Integer> hm = new HashMap<String, Integer>();
		int idx=1;
		
		for(char ch='A'; ch<='Z'; ++ch) {
			hm.put(String.valueOf(ch), idx++);
		}
		
		String exStr;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<msg.length(); ++i) {
			sb.append(msg.charAt(i));
			
			if(hm.containsKey(sb.toString())) {
				if(i==msg.length()-1) {
					answerList.add(hm.get(sb.toString()));
				}
				continue;
			}
			else {
				exStr = sb.toString().substring(0, sb.length()-1);
				answerList.add(hm.get(exStr));
				hm.put(sb.toString(), idx++);
				sb.setLength(0);
				i--;
			}
		}
		
		return answerList.stream().mapToInt((i) -> i).toArray();
	}


	public static void main(String[] args) {
		Solution_P_L2_17684_압축 sol = new Solution_P_L2_17684_압축();
		String msg = "TOBEORNOTTOBEORTOBEORNOT";
//		String msg = "KAKAO";
		int[] answer = sol.solution(msg);
		System.out.println(Arrays.toString(answer));
	}
}