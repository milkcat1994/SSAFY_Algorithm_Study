import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -최대상금-
 * 1. 맨 앞부터 최대값으로 교체 해 나갈 것이다.
 * 2. 자신(앞쪽)을 제외한 가장 큰값중 마지막의 자리와 교체한다.
 * 3. 자신이 가장 큰값이라면 swap하지 않고 다음 index로 넘긴다.
 * 4. 자신을 제외한 가장 큰값이 이전에 교체한 값과 같다면 교체된 값들을 비교하여 큰값이 앞쪽으로 올 수 있도록 한다.
 * 5. 만약 swap이후 최대값이라면 남은 swap이 짝수라면 그대로 종료
 * └──홀수라면 맨 마지막 index끼리 교체하고 종료한다.
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do
public class Solution_D3_1244_최대상금 {
	static int ssize;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		int[] arr, maxArr;
		int swap, max,maxIdx,exMax,exIdx;
		String tempStr;
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			tempStr = st.nextToken();
			swap = Integer.parseInt(st.nextToken());
			ssize = tempStr.length();
			arr = new int[ssize];
			maxArr = new int[ssize];
			
			//바꾸기 이전 숫자 배열 저장
			for(int i = 0; i < ssize; ++i) {
				arr[i] = tempStr.charAt(i) - '0';
				maxArr[i] = arr[i];
			}
			max = 0;	maxIdx=0;
			exMax = -1; exIdx=-1;
			//해당 배열의 최대값 미리 얻기
			getMax(maxArr);
			
			//맨앞쪽부터 가장 큰 수로 채워나갈 예정
			for(int i = 0; i < ssize; ++i) {
				//바꾸기 횟수 다 사용했다면 종료
				if(swap <= 0)
					break;
				max = arr[i];
				maxIdx = i;
				
				//i다음 부터 가장 큰 수 찾기 -> 현재 자리와 교환할 가장 큰 값 찾기
				for(int j = i+1; j < ssize; ++j) {
					if(max <= arr[j]) {
						max = arr[j];
						maxIdx = j;
					}
				}
				
				//자기 자신이 가장 큰 값이라면 다음 위치 찾기
				if(maxIdx == i)
					continue;
				else {
					//해당 위치와 바꾸기
					swap(arr, i, maxIdx);
					//이전 최대값과 현재 최대값이 같다면
					//이전 idx와 현재 idx와의 대소 비교 이후 swap
					if(exMax == arr[i]) {
						//exIdx는 현재 maxIdx보다 항상 뒤이다.
						if(arr[exIdx] > arr[maxIdx])
							swap(arr, exIdx, maxIdx);
					}
					//바뀜을 당한 자리 저장
					exMax = arr[i];
					exIdx = maxIdx;
					swap--;
				}

				//최대값을 찾았다면
				if(isSame(arr, maxArr)) {
					//남은 변경 횟수가 홀수일때 마지막 2개 교환
					if(swap%2 == 1)
						swap(arr, ssize-1, ssize-2);
					break;
				}
			}
			
			//최대 값 출력
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < ssize; ++i) {
				sb.append(arr[i]);
			}
			sb.append('\n');
			
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	//두개의 배열이 같은지 확인
	public static boolean isSame(int[] arr, int[] arr2) {
		for(int i = 0; i < ssize; ++i) {
			if(arr[i] != arr2[i])
				return false;
		}
		return true;
	}

	//내림차순 정렬
	public static void getMax(int[] arr) {
		int tempInt;
		for(int i = 0; i < ssize; ++i) {
			for(int j = i+1; j < ssize; ++j) {
				if(arr[i] < arr[j]) {
					tempInt = arr[i];
					arr[i] = arr[j];
					arr[j] = tempInt;
				}
			}
		}
	}
	
	//tgtIdx와 maxIdx의 교환
	public static void swap(int[] arr, int tgtIdx, int maxIdx) {
		int temp = arr[maxIdx];
		arr[maxIdx] = arr[tgtIdx];
		arr[tgtIdx] = temp;
	}
}
