import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -버스 갈아타기-
 * 1. 버스 시작 지점을 작게 입력 받아 계산 편리하게 한다.
 * 2. 각 버스 정보를 배열로 저장하고 BFS에서 모든 버스 정보를 항상 확인한다.
 * └──리스트로 만들어 관리할 경우 주어진 메모리를 초과한다.
 * 3. 현재 버스에서 환승 가능한(서로 교차되는) 버스가 있다면 해당 버스를 Queue에 추가한다.
 */

//출처 : https://www.acmicpc.net/problem/2536
public class Main_B_G1_2536_버스갈아타기 {
	static int ROW,COL, K;
	static class Bus {
		int sr,sc,er,ec;
		public Bus(int sr, int sc, int er, int ec) {
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
	}
	
	static Bus[] busArr;
	static boolean[] isSelected;
	static int sr,sc,er,ec;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		COL = Integer.parseInt(st.nextToken());
		ROW = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		isSelected = new boolean[K+1];
		busArr = new Bus[K+1];

		int bn, tr,tc;
		for(int k = 1; k<=K; ++k) {
			st = new StringTokenizer(br.readLine(), " ");
			bn = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			sr = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			er = Integer.parseInt(st.nextToken());
			//오름차순 정리
			if(er<sr) {
				tr=sr;	sr=er; er=tr;
			}
			else if(ec<sc) {
				tc=sc;	sc=ec; ec=tc;
			}
			busArr[bn] = new Bus(sr,sc,er,ec);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		sc = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());

		if(sr==er&&sc==ec) {
			System.out.print(0);
			return;
		}
		System.out.print(bfs());
	}
	
	public static int bfs() {
		Queue<Integer> que = new LinkedList<>();
		Bus tb,tb2;
		for(int bn = 1; bn<=K; ++bn) {
			tb=busArr[bn];
			if(tb.sr<=sr && sr<=tb.er && tb.sc<=sc && sc<=tb.ec) {
				isSelected[bn] = true;
				que.offer(bn);
			}
		}
		
		int qs,ci, time=0;
		while(!que.isEmpty()) {
			qs=que.size();
			while(--qs>=0) {
				ci=que.poll();
				tb=busArr[ci];
				if(tb.sr<=er && er<=tb.er && tb.sc<=ec && ec<=tb.ec)
					return time+1;
				for (int ni = 1; ni <= K; ++ni) {
					if(isSelected[ni]) continue;
					tb2 = busArr[ni];
					if(tb.sr<=tb2.sr && tb2.sr<=tb.er && tb2.sc<=tb.sc && tb.sc<= tb2.ec
							|| tb2.sr<=tb.sr && tb.sr<=tb2.er && tb.sc<=tb2.sc && tb2.sc<= tb.ec) {
						isSelected[ni] = true;
						que.offer(ni);
					}
				}
			}
			time++;
		} //end while(que empty)
		return time;
	}
}