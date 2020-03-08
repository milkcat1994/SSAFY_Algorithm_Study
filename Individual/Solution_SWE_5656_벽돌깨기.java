import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -벽돌 깨기-
 * 1. 중복 가능 순열로 모든 세로선에 구슬을 떨어뜨려본다.
 * 2. 구슬을 떨굴때 마다 판을 복사하여 부순 뒤 복구하는 코드를 짜지 않았다.
 * 3. 해당 세로선에서 부술 블럭을 찾았다면 해당 블럭을 시작으로 사방을 확인하여 부술 블럭을 다시 찾는다.
 * 4. 부순 블럭 수를 반환하여 현재까지 남은 블럭수를 계속 가지고 간다.
 * 5. 모든 구슬을 다 떨어뜨렸거나 해당 위치에서 부술 블럭이 없다면 값을 최소값으로 갱신하고 빠져나온다.
 */

/*
 * 메모리 : 60472KB 
 * 시간 : 180ms 
 * 코드길이 : 2471B 
 * 소요시간 : 1H
 */

//200P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWsBQpPqMNMDFARG
public class Solution_SWE_5656_벽돌깨기 {
	static int N,ROW,COL;
	static int[][] board;
	static int[] drow={-1,1,0,0};
	static int[] dcol={0,0,-1,1};
	static int minRes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			N=Integer.parseInt(st.nextToken());
			COL=Integer.parseInt(st.nextToken());
			ROW=Integer.parseInt(st.nextToken());
			board = new int[ROW][COL];
			int bc=0;
			minRes=1<<25;
			for (int r = 0; r < ROW; ++r) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < COL; ++c) {
					board[r][c] = Integer.parseInt(st.nextToken());
					if(board[r][c]!=0) bc++;
				}
			}

			for(int c=0; c < COL; ++c)
				dfs(N,c,board, bc);
			System.out.println("#"+t+" "+minRes);
		} //end TestCase
	}
	
	static void dfs(int n, int cc, int[][] b, int count) {
		int[][] cb = copy(b);
		
		int delBnum=0;
		boolean isFind = false;
		//해당 위치에서 부술 블록이 있는 지 확인, 없다면 그냥 나가기
		for(int cr=0; cr <ROW; ++cr) {
			if(cb[cr][cc] != 0) {
				isFind=true;
				delBnum=delBlock(cr,cc,cb);
				//모든 벽돌 아래로 쭉 당겨야 한다.
				movedown(cb);
				break;
			}
		}

		//모든 공 다 사용했을때, 부술 블록 없었을때
		if(!isFind || n==1) {
			minRes = minRes < count-delBnum ? minRes : count-delBnum;
			return;
		}
		
		//모든 col에 대해서 한번씩 떨굴것이다.
		for(int c=0; c < COL; ++c) {
			dfs(n-1, c, cb, count-delBnum);	//count로 남은 벽돌 수 기억하기
		}
	}
	
	static void movedown(int[][] b) {
		boolean flag;	//채우고자 하는지
		int tr;			//채울 번호
		for (int c = 0; c < COL; ++c) {
			tr=ROW-1;	flag=false;
			for (int r = ROW-1; r >=0; --r) {
				if(b[r][c] == 0 && !flag) {
					tr=r;	//0이라면 해당 자리 저장
					flag=true;
				}
				else if(b[r][c]!=0 && flag){
					b[tr][c]=b[r][c];
					b[r][c]=0;
					tr--;
				}
			}
		}
	}
	
	//벽돌 부수기
	static int delBlock(int cr, int cc, int[][] b) {
		//block의 크기
		int bs=b[cr][cc];
		//저장했으면 해당 자리 0으로 바꾸어줄것.
		b[cr][cc]=0;
		//block의 크기가 0이거나 1이면 해당 숫자 반환
		if(bs==0 || bs==1) return bs;
		
		int nr,nc, delb=0;
		for(int dir = 0; dir < 4; ++dir) {
			for(int d = 1; d<bs; ++d) {
				nr=cr+drow[dir]*d;	nc=cc+dcol[dir]*d;
				if(isOut(nr,nc)) continue;
				delb+=delBlock(nr, nc, b);
			}
		}
		//부순 block수+자기자신
		return delb+1;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
	
	static int[][] copy(int[][] b){
		int[][] cb=new int[ROW][COL];
		for (int row = 0; row < ROW; ++row) {
			for (int col = 0; col < COL; ++col) {
				cb[row][col] = b[row][col];
			}
		}
		return cb;
	}
}
