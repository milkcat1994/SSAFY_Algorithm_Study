import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -큐빙-
 * 3x3배열 6개로 회전
 */

//출처 : https://www.acmicpc.net/problem/5373
public class Main_B_G1_5373_큐빙 {
	
	public static class Rotate {
		char p, dir;
		Rotate(char p, char dir){
			this.p = p;
			this.dir = dir;
		}
	}
	static Queue<Rotate> que = new LinkedList<Rotate>();
	static char[][] upup,down,fron,back,left,righ;
	
	static char tc1,tc2,tc3;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		int N;

		String tempStr;
		Rotate tempRot;
		for(int t=0; t < T; ++t) {
			init();
			//큐브 돌릴 횟수
			N = Integer.parseInt(br.readLine());
			
			tempStr=br.readLine();
			for(int i = 0; i < N*3; i+=3) {
				que.offer(new Rotate(tempStr.charAt(i), tempStr.charAt(i+1)));
			}
			
			while(!que.isEmpty()) {
				tempRot=que.poll();
				switch (tempRot.p) {
				case 'U':
					up(tempRot.dir);
					break;
				case 'D':
					down(tempRot.dir);
					break;
				case 'F':
					front(tempRot.dir);
					break;
				case 'B':
					back(tempRot.dir);
					break;
				case 'L':
					left(tempRot.dir);
					break;
				case 'R':
					right(tempRot.dir);
					break;
				}
			} //end while(!que.isEmpty())
			
			for (int row = 0; row < 3; ++row) {
				for (int col = 0; col < 3; ++col) {
					sb.append(upup[row][col]);
				}
				sb.append('\n');
			}
		} //end TestCase
		System.out.println(sb.toString());
	}
	
	public static void init() {
		upup   = new char[][]{{'w','w','w'},{'w','w','w'},{'w','w','w'}};
		down = new char[][]{{'y','y','y'},{'y','y','y'},{'y','y','y'}};
		fron = new char[][]{{'r','r','r'},{'r','r','r'},{'r','r','r'}};
		back = new char[][]{{'o','o','o'},{'o','o','o'},{'o','o','o'}};
		left = new char[][]{{'g','g','g'},{'g','g','g'},{'g','g','g'}};
		righ = new char[][]{{'b','b','b'},{'b','b','b'},{'b','b','b'}};
	}
	
	public static void up(char dir) {
		//1. up배열 dir방향 회전
		//2. l,b,r,f dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=upup[2][0];			tc2=upup[2][1];
			upup[2][0]=upup[0][0];	upup[2][1]=upup[1][0];
			upup[0][0]=upup[0][2];	upup[1][0]=upup[0][1];
			upup[0][2]=upup[2][2];	upup[0][1]=upup[1][2];
			upup[2][2]=tc1;			upup[1][2]=tc2;
			
			tc1=left[0][0]; 		tc2=left[0][1]; 		tc3=left[0][2];
			left[0][0]=back[0][0]; 	left[0][1]=back[0][1]; 	left[0][2]=back[0][2];
			back[0][0]=righ[0][0]; 	back[0][1]=righ[0][1]; 	back[0][2]=righ[0][2];
			righ[0][0]=fron[0][0]; 	righ[0][1]=fron[0][1]; 	righ[0][2]=fron[0][2];
			fron[0][0]=tc1; 		fron[0][1]=tc2; 		fron[0][2]=tc3;
			break;
			//시계방향
		case '+':
			tc1=upup[0][0];			tc2=upup[1][0];
			upup[0][0]=upup[2][0];	upup[1][0]=upup[2][1];
			upup[2][0]=upup[2][2];	upup[2][1]=upup[1][2];
			upup[2][2]=upup[0][2];	upup[1][2]=upup[0][1];
			upup[0][2]=tc1;			upup[0][1]=tc2;
			
			tc1=left[0][0]; 		tc2=left[0][1];			tc3=left[0][2];
			left[0][0]=fron[0][0];	left[0][1]=fron[0][1]; 	left[0][2]=fron[0][2];
			fron[0][0]=righ[0][0];	fron[0][1]=righ[0][1]; 	fron[0][2]=righ[0][2];
			righ[0][0]=back[0][0];	righ[0][1]=back[0][1];	righ[0][2]=back[0][2];
			back[0][0]=tc1;			back[0][1]=tc2;			back[0][2]=tc3;
			break;
		}
	}
	
	
	public static void down(char dir) {
		//1. down배열 dir방향 회전
		//2. l,f,r,b dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=down[2][0];			tc2=down[2][1];
			down[2][0]=down[0][0];	down[2][1]=down[1][0];
			down[0][0]=down[0][2];	down[1][0]=down[0][1];
			down[0][2]=down[2][2];	down[0][1]=down[1][2];
			down[2][2]=tc1;			down[1][2]=tc2;

			tc1=left[2][2];			tc2=left[2][1];			tc3=left[2][0];
			left[2][2]=fron[2][2];	left[2][1]=fron[2][1];	left[2][0]=fron[2][0];
			fron[2][2]=righ[2][2];	fron[2][1]=righ[2][1];	fron[2][0]=righ[2][0];
			righ[2][2]=back[2][2];	righ[2][1]=back[2][1];	righ[2][0]=back[2][0];
			back[2][2]=tc1;			back[2][1]=tc2;			back[2][0]=tc3;
			break;
			//시계방향
		case '+':
			tc1=down[0][0];			tc2=down[1][0];
			down[0][0]=down[2][0];	down[1][0]=down[2][1];
			down[2][0]=down[2][2];	down[2][1]=down[1][2];
			down[2][2]=down[0][2];	down[1][2]=down[0][1];
			down[0][2]=tc1;			down[0][1]=tc2;

			tc1=left[2][2];			tc2=left[2][1];			tc3=left[2][0];
			left[2][2]=back[2][2];	left[2][1]=back[2][1];	left[2][0]=back[2][0];
			back[2][2]=righ[2][2];	back[2][1]=righ[2][1];	back[2][0]=righ[2][0];
			righ[2][2]=fron[2][2];	righ[2][1]=fron[2][1];	righ[2][0]=fron[2][0];
			fron[2][2]=tc1;			fron[2][1]=tc2;			fron[2][0]=tc3;
			break;
		}
	}
	
	public static void front(char dir) {
		//1. front배열 dir방향 회전
		//2. l,u,r,d dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=fron[2][0];			tc2=fron[2][1];
			fron[2][0]=fron[0][0];	fron[2][1]=fron[1][0];
			fron[0][0]=fron[0][2];	fron[1][0]=fron[0][1];
			fron[0][2]=fron[2][2];	fron[0][1]=fron[1][2];
			fron[2][2]=tc1;			fron[1][2]=tc2;

			tc1=left[0][2];			tc2=left[1][2];			tc3=left[2][2];
			left[0][2]=upup[2][2];	left[1][2]=upup[2][1];	left[2][2]=upup[2][0];
			upup[2][2]=righ[2][0];	upup[2][1]=righ[1][0];	upup[2][0]=righ[0][0];
			righ[2][0]=down[0][0];	righ[1][0]=down[0][1];	righ[0][0]=down[0][2];
			down[0][0]=tc1;			down[0][1]=tc2;			down[0][2]=tc3;
			break;
			//시계방향
		case '+':
			tc1=fron[0][0];			tc2=fron[1][0];
			fron[0][0]=fron[2][0];	fron[1][0]=fron[2][1];
			fron[2][0]=fron[2][2];	fron[2][1]=fron[1][2];
			fron[2][2]=fron[0][2];	fron[1][2]=fron[0][1];
			fron[0][2]=tc1;			fron[0][1]=tc2;
			
			tc1=left[0][2];			tc2=left[1][2];			tc3=left[2][2];
			left[0][2]=down[0][0];	left[1][2]=down[0][1];	left[2][2]=down[0][2];
			down[0][0]=righ[2][0];	down[0][1]=righ[1][0];	down[0][2]=righ[0][0];
			righ[2][0]=upup[2][2];	righ[1][0]=upup[2][1];	righ[0][0]=upup[2][0];
			upup[2][2]=tc1;			upup[2][1]=tc2;			upup[2][0]=tc3;
			break;
		}
	}
	
	public static void back(char dir) {
		//1. back배열 dir방향 회전
		//2. r,u,l,d dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=back[2][0];			tc2=back[2][1];
			back[2][0]=back[0][0];	back[2][1]=back[1][0];
			back[0][0]=back[0][2];	back[1][0]=back[0][1];
			back[0][2]=back[2][2];	back[0][1]=back[1][2];
			back[2][2]=tc1;			back[1][2]=tc2;
			
			tc1=righ[0][2];			tc2=righ[1][2];			tc3=righ[2][2];
			righ[0][2]=upup[0][0];	righ[1][2]=upup[0][1];	righ[2][2]=upup[0][2];
			upup[0][0]=left[2][0];	upup[0][1]=left[1][0];	upup[0][2]=left[0][0];
			left[2][0]=down[2][2];	left[1][0]=down[2][1];	left[0][0]=down[2][0];
			down[2][2]=tc1;			down[2][1]=tc2;			down[2][0]=tc3;
			break;
			//시계방향
		case '+':
			tc1=back[0][0];			tc2=back[1][0];
			back[0][0]=back[2][0];	back[1][0]=back[2][1];
			back[2][0]=back[2][2];	back[2][1]=back[1][2];
			back[2][2]=back[0][2];	back[1][2]=back[0][1];
			back[0][2]=tc1;			back[0][1]=tc2;
			
			tc1=righ[0][2];			tc2=righ[1][2];			tc3=righ[2][2];
			righ[0][2]=down[2][2];	righ[1][2]=down[2][1];	righ[2][2]=down[2][0];
			down[2][2]=left[2][0];	down[2][1]=left[1][0];	down[2][0]=left[0][0];
			left[2][0]=upup[0][0];	left[1][0]=upup[0][1];	left[0][0]=upup[0][2];
			upup[0][0]=tc1;			upup[0][1]=tc2;			upup[0][2]=tc3;
			break;
		}
	}
	
	public static void left(char dir) {
		//1. left배열 dir방향 회전
		//2. b,u,f,d dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=left[2][0];			tc2=left[2][1];
			left[2][0]=left[0][0];	left[2][1]=left[1][0];
			left[0][0]=left[0][2];	left[1][0]=left[0][1];
			left[0][2]=left[2][2];	left[0][1]=left[1][2];
			left[2][2]=tc1;			left[1][2]=tc2;
			
			tc1=back[0][2];			tc2=back[1][2];			tc3=back[2][2];
			back[0][2]=upup[2][0];	back[1][2]=upup[1][0];	back[2][2]=upup[0][0];
			upup[2][0]=fron[2][0];	upup[1][0]=fron[1][0];	upup[0][0]=fron[0][0];
			fron[2][0]=down[2][0];	fron[1][0]=down[1][0];	fron[0][0]=down[0][0];
			down[2][0]=tc1;			down[1][0]=tc2;			down[0][0]=tc3;
			break;
			//시계방향
		case '+':
			tc1=left[0][0];			tc2=left[1][0];
			left[0][0]=left[2][0];	left[1][0]=left[2][1];
			left[2][0]=left[2][2];	left[2][1]=left[1][2];
			left[2][2]=left[0][2];	left[1][2]=left[0][1];
			left[0][2]=tc1;			left[0][1]=tc2;
			
			tc1=back[0][2];			tc2=back[1][2];			tc3=back[2][2];
			back[0][2]=down[2][0];	back[1][2]=down[1][0];	back[2][2]=down[0][0];
			down[2][0]=fron[2][0];	down[1][0]=fron[1][0];	down[0][0]=fron[0][0];
			fron[2][0]=upup[2][0];	fron[1][0]=upup[1][0];	fron[0][0]=upup[0][0];
			upup[2][0]=tc1;			upup[1][0]=tc2;			upup[0][0]=tc3;
			break;
		}
	}


	public static void right(char dir) {
		//1. right배열 dir방향 회전
		//2. f,u,b,d dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=righ[2][0];			tc2=righ[2][1];
			righ[2][0]=righ[0][0];	righ[2][1]=righ[1][0];
			righ[0][0]=righ[0][2];	righ[1][0]=righ[0][1];
			righ[0][2]=righ[2][2];	righ[0][1]=righ[1][2];
			righ[2][2]=tc1;			righ[1][2]=tc2;
			
			tc1=fron[0][2];			tc2=fron[1][2];			tc3=fron[2][2];
			fron[0][2]=upup[0][2];	fron[1][2]=upup[1][2];	fron[2][2]=upup[2][2];
			upup[0][2]=back[2][0];	upup[1][2]=back[1][0];	upup[2][2]=back[0][0];
			back[2][0]=down[0][2];	back[1][0]=down[1][2];	back[0][0]=down[2][2];
			down[0][2]=tc1;			down[1][2]=tc2;			down[2][2]=tc3;
			break;
			//시계방향
		case '+':
			tc1=righ[0][0];			tc2=righ[1][0];
			righ[0][0]=righ[2][0];	righ[1][0]=righ[2][1];
			righ[2][0]=righ[2][2];	righ[2][1]=righ[1][2];
			righ[2][2]=righ[0][2];	righ[1][2]=righ[0][1];
			righ[0][2]=tc1;			righ[0][1]=tc2;
			
			tc1=fron[0][2];			tc2=fron[1][2];			tc3=fron[2][2];
			fron[0][2]=down[0][2];	fron[1][2]=down[1][2];	fron[2][2]=down[2][2];
			down[0][2]=back[2][0];	down[1][2]=back[1][0];	down[2][2]=back[0][0];
			back[2][0]=upup[0][2];	back[1][0]=upup[1][2];	back[0][0]=upup[2][2];
			upup[0][2]=tc1;			upup[1][2]=tc2;			upup[2][2]=tc3;
			break;
		}
	}
	
}
