import java.io.BufferedReader;
import java.io.InputStreamReader;
 
/*
 * -그릇-
 * 1. 이전 괄호 상태에 따라 10을 더할지 5를 더할지 판단하는 문제
 */

// 출처 : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1865&sca=2050
public class Main_J_2604_그릇 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 괄호 양식 저장
        String tempString;
         
        // 이전 괄호 형식 저장
        int prevChar = ' ';
        int result = 0;
         
        tempString = br.readLine();
        int strSize = tempString.length();
         
        // 한칸 씩 확인
        for(int i = 0; i < strSize; ++i) {
            if(prevChar != tempString.charAt(i)) {
                result += 10;
                prevChar = tempString.charAt(i);
            }
            else {
                result += 5;
            }
        }
        // end for
        System.out.println(result);
    }
}