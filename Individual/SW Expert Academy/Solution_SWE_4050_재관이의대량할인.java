import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIseXoKEUcDFAWN
public class Solution_SWE_4050_재관이의대량할인 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[N];
			for(int i = 0; i < N; ++i)
				arr[i] = Integer.parseInt(st.nextToken());
				
			Arrays.sort(arr);
			
			int k = 0, res=0;
			for(int n = N-1; n >= 0; --n) {
				if(k==2) {
					k=0;
					continue;
				}
				else {
					res+=arr[n];
					k++;
				}
			}
			System.out.println("#"+t+" "+res);
		}
	}
}
