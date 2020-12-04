import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -LCS3-
 * 기본 LCS 길이를 구하는 2차원에서 3차원으로 변경
 */

//출처 : https://www.acmicpc.net/problem/1958
public class Main_B_G3_1958_LCS3 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		String c = br.readLine();
		
		int al = a.length(), bl = b.length(), cl = c.length();
		
		int[][][] arr = new int[al+1][bl+1][cl+1];
		for (int row = 1; row <= al; ++row) {
			for (int col = 1; col <= bl; ++col) {
				for(int depth = 1; depth <= cl; ++depth) {
					if(a.charAt(row-1) == b.charAt(col-1) && b.charAt(col-1) == c.charAt(depth-1)) {
						arr[row][col][depth] = arr[row-1][col-1][depth-1] + 1;
					}
					else {
						arr[row][col][depth] = getMax(arr[row-1][col][depth], arr[row][col-1][depth], arr[row][col][depth-1]);
					}
				}
			}
		}
		System.out.println(arr[al][bl][cl]);
	}
	
	static int getMax(int row, int col, int depth) {
		return Math.max(Math.max(row, col), depth);
	}
}