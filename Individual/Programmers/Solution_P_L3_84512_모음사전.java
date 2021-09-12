import java.util.Arrays;
import java.util.HashMap;

/*
 * -모음사전-
 * 1. 각자리별 뛰어넘을 개수를 미리 저장한다.
 * 2. 순서대로 확인하며 뛰어넘을 개수를 더하여 결과를 출력한다.
 * 
 * 테스트 35 〉	통과 (0.11ms, 70.4MB)
 * 테스트 36 〉	통과 (0.13ms, 60MB)
 * 테스트 37 〉	통과 (0.09ms, 59MB)
 * 테스트 38 〉	통과 (0.11ms, 59MB)
 * 테스트 39 〉	통과 (0.11ms, 68.4MB)
 * 테스트 40 〉	통과 (0.10ms, 59.7MB)
 * 
 * 풀이시간 : 30m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/84512
public class Solution_P_L3_84512_모음사전 {
	final int VOWEL_CNT = 5;
	
	public int solution(String word) {
		int answer = 0;
		String vowel = "AEIOU";
		HashMap<Character, Integer> hm = new HashMap<>();
		for(int i=0; i<VOWEL_CNT; ++i) {
			hm.put(vowel.charAt(i), i);
		}
		
		int[] gapArr = new int[VOWEL_CNT];
		int gap = 1;
		for(int i=0; i<VOWEL_CNT; ++i) {
			gapArr[VOWEL_CNT - i - 1] = gap;
			gap = gap * 5 + 1;
		}
		
		int wordLength = word.length();
		for(int i=0; i<wordLength; ++i) {
			answer += hm.get(word.charAt(i)) * gapArr[i] + 1;
		}
		
		System.out.println(Arrays.toString(gapArr));
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_P_L3_84512_모음사전 sol = new Solution_P_L3_84512_모음사전();
		String word = "AAAE";
		int answer = sol.solution(word);
		System.out.println(answer);
	}
}