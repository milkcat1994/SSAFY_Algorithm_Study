import java.util.LinkedList;
import java.util.Queue;

/*
 * -캐시-
 * 
 * 테스트 1 〉	통과 (0.18ms, 52.7MB)
 * 테스트 2 〉	통과 (0.17ms, 54.2MB)
 * 테스트 3 〉	통과 (0.18ms, 52.1MB)
 * 테스트 4 〉	통과 (0.20ms, 52.2MB)
 * 테스트 5 〉	통과 (0.14ms, 53.1MB)
 * 테스트 6 〉	통과 (0.11ms, 52.1MB)
 * 테스트 7 〉	통과 (0.16ms, 52.1MB)
 * 테스트 8 〉	통과 (0.19ms, 52.8MB)
 * 테스트 9 〉	통과 (0.18ms, 52.4MB)
 * 테스트 10 〉	통과 (0.37ms, 51.9MB)
 * 테스트 11 〉	통과 (64.01ms, 83.3MB)
 * 테스트 12 〉	통과 (0.42ms, 51.9MB)
 * 테스트 13 〉	통과 (0.76ms, 52.3MB)
 * 테스트 14 〉	통과 (1.14ms, 53.4MB)
 * 테스트 15 〉	통과 (1.23ms, 52MB)
 * 테스트 16 〉	통과 (3.29ms, 52.6MB)
 * 테스트 17 〉	통과 (2.41ms, 53.7MB)
 * 테스트 18 〉	통과 (3.60ms, 53.5MB)
 * 테스트 19 〉	통과 (4.45ms, 52.5MB)
 * 테스트 20 〉	통과 (4.13ms, 53.8MB)
 * 
 * 풀이 시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17680
public class Solution_P_L2_17680_캐시 {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		
		Queue<String> que = new LinkedList<>();
		for(String city : cities) {
			city = city.toLowerCase();
			if(que.contains(city)) {
				answer+=cacheHit(que, city);
			}
			else {
				answer+=cacheMiss(que, city, cacheSize);
			}
		}
		return answer;
	}
	
	int cacheHit(Queue<String> que, String city){
		que.remove(city);
		que.offer(city);
		return 1;
	}
	
	int cacheMiss(Queue<String> que, String city, int cacheSize) {
		if(cacheSize <= que.size())
			que.poll();
		if(que.size() < cacheSize)
			que.offer(city);
		return 5;
	}


	public static void main(String[] args) {
		Solution_P_L2_17680_캐시 sol = new Solution_P_L2_17680_캐시();
//		int cacheSize = 3;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		int cacheSize = 2;
		String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
		int answer = sol.solution(cacheSize, cities);
		System.out.println(answer);
	}
}