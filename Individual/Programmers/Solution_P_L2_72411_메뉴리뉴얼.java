import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * -메뉴 리뉴얼-
 * 테스트 1 〉	통과 (16.22ms, 53.4MB)
 * 테스트 2 〉	통과 (19.04ms, 55.5MB)
 * 테스트 3 〉	통과 (16.34ms, 53.3MB)
 * 테스트 4 〉	통과 (15.32ms, 52.8MB)
 * 테스트 5 〉	통과 (14.87ms, 54MB)
 * 테스트 6 〉	통과 (18.97ms, 54.7MB)
 * 테스트 7 〉	통과 (18.80ms, 53.9MB)
 * 테스트 8 〉	통과 (42.71ms, 58.7MB)
 * 테스트 9 〉	통과 (37.59ms, 56.8MB)
 * 테스트 10 〉	통과 (44.55ms, 56.7MB)
 * 테스트 11 〉	통과 (44.58ms, 56.9MB)
 * 테스트 12 〉	통과 (32.55ms, 55.3MB)
 * 테스트 13 〉	통과 (44.17ms, 57.6MB)
 * 테스트 14 〉	통과 (42.35ms, 58MB)
 * 테스트 15 〉	통과 (38.27ms, 56.9MB)
 * 테스트 16 〉	통과 (50.87ms, 58.3MB)
 * 테스트 17 〉	통과 (49.98ms, 60.7MB)
 * 테스트 18 〉	통과 (51.00ms, 61.4MB)
 * 테스트 19 〉	통과 (50.57ms, 60.8MB)
 * 테스트 20 〉	통과 (44.94ms, 59.4MB)
 * 
 * 풀이시간 : 45M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/72411
public class Solution_P_L2_72411_메뉴리뉴얼 {
	Map<String, Integer> hm;
	boolean[] isAble;
	int[] maxCnt;
	
	public String[] solution(String[] orders, int[] course) {
		hm = new HashMap<>();
		
		isAble = new boolean[21];
		maxCnt = new int[isAble.length];
		
		for(Integer cnt : course) {
			isAble[cnt] = true;
		}

		for(String order : orders) {
			combination(order, 0, "");
		}
		
		Iterator<String> iter = hm.keySet().iterator();
		String order;
		List<String> result = new ArrayList<>();
		while(iter.hasNext()) {
			order = iter.next();
			if(isAble[order.length()] && maxCnt[order.length()] > 1 && maxCnt[order.length()] == hm.get(order)) {
				result.add(order);
			}
		}
		result.sort((o1, o2) -> {
			return o1.compareTo(o2);
		});
		
		return result.toArray(new String[result.size()]);
	}
	
	void combination(String order, int idx, String combi){
		if(combi.length() >= 2 && isAble[combi.length()]) {
			if(hm.containsKey(combi))
				hm.put(combi, hm.get(combi)+1);
			else
				hm.put(combi, 1);
			
			maxCnt[combi.length()] = Math.max(maxCnt[combi.length()], hm.get(combi));
			
		}
		
		if(idx==order.length()) {
			return;
		}
		
		for(int i=idx; i<order.length(); ++i) {
			combination(order, i+1, orderString(combi+order.charAt(i)));
		}
	}
	
	String orderString(String str) {
		char[] chArr = str.toCharArray();
		Arrays.sort(chArr);
		
		StringBuilder sb = new StringBuilder();
		for(Character ch : chArr)
			sb.append(ch);
		return sb.toString();
	}

	public static void main(String[] args) {
		Solution_P_L2_72411_메뉴리뉴얼 sol = new Solution_P_L2_72411_메뉴리뉴얼();
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		
		String[] answer = sol.solution(orders, course);
		for(String str : answer) {
			System.out.println(str);
		}
	}
}