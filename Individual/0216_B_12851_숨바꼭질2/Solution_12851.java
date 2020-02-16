import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -숨바꼭질2-
 * 1. 목적 위치가 같거나 작은경우 계산해서 바로 출력
 * 2. 같은 거리의 경로가 갈 수 있는곳은 모두 간 뒤 방문 표시
 * 3. 현재 위치가 작다면 모든 경우 진행, 크다면 -1만 진행
 */

//출처 : https://www.acmicpc.net/problem/12851
public class Solution_12851 {
	static boolean[] isVisited = new boolean[130001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N,K,qs,cr,nr,result = 0,time=0;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N >= K) {
			System.out.println((N-K)+"\n1");
			return;
		}
		
		Queue<Integer> que = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		que.offer(N);
		boolean arrive = false;
		isVisited[N] = true;
		
		while(!que.isEmpty()) {
			qs = que.size();
			while(--qs >= 0) {
				cr = que.poll();
				stack.push(cr);
				
				//현재 위치가 작을때만 2배,+1,-1 확인
				if(cr < K) {
					//*2
					nr = cr*2;
					if(!isOut(nr)) {
						if(nr == K) {
							result++;
							arrive = true;
						}
						else
							que.offer(nr);
					}
					//+1
					nr = cr+1;
					if(!isOut(nr)) {
						if(nr == K) {
							result++;
							arrive = true;
						}
						else
							que.offer(nr);
					}
					//-1
					nr = cr-1;
					if(!isOut(nr)) {
						if(nr == K) {
							result++;
							arrive = true;
						}
						else
							que.offer(nr);
					}
				}
				//현재 위치 크다면 -1이 최선
				else {
					//-1
					nr = cr-1;
					if(!isOut(nr)) {
						if(nr == K) {
							result++;
							arrive = true;
						}
						else
							que.offer(nr);
					}
				}
			} //end while(--qsize)
			
			time++;
			if(arrive) {
				sb.append(time).append('\n').append(result);
				System.out.print(sb.toString());
				return;
			}
			
			//지금 시간에 방문했던 곳 한꺼번에 표시
			for(Integer in : stack) {
				isVisited[in] = true;
			}
			stack.clear();
			
		} //end while(!que.isEmpty())
	}
	
	//너무 밖으로 나가거나 방문한곳이라면 이동x
	public static boolean isOut(int pos) {
		if(pos<0 || pos > 130000 || isVisited[pos])
			return true;
		return false;
	}
}