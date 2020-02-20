import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -숨바꼭질4-
 * 1. N이 K보다 큰경우 '-1'로만 경로를 찍어낸다.
 * 2. visit배열을 -1로 초기화 하여 다음 위치를 해당 위치에 넣어줄 것이다.
 * 3. 도착 하였을 경우 visit배열을 순회하며 경로를 stack에 담아준다.
 * └──도착지점에서 출발지점으로 향하므로 stack을 사용하였다.
 * 4. stack에서 빼주며 경로를 출력한다.
 */

//출처 : https://www.acmicpc.net/problem/13913
public class Main_B_G4_13913_숨바꼭질4 {
	static int N,K;
	static final int LIMIT = 100000;
	static int[] isVisited = new int[LIMIT+1];
	static Stack<Integer> stack = new Stack<Integer>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//N이 K보다 크다면 -1해주는 것으로 출력해준다.
		if(N>=K) {
			sb.append(N-K).append('\n');
			for(int i = N; i >= K; --i) {
				sb.append(i).append(' ');
			}
			System.out.print(sb.toString());
			return;
		}
		
		Arrays.fill(isVisited, -1);
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(N);
		int qsize,cp,np,time=0;
		
		while(!que.isEmpty()) {
			qsize = que.size();
			time++;
			
			while(--qsize >= 0) {
				cp = que.poll();
				
				//낮은 곳에 위치하기에 -1이 최적
				if(cp >= K) {
					np = cp-1;
					if(!isOut(np)) {
						isVisited[np] = cp;
						if(np == K) {
							getTrack(np);
							printTrack(time);
							return;
						}
						que.offer(np);
					}
				}
				else {
					for(int i = 0; i < 3; ++i) {
						switch (i) {
						case 0:	np = cp*2;
							break;
						case 1:	np = cp+1;
							break;
						case 2:	default:
							np = cp-1;
							break;
						}
						if(!isOut(np)) {
							isVisited[np] = cp;
							if(np == K) {
								getTrack(np);
								printTrack(time);
								return;
							}
							que.offer(np);
						}
					} //end for(i)
				} //end if(cp>=K)
			} //end while(--qsize)
		} //end while(!que.isEmpty())
	}
	
	//경로 출력
	public static void printTrack(int time) {
		sb.append(time).append('\n');
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(' ');
		}
		System.out.print(sb.toString());
	}
	
	//stack에 현재 경로 넣기
	public static void getTrack(int k) {
		int cp=k;
		do {
			stack.push(cp);
			cp = isVisited[cp];
		} while(cp != -1);
		//시작지점의 배열값은 -1이다.
	}
	
	public static boolean isOut(int pos) {
		if(pos<0 || pos>LIMIT || isVisited[pos] != -1 || pos==N)
			return true;
		return false;
	}
}