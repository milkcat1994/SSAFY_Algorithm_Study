import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -운동-
 * 플루이드 워셜 이용한 최소값 저장 및, 사이클 판단
 */

//출처 : https://www.acmicpc.net/problem/1956
public class Main_B_G4_1956_운동 {
	static final int max = 10000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V,E;
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[V][V];
		for (int row = 0; row < V; ++row) {
			for (int col = 0; col < V; ++col) {
				map[row][col] = max;
			}
		}
		
		int start,end,weight;
		for(int e = 0; e < E; ++e) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken())-1;
			end = Integer.parseInt(st.nextToken())-1;
			weight = Integer.parseInt(st.nextToken());
			map[start][end] = weight;
		}
		
		for (int k = 0; k< V; ++k) {
			for (int i = 0; i < V; ++i) {
				for (int j = 0; j < V; ++j) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}

		int minWeight = Integer.MAX_VALUE;
		for (int k = 0; k< V; ++k) {
			for (int i = k+1; i < V; ++i) {
				if(map[i][k] == max || map[k][i] == max) continue;
				minWeight = Math.min(map[i][k]+map[k][i], minWeight);
			}
		}
		if(minWeight == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(minWeight);
	}
}
