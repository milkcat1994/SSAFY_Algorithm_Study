import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -가장 먼 노드-
 * 가장 마지막 size를 기억하는 방식
 * 
 * 풀이 시간 : 8M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/49189
public class Solution_P_L3_49189_가장먼노드 {
	public int solution(int n, int[][] edges) {
		int answer = 0;
		List<ArrayList<Integer>> list = new ArrayList<>();
		for(int i=0; i<=n; ++i)
			list.add(new ArrayList<>());
		
		for(int[] edge : edges) {
			list.get(edge[0]).add(edge[1]);
			list.get(edge[1]).add(edge[0]);
		}
		
		boolean[] isVisited = new boolean[n+1];
		
		Queue<Integer> que = new LinkedList<>();
		que.offer(1);
		isVisited[1] = true;
		
		int size, curNode;
		while(!que.isEmpty()) {
			size = que.size();
			answer = size;
			
			while(--size>=0) {
				curNode = que.poll();
				for(Integer nodeNum : list.get(curNode)) {
					if(isVisited[nodeNum]) continue;
					isVisited[nodeNum] = true;
					que.offer(nodeNum);
					answer++;
				}
			}
		}
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_L3_49189_가장먼노드 sol = new Solution_P_L3_49189_가장먼노드();
		int n=6;
		int[][] edge = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
		int answer = sol.solution(n, edge);
		System.out.println(answer);
	}
}