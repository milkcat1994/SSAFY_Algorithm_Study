import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
 * ++ *2위치로 이동하는 좌표를 가장 먼저 넣어주는 방식 채택
 */

//출처 : https://www.acmicpc.net/problem/13913
public class Solution_13913_2 {
	static int N,K;
	static final int LIMIT = 100000;
	static int[] timeArr = new int[LIMIT+1];
	static int[] isVisited = new int[LIMIT+1];
	
	static Queue<Integer> que = new LinkedList<Integer>();
	static List<Integer> list = new ArrayList<Integer>();
	static Stack<Integer> stack = new Stack<Integer>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N>=K) {
			sb.append(N-K).append('\n');
			for(int i = N; i >= K; --i) {
				sb.append(i).append(' ');
			}
			System.out.print(sb.toString());
			return;
		}
		
		Arrays.fill(isVisited, -1);
		que.offer(N);
		int qsize,cp,np;
		
		while(!que.isEmpty()) {
			qsize = que.size();
			//2배로 뛴것에서 찾았다면
			if(find1()) {
				getTrack(K);
				printTrack(timeArr[K]);
				return;
			}
			
			while(--qsize >= 0) {
				cp = que.poll();
				for(int i = 0; i < 2; ++i) {
					switch (i) {
					case 0:
						np = cp-1;
						break;
					case 1:
					default:
						np = cp+1;
						break;
					}
					
					if(!isOut(np)) {
						isVisited[np] = cp;
						timeArr[np] = timeArr[cp]+1;
						if(np == K) {
							getTrack(np);
							printTrack(timeArr[np]);
							return;
						}
						que.offer(np);
					}
				}
			} //end while(--qsize)
		} //end while(!que.isEmpty())
	}
	
	//2배 먼저 확인
	public static boolean find1() {
		int tp, tt;
		for(Integer ti : que) {
			tt = timeArr[ti];
			tp = ti;
			while(true) {
				tp *= 2;
				tt++;
				//2배 지점이 나갔거나 이미 방문 했다면 pass
				if(isOut(tp))
					break;
					
				//해당 지점 time설정
				timeArr[tp] = tt;
				isVisited[tp] = tp/2;
				list.add(tp);
				//도착했다면
				if(tp == K) {
					return true;
				}
			}
		}
		que.addAll(list);
		list.clear();
		return false;
	}
	
	public static void printTrack(int time) {
		sb.append(time).append('\n');
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(' ');
		}
		System.out.print(sb.toString());
	}
	
	//stack에 현재 경로 넣기
	public static void getTrack(int k) {
		int cp;
		cp = k;
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
