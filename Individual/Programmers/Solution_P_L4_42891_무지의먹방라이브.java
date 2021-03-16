import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * -무지의 먹방 라이브-
 * 정확성  테스트
 * 테스트 1 〉	통과 (1.09ms, 51.9MB)
 * 테스트 2 〉	통과 (1.10ms, 52.2MB)
 * 테스트 3 〉	통과 (1.02ms, 52MB)
 * 테스트 4 〉	통과 (0.94ms, 51.8MB)
 * 테스트 5 〉	통과 (1.13ms, 52.2MB)
 * 테스트 6 〉	통과 (0.98ms, 52.2MB)
 * 테스트 7 〉	통과 (5.21ms, 51.9MB)
 * 테스트 8 〉	통과 (1.00ms, 54.1MB)
 * 테스트 9 〉	통과 (1.01ms, 52.2MB)
 * 테스트 10 〉	통과 (0.97ms, 52.5MB)
 * 테스트 11 〉	통과 (1.01ms, 52.5MB)
 * 테스트 12 〉	통과 (1.04ms, 53.1MB)
 * 테스트 13 〉	통과 (0.81ms, 53MB)
 * 테스트 14 〉	통과 (1.18ms, 53MB)
 * 테스트 15 〉	통과 (1.01ms, 52.1MB)
 * 테스트 16 〉	통과 (0.83ms, 52.5MB)
 * 테스트 17 〉	통과 (0.78ms, 51.9MB)
 * 테스트 18 〉	통과 (0.98ms, 53.6MB)
 * 테스트 19 〉	통과 (0.81ms, 52.5MB)
 * 테스트 20 〉	통과 (0.81ms, 51.8MB)
 * 테스트 21 〉	통과 (1.42ms, 52.1MB)
 * 테스트 22 〉	통과 (1.30ms, 52.2MB)
 * 테스트 23 〉	통과 (1.00ms, 52.2MB)
 * 테스트 24 〉	통과 (2.43ms, 53.6MB)
 * 테스트 25 〉	통과 (4.15ms, 53.2MB)
 * 테스트 26 〉	통과 (2.70ms, 52.4MB)
 * 테스트 27 〉	통과 (4.23ms, 53.5MB)
 * 테스트 28 〉	통과 (1.14ms, 51.7MB)
 * 테스트 29 〉	통과 (1.14ms, 54.3MB)
 * 테스트 30 〉	통과 (1.02ms, 52.9MB)
 * 테스트 31 〉	통과 (1.12ms, 54MB)
 * 테스트 32 〉	통과 (1.12ms, 51.6MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (348.13ms, 93.6MB)
 * 테스트 2 〉	통과 (68.48ms, 68.8MB)
 * 테스트 3 〉	통과 (340.92ms, 89.8MB)
 * 테스트 4 〉	통과 (79.21ms, 67.1MB)
 * 테스트 5 〉	통과 (297.59ms, 87.9MB)
 * 테스트 6 〉	통과 (214.49ms, 84.7MB)
 * 테스트 7 〉	통과 (203.66ms, 85.1MB)
 * 테스트 8 〉	통과 (79.73ms, 66.7MB)
 * 
 * 풀이시간 1H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42891
public class Solution_P_L4_42891_무지의먹방라이브 {
	
	public int solution(int[] food_times, long k) {
		// time, cnt 를 HashMap으로 만들어서 같은 시간에 대한 중복 계산 삭제
		Map<Integer, Integer> hm = new HashMap<>();
		for(Integer ti : food_times) {
			if(hm.containsKey(ti))
				hm.put(ti, hm.get(ti)+1);
			else
				hm.put(ti, 1);
		}
		
		Iterator<Integer> iter = hm.keySet().iterator();
		List<Food> list = new ArrayList<>();
		int ti;
		while(iter.hasNext()) {
			ti = iter.next();
			list.add(new Food(ti, hm.get(ti)));
		}
		
		// 시간을 기준으로 오름차순으로 정렬한다.
		Collections.sort(list, new Comparator<Food>() {
			@Override
			public int compare(Food o1, Food o2) {
				return o1.time-o2.time;
			}
		});
		
		long time;
		long stackTime=0; // 그간 사용한 시간량
		long spandTime;
		int foodSize = food_times.length;
		long K = k;
		
		// 가장 적게 써야하는 것부터 빠져나온다.
		for(Food food : list) {
			// 음식 시간과 지금까지 사용한 시간의 차이로 필요한 시간량만 추출
			time = food.time-stackTime;
			spandTime = time*foodSize;
			
			// 시간(K)이 더 남아있다면 K를 빼주고, 음식 숫자조절, 그간 사용한 시간량 증가
			if(K >= spandTime) {
				K -= spandTime;
				foodSize -= food.cnt;
				stackTime += time;
			}
			// 시간이 모잘라서 남은 갯수만큼만 이동해야한다.
			else {
				// 몫을 구해서 나머지만 돌도록 진행
				long cycle = (long) Math.floor(K/foodSize);

				K -= cycle*foodSize;
				stackTime += cycle;
				
				// 남은만큼 진행할것이다.
				int originFoodSize = food_times.length;
				for(int idx = 0; idx<originFoodSize; ++idx) {
					// 0인 곳을 제외하고 지나갈것이며, K==0이면 해당 idx를 반환한다.
					if(food_times[idx] > stackTime) {
						if(K==0)
							return idx+1;
						K--;
					}
				}
			}
		}
		
		// 시간이 모두 흘렀는데 K가 남아있을경우 -1 리턴
		return -1;
	}

	public class Food {
		int time, cnt;
		Food(int time, int cnt){
			this.time = time;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) {
		Solution_P_L4_42891_무지의먹방라이브 sol = new Solution_P_L4_42891_무지의먹방라이브();
		int[] food_times = {3,1,2};
		int k = 6;
		int answer = sol.solution(food_times, k);
		System.out.println(answer);
	}
}