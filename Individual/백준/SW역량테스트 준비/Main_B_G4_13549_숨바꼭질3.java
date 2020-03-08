import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -숨바꼭질3-
 * 1. 2배로 순간이동을 먼저 하여 해당 좌표를 Queue에 먼저 추가하고
 * 2. +1,-1로 이동한다.
 */

//출처 : https://www.acmicpc.net/problem/13549
public class Main_13549_숨바꼭질3 {
	static final int LIMIT = 100000;
	static int N,K;
	static boolean[] isVisited = new boolean[LIMIT+1];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//K가 N보다 작다면 그 차이값이 걸릴 시간이다.
		if(N>=K) {
			System.out.println(N-K);
			return;
		}
		
		Queue<Integer> que = new LinkedList<Integer>();
		List<Integer> list = new ArrayList<Integer>();	//2배 뛸 구역 임시 저장할 List
		que.offer(N);
		
		int time=0 ,cr,nr,qsize, tempPos;
		while(!que.isEmpty()) {
			//현재 가진 좌표들에 대해 2배 점프 할 수 있는 곳을 전부 넣어준다.
			for(Integer ti : que) {
				tempPos = ti;
				while(true) {
					tempPos *= 2;
					if(isOut(tempPos))
						break;
					if(tempPos == K) {
						System.out.println(time);
						return;
					}
					isVisited[tempPos] = true;
					list.add(tempPos);
				}
			}
			que.addAll(list);
			list.clear();
			time++;
			
			qsize = que.size();
			while(--qsize >= 0) {
				cr = que.poll();
				//현재 위치가 크다면 뒤로 한칸 가는것이 최선이다.
				if(cr > K) {
					nr = cr -1;
					if(!isOut(nr)) {
						if(nr == K) {
							System.out.println(time);
							return;
						}
						isVisited[nr] = true;
						que.offer(nr);
					}
				}
				else {
					nr = cr +1;
					if(!isOut(nr)) {
						if(nr == K) {
							System.out.println(time);
							return;
						}
						isVisited[nr] = true;
						que.offer(nr);
					}
					nr = cr -1;
					if(!isOut(nr)) {
						if(nr == K) {
							System.out.println(time);
							return;
						}
						isVisited[nr] = true;
						que.offer(nr);
					}
				}
				
			} //end while(--qsize>=0)
			
		} //end while(!que.isEmpty())
	}
	
	public static boolean isOut(int pos) {
		if(pos<0 || pos>LIMIT || isVisited[pos])
			return true;
		return false;
	}
}