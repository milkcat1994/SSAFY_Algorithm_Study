import java.util.HashSet;
import java.util.Set;

/*
 * -불량사용자-
 * 1. 첫 풀이는 hashMap과 set을 이용하여 사전에 선택가능한 user_id를 선별하여 처리하였으나
 * 2. dfs 함수내에서 idx를 바로 불러와 사용가능하여 hashMap을 제외하였다.
 * 3. 또한 user_id의 수가 8개 이하임을 고려하여 bitMask가 가능하여 중복 처리가 더 쉬워졌다.
 * 
 * 테스트 1 〉	통과 (0.54ms, 52.8MB)
 * 테스트 2 〉	통과 (1.57ms, 53.2MB)
 * 테스트 3 〉	통과 (1.62ms, 52.9MB)
 * 테스트 4 〉	통과 (1.59ms, 52.2MB)
 * 테스트 5 〉	통과 (236.38ms, 102MB)
 * 테스트 6 〉	통과 (24.08ms, 54.1MB)
 * 테스트 7 〉	통과 (1.55ms, 51.4MB)
 * 테스트 8 〉	통과 (1.86ms, 52.7MB)
 * 테스트 9 〉	통과 (2.52ms, 53.8MB)
 * 테스트 10 〉	통과 (1.02ms, 52.5MB)
 * 테스트 11 〉	통과 (3.06ms, 52.2MB)
 * 풀이 시간 : 40M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64064
public class Solution_P_L3_64064_불량사용자 {
	static Set<Integer> set;
	
	public int solution(String[] user_id, String[] banned_id) {
		set = new HashSet<>();
		
		dfs(0, user_id, banned_id, 0);
		
		return set.size();
	}
	
	void dfs(int idx, String[] user_id, String[] banned_id, int isSelected){
		// 모두 골랐다면 set으로 중복 제거
		if(idx==banned_id.length) {
			set.add(isSelected);
			return;
		}
		
		String regex = banned_id[idx].replace("*", ".");
		for(int userIdIdx=0; userIdIdx<user_id.length; ++userIdIdx) {
			if((isSelected & (1<<userIdIdx)) > 0 || !user_id[userIdIdx].matches(regex)) continue;
			
			dfs(idx+1, user_id, banned_id, isSelected | (1<<userIdIdx));
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_64064_불량사용자 sol = new Solution_P_L3_64064_불량사용자();
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		int answer = sol.solution(user_id, banned_id);
		System.out.println(answer);
	}
}