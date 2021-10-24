import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -전력망을 둘로 나누기-
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/86971
public class Solution_P_L2_86971_전력망을둘로나누기 {
	public int solution(int n, int[][] wires) {
		int wireNum = wires.length;
		int answer = Integer.MAX_VALUE;
		
		for(int disable = 0; disable<wireNum; ++disable) {
			List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			for(int i=0; i<=n; ++i) {
				list.add(new ArrayList<Integer>());
			}
			
			int s,e;
			for(int i = 0; i < wireNum; ++i) {
				if(disable == i) continue;
				s = wires[i][0];
				e = wires[i][1];
				
				list.get(s).add(e);
				list.get(e).add(s);
			}
	
			boolean[] isVisited = new boolean[n+1];
			Queue<Integer> que = new LinkedList<>();
			s = 1;
			isVisited[s] = true;
			que.offer(s);
			
			int cnt = 1;
			while(!que.isEmpty()) {
				s = que.poll();
				for(Integer next : list.get(s)) {
					if(isVisited[next]) continue;
					isVisited[next] = true;
					que.offer(next);
					cnt++;
				}
			}
			answer = Math.min(answer, Math.abs(cnt-(n-cnt)));
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Solution_P_L2_86971_전력망을둘로나누기 sol = new Solution_P_L2_86971_전력망을둘로나누기();
		int n = 9;
		int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
		
		int answer = sol.solution(n, wires);
		System.out.println(answer);
	}
}