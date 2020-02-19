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
public class Solution_5373_큐빙 {
	
	public static class Rotate {
		char p, dir;
		Rotate(char p, char dir){
			this.p = p;
			this.dir = dir;
		}
	}
	static Queue<Rotate> que = new LinkedList<Rotate>();
	static char[][] up,down,front,back,left,right;
	
	static char tc1,tc2,tc3,tc4,tc5,tc6;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		int N;

		String tempStr;
		Rotate tempRot;
		for(int t = 0; t < T; ++t) {
			init();
			//큐브 돌릴 횟수
			N = Integer.parseInt(br.readLine());
			
			tempStr = br.readLine();
			for(int i = 0; i < N*3; i+=3) {
				que.offer(new Rotate(tempStr.charAt(i), tempStr.charAt(i+1)));
			}
			
			while(!que.isEmpty()) {
				tempRot = que.poll();
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
					sb.append(up[row][col]);
				}
				sb.append('\n');
			}
		} //end TestCase
		System.out.println(sb.toString());
	}
	
	public static void init() {
		up = new char[][]{{'w','w','w'},{'w','w','w'},{'w','w','w'}};
		down = new char[][]{{'y','y','y'},{'y','y','y'},{'y','y','y'}};
		front = new char[][]{{'r','r','r'},{'r','r','r'},{'r','r','r'}};
		back = new char[][]{{'o','o','o'},{'o','o','o'},{'o','o','o'}};
		left = new char[][]{{'g','g','g'},{'g','g','g'},{'g','g','g'}};
		right = new char[][]{{'b','b','b'},{'b','b','b'},{'b','b','b'}};
	}
	
	public static void up(char dir) {
		//1. up배열 dir방향 회전
		//2. f,b,l,r 0row dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1 = up[2][0];	tc2 = up[2][1];
			up[2][0] = up[0][0];	up[2][1] = up[1][0];
			tc3 = up[2][2];	tc4 = up[1][2];
			up[2][2] = tc1;	up[1][2] = tc2;
			tc1 = up[0][2];	tc2 = up[0][1];
			up[0][2] = tc3;	up[0][1] = tc4;
			up[0][0] = tc1;	up[1][0] = tc2;
			
			tc1=left[0][0]; tc2=left[0][1]; tc3=left[0][2];
			tc4=front[0][0]; tc5=front[0][1]; tc6=front[0][2];
			front[0][0]=tc1; front[0][1]=tc2; front[0][2]=tc3;
			tc1=right[0][0]; tc2=right[0][1]; tc3=right[0][2];
			right[0][0]=tc4; right[0][1]=tc5; right[0][2]=tc6;
			tc4=back[0][0]; tc5=back[0][1]; tc6=back[0][2];
			back[0][0]=tc1; back[0][1]=tc2; back[0][2]=tc3;
			left[0][0]=tc4; left[0][1]=tc5; left[0][2]=tc6;
			break;
			//시계방향
		case '+':
			tc1 = up[0][0];	tc2 = up[1][0];
			up[0][0] = up[2][0];	up[1][0] = up[2][1];
			tc3 = up[0][2];	tc4 = up[0][1];
			up[0][2] = tc1;	up[0][1] = tc2;
			tc1 = up[2][2];	tc2 = up[1][2];
			up[2][2] = tc3;	up[1][2] = tc4;
			up[2][0] = tc1; up[2][1] = tc2;
			
			tc1=left[0][0]; tc2=left[0][1]; tc3=left[0][2];
			tc4=back[0][0]; tc5=back[0][1]; tc6=back[0][2];
			back[0][0]=tc1; back[0][1]=tc2; back[0][2]=tc3;
			tc1=right[0][0]; tc2=right[0][1]; tc3=right[0][2];
			right[0][0]=tc4; right[0][1]=tc5; right[0][2]=tc6;
			tc4=front[0][0]; tc5=front[0][1]; tc6=front[0][2];
			front[0][0]=tc1; front[0][1]=tc2; front[0][2]=tc3;
			left[0][0]=tc4; left[0][1]=tc5; left[0][2]=tc6;
			break;
		}
	}
	
	
	public static void down(char dir) {
		//1. down배열 dir방향 회전
		//2. f,b,l,r 0row dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1 = down[2][0];	tc2 = down[2][1];
			down[2][0] = down[0][0];	down[2][1] = down[1][0];
			tc3 = down[2][2];	tc4 = down[1][2];
			down[2][2] = tc1;	down[1][2] = tc2;
			tc1 = down[0][2];	tc2 = down[0][1];
			down[0][2] = tc3;	down[0][1] = tc4;
			down[0][0] = tc1;	down[1][0] = tc2;
			
			tc1=left[2][2]; tc2=left[2][1]; tc3=left[2][0];
			tc4=back[2][2]; tc5=back[2][1]; tc6=back[2][0];
			back[2][2]=tc1; back[2][1]=tc2; back[2][0]=tc3;
			tc1=right[2][2]; tc2=right[2][1]; tc3=right[2][0];
			right[2][2]=tc4; right[2][1]=tc5; right[2][0]=tc6;
			tc4=front[2][2]; tc5=front[2][1]; tc6=front[2][0];
			front[2][2]=tc1; front[2][1]=tc2; front[2][0]=tc3;
			left[2][2]=tc4; left[2][1]=tc5; left[2][0]=tc6;
			break;
			//시계방향
		case '+':
			tc1 = down[0][0];	tc2 = down[1][0];
			down[0][0] = down[2][0];	down[1][0] = down[2][1];
			tc3 = down[0][2];	tc4 = down[0][1];
			down[0][2] = tc1;	down[0][1] = tc2;
			tc1 = down[2][2];	tc2 = down[1][2];
			down[2][2] = tc3;	down[1][2] = tc4;
			down[2][0] = tc1; down[2][1] = tc2;

			tc1=left[2][2]; tc2=left[2][1]; tc3=left[2][0];
			tc4=front[2][2]; tc5=front[2][1]; tc6=front[2][0];
			front[2][2]=tc1; front[2][1]=tc2; front[2][0]=tc3;
			tc1=right[2][2]; tc2=right[2][1]; tc3=right[2][0];
			right[2][2]=tc4; right[2][1]=tc5; right[2][0]=tc6;
			tc4=back[2][2]; tc5=back[2][1]; tc6=back[2][0];
			back[2][2]=tc1; back[2][1]=tc2; back[2][0]=tc3;
			left[2][2]=tc4; left[2][1]=tc5; left[2][0]=tc6;
			break;
		}
	}
	
	public static void front(char dir) {
		//1. front배열 dir방향 회전
		//2. lurd dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=front[2][0];	tc2=front[2][1];
			front[2][0] = front[0][0];	front[2][1] = front[1][0];
			tc3 = front[2][2];	tc4 = front[1][2];
			front[2][2] = tc1;	front[1][2] = tc2;
			tc1 = front[0][2];	tc2 = front[0][1];
			front[0][2] = tc3;	front[0][1] = tc4;
			front[0][0] = tc1;	front[1][0] = tc2;
			
			tc1=left[0][2]; tc2=left[1][2]; tc3=left[2][2];
			tc4=down[0][0]; tc5=down[0][1]; tc6=down[0][2];
			down[0][0]=tc1; down[0][1]=tc2; down[0][2]=tc3;
			tc1=right[2][0]; tc2=right[1][0]; tc3=right[0][0];
			right[2][0]=tc4; right[1][0]=tc5; right[0][0]=tc6;
			tc4=up[2][2]; tc5=up[2][1]; tc6=up[2][0];
			up[2][2]=tc1; up[2][1]=tc2; up[2][0]=tc3;
			left[0][2]=tc4; left[1][2]=tc5; left[2][2]=tc6;
			break;
			//시계방향
		case '+':
			tc1 = front[0][0];	tc2 = front[1][0];
			front[0][0] = front[2][0];	front[1][0] = front[2][1];
			tc3 = front[0][2];	tc4 = front[0][1];
			front[0][2] = tc1;	front[0][1] = tc2;
			tc1 = front[2][2];	tc2 = front[1][2];
			front[2][2] = tc3;	front[1][2] = tc4;
			front[2][0] = tc1; front[2][1] = tc2;

			tc1=left[0][2]; tc2=left[1][2]; tc3=left[2][2];
			tc4=up[2][2]; tc5=up[2][1]; tc6=up[2][0];
			up[2][2]=tc1; up[2][1]=tc2; up[2][0]=tc3;
			tc1=right[2][0]; tc2=right[1][0]; tc3=right[0][0];
			right[2][0]=tc4; right[1][0]=tc5; right[0][0]=tc6;
			tc4=down[0][0]; tc5=down[0][1]; tc6=down[0][2];
			down[0][0]=tc1; down[0][1]=tc2; down[0][2]=tc3;
			left[0][2]=tc4; left[1][2]=tc5; left[2][2]=tc6;
			break;
		}
	}
	
	//미구현
	public static void back(char dir) {
		//1. down배열 dir방향 회전
		//2. f,b,l,r 0row dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1 = back[2][0];	tc2 = back[2][1];
			back[2][0] = back[0][0];	back[2][1] = back[1][0];
			tc3 = back[2][2];	tc4 = back[1][2];
			back[2][2] = tc1;	back[1][2] = tc2;
			tc1 = back[0][2];	tc2 = back[0][1];
			back[0][2] = tc3;	back[0][1] = tc4;
			back[0][0] = tc1;	back[1][0] = tc2;
			
			tc1=right[0][2]; tc2=right[1][2]; tc3=right[2][2];
			tc4=down[2][2]; tc5=down[2][1]; tc6=down[2][0];
			down[2][2]=tc1; down[2][1]=tc2; down[2][0]=tc3;
			tc1=left[2][0]; tc2=left[1][0]; tc3=left[0][0];
			left[2][0]=tc4; left[1][0]=tc5; left[0][0]=tc6;
			tc4=up[0][0]; tc5=up[0][1]; tc6=up[0][2];
			up[0][0]=tc1; up[0][1]=tc2; up[0][2]=tc3;
			right[0][2]=tc4; right[1][2]=tc5; right[2][2]=tc6;
			break;
			//시계방향
		case '+':
			tc1 = back[0][0];	tc2 = back[1][0];
			back[0][0] = back[2][0];	back[1][0] = back[2][1];
			tc3 = back[0][2];	tc4 = back[0][1];
			back[0][2] = tc1;	back[0][1] = tc2;
			tc1 = back[2][2];	tc2 = back[1][2];
			back[2][2] = tc3;	back[1][2] = tc4;
			back[2][0] = tc1; back[2][1] = tc2;

			tc1=right[0][2]; tc2=right[1][2]; tc3=right[2][2];
			tc4=up[0][0]; tc5=up[0][1]; tc6=up[0][2];
			up[0][0]=tc1; up[0][1]=tc2; up[0][2]=tc3;
			tc1=left[2][0]; tc2=left[1][0]; tc3=left[0][0];
			left[2][0]=tc4; left[1][0]=tc5; left[0][0]=tc6;
			tc4=down[2][2]; tc5=down[2][1]; tc6=down[2][0];
			down[2][2]=tc1; down[2][1]=tc2; down[2][0]=tc3;
			right[0][2]=tc4; right[1][2]=tc5; right[2][2]=tc6;
			break;
		}
	}
	
	public static void left(char dir) {
		//1. left배열 dir방향 회전
		//2. ufdb dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=left[2][0];	tc2=left[2][1];
			left[2][0] = left[0][0];	left[2][1] = left[1][0];
			tc3 = left[2][2];	tc4 = left[1][2];
			left[2][2] = tc1;	left[1][2] = tc2;
			tc1 = left[0][2];	tc2 = left[0][1];
			left[0][2] = tc3;	left[0][1] = tc4;
			left[0][0] = tc1;	left[1][0] = tc2;
			
			tc1=back[0][2]; tc2=back[1][2]; tc3=back[2][2];
			tc4=down[2][0]; tc5=down[1][0]; tc6=down[0][0];
			down[2][0]=tc1; down[1][0]=tc2; down[0][0]=tc3;
			tc1=front[2][0]; tc2=front[1][0]; tc3=front[0][0];
			front[2][0]=tc4; front[1][0]=tc5; front[0][0]=tc6;
			tc4=up[2][0]; tc5=up[1][0]; tc6=up[0][0];
			up[2][0]=tc1; up[1][0]=tc2; up[0][0]=tc3;
			back[0][2]=tc4; back[1][2]=tc5; back[2][2]=tc6;
			break;
			
			//시계방향
		case '+':
			tc1 = left[0][0];	tc2 = left[1][0];
			left[0][0] = left[2][0];	left[1][0] = left[2][1];
			tc3 = left[0][2];	tc4 = left[0][1];
			left[0][2] = tc1;	left[0][1] = tc2;
			tc1 = left[2][2];	tc2 = left[1][2];
			left[2][2] = tc3;	left[1][2] = tc4;
			left[2][0] = tc1; left[2][1] = tc2;

			tc1=back[0][2]; tc2=back[1][2]; tc3=back[2][2];
			tc4=up[2][0]; tc5=up[1][0]; tc6=up[0][0];
			up[2][0]=tc1; up[1][0]=tc2; up[0][0]=tc3;
			tc1=front[2][0]; tc2=front[1][0]; tc3=front[0][0];
			front[2][0]=tc4; front[1][0]=tc5; front[0][0]=tc6;
			tc4=down[2][0]; tc5=down[1][0]; tc6=down[0][0];
			down[2][0]=tc1; down[1][0]=tc2; down[0][0]=tc3;
			back[0][2]=tc4; back[1][2]=tc5; back[2][2]=tc6;
			break;
		}
	}


	public static void right(char dir) {
		//1. right배열 dir방향 회전
		//2. ubdf dir방향 회전
		switch (dir) {
		//반시계방향
		case '-':
			tc1=right[2][0];	tc2=right[2][1];
			right[2][0] = right[0][0];	right[2][1] = right[1][0];
			tc3 = right[2][2];	tc4 = right[1][2];
			right[2][2] = tc1;	right[1][2] = tc2;
			tc1 = right[0][2];	tc2 = right[0][1];
			right[0][2] = tc3;	right[0][1] = tc4;
			right[0][0] = tc1;	right[1][0] = tc2;
			
			tc1=front[0][2]; tc2=front[1][2]; tc3=front[2][2];
			tc4=down[0][2]; tc5=down[1][2]; tc6=down[2][2];
			down[0][2]=tc1; down[1][2]=tc2; down[2][2]=tc3;
			tc1=back[2][0]; tc2=back[1][0]; tc3=back[0][0];
			back[2][0]=tc4; back[1][0]=tc5; back[0][0]=tc6;
			tc4=up[0][2]; tc5=up[1][2]; tc6=up[2][2];
			up[0][2]=tc1; up[1][2]=tc2; up[2][2]=tc3;
			front[0][2]=tc4; front[1][2]=tc5; front[2][2]=tc6;
			break;
			//시계방향
		case '+':
			tc1 = right[0][0];	tc2 = right[1][0];
			right[0][0] = right[2][0];	right[1][0] = right[2][1];
			tc3 = right[0][2];	tc4 = right[0][1];
			right[0][2] = tc1;	right[0][1] = tc2;
			tc1 = right[2][2];	tc2 = right[1][2];
			right[2][2] = tc3;	right[1][2] = tc4;
			right[2][0] = tc1; right[2][1] = tc2;

			tc1=front[0][2]; tc2=front[1][2]; tc3=front[2][2];
			tc4=up[0][2]; tc5=up[1][2]; tc6=up[2][2];
			up[0][2]=tc1; up[1][2]=tc2; up[2][2]=tc3;
			tc1=back[2][0]; tc2=back[1][0]; tc3=back[0][0];
			back[2][0]=tc4; back[1][0]=tc5; back[0][0]=tc6;
			tc4=down[0][2]; tc5=down[1][2]; tc6=down[2][2];
			down[0][2]=tc1; down[1][2]=tc2; down[2][2]=tc3;
			front[0][2]=tc4; front[1][2]=tc5; front[2][2]=tc6;
			break;
		}
	}
	
}
