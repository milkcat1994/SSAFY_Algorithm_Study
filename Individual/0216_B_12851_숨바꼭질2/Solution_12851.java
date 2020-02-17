import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -숨바꼭질2-
 * 1. 목적 위치가 같거나 작은경우 계산해서 바로 출력
 * 2. 같은 거리의 경로가 갈 수 있는곳은 모두 간 뒤 방문 표시
 * 3. 현재 위치가 작다면 모든 경우 진행, 크다면 -1만 진행
 */

//출처 : https://www.acmicpc.net/problem/12851
public class Solution_12851 {
	static final int LIMIT = 100000;
	static boolean[] isVisited = new boolean[LIMIT+1];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N,K,qs,cp,np,result=0,time=0;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N >= K) {
			System.out.println((N-K)+"\n1");
			return;
		}
		
		Queue<Integer> que = new LinkedList<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		Iterator<Integer> iter;
		
		que.offer(N);
		boolean arrive = false;
		isVisited[N] = true;
		
		while(!que.isEmpty()) {
			qs = que.size();
			while(--qs >= 0) {
				cp = que.poll();
				list.add(cp);
				
				//현재 위치가 작을때만 2배,+1,-1 확인
				if(cp < K) {
					for(int i = 0; i < 3; ++i) {
						switch (i) {
						case 0:	np = cp*2;
							break;
						case 1:	np = cp+1;
							break;
						case 2:
						default:
							np = cp-1;
							break;
						}
						
						if(!isOut(np)) {
							if(np == K) {
								result++;
								arrive = true;
							}
							else
								que.offer(np);
						}
						
					}
				}
				//현재 위치 크다면 -1이 최선
				else {
					//-1
					np = cp-1;
					if(!isOut(np)) {
						if(np == K) {
							result++;
							arrive = true;
						}
						else
							que.offer(np);
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
			iter = list.iterator();
			while(iter.hasNext())
				isVisited[iter.next()] = true;
			list.clear();
			
		} //end while(!que.isEmpty())
	}
	
	//너무 밖으로 나가거나 방문한곳이라면 이동x
	public static boolean isOut(int pos) {
		if(pos<0 || pos > LIMIT || isVisited[pos])
			return true;
		return false;
	}
}