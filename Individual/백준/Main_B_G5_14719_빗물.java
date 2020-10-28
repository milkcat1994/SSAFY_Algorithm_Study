import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -빗물-
 */

//출처 : https://www.acmicpc.net/problem/14719
public class Main_B_G5_14719_빗물 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		st.nextToken();
		int W = Integer.parseInt(st.nextToken());
		int[] arr = new int[W];
		int maxH = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<W; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxH = arr[i] > maxH ? arr[i] : maxH;
		}
		
		boolean isIn;
		int ts, res=0;
		for (int row = 1; row <= maxH; ++row) {
			isIn=false;
			ts=0;
			for (int col = 0; col < W; ++col) {
				if(!isIn) {
					if(arr[col] >= row) {
						isIn=true;
					}
				}
				else {
					if(arr[col] < row) {
						ts++;
					}
					else if(arr[col] >= row && ts!=0) {
						res+=ts;
						ts=0;
					}
				}
			}
		}
		System.out.println(res);
	}
}
