import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -숨바꼭질-
 * 1. BFS를 이용한 문제이다.
 * 2. 0~100000 사이의 좌표를 지니므로 방문 배열의 크기는 넉넉히 100001으로 주었다.
 * 3. 처음 자리가 같을 경우 확인하고 그 뒤 *2,-1,+1의 좌표값을 확인하며 같은 좌표를 가지는지 확인한다.
 * 4. 같은 좌표라면 현재까지의 BFS의 Depth를 반환한다.(time)
 */

//출처 : https://www.acmicpc.net/problem/1697
public class Solution_1697 {
	static final int LIMIT = 100000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[LIMIT+1];
		
		int time = 0;
		
		//같은 자리일때
		if(N == K) {
			System.out.println(time);
			return;
		}
		
		que.offer(N);
		int c,n,size;
		while(!que.isEmpty()) {
			size = que.size();
			
			time++;
			while(--size >= 0) {
				c = que.poll();
				for(int i = 0; i < 3; ++i) {
					switch (i) {
					case 0:	n = c*2;
						break;
					case 1:	n = c-1;
						break;
					case 2:
					default:
						n = c+1;
						break;
					}
					
					if(n == K) {
						System.out.println(time);
						return;
					}
					// 한계범위 넘지 않았다면, 방문하지 않았다면 해당 좌표 확인 필요
					if(n >= 0 && n <= LIMIT) {
						if(isVisited[n])
							continue;
						que.offer(n);
						isVisited[n] = true;
					}
				}
			} //end while(--size >= 0)
			
		} //end while(!que.isEmpty())
	}
}