import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -모래성 쌓기-
 * 1. 모든 모래에 대해 주변 모래량까지 합하여 저장.
 * 2. 해당 모래량이 8이하라면 부서질 수 있는 모래이다.
 * 3. 처음으로 부서질 수 있는 모래를 Queue에 담고 하나씩 꺼내가며 주변 모래에 영향을 준다.
 * 4. 위의 영향으로 부서질 수 있는 모래가 된다면 해당 모래 Queue에 넣기
 * 
 * > 모든 모래 Set으로 넣어 모두 확인하다가 시간초과
 * > PQ에 넣어 PQ내부 객체 내부값 변경하여 작은 것만 뽑아내려했으나 PQ는 offer,poll시에만 내부 구조 변경이라 실패
 */

/*
 * 메모리 : 144840KB 
 * 시간 : 609ms 
 * 코드길이 : 2501B 
 * 소요시간 : 2H
 */

//150P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PNx_KACIDFAUq
public class Solution_SWE_1907_모래성쌓기 {
	static int ROW,COL;
	static boolean[][] isVisited;
	static int[] drow= {-1,-1,-1,0,0,1,1,1};
	static int[] dcol= {-1,0,1,-1,1,-1,0,1};
	
	static class Send {
		int row,col,sum;
		Send(int row, int col, int sum){
			this.row=row;
			this.col=col;
			this.sum=sum;
		}
	}
	
	static Send[][] sendArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			ROW = Integer.parseInt(st.nextToken());
			COL = Integer.parseInt(st.nextToken());
			
			String tstr;
			char tc;
			isVisited = new boolean[ROW][COL];
			sendArr = new Send[ROW][COL];
			for (int row = 0; row < ROW; ++row) {
				tstr = br.readLine();
				for (int col = 0; col < COL; ++col) {
					tc=tstr.charAt(col);
					if(tc=='.') {
						//빈 곳은 확인할 필요 없으며, 모래값은 0
						sendArr[row][col] = new Send(row,col,0);
						isVisited[row][col] = true;
					}
					else {
						sendArr[row][col] = new Send(row,col,tc-'0');
					}
				}
			}
			int cr,cc, nr,nc,qs, tempSend, time=0;
			//모든 모래 확인 하며 주변의 모래량 더해주기
			for (int row = 1; row < ROW-1; ++row) {
				for (int col = 1; col < COL-1; ++col) {
					if(isVisited[row][col]) continue;
					tempSend=0;
					for(int dir = 0; dir < 8; ++dir) {
						nr=row+drow[dir];	nc=col+dcol[dir];
						if(sendArr[nr][nc].sum!=0)
							tempSend++;
					}
					sendArr[row][col].sum+=tempSend;
				}
			}
			
			//처음으로 부서질 수 있는 모래 넣어주기
			Queue<Send> que = new LinkedList<>();
			for (int row = 1; row < ROW-1; ++row) {
				for (int col = 1; col < COL-1; ++col) {
					if(isVisited[row][col]) continue;
					if(sendArr[row][col].sum < 9) {
						isVisited[row][col]=true;
						que.offer(sendArr[row][col]);
					}
				}
			}
			
			Send ts;
			while(!que.isEmpty()) {
				qs = que.size();
				while(--qs >= 0) {
					//사라질 모래 주변 sum-1해주기
					ts = que.poll();
					cr=ts.row;	cc=ts.col;
					for(int dir = 0; dir < 8; ++dir) {
						nr=cr+drow[dir];	nc=cc+dcol[dir];
						sendArr[nr][nc].sum--;
						//넣지 않은 모래고, sum이 8이하라면 부서질 수 있으므로 que에 넣기
						if(sendArr[nr][nc].sum<9 && !isVisited[nr][nc]) {
							isVisited[nr][nc]=true;
							que.offer(sendArr[nr][nc]);
						}
					}
				}
				time++;
			}
			
			sb.append('#').append(t).append(' ').append(time).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
}