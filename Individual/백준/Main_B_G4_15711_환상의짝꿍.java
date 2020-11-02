import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -환상의 짝꿍-
 */

//출처 : https://www.acmicpc.net/problem/15711
public class Main_B_G4_15711_환상의짝꿍 {
	static int MAX=2000001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int T = Integer.parseInt(st.nextToken());
		long a, b;
		//200만까지 미리 소수를 구해놓을 예정
		
		boolean[] arr = new boolean[MAX];
		arr[0]=true; arr[1]=true;
		
		int half=MAX/2, ti;
		for(int i = 2; i<=half; ++i) {
			if(arr[i]) continue;
			for(int j = 2; j < MAX; ++j) {
				ti = i*j;
				if(ti >= MAX) break;
				arr[ti] = true;
			}
		}
		
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i<MAX; ++i) {
			if(!arr[i])
				list.add(i);
		}
		int lSize = list.size();
		
		long sum;
		W: while(--T>=0) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken());
			sum = a+b;
			
			if(sum <4)
				System.out.println("NO");
			else if(sum%2 == 0)
				System.out.println("YES");
			else {
				sum-=2;
				for(int i = 0; i < lSize; ++i) {
					if((long)list.get(i) * list.get(i) > sum) break;
					if(sum%list.get(i) == 0) {
						System.out.println("NO");
						continue W;
					}
				}
				System.out.println("YES");
			}
		}
	}
}
