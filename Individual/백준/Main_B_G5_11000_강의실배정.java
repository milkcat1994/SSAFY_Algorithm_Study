import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -강의실 배정-
 * 시작점, 끝점을 각각 PQ에 집어넣고, 우선순위는 숫자가 작은것, 숫자가 같다면 끝점 일때이다.
 * 이유는 끝나는 점과 시작점이 같다면 연속해서 수업을 들을 수 있는 조건 때문.
 * 
 * pq를 순서대로 돌면서 최대로 겹치는 부분을 찾는다.
 */

//출처 : https://www.acmicpc.net/problem/11000
public class Main_B_G5_11000_강의실배정 {
	static class Point implements Comparable<Point>{
		int i;
		boolean in;
		Point(int i, boolean in){
			this.i = i;
			this.in = in;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.i == o.i) {
				if(this.in && !o.in)
					return 1;
				return -1;
			}
			
			return this.i - o.i;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for(int i = 0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new Point(Integer.parseInt(st.nextToken()), true));
			pq.offer(new Point(Integer.parseInt(st.nextToken()), false));
		}
		
		Point tp;
		int res=0, maxRes=0;
		while(!pq.isEmpty()) {
			tp = pq.poll();
			if(tp.in) {
				res++;
			}
			else {
				res--;
			}
			maxRes = maxRes > res ? maxRes : res;
		}
		
		System.out.println(maxRes);
	}
}
