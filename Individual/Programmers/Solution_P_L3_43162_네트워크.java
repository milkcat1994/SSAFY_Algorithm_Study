import java.util.Arrays;

/*
 * -네트워크-
 * 테스트 1 〉	통과 (0.04ms, 51.7MB)
 * 테스트 2 〉	통과 (0.03ms, 52.8MB)
 * 테스트 3 〉	통과 (0.09ms, 53MB)
 * 테스트 4 〉	통과 (0.08ms, 52.4MB)
 * 테스트 5 〉	통과 (0.03ms, 52.3MB)
 * 테스트 6 〉	통과 (0.32ms, 52.4MB)
 * 테스트 7 〉	통과 (0.05ms, 53.5MB)
 * 테스트 8 〉	통과 (0.20ms, 52.9MB)
 * 테스트 9 〉	통과 (0.11ms, 52.2MB)
 * 테스트 10 〉	통과 (0.46ms, 53.4MB)
 * 테스트 11 〉	통과 (1.54ms, 54MB)
 * 테스트 12 〉	통과 (0.51ms, 52.7MB)
 * 테스트 13 〉	통과 (0.30ms, 53.1MB)
 * 
 * 풀이시간 : 5M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/43162
public class Solution_P_L3_43162_네트워크 {
	int[] parents;
	
	public int solution(int n, int[][] computers) {
		int answer = 0;
		initParents(n);
		
		for (int row = 0; row < n; ++row) {
			for (int col = 0; col < n; ++col) {
				if(computers[row][col] == 1)
					union(row, col);
			}
		}
		
		for(int i=0; i<n; ++i)
			if(parents[i]==-1) answer++;
		return answer;
	}
	
	public void initParents(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}

	public int getParent(int x) {
		if(parents[x]==-1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	public boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa==pb) return false;
		
		parents[pa] = pb;
		return true;
	}

	public static void main(String[] args) {
		Solution_P_L3_43162_네트워크 sol = new Solution_P_L3_43162_네트워크();
		int n=3;
		int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};
		int answer = sol.solution(n, computers);
		System.out.println(answer);
	}
}