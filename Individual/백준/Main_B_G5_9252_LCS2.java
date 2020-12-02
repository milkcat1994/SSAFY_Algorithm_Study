import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * -LCS2-
 * LCS 길이 및 Tracking
 * 참조 : https://mygumi.tistory.com/126
 */

//출처 : https://www.acmicpc.net/problem/9252
public class Main_B_G5_9252_LCS2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
		
		int al = a.length(), bl = b.length();
		
		int[][] arr = new int[al+1][bl+1];
		
		for (int row = 1; row <= al; ++row) {
			for (int col = 1; col <= bl; ++col) {
				if(a.charAt(row-1) == b.charAt(col-1)) {
					arr[row][col] = arr[row-1][col-1] + 1;
				}
				else {
					arr[row][col] = Math.max(arr[row-1][col], arr[row][col-1]);
				}
			}
		}
		
		
		if(arr[al][bl] == 0) {
			System.out.println(0);
		}
		else {
			Stack<Integer> stack = new Stack<>();
			int temp = arr[al][bl];
			int cr=al,cc=bl;
			
			while(arr[cr][cc] != 0) {
				while(arr[cr-1][cc] == temp || arr[cr][cc-1] == temp) {
					if(arr[cr-1][cc] == temp) {
						cr--;
					}
					else {
						cc--;
					}
				}
				stack.push(cr-1);
				cr--; cc--;
				temp--;
			}
			
			System.out.println(arr[al][bl]);
			while(!stack.isEmpty()) {
				System.out.print(a.charAt(stack.pop()));
			}
		}
	}
}
