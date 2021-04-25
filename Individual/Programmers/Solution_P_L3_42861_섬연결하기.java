import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * -섬 연결하기-
 * 1. union-find
 * 
 * 풀이 시간 : 15M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42861
public class Solution_P_L3_42861_섬연결하기 {
	int[] parents;
	public int solution(int n, int[][] costs) {
		int answer=0;
		initParent(n);
		
		List<Edge> list = new ArrayList<>();
		
		for(int[] arr : costs) {
			list.add(new Edge(arr[0], arr[1], arr[2]));
		}
		
		Collections.sort(list, (e1, e2) -> Integer.compare(e1.w, e2.w));
		
		for(Edge edge : list) {
			if(union(edge.s, edge.e))
				answer+=edge.w;
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
	
	class Edge{
		int s,e,w;
		Edge(int s, int e, int w){
			this.s=s;
			this.e=e;
			this.w=w;
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_42861_섬연결하기 sol = new Solution_P_L3_42861_섬연결하기();
		int n=4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		int answer = sol.solution(n, costs);
		System.out.println(answer);
	}
}