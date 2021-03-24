import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * -디스크 컨트롤러-
 * 
 * 비선점 스케쥴링인 SJF(Shortest Job First) 이용
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42627
public class Solution_P_L3_42627_디스크컨트롤러 {
	public int solution(int[][] jobs) {
		int answer = 0;
		Arrays.sort(jobs, (o1, o2) -> {
			return o1[0]-o2[0];
		});
		Queue<int[]> que = new LinkedList<>();
		for(int[] job : jobs)
			que.offer(job);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[1]-o2[1];
		});
		
		int time = 0;
		int[] job;
		while(!que.isEmpty() || !pq.isEmpty()) {
			while(!que.isEmpty() && que.peek()[0] <= time) {
				pq.offer(que.poll());
			}
			
			if(pq.isEmpty()) {
				time = que.peek()[0];
				while(!que.isEmpty() && que.peek()[0] <= time) {
					pq.offer(que.poll());
				}
			}
			
			job = pq.poll();
			time = time < job[0] ? job[0]+job[1] : time+job[1];
			answer += time-job[0];
		}
		
		return answer/jobs.length;
	}


	public static void main(String[] args) {
		Solution_P_L3_42627_디스크컨트롤러 sol = new Solution_P_L3_42627_디스크컨트롤러();
		int[][] jobs = {{0,3}, {1,9}, {2,6}};
		int answer = sol.solution(jobs);
		System.out.println(answer);
	}
}