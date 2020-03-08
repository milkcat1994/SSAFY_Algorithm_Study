import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -2048(Easy)-
 * 1. 상하좌우 이동에 대한 함수 분할
 * 2. DFS이용한 완전탐색 이후 max값 갱신
 */

//출처 : https://www.acmicpc.net/problem/12100
public class Main_B_G2_12100_2048Easy {
	static int N;
	static int maxResult = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		move(board, 0);
		System.out.println(maxResult);
	} //end main
	
	public static void move(int[][] board, int count) {
		if(count == 5) {
			getMaxBlock(board);
			return;
		}
		int[][] originBoard;
		
		//4방향 움직이기
		originBoard = up(board);
		move(originBoard, count+1);
		
		originBoard = down(board);
		move(originBoard, count+1);
		
		originBoard = left(board);
		move(originBoard, count+1);
		
		originBoard = right(board);
		move(originBoard, count+1);
	}
	
	//UP
	public static int[][] up(int[][] board) {
		int[][] copyBoard = new int[N][N];
		int tInt, sSize;
		boolean first;
		Stack<Integer> stack = new Stack<Integer>();
		//row별로 위로 모으기
		for (int col = 0; col < N; ++col) {
			first = true;
			for (int row = 0; row < N; ++row) {
				tInt = board[row][col];
				if(tInt == 0) continue;
				//처음 들어가는 거면 일단 넣기
				if(first) {
					stack.push(tInt);
					first = false;
				}
				//처음 들어가는게 아니라면 마지막 숫자와 같은지 파악
				else {
					//같다면 stack에서 하나 빼고 2배 값 넣어주기
					if(stack.peek() == tInt) {
						stack.pop();
						stack.push(tInt*2);
						first = true;
					}
					//같지 않다면 다음 들어오는값은 다시 위 과정 반복 해야한다.
					else {
						stack.push(tInt);
					}
				}
			}
			//하나의 row다 보았다면 stack다돌면서 해당 col다 채우기
			sSize = stack.size();
			
			for(int row = sSize-1; row>=0; --row) {
				copyBoard[row][col] = stack.pop();
			}
		}
		return copyBoard;
	}
	
	//DOWN
	public static int[][] down(int[][] board) {
		int[][] copyBoard = new int[N][N];
		int tInt, sSize;
		boolean first;
		Stack<Integer> stack = new Stack<Integer>();
		for (int col = 0; col < N; ++col) {
			first = true;
			for (int row = N-1; row >= 0; --row) {
				tInt = board[row][col];
				if(tInt == 0) continue;
				if(first) {
					stack.push(tInt);
					first = false;
				}
				else {
					if(stack.peek() == tInt) {
						stack.pop();
						stack.push(tInt*2);
						first = true;
					}
					else {
						stack.push(tInt);
					}
				}
			}
			sSize = stack.size();
			
			for(int row = N-sSize; row<N; ++row) {
				copyBoard[row][col] = stack.pop();
			}
		}
		return copyBoard;
	}

	//LEFT
	public static int[][] left(int[][] board) {
		int[][] copyBoard = new int[N][N];
		int tInt, sSize;
		boolean first;
		Stack<Integer> stack = new Stack<Integer>();
		for (int row = 0; row < N; ++row) {
			first = true;
			for (int col = 0; col < N; ++col) {
				tInt = board[row][col];
				if(tInt == 0) continue;
				if(first) {
					stack.push(tInt);
					first = false;
				}
				else {
					if(stack.peek() == tInt) {
						stack.pop();
						stack.push(tInt*2);
						first = true;
					}
					else {
						stack.push(tInt);
					}
				}
			}
			sSize = stack.size();
			
			for(int col = sSize-1; col>=0; --col) {
				copyBoard[row][col] = stack.pop();
			}
		}
		return copyBoard;
	}

	//RIGHT
	public static int[][] right(int[][] board) {
		int[][] copyBoard = new int[N][N];
		int tInt, sSize;
		boolean first;
		Stack<Integer> stack = new Stack<Integer>();
		for (int row = 0; row < N; ++row) {
			first = true;
			for (int col = N-1; col >= 0; --col) {
				tInt = board[row][col];
				if(tInt == 0) continue;
				if(first) {
					stack.push(tInt);
					first = false;
				}
				else {
					if(stack.peek() == tInt) {
						stack.pop();
						stack.push(tInt*2);
						first = true;
					}
					else {
						stack.push(tInt);
					}
				}
			}
			sSize = stack.size();

			for(int col = N-sSize; col<N; ++col) {
				copyBoard[row][col] = stack.pop();
			}
		}
		return copyBoard;
	}
	
	public static void getMaxBlock(int[][] board) {
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				maxResult = maxResult < board[row][col] ? board[row][col] : maxResult;
			}
		}
	}
}
