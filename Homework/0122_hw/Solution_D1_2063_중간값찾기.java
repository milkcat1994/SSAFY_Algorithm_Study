import java.util.Scanner;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QPsXKA2UDFAUq&categoryId=AV5QPsXKA2UDFAUq&categoryType=CODE

class Solution_D1_2063_중간값찾기
{	
	final static int MAX_SCORE = 100;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		sc.nextLine();
        
		int result = 0;
        //점수를 Line으로 받아 저장
		String tempStr = sc.nextLine();
		int[] intArr = new int[MAX_SCORE+1];
//		점수 개수를 배열에 저장한다.
		for(String num : tempStr.split(" ")) {
			intArr[Integer.parseInt(num)]++;
		}
		
		int sum = 0;
		int targetResult = T/2;
        
        //중간값을 찾기 위한 for문
		for(int index = 0; index < intArr.length; ++index) {
            //중간위치값이 더 크다면 해당 숫자의 갯수를 더해준다.
			if(sum < targetResult) {
				sum += intArr[index];
			}
            //중간위치값이 지금까지의 숫자의 수 이하라면 지금 숫자가 중간위치를 포함하고있다.
			else {
				result = index;
				break;
			}
		}
		System.out.println(result);
		sc.close();
    }
}