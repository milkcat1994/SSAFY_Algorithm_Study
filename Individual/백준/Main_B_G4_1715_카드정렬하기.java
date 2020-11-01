import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -카드 정렬하기-
 */

//출처 : https://www.acmicpc.net/problem/1715
public class Main_B_G4_1715_카드정렬하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i<N; ++i) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int ti, res=0;
		while(pq.size() != 1) {
			ti = pq.poll() + pq.poll();
			res+=ti;
			pq.offer(ti);
		}
		System.out.println(res);
	}
}