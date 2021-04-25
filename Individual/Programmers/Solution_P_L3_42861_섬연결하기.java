import java.util.Arrays;

/*
 * -섬 연결하기-
 * 1. union-find
 * 2. List 대신 costs를 변결하면 간단
 * 
 * 풀이 시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42861
public class Solution_P_L3_42861_섬연결하기 {
	int[] parents;
	public int solution(int n, int[][] costs) {
		int answer=0;
		int[][] costsCopy = new int[costs.length][3];
		for(int i=0; i<costs.length; ++i) {
			costsCopy[i] = costs[i].clone();
		}
		
		initParent(n);
		
		Arrays.sort(costsCopy, (e1, e2) -> Integer.compare(e1[2], e2[2]));
		
		for(int idx=0; idx<costsCopy.length; ++idx) {
			if(union(costsCopy[idx][0], costsCopy[idx][1]))
				answer+=costsCopy[idx][2];
		}
		
		return answer;
	}
	
	void initParent(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}
	
	int getParent(int x) {
		if(parents[x]==-1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa==pb) return false;
		
		parents[pa] = pb;
		return true;
	}

	public static void main(String[] args) {
		Solution_P_L3_42861_섬연결하기 sol = new Solution_P_L3_42861_섬연결하기();
		int n=4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		int answer = sol.solution(n, costs);
		System.out.println(answer);
	}
}