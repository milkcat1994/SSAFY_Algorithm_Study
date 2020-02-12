import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -연산자 끼워넣기-
 * 1. 각 연산자의 개수를 미리 저장하고
 * 2. calculate 함수에서 각 연산자를 하나씩 꺼내가며 최종 값을 구해나간다.
 * 3. 모든 연산자를 선택했다면 최소, 최대값을 갱신한다.
 */

//출처 : https://www.acmicpc.net/problem/14888
public class Solution_14888 {
	// 출력할 max값, min값
	static int maxResult = Integer.MIN_VALUE;
	static int minResult = Integer.MAX_VALUE;
	// 수의 개수
	static int numNum;
	// 사용할 수의 배열
	static int[] num;
	// 사용할 연산자의 개수
	static int[] operation;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tempStringArr;
		// 사용할 연산자의 개수
		operation = new int[4];
		
		numNum= Integer.parseInt(br.readLine());
		num = new int[numNum];
		
		// 사용할 수의 배열 초기화
		tempStringArr = br.readLine().split(" ");
		for(int i = 0; i < numNum; ++i) {
			num[i] = Integer.parseInt(tempStringArr[i]);
		}
		
		// 사용할 연산자의 수 초기화
		tempStringArr = br.readLine().split(" ");
		for(int i = 0; i < 4; ++i) {
			operation[i] = Integer.parseInt(tempStringArr[i]);
		}
			
		calculate(1, num[0]);
		
		//각 최대값, 최소값 출력
		System.out.println(maxResult);
		System.out.println(minResult);
	}
	
	// 현재 operation의 index, 지금까지의 계산값
	private static void calculate(int index, int result) {
		// 만약 끝까지 다 선택했다면 최고,최소값 갱신 이후 다음 operation보기
		if(index == numNum) {
			maxResult = maxResult > result ? maxResult : result;
			minResult = minResult > result ? result : minResult;
			return;
		}
		
		// 4가지 Operation을 모두 돌것이다.
		for(int i = 0; i < 4; ++i) {
			// 해당 Operation이 없다면 다음 operation 찾기
			if(operation[i] == 0) continue;
			
			//해당 Operation이 있다면 해당 Operation - 이후 계산
			operation[i]--;
			switch (i) {
			case 0:
				calculate(index+1, result + num[index]);
				break;
			case 1:
				calculate(index+1, result - num[index]);
				break;
			case 2:
				calculate(index+1, result * num[index]);
				break;
				// case 3:
			default:
				calculate(index+1, result / num[index]);
				break;
			}//end switch
			//빠져 나왔을 시 해당 Operation 개수 증가
			operation[i]++;
		}// end for(i)
	}
}
