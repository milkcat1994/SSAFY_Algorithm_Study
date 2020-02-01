import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * -입력-
 * 첫째 줄에 N (2 ≤ N ≤ 100)과 L (1 ≤ L ≤ N)이 주어진다.
 * 둘째 줄부터 N개의 줄에 지도가 주어진다.
 * 각 칸의 높이는 10보다 작거나 같은 자연수이다.
 */

/*
 * -출력-
 * 첫째 줄에 지나갈 수 있는 길의 개수를 출력한다.
 */

// 출처 : https://www.acmicpc.net/problem/14890
public class Solution_14890 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// N과 L 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[N][N];

		// 배열 초기화
		String tempString;
		for (int row = 0; row < N; ++row) {
			tempString = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = tempString.charAt(col*2);
			}
		}
		
		boolean isGood = true;
		char firstChar = ' ', curChar, exChar = ' ';
		// 현재 까지 보관 갯수
		int curNum = 0;
		// 다음부터 필요한 동일한 값의 개수
		int needBase = 0;
		int result = 0;
		
		// row기준 서치
		for (int row = 0; row < N; ++row) {
			C:for (int col = 0; col < N; ++col) {
				curChar = board[row][col];
				// 만일 비어있다면
				if(curNum == 0 && firstChar == ' ')  {
					curNum++;
					exChar = curChar;
					continue;
				}
				
				// 이전값이 1작음
				if(exChar+1 == curChar) {
					// 현재 보관갯수가 L보다 작다면 false임
					if(curNum < L) {
						isGood = false;
						break C;
					}
					//만일 크다면 needBase가 0일때만 현재 보관갯수=1(현재갯수)
					else {
						if(needBase == 0) {
							curNum=1;
						}
						else {
							isGood = false;
							break C;
						}
					}
				}
				// 이전값이 1크다면
				else if(exChar == curChar+1) {
					// needBase==0이라면 needBase 증가후 현재 보관갯수=1(현재갯수)
					if(needBase == 0) {
						// 다음부터 L만큼 현재 넣을 값의 개수 필요
						needBase++;
						curNum = 1;
					}
					// needBase가 0이 아니라면 L이어야 넣을 수 있다.
					else {
						if(needBase == L) {
							needBase = 1;
							curNum = 1;
						}
						else {
							isGood = false;
							break C;
						}
					}
				}
				// 이전값과 현재값이 같다면 needBase가 있는지 확인후 넣기
				else if(exChar == curChar) {
					// needBase가 0이라면 보관갯수 증가
					if(needBase == 0) {
						curNum++;
					}
					// needBase가 1이상이라면 L만큼인지 확인
					else {
						// needBase == L이라면 현재 보관갯수=1(현재갯수)
						if(needBase == L) {
							needBase = 0;
							curNum = 1;
						}
						// needBase != L이라면 needBase++ 이후 현재 갯수 증가
						else {
							needBase++;
							curNum++;
						} //end if(needBase == L)
					} //end if(needBase == 0
				}
				// 차이가 1이 아닌경우
				else {
					isGood = false;
					break C;
				} //end if(exChar ? curChar)
				exChar = curChar;
			} //end C:for(col)
		
			// 잘 넣었다면 결과값 1증가
			if(isGood && (needBase == 0 || needBase == L)) {
				result++;
			}
			isGood = true;
			curNum = 0;
			needBase = 0;
			firstChar = ' ';
			exChar = ' ';
		} //end for(row)
		
		// col기준 서치
		for (int col = 0; col < N; ++col) {
			R:for (int row = 0; row < N; ++row) {
				curChar = board[row][col];
				// 만일 비어있다면
				if(curNum == 0 && firstChar == ' ')  {
					curNum++;
					exChar = curChar;
					continue;
				}
				
				// 이전값이 1작음
				if(exChar+1 == curChar) {
					// 현재 보관갯수가 L보다 작다면 false임
					if(curNum < L) {
						isGood = false;
						break R;
					}
					//만일 크다면 needBase가 0일때만 현재 보관갯수=1(현재갯수)
					else {
						if(needBase == 0) {
							curNum=1;
						}
						else {
							isGood = false;
							break R;
						}
					}
				}
				// 이전값이 1크다면
				else if(exChar == curChar+1) {
					// needBase==0이라면 needBase 증가후 현재 보관갯수=1(현재갯수)
					if(needBase == 0) {
						// 다음부터 L만큼 현재 넣을 값의 개수 필요
						needBase++;
						curNum = 1;
					}
					// needBase가 0이 아니라면 L이어야 넣을 수 있다.
					else {
						if(needBase == L) {
							needBase = 1;
							curNum = 1;
						}
						else {
							isGood = false;
							break R;
						}
					}
				}
				// 이전값과 현재값이 같다면 needBase가 있는지 확인후 넣기
				else if(exChar == curChar) {
					// needBase가 0이라면 보관갯수 증가
					if(needBase == 0) {
						curNum++;
					}
					// needBase가 1이상이라면 L만큼인지 확인
					else {
						// needBase == L이라면 현재 보관갯수=1(현재갯수)
						if(needBase == L) {
							needBase = 0;
							curNum = 1;
						}
						// needBase != L이라면 needBase++ 이후 현재 갯수 증가
						else {
							needBase++;
							curNum++;
						} //end if(needBase == L)
					} //end if(needBase == 0
				}
				// 차이가 1이 아닌경우
				else {
					isGood = false;
					break R;
				} //end if(exChar ? curChar)
				exChar = curChar;
			} //end R:for(row)
			if(isGood && (needBase == 0 || needBase == L)) {
				result++;
			}
			isGood = true;
			curNum = 0;
			needBase = 0;
			firstChar = ' ';
			exChar = ' ';
		} //end for(col)
		
		System.out.println(result);
	}
}
