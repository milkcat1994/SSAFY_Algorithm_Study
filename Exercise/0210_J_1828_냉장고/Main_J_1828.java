import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -냉장고-
 * 1. 모든 low, high값을 +270하여 0부터 받아들인다.
 * 2. 각 지점의 low, high값을 비교하여 high오름차순 > low오름차순 으로 나열한다.
 * 3. PriorityQueue 이용위해 Point Class에 Comparable 구현
 * 4. Queue에 있는 값을 순차적으로 뽑으며, 처음 뽑힌 Queue값의 high값보다 작은 low값은 모두 삭제시킨다.
 * 5. 만약 뽑힌 low값이 현재의 high값보다 높다면 해당 값의 high값을 이용하여 4번으로 되돌아간다.
 * 6. high값을 뽑은 수만큼이 최소 필요한 냉장고의 갯수이다.
 */

//출처 : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1101&sca=3050
public class Main_J_1828 {
	static int N;
	
	static class Point implements Comparable<Point> {
		int low, high;
		Point(int low, int high) {
			this.low=low;
			this.high=high;
		}
		
		//high값 오름차순, 만일 같다면 low값 오름차순.
		@Override
		public int compareTo(Point o) {
			if(this.high > o.high) {
				return 1;
			}
			else if(this.high == o.high){
				if(this.low > o.low)
					return 1;
				else if(this.low == o.low)
					return 0;
				else
					return -1;
			}
			else {
				return -1;
			}
		}
	}
	
	//모든 low,high값 순차적으로 저장할 Queue
	static PriorityQueue<Point> pq = new PriorityQueue<Point>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int count=0;
		int low,high;
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			low = Integer.parseInt(st.nextToken())+270;
			high = Integer.parseInt(st.nextToken())+270;
			
			pq.offer(new Point(low, high));
		}
		Point tp;
		
		//큐 빌때까지 숫자 뽑기
		while(!pq.isEmpty()) {
			tp = pq.poll();
			//비어 있지 않고 해당 low값이 현재 뽑은 high값보다 작다면 해당 low값 가진 Point 삭제
			while(!pq.isEmpty() && pq.peek().low <= tp.high) {
				pq.poll();
			}
			count++;
		}
		System.out.println(count);
	}
}