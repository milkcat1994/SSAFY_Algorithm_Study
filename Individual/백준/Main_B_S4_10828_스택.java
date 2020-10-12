import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
 * -스택-
 * push X
 * pop   -> 나온 숫자 출력
 * size  -> 개수 출력
 * empty -> 비어있으면 1, 아니면 0 출력
 * top   -> 가장 위에 있는 정수 출력, 없다면 -1 출력
 */
import java.util.List;

//출처 : https://www.acmicpc.net/problem/10828
public class Main_B_S4_10828_스택 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        String operation;
        String[] splitString;
        N = Integer.parseInt(br.readLine());
        int listSize = 0;

        for(int i = 0; i < N; ++i) {
            //명령어 입력
            operation = br.readLine();
            //push일때
            if(operation.contains("push")){
                splitString = operation.split(" ");
                list.add(Integer.parseInt(splitString[1]));
                listSize++;
            }
            else{
                switch (operation) {
                    case "pop":
                        if(list.isEmpty())
                            sb.append("-1").append('\n');
                        else{
                            sb.append(list.get(listSize-1)).append('\n');
                            list.remove(listSize-1);
                            listSize--;
                        }
                        break;
                    case "size":
                        sb.append(listSize).append('\n');
                        break;
                    case "empty":
                        if(list.isEmpty())
                           sb.append(1).append('\n');
                        else
                           sb.append(0).append('\n');
                           break;
                    case "top":
                        if(list.isEmpty())
                            sb.append("-1").append('\n');
                        else
                            sb.append(list.get(listSize-1)).append('\n');
                    break;
                    default:
                        break;
                }
            }
        }

        System.out.println(sb.toString());
	}
}