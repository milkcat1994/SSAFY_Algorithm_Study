import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//출처 : https://www.acmicpc.net/problem/6987
public class Main_B_S3_6987_월드컵 {
	static int[][] score;
	static int ROW, COL;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ROW = 6;	COL = 3;
		for (int i = 0; i < 4; ++i) {
			score = new int[6][3];
			st = new StringTokenizer(br.readLine(), " ");
			int tot = 0;
			for (int row = 0; row < ROW; ++row) {
				for (int col = 0; col < COL; ++col) {
					score[row][col] = Integer.parseInt(st.nextToken());
					tot+=score[row][col];
				}
			}
			if (tot == 30 && dfs(0, 1))
				System.out.print(1 + " ");
			else
				System.out.print(0 + " ");
		}
	}// end Main

	static boolean dfs(int a, int b) {
		if (b == 6) {
			if(score[a][0] != 0 || score[a][1] != 0 || score[a][2] != 0)
				return false;
			a++;
			b = a + 1;
			if (a == 5) {
				return true;
			}
		}
		
		for(int j = 0; j < 3; ++j) {
			if(score[a][j]>0 && score[b][2-j]>0) {
				score[a][j]--;	score[b][2-j]--;
				if(dfs(a,b+1)) return true;
				score[a][j]++;	score[b][2-j]++;
			}
		}
		return false;
	}
}