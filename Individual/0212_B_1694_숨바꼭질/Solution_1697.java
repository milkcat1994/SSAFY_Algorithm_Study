import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -숨바꼭질-
 * 1. BFS를 이용한 문제이다.
 * 2. 0~100000 사이의 좌표를 지니므로 방문 배열의 크기는 넉넉히 200000으로 주었다.
 * 3. 처음 자리가 같을 경우 확인하고 그 뒤 -1,1,*2의 좌표값을 확인하며 같은 좌표를 가지는지 확인한다.
 * 4. 같은 좌표라면 현재까지의 BFS의 Depth를 반환한다.(time)
 */

//출처 : https://www.acmicpc.net/problem/1697
public class Solution_1697 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[200000];
		
		que.offer(N);
		int c,n,size;
		int[] d = {-1,1};
		int time = 0;
		
		//같은 자리일때
		if(N == K) {
			System.out.println(time);
			return;
		}
		
		while(!que.isEmpty()) {
			size = que.size();
			
			time++;
			while(--size >= 0) {
				c = que.poll();
				for(int i = 0; i < 2; ++i) {
					n = c+d[i];
					if(n == K) {
						System.out.println(time);
						return;
					}
					if(n >= 0 && n < 200000) {
						if(isVisited[n])
							continue;
						que.offer(n);
						isVisited[n] = true;
					}
				}
				
				n = c*2;
				if(n == K) {
					System.out.println(time);
					return;
				}
				
				if(n >= 0 && n < 200000) {
					if(isVisited[n])
						continue;
					que.offer(n);
					isVisited[n] = true;
				}
				
			} //end while(--size >= 0)
			
		} //end while(!que.isEmpty())
	}
}
