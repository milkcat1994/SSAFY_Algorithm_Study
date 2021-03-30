import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -외벽 점검-
 * 1. dfs를 통해 연결하지 않을 weak사이 구간을 선정한다.
 * 2. 연결 하지 않을 구간 선택시 bfs()를 통해  이어져야 하는 구간을 담은 list를 반환한다.
 * 3. 이어져야 하는 구간의 list를 통해 isAble()를 통해 dist로 연결 가능한지 확인한다.
 * 
 * 
 * 테스트 1 〉	통과 (5.41ms, 52.9MB)
 * 테스트 2 〉	통과 (5.60ms, 51.8MB)
 * 테스트 3 〉	통과 (14.92ms, 54.9MB)
 * 테스트 4 〉	통과 (7.75ms, 52.3MB)
 * 테스트 5 〉	통과 (9.44ms, 52.9MB)
 * 테스트 6 〉	통과 (136.55ms, 70MB)
 * 테스트 7 〉	통과 (1.32ms, 53.8MB)
 * 테스트 8 〉	통과 (8.35ms, 52MB)
 * 테스트 9 〉	통과 (8.47ms, 52.7MB)
 * 테스트 10 〉	통과 (127.16ms, 68.6MB)
 * 테스트 11 〉	통과 (127.98ms, 70MB)
 * 테스트 12 〉	통과 (118.13ms, 68.5MB)
 * 테스트 13 〉	통과 (121.49ms, 69.7MB)
 * 테스트 14 〉	통과 (145.86ms, 73.4MB)
 * 테스트 15 〉	통과 (91.50ms, 67.7MB)
 * 테스트 16 〉	통과 (89.35ms, 67.4MB)
 * 테스트 17 〉	통과 (100.24ms, 68.2MB)
 * 테스트 18 〉	통과 (102.21ms, 68.6MB)
 * 테스트 19 〉	통과 (108.83ms, 67.4MB)
 * 테스트 20 〉	통과 (94.00ms, 67.4MB)
 * 테스트 21 〉	통과 (114.08ms, 68.6MB)
 * 테스트 22 〉	통과 (10.41ms, 52.5MB)
 * 테스트 23 〉	통과 (10.75ms, 53.3MB)
 * 테스트 24 〉	통과 (17.88ms, 53.5MB)
 * 테스트 25 〉	통과 (1.78ms, 51.9MB)
 * 
 * 풀이 시간 : 2H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/60062
public class Solution_P_L3_60062_외벽점검 {
	
	int minRes = Integer.MAX_VALUE;
	public int solution(int n, int[] weak, int[] dist) {
		//오름차순 정리
		Arrays.sort(dist);
		
		int weakLength = weak.length;
		Point[] pointArr = new Point[weakLength];
		for(int i=0; i<weakLength-1; ++i) {
			pointArr[i] = new Point(weak[i+1] - weak[i], i);
		}
		pointArr[weakLength-1] = new Point(n-weak[weakLength-1] + weak[0], weakLength-1);
		
		boolean[] isUse = new boolean[weakLength];
		Arrays.fill(isUse, true);

		dfs(isUse, weakLength, dist, pointArr, 0, 1);
		
		return minRes == Integer.MAX_VALUE ? -1 : minRes;
	}
	
	void dfs(boolean[] isUse, int weakLength, int[] dist, Point[] pointArr, int idx, int cnt) {
		for(int i=idx; i<weakLength; ++i) {
			if(!isUse[i]) continue;
			isUse[i] = false;
			boolean[] isUseWeakIdx = new boolean[weakLength];

			List<Integer> needLengthList;
			needLengthList = bfs(weakLength, isUseWeakIdx, isUse, pointArr);
			
			if(isAble(needLengthList, dist, weakLength, isUseWeakIdx)) {
				// list의 빠지는 개수에 비한 수
				minRes = Math.min(minRes, cnt);
			}
			
			dfs( isUse, weakLength, dist, pointArr, i, cnt+1);
			
			isUse[i] = true; 
		}
	}
	
	List<Integer> bfs(int weakLength, boolean[] isUseWeakIdx, boolean[] isUse, Point[] pointArr){
		Queue<Point> que = new LinkedList<>();
		Point tp;
		// weak사이 거리 사용여부
		boolean[] isVisited = new boolean[weakLength];
		// weak 사용여부

		List<Integer> needLengthList = new ArrayList<>();
		int leftIdx,rightIdx;
		int length = 0;
		for(int i=0; i<weakLength; ++i) {
			Point point = pointArr[i];
			
			if(!isUse[point.idx] || isVisited[point.idx])continue;
			tp = point;
			que.offer(tp);
			isVisited[tp.idx] = true;
			isUseWeakIdx[tp.idx%weakLength] = true;
			isUseWeakIdx[(tp.idx+1)%weakLength] = true;
			length  = tp.num;
			
			while(!que.isEmpty()) {
				tp = que.poll();
				// 시계 반시계 이동 가능 및 연결 여부 확인
				leftIdx = (tp.idx-1+weakLength)%weakLength;
				rightIdx = (tp.idx+1)%weakLength;
				if(isUse[leftIdx] && !isVisited[leftIdx]) {
					isVisited[leftIdx] = true;
					isUseWeakIdx[leftIdx%weakLength] = true;
					isUseWeakIdx[(leftIdx+1)%weakLength] = true;
					que.offer(pointArr[leftIdx]);
					length += pointArr[leftIdx].num;
				}
				
				if(isUse[rightIdx] && !isVisited[rightIdx]) {
					isVisited[rightIdx] = true;
					isUseWeakIdx[rightIdx%weakLength] = true;
					isUseWeakIdx[(rightIdx+1)%weakLength] = true;
					que.offer(pointArr[rightIdx]);
					length += pointArr[rightIdx].num;
				}
			}
			
			// 이어져야하는 길이 보관 위한 List 관리
			needLengthList.add(length);
		}
		return needLengthList;
	}
	
	boolean isAble(List<Integer> needLengthList, int[] dist, int weakLength, boolean[] isUseWeakIdx) {
		// 작은 길이 부터 채워나갈 수 있는지 확인한다.
		int needDistCnt = needLengthList.size();
		int curDistCnt=0;
		Collections.sort(needLengthList);
		boolean[] isSelected = new boolean[dist.length];
		int startIdx=0;
		for(Integer ti : needLengthList) {
			if(curDistCnt == needDistCnt)
				break;
			
			for(int i=startIdx; i<dist.length; ++i) {
				if(isSelected[i]) continue;
				// 설치 가능하다면
				if(ti <= dist[i]) {
					isSelected[i] = true;
					startIdx=i+1;
					curDistCnt++;
					break;
				}
			}
		}
		
		if(curDistCnt == needDistCnt) {
			int remainDist=0;
			for(int i=0; i<dist.length; ++i) {
				if(!isSelected[i]) {
					remainDist++;
				}
			}
			
			int remainWeak=0;
			for(int i=0; i<weakLength; ++i) {
				if(!isUseWeakIdx[i]) {
					remainWeak++;
				}
			}
			// 남은 dist가 남은 weak를 채울 수 있다면 성공
			if(remainWeak <= remainDist) {
				//성공
				return true;
			}
			else {
				// 실패
				return false;
			}
		}
		else {
			// 길이 채울 수 없다면 실패
			return false;
		}
	}
	
	class Point {
		int num,idx;

		public Point(int num, int idx) {
			this.num = num;
			this.idx = idx;
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