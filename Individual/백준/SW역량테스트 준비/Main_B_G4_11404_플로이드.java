import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//출처 : https://www.acmicpc.net/problem/11404
public class Main_B_G4_11404_플로이드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] D = new int[n][n];
		int max = Integer.MAX_VALUE;
		for(int i = 0; i < n; ++i)
			Arrays.fill(D[i], max);
		
		int u,v,w=0;
		for(int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken())-1;
			v = Integer.parseInt(st.nextToken())-1;
			w = Integer.parseInt(st.nextToken());
			if(D[u][v] > w) D[u][v] = w;
		}
		
		for(int k = 0; k < n; ++k) {
			for(int i = 0; i < n; ++i) {
				if(i == k) continue;
				for(int j = 0; j < n; ++j) {
					if(k == j || i == j) continue;
					
					if(D[i][k] == max || D[k][j] == max || D[i][j] <= D[i][k] + D[k][j]) continue;
						D[i][j] = D[i][k] + D[k][j];
				}
			}
		}

		for (int row = 0; row < n; ++row) {
			for (int col = 0; col < n; ++col) {
				sb.append(D[row][col] == max ? 0 : D[row][col]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
