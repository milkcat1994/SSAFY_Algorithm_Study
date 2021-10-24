/*
 * -최소직사각형-
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/86491
public class Solution_P_L1_86491_최소직사각형 {
	public int solution(int[][] sizes) {
		int lm = 0;
		int rm = 0;
		
		int sizeNum = sizes.length;
		for(int i=0; i<sizeNum; ++i) {
			lm = Math.max(lm, Math.min(sizes[i][0], sizes[i][1]));
			rm = Math.max(rm, Math.max(sizes[i][0], sizes[i][1]));
		}
		
		return lm * rm;
	}


	public static void main(String[] args) {
		Solution_P_L1_86491_최소직사각형 sol = new Solution_P_L1_86491_최소직사각형();
		int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
		int answer = sol.solution(sizes);
		System.out.println(answer);
	}
}