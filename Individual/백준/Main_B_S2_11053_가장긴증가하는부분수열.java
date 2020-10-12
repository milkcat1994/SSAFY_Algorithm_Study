
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S2_11053_가장긴증가하는부분수열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] res = new int[N];
		int size = 1;
		res[0] = arr[0];
		for(int i = 1; i < N; ++i) {
			if(res[size-1] < arr[i]) {
				res[size] = arr[i];
				size++;
			}
			else {
				for(int j = 0; j < size; ++j) {
					if(arr[i] <= res[j]) {
						res[j] = arr[i];
						break;
					}
				}
			}
		}
		System.out.print(size);
		
	}
}
