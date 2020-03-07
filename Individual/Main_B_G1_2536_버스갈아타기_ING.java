import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -버스 갈아타기-
 */
//15
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
	static List<Integer>[] edge;
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
		busArr[0] = new Bus(-1,-1,-1,-1);
		edge = new LinkedList[K+1];

		int bn, tr,tc;
		for(int k = 1; k<=K; ++k) {
			st = new StringTokenizer(br.readLine(), " ");
			bn = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			sr = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			er = Integer.parseInt(st.nextToken());
			if(er<sr) {
				tr=sr;	sr=er; er=tr;
			}
			else if(ec<sc) {
				tc=sc;	sc=ec; ec=tc;
			}
			busArr[bn] = new Bus(sr,sc,er,ec);
			edge[k] = new LinkedList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		sc = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		
		Bus b1,b2;
		for (int si = 1; si < K; ++si) {
			b1 = busArr[si];
			for (int ei = si+1; ei <= K; ++ei) {
				b2 = busArr[ei];
				if(b1.sr<=b2.sr && b2.sr<=b1.er && b2.sc<=b1.sc && b1.sc<= b2.ec
						|| b2.sr<=b1.sr && b1.sr<=b2.er && b1.sc<=b2.sc && b2.sc<= b1.ec) {
					edge[si].add(ei);
					edge[ei].add(si);
				}
			}
		}

		if(sr==er&&sc==ec) {
			System.out.print(0);
			return;
		}
		System.out.print(bfs());
	}
	
	public static int bfs() {
		Queue<Integer> que = new LinkedList<>();
		Bus tb;
		for(int bn = 1; bn<=K; ++bn) {
			tb=busArr[bn];
			if(tb.sr<=sr && sr<=tb.er && tb.sc<=sc && sc<=tb.ec) {
				isSelected[bn] = true;
				que.offer(bn);
			}
		}
		
		int qs,ti, time=0;
		while(!que.isEmpty()) {
			qs=que.size();
			while(--qs>=0) {
				ti=que.poll();
				tb=busArr[ti];
				if(tb.sr<=er && er<=tb.er && tb.sc<=ec && ec<=tb.ec)
					return time+1;
				for(Integer bn : edge[ti]) {
					if(isSelected[bn]) continue;
					
					isSelected[bn] = true;
					que.offer(bn);
				}
			}
			time++;
		} //end while(que empty)
		return time;
	}
}