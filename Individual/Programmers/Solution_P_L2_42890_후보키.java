import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * -후보키-
 * 테스트 1 〉	통과 (14.63ms, 53.1MB)
 * 테스트 2 〉	통과 (15.40ms, 52.7MB)
 * 테스트 3 〉	통과 (15.43ms, 53.3MB)
 * 테스트 4 〉	통과 (15.74ms, 52.7MB)
 * 테스트 5 〉	통과 (17.72ms, 53.4MB)
 * 테스트 6 〉	통과 (14.45ms, 53.2MB)
 * 테스트 7 〉	통과 (14.63ms, 53.2MB)
 * 테스트 8 〉	통과 (13.47ms, 52.9MB)
 * 테스트 9 〉	통과 (20.25ms, 53.9MB)
 * 테스트 10 〉	통과 (15.93ms, 53.4MB)
 * 테스트 11 〉	통과 (19.28ms, 53.4MB)
 * 테스트 12 〉	통과 (33.99ms, 54.6MB)
 * 테스트 13 〉	통과 (25.56ms, 54.8MB)
 * 테스트 14 〉	통과 (16.16ms, 52.9MB)
 * 테스트 15 〉	통과 (23.54ms, 53MB)
 * 테스트 16 〉	통과 (28.40ms, 53.3MB)
 * 테스트 17 〉	통과 (15.34ms, 52.9MB)
 * 테스트 18 〉	통과 (42.00ms, 54.5MB)
 * 테스트 19 〉	통과 (34.76ms, 55MB)
 * 테스트 20 〉	통과 (43.38ms, 55.1MB)
 * 테스트 21 〉	통과 (49.91ms, 55.6MB)
 * 테스트 22 〉	통과 (38.24ms, 54.8MB)
 * 테스트 23 〉	통과 (21.36ms, 53.1MB)
 * 테스트 24 〉	통과 (30.12ms, 53.6MB)
 * 테스트 25 〉	통과 (42.48ms, 55.8MB)
 * 테스트 26 〉	통과 (44.84ms, 55.1MB)
 * 테스트 27 〉	통과 (22.32ms, 53.2MB)
 * 테스트 28 〉	통과 (25.18ms, 53.8MB)
 * 풀이 시간 : 1H 40M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42890
public class Solution_P_L2_42890_후보키 {
	static List<String[]> isAlreadyCandidateList;
	static int maxCol, maxRow;
	static int res;
	
	public int solution(String[][] relation) {
		maxCol = relation[0].length;
		maxRow = relation.length;
		isAlreadyCandidateList = new ArrayList<>();
		
		for(int max=1; max<=maxCol; ++max) {
			combination(0,max,0,relation, "");
		}
		return res;
	}
	
	public void combination(int cnt, int maxCnt, int startIdx, String[][] relation, String selectString) {
		if(cnt==maxCnt) {
			if(isDuplicate(selectString)) return;
			
			if(isCandidate(relation, selectString)) {
				res++;
				isAlreadyCandidateList.add(selectString.split(""));
			}
			return;
		}
		
		for(int idx=startIdx; idx<maxCol; ++idx) {
			
			combination(cnt+1, maxCnt, idx+1, relation, selectString+idx);
		}
	}
	
	public boolean isDuplicate(String selectString) {
		String[] selectStringArr = selectString.split("");
		
		for(String[] stringArr : isAlreadyCandidateList) {
			int cnt=0;
			for(int i=0; i<stringArr.length; ++i) {
				for(String str : selectStringArr) {
					if(str.equals(stringArr[i]))
						cnt++;
				}
			}
			
			if(cnt==stringArr.length)
				return true;
		}
		return false;
	}
	
	public boolean isCandidate(String[][] relation, String selectString) {
		Set<String> set = new HashSet<>();
		
		StringBuilder sb = new StringBuilder();
		for(int row=0; row<maxRow; ++row) {
			sb.setLength(0);
			for(String selectCol : selectString.split("")) {
				sb.append(relation[row][Integer.parseInt(selectCol)]+" ");
			}
			if(!set.add(sb.toString()))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Solution_P_L2_42890_후보키 sol = new Solution_P_L2_42890_후보키();
		String[][] strArr = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","1"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
//		String[][] strArr = {{"100", "ryan", "music"}, {"200", "apeach", "music"}, {"100", "apeach", "music"}, {"100", "ryan", "math"},{"200", "tube", "music"}};
		int answer = sol.solution(strArr);
		System.out.println(answer);
	}
}