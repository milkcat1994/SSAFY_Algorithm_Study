import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -택배-
 * 도착 지점인것이 가장 먼저인 것을 뽑아 처리한다.
 * 구간의 최대 실을 수 있는 짐의 양을 구한다. -> 실을 수 있는 총량을 초기값으로 넣었기에 남은 값은 실을 수 있는 최소 값이다.
 * 최소 값을 구했다면 해당 구간 모두 빼준다.
 */

//출처 : https://www.acmicpc.net/problem/8980
public class Main_B_G4_8980_택배 {
	static int N,C,M;
	static int[] arr;
	
	static class Box implements Comparable<Box>{
		int s,e,w;
		Box(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Box o) {
			return this.e - o.e;
		}
	}
	
	static int S,E, MIN;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		Arrays.fill(arr, C);

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Box> pq = new PriorityQueue<>();
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Box box;
		int res=0;
		while(!pq.isEmpty()) {
			box = pq.poll();
			S = box.s;
			E = box.e;
			MIN = box.w;
			findMin(S);
			res+=MIN;
			
			if(MIN!=0) {
				for(int cur=S; cur<E; ++cur) {
					arr[cur]-=MIN;
				}
			}
		}
		
		System.out.println(res);
	}
	
	public static void findMin(int cur) {
		if(cur == E) return;
		
		//사이 구간 중 최소값 반환
		MIN = MIN < arr[cur] ? MIN : arr[cur];
		findMin(cur+1);
		
		return;
	}
}
