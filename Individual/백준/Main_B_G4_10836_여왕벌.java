import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -여왕벌-
 * 가장 상단에서의 합이 좌측의 합보다 같거나 클 수 밖에 없는 구조.
 */

//출처 : https://www.acmicpc.net/problem/10836
public class Main_B_G4_10836_여왕벌 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int a,b,c;
		int size=2*M-1;
		int[] arr = new int[size+1];
		int[] res = new int[size];
		
		
		for(int n=0; n<N; ++n) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			arr[a]++;
			arr[a+b]++;
		}
		
		int ti=0;
		for(int i=0; i<size; ++i) {
			ti+=arr[i];
			res[i]+=ti;
		}
		
		//row 0 출력
		for(int col = M-1; col < size; ++col) {
			sb.append(res[col]+1).append(' ');
		}
		sb.append('\n');
		
		//row 1아래 출력
		for (int row = 1; row < M; ++row) {
			//col 0은 따로 출력
			sb.append(res[M-1-row]+1).append(' ');
			//col 1이상 출력
			for (int col = M; col < size; ++col) {
				sb.append(res[col]+1).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.print(sb.toString());
	}
}
