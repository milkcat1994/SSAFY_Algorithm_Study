import java.util.HashMap;
import java.util.Map;

/*
 * -뉴스 클러스터링-
 * 테스트 1 〉	통과 (0.95ms, 52MB)
 * 테스트 2 〉	통과 (1.27ms, 52.9MB)
 * 테스트 3 〉	통과 (0.98ms, 52.5MB)
 * 테스트 4 〉	통과 (37.65ms, 57.2MB)
 * 테스트 5 〉	통과 (2.75ms, 52.6MB)
 * 테스트 6 〉	통과 (0.88ms, 52.6MB)
 * 테스트 7 〉	통과 (4.91ms, 54.2MB)
 * 테스트 8 〉	통과 (6.33ms, 52.4MB)
 * 테스트 9 〉	통과 (5.20ms, 52.8MB)
 * 테스트 10 〉	통과 (9.48ms, 52.3MB)
 * 테스트 11 〉	통과 (16.17ms, 53.1MB)
 * 테스트 12 〉	통과 (0.93ms, 51.9MB)
 * 테스트 13 〉	통과 (3.19ms, 53.3MB)
 * 
 * 풀이 시간 : 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17677
public class Solution_P_L2_17677_뉴스클러스터링 {
	public int solution(String str1, String str2) {
		String str;
		Map<String, Integer> hm = new HashMap<>();
		for(int i=0; i<str1.length()-1; ++i) {
			str = str1.substring(i,i+2);
			// 대소문자만 들어왔을 경우
			if(!str.matches(".*[^a-zA-Z].*")) {
				str = str.toUpperCase();
				hm.put(str, hm.getOrDefault(str, 0)+1);
			}
		}

		Map<String, Integer> hm2 = new HashMap<>();
		for(int i=0; i<str2.length()-1; ++i) {
			str = str2.substring(i,i+2);
			// 대소문자만 들어왔을 경우
			if(!str.matches(".*[^a-zA-Z].*")) {
				str = str.toUpperCase();
				hm2.put(str, hm2.getOrDefault(str, 0)+1);
			}
		}
		
		int cross=0;
		int sum=0;
		for(Map.Entry<String, Integer> entry : hm.entrySet()) {
			// 해당 되는 key를 가지고 있다면 교차되는 최소 개수 저장하기
			if(hm2.containsKey(entry.getKey())) {
				cross += Math.min(entry.getValue(), hm2.get(entry.getKey()));
				sum += Math.max(entry.getValue(), hm2.get(entry.getKey()));
			}
			// 없다면 따로 더해주기
			else {
				sum += entry.getValue();
			}
		}
		
		for(Map.Entry<String, Integer> entry : hm2.entrySet()) {
			if(!hm.containsKey(entry.getKey())) {
				sum += entry.getValue();
			}
		}
		
		if(cross==0 && sum==0)
			return 1*65536;
		return (int)(((double)cross/sum)*65536);
	}

	public static void main(String[] args) {
		Solution_P_L2_17677_뉴스클러스터링 sol = new Solution_P_L2_17677_뉴스클러스터링();
		String str1 = "E=M*C^2";
		String str2 = "e=m*c^2";
		int answer = sol.solution(str1, str2);
		System.out.println(answer);
	}
}