import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * -좋은 수열-
 */

//출처 : https://www.acmicpc.net/problem/2661
public class Main_B_G4_2661_좋은수열 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		dfs(0, 0);
		for(int i = 0; i < N; ++i)
			System.out.print(arr[i]);
	}
	
	static boolean dfs(int cnt, int num) {
		//좋은 수열인지 확인
		boolean isGood = true;
		//끝에서 부터 cnt/2까지 중복되는 부분이 있는지 확인.
		for(int i = 1; i <= (cnt/2); ++i) {
			for(int j = cnt-1; j > cnt-1-i; --j) {
				if(arr[j-i] != arr[j]) {
					isGood = false;
					break;
				}
			}
			if(isGood) return false;
			isGood = true;
		}
		
		//좋은 수열이며, 끝까지 다 보았다면 성공.
		if(cnt == N) {
			return true;
		}
		
		// 이전과 같은 수는 고르지 않는다.
		for(int i = 1; i < 4; ++i) {
			if(i == num) continue;
			arr[cnt] = i;
			if(dfs(cnt+1, i)) return true;
		}
		
		return false;
	}
}
