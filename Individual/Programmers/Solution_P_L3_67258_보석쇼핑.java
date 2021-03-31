import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * -보석쇼핑-
 * 1. 모든 보석을 담을 수 있는 윈도우만들기
 * 2. 그다음 남은 보석 개수가 1개가 남을 때까지 왼쪽 줄이기
 * 3. 해당 보석 채우기 위한 우측 포인터 이동
 * 
 * 풀이 시간 : 45M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/67258
public class Solution_P_L3_67258_보석쇼핑 {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		Map<String, Integer> gemMap = new HashMap<>();
		int idx=0;
		for(int i=0; i<gems.length; ++i)
			if(!gemMap.containsKey(gems[i]))
				gemMap.put(gems[i], idx++);
		int gemSize = gemMap.size();
		
		int[] gemCntArr = new int[gemSize];
		int left = 0;
		// 가장 처음 모든 보석을 담을 수 있는 window 생성
		int right = makeDefaultWindow(gemCntArr, left, gems, gemMap, gemSize);
		
		int minLength = right+1;
		answer[0] = left;
		answer[1] = right;
		while(left != -1 && right != -1) {
			// 개수가 1인것 나올때 까지 이동 -> 최대수 가질 수 있는 마지노선
			left = moveLeftRemainOne(gemCntArr, left, right, gems, gemMap);
			if(minLength > right-left+1) {
				minLength = right-left+1;
				answer[0] = left;
				answer[1] = right;
			}
			
			// 개수 1인것 삭제하여 0으로 만들어 주기.
			gemCntArr[gemMap.get(gems[left])]--;
			left++;
			// left가 범위 밖 이동 시 while문 탈출
			if(left>=gems.length)
				break;
			
			// 개수가 0이었던 것이 나올때 까지 이동
			right = moveRight(gemCntArr, right+1, gems, gemMap);
			
			// left, right가 -1 이라면 보석수를 모두 가질 수 없는 경우이다.
			if(left != -1 && right != -1) {
				if(minLength > right-left+1) {
					minLength = right-left+1;
					answer[0] = left;
					answer[1] = right;
				}
			}
		}
		
		answer[0]++;
		answer[1]++;
		return answer;
	}

	int moveLeftRemainOne(int[] gemCntArr, int left, int right, String[] gems, Map<String, Integer> gemMap){
		int idx;
		while(left <= right) {
			idx = gemMap.get(gems[left]);
			if(gemCntArr[idx] == 1) {
				return left;
			}
			gemCntArr[idx]--;
			left++;
		}
		// 모든 보석 선택 불가
		return -1;
	}
	
	int moveRight(int[] gemCntArr, int right, String[] gems, Map<String, Integer> gemMap){
		int idx;
		while(right < gems.length) {
			idx = gemMap.get(gems[right]);
			gemCntArr[idx]++;
			if(gemCntArr[idx]==1) {
				return right;
			}
			right++;
		}
		// 모든 보석 선택 불가
		return -1;
	}
	
	int makeDefaultWindow(int[] gemCntArr, int idx, String[] gems, Map<String, Integer> gemMap, int gemSize) {
		Set<String> set = new HashSet<>();
		for(int i=idx; i<gems.length; ++i) {
			gemCntArr[gemMap.get(gems[i])]++;
			set.add(gems[i]);
			if(set.size() == gemSize) {
				return i;
			}
		}
		return gems.length-1;
	}

	public static void main(String[] args) {
		Solution_P_L3_67258_보석쇼핑 sol = new Solution_P_L3_67258_보석쇼핑();
//		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//		String[] gems = {"AA", "AB", "AC", "AA", "AC"};
		String[] gems = {"XYZ", "XYZ", "XYZ"};
		int[] answer = sol.solution(gems);
		for(Integer ti : answer)
			System.out.println(ti);
	}
}