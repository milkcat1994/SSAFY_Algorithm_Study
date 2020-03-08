import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -체스-
 * 1. wall, isVisited 배열 통한 진행 사항 변경
 * 2. 이후 정해진 시뮬레이션 진행
 */

//출처 : https://www.acmicpc.net/problem/1986
public class Main_B_S1_1986_체스 {
	static int ROW,COL,Q,K,P;
	static boolean[][] wall;
	static boolean[][] isVisited;
	static int[] dqrow={-1,1,0,0,-1,1,1,-1};
	static int[] dqcol={0,0,-1,1,1,1,-1,-1};

	static int[] dhrow={-2,-2,-1,1,2,2,1,-1};
	static int[] dhcol={-1,1,2,2,1,-1,-2,-2};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		wall=new boolean[ROW][COL];
		isVisited=new boolean[ROW][COL];
		st = new StringTokenizer(br.readLine(), " ");
		int r,c;
		
		Q=Integer.parseInt(st.nextToken());
		int[][] queen = new int[Q][2];
		for(int q = 0; q<Q; ++q) {
			r=Integer.parseInt(st.nextToken())-1;
			c=Integer.parseInt(st.nextToken())-1;
			queen[q][0]=r;
			queen[q][1]=c;
			wall[r][c]=true;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		K=Integer.parseInt(st.nextToken());
		int[][] knight = new int[K][2];
		for(int k = 0; k<K; ++k) {
			r=Integer.parseInt(st.nextToken())-1;
			c=Integer.parseInt(st.nextToken())-1;
			knight[k][0]=r;
			knight[k][1]=c;
			wall[r][c]=true;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		P=Integer.parseInt(st.nextToken());
		int[][] pawn = new int[P][2];
		for(int p = 0; p<P; ++p) {
			r=Integer.parseInt(st.nextToken())-1;
			c=Integer.parseInt(st.nextToken())-1;
			pawn[p][0]=r;
			pawn[p][1]=c;
			wall[r][c]=true;
		}
		
		int res=0;
		//모든 q돌면서 상하좌우, 대각선 확인
		int cr,cc, nr,nc;
		for(int q = 0; q<Q; ++q) {
			cr=queen[q][0];	cc=queen[q][1];
			for(int dir=0; dir<8; ++dir) {
				nr=cr;	nc=cc;
				for(int j=1; j<1000; ++j) {
					nr+=dqrow[dir];	nc+=dqcol[dir];
					if(isOut(nr, nc) || wall[nr][nc]) break;
					if(!isVisited[nr][nc]) {
						isVisited[nr][nc]=true;
						res++;
					}
				}
			}
		}
		
		//모든 k돌면서 8방향 확인
		for(int k=0; k<K; ++k) {
			cr=knight[k][0];	cc=knight[k][1];
			for(int dir=0; dir<8; ++dir) {
				nr=cr+dhrow[dir];	nc=cc+dhcol[dir];
				if(isOut(nr, nc) || wall[nr][nc] || isVisited[nr][nc]) continue;
				isVisited[nr][nc]=true;
				res++;
			}
		}
		System.out.print(ROW*COL-res-Q-K-P);
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
}
