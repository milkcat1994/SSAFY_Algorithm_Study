import java.util.Arrays;

/*
 * -[1차] 추석 트래픽-
 * 테스트 1 〉	통과 (2.48ms, 52.8MB)
 * 테스트 2 〉	통과 (63.36ms, 59MB)
 * 테스트 3 〉	통과 (62.14ms, 59.9MB)
 * 테스트 4 〉	통과 (1.93ms, 52.6MB)
 * 테스트 5 〉	통과 (12.15ms, 53.1MB)
 * 테스트 6 〉	통과 (12.17ms, 54.1MB)
 * 테스트 7 〉	통과 (59.04ms, 59.3MB)
 * 테스트 8 〉	통과 (56.75ms, 59.4MB)
 * 테스트 9 〉	통과 (9.43ms, 52.5MB)
 * 테스트 10 〉	통과 (2.52ms, 52.1MB)
 * 테스트 11 〉	통과 (1.45ms, 53.3MB)
 * 테스트 12 〉	통과 (63.30ms, 60.5MB)
 * 테스트 13 〉	통과 (9.35ms, 52.7MB)
 * 테스트 14 〉	통과 (2.10ms, 53.1MB)
 * 테스트 15 〉	통과 (2.05ms, 53.4MB)
 * 테스트 16 〉	통과 (3.75ms, 52.2MB)
 * 테스트 17 〉	통과 (3.82ms, 51.8MB)
 * 테스트 18 〉	통과 (93.45ms, 60.8MB)
 * 테스트 19 〉	통과 (107.56ms, 62.8MB)
 * 테스트 20 〉	통과 (97.22ms, 62.5MB)
 * 테스트 21 〉	통과 (0.63ms, 53.9MB)
 * 테스트 22 〉	통과 (0.69ms, 54.1MB)
 * 
 * 풀이 시간 : 45M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17676
public class Solution_P_L3_17676_추석트래픽 {
	public int solution(String[] lines) {
		int length = lines.length;
		String[] times = new String[length];
		String[] terms = new String[length];
		int[] endTimeArr = new int[length];
		int[] termArr = new int[length];
		int[] answer = new int[length];
		
		String[] tempStrArr;
		for(int i=0; i<length; ++i) {
			tempStrArr = lines[i].split(" ");
			times[i] = tempStrArr[1];
			terms[i] = tempStrArr[2];
		}
		
		int[] multiple = {3600000, 60000, 1000, 1};
		for(int i=0; i<length; ++i) {
			tempStrArr = times[i].split("[:\\.]");
			for(int j=0; j<4; ++j) {
				endTimeArr[i] += Integer.parseInt(tempStrArr[j]) * multiple[j];
			}
			tempStrArr = terms[i].split("[\\.s]");
			for(int j=0; j<tempStrArr.length; ++j) {
				termArr[i] += Integer.parseInt(tempStrArr[j]) * multiple[j+2];
			}
		}
		
		int maxTime, startTime;
		for(int i=0; i<length; ++i) {
			maxTime = endTimeArr[i]+1000-1;
			for(int j=i+1; j<length; ++j) {
				startTime = endTimeArr[j] -termArr[j]+ 1;
				if((startTime <= maxTime && maxTime <= endTimeArr[j]) || startTime <= maxTime)
					answer[i]++;
			}
		}
		Arrays.sort(answer);
		
		return answer[answer.length-1]+1;
	}


	public static void main(String[] args) {
		Solution_P_L3_17676_추석트래픽 sol = new Solution_P_L3_17676_추석트래픽();
//		String[] lines = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
		String[] lines = {"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
				"2016-09-15 21:00:02.066 2.62s"};
		int answer = sol.solution(lines);
		System.out.println(answer);
	}
}
