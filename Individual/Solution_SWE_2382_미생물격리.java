import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -미생물격리-
 * 1. 미생물을 담는 Queue(vq)를 만들어 이동을 한다.
 * 2. 이동을 마친 미생물은 board 칸마다 있는 Queue에 들어가며
 * 3. board를 순회하며 곂친 미생물을 처리하여 다시 vq에 담는다.
 */

/*
 * 메모리 : 115620KB 
 * 시간 : 1144ms 
 * 코드길이 : 2641B 
 * 소요시간 : 40M
 */

//200P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl&
public class Solution_SWE_2382_미생물격리 {
	static int N,M,K;	//셀의 개수, 격리시간, 미생물 군집의 수
	static Cell[][] board;
	static int[] drow={0,-1,1,0,0};
	static int[] dcol={0,0,0,-1,1};
	static Queue<Virus> vq;
	
	static class Virus {
		int row,col,sum,dir;

		public Virus(int row, int col, int sum, int dir) {
			this.row = row;
			this.col = col;
			this.sum = sum;
			this.dir = dir;
		}
	}
	
	static class Cell {
		Queue<Virus> que;
		public Cell() {
			que = new LinkedList<>();
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			board = new Cell[N][N];
			for (int row = 0; row < N; ++row) {
				for (int col = 0; col < N; ++col) {
					board[row][col] = new Cell();
				}
			}
			vq = new LinkedList<>();
			
			int r,c,n,d;
			for(int k = 0; k < K; ++k) {
				st = new StringTokenizer(br.readLine(), " ");
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				n = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				vq.add(new Virus(r,c,n,d));
			}
			
			//쭉 돌면서 que 안비어있다면?
			Virus tv, tmv;	//임시virus, 가장 큰 virus
			Queue<Virus> tq;
			int cr,cc,cd,cs, nr,nc;
			while(--M >= 0) {
				while(!vq.isEmpty()) {
					tv = vq.poll();
					cr=tv.row;	cc=tv.col;	cd=tv.dir;	cs=tv.sum;
					nr=cr+drow[cd];	nc=cc+dcol[cd];
					//나갔다면 양 반줄이고 방향 바꾸기
					if(isOut(nr, nc)) {
						tv.sum = cs/2;
						tv.dir = cd==1 ? 2 : cd==2 ? 1 : cd==3 ? 4 : 3;
					}
					//좌표 변경
					tv.row=nr;	tv.col=nc;
					board[nr][nc].que.offer(tv);
				}
				
				//모든 좌표 돌며 que 비어있지 않다면 해당 좌표 계산 해주고 vq에 다시 넣어주기.
				int qs;
				for (int row = 0; row < N; ++row) {
					for (int col = 0; col < N; ++col) {
						tq = board[row][col].que;
						qs = tq.size();
						if(qs!=0) {
							tmv = tq.poll();
							cs=tmv.sum;
							while(!tq.isEmpty()) {
								tv = tq.poll();
								cs+=tv.sum;
								//큰 개수를 tmv에 저장.
								if(tmv.sum<tv.sum) {
									tmv=tv;
								}
							}
							//합계 갱신. 방향은 tmv로 
							tmv.sum=cs;
							vq.offer(tmv);
						}
					} //end for(col)
				}
			} //while(--M>=0)
			
			System.out.println("#"+t+" "+getSum());
		} //end TestCase
	}
	
	public static int getSum() {
		int sum=0;
		for(Virus v : vq) {
			sum+=v.sum;
		}
		return sum;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<=0 || col<=0 || row>=N-1 || col>=N-1)
			return true;
		return false;
	}
}
