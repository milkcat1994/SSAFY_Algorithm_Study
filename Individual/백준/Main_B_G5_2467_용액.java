import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -용액-
 */

//출처 : https://www.acmicpc.net/problem/2467
public class Main_B_G5_2467_용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int lr=0,rr=N-1, li=0,ri=N-1, res = Integer.MAX_VALUE, sum;
		
		while(li<ri) {
			sum = arr[li]+arr[ri];
			if(Math.abs(sum) < Math.abs(res)) {
				lr = li;
				rr = ri;
				res = sum;
			}
			if(sum < 0) {
				li++;
			}
			else {
				ri--;
			}
		}
		
		System.out.println(arr[lr] + " " + arr[rr]);
	}
}
