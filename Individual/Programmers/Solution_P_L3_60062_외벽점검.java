import java.util.Arrays;
import java.util.Comparator;

/*
 * -외벽 점검-
 * 1. dfs를 통해 연결하지 않을 weak사이 구간을 선정한다.
 * 2. 연결 하지 않을 구간 선택시 bfs()를 통해  이어져야 하는 구간을 담은 list를 반환한다.
 * 3. 이어져야 하는 구간의 list를 통해 isAble()를 통해 dist로 연결 가능한지 확인한다.
 * 
 * -- 수정
 * 연결하지 않을 구간을 선택하는 것이 아니라 완전탐색으로 진행할 것.
 * Greedy하게 dist를 선정하기 위해 가장 긴 구간을 앞으로 정렬하였다.
 * weak의 시작 위치를 선정할 것이다. -> 원형임을 감안할것.
 * 
 * 테스트 1 〉	통과 (4.05ms, 53.2MB)
 * 테스트 2 〉	통과 (6.27ms, 52.5MB)
 * 테스트 3 〉	통과 (291.77ms, 52.4MB)
 * 테스트 4 〉	통과 (95.29ms, 52.4MB)
 * 테스트 5 〉	통과 (14.81ms, 52.9MB)
 * 테스트 6 〉	통과 (93.77ms, 52.5MB)
 * 테스트 7 〉	통과 (4.69ms, 52.6MB)
 * 테스트 8 〉	통과 (4.27ms, 55.8MB)
 * 테스트 9 〉	통과 (4.53ms, 52.1MB)
 * 테스트 10 〉	통과 (1091.41ms, 52.9MB)
 * 테스트 11 〉	통과 (1541.07ms, 52.4MB)
 * 테스트 12 〉	통과 (1370.02ms, 53.1MB)
 * 테스트 13 〉	통과 (3188.12ms, 53.2MB)
 * 테스트 14 〉	통과 (1903.64ms, 53.1MB)
 * 테스트 15 〉	통과 (838.68ms, 52.4MB)
 * 테스트 16 〉	통과 (35.71ms, 52.5MB)
 * 테스트 17 〉	통과 (127.28ms, 52MB)
 * 테스트 18 〉	통과 (57.83ms, 52.8MB)
 * 테스트 19 〉	통과 (5.81ms, 54.8MB)
 * 테스트 20 〉	통과 (14.20ms, 52.6MB)
 * 테스트 21 〉	통과 (4.94ms, 52.2MB)
 * 테스트 22 〉	통과 (10.59ms, 52.4MB)
 * 테스트 23 〉	통과 (5.80ms, 53.5MB)
 * 테스트 24 〉	통과 (11.95ms, 52.6MB)
 * 테스트 25 〉	통과 (7.28ms, 52.3MB)
 * 
 * 풀이 시간 : 2H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/60062
public class Solution_P_L3_60062_외벽점검 {
	final int INF = Integer.MAX_VALUE;
	int minRes = INF;
	
	public int solution(int n, int[] weak, int[] dist) {
		// 내림 차순 정리
		// int 는 primitives type 이기에 sort시 Comparator를 지원하지 않는다.
		// Integer로 바꾸거나 아래와 같이 사용해야 한다.
		dist = Arrays.stream(dist).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
		
		int weakLength = weak.length;
		// weak 원형임을 감안하여 새로운 배열 생성
		int[] newWeak = new int[weakLength*2];
		for(int i=0; i<weakLength; ++i) {
			newWeak[i] = weak[i];
			newWeak[i+weakLength] = weak[i]+n;
		}
		
		// weak선택
		dfs(newWeak, dist, 0, weakLength, 0);
		
		return minRes == INF ? -1 : minRes;
	}
	
	// weak배열, 선택된 weak
	void dfs(int[] newWeak, int[] dist, int selected, int weakLength, int dIdx) {
		if(selected == (1<<weakLength) -1) {
			minRes = Math.min(minRes, dIdx);
			return;
		}
		
		// 실패
		if(dIdx == dist.length) {
			return;
		}
		
		int backupSelected;
		for(int i=0; i<weakLength; ++i) {
			if((selected & 1<<i) > 0) continue;
			backupSelected = selected;
			
			// 시작 위치 i에서 j만큼 떨어진 길이까지 해당 dist가 커버가 가능한지 확인한다.
			for(int j=0; j<weakLength; ++j) {
				int idx = i+j;
				// newWeak는 weak의 두배이며, 방문관리는 weakLength로 하기에 나머지 연산 실행
				if((selected & 1<<(idx%weakLength)) > 0) continue;
				
				int length = newWeak[idx] - newWeak[i];
				// 해당 길이를 해당 dist가 커버할 수 있다면 해당 weak는 해당 dIdx에서 선택됨
				if(length <= dist[dIdx]) {
					selected |= (1<<(idx%weakLength));
				}
				else
					break;
			}
			
			// dist에 의해 선택될 수 있는 weak라면 selected해주기
			dfs(newWeak, dist, selected, weakLength, dIdx+1);
			
			// weak 선택 백업
			selected = backupSelected;
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_60062_외벽점검 sol = new Solution_P_L3_60062_외벽점검();
//		int n = 12;
//		int[] weak = {1,5,6,10};
//		int[] dist = {1,2,3,4};
		int n = 200;
		int[] weak = {0, 10, 50, 80, 120, 160};
		int[] dist = {1, 10, 5, 40, 30};
		int answer = sol.solution(n, weak, dist);
		System.out.println(answer);
	}
}