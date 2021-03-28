import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * -순위 검색-
 * 1. 3중 for문 사용시 시간초과
 * 2. query를 Trie로 구현할 시 시간초과
 * 3. info에 대해 미리 hashmap으로 등록해두고, 점수에 대해서는 이분 탐색으로 찾기
 * 
 * 다른 풀이 : info를 5차원배열로 구성, 각 점수 이하에 대한 배열에 대해 미리 계산,
 * query를 돌면서 해당되는 배열 값 바로 반환
 * 
 * ++ info배열 Trie로 구성 후 해당 되는 점수 이분탐색도 가능할듯.
 * 풀이 시간 : 3H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/72412
public class Solution_P_L2_72412_순위검색 {
	int[] answer;
	public int[] solution(String[] info, String[] query) {
		String[] arr;
		int infoSize = info.length;
		Map<String, List<Integer>> hm = new HashMap<>();
		for(int idx=0; idx<infoSize; ++idx) {
			arr = info[idx].split(" ");
			makeString(arr, 0, "", hm);
		}
		
		Iterator<String> iter = hm.keySet().iterator();
		while(iter.hasNext()) {
			hm.get(iter.next()).sort((a,b) -> a-b);
		}
		
		int querySize = query.length;
		answer = new int[querySize];
		List<Integer> list;
		int targetGrade, targetIdx;
		for(int idx=0; idx<querySize; ++idx) {
			arr = query[idx].replace(" and ", "").split("\\s");
			if(hm.containsKey(arr[0])) {
				targetGrade = arr[1].equals("-") ? 0 : Integer.parseInt(arr[1]);
				list = hm.get(arr[0]);
				targetIdx = findIdx(targetGrade, list);
				answer[idx] +=list.size()-targetIdx;
			}
		}
		
		return answer;
	}
	
	int findIdx(int targetGrade, List<Integer> list) {
		int start = 0;
		int end = list.size()-1;
		int mid;
		
		while(start <= end) {
			mid = (start+end)/2;
			if(list.get(mid) < targetGrade)
				start = mid+1;
			else
				end = mid-1;
		}
		
		return start;
	}
	
	void makeString(String[] arr, int idx, String str, Map<String, List<Integer>> hm) {
		if(idx==4) {
			if(!hm.containsKey(str))
				hm.put(str, new ArrayList<>());
			hm.get(str).add(Integer.parseInt(arr[4]));
			return;
		}
		makeString(arr, idx+1, str+arr[idx], hm);
		makeString(arr, idx+1, str+"-", hm);
	}

	public static void main(String[] args) {
		Solution_P_L2_72412_순위검색 sol = new Solution_P_L2_72412_순위검색();
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		int[] answer = sol.solution(info, query);
		for(Integer ti : answer)
			System.out.println(ti);
	}
}