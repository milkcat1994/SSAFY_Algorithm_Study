import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -LCS-
 * LCS(Longest Common Subsequence) 길이 측정
 */

//출처 : https://www.acmicpc.net/problem/9251
public class Main_B_G5_9251_LCS {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
		
		int al = a.length(), bl = b.length();
		int[][] arr = new int[al+1][bl+1];
		
		for (int row = 1; row <= al; ++row) {
			for (int col = 1; col <= bl; ++col) {
				if(a.charAt(row-1) == b.charAt(col-1)) {
					arr[row][col] = arr[row-1][col-1]+1;
				}
				else {
					arr[row][col] = Math.max(arr[row-1][col], arr[row][col-1]);
				}
			}
		}
		System.out.println(arr[al][bl]);
	}
}
