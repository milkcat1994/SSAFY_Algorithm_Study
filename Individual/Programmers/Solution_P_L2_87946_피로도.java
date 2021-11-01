/*
 * -피로도-
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/87946
public class Solution_P_L2_87946_피로도 {
	
	public int solution(int k, int[][] dungeons) {
		return dfs(k, 0, dungeons.length, dungeons, 0);
	}
	
	public int dfs(int k, int depth, int dungeonLength, int[][] dungeons, int visited) {
		int max = depth;
		
		for(int i=0; i<dungeonLength; ++i) {
			if((1<<i & visited) > 0) continue;
			
			if(k < dungeons[i][0]) continue;
			
			max = Math.max(max, dfs(k - dungeons[i][1], depth+1, dungeonLength, dungeons, visited | 1<<i));
		}
		
		return max;
	}

	public static void main(String[] args) {
		Solution_P_L2_87946_피로도 sol = new Solution_P_L2_87946_피로도();
		int k=80;
		int[][] dungeons = {{80,20},{50,40},{30,10}};
		int answer = sol.solution(k, dungeons);
		System.out.println(answer);
	}
}