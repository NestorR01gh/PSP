import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Ej6 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Integer[] nums = new Integer[5];
		
		for(int i = 0; i < nums.length; i++) {
			System.out.print("Introduce número " + (i+1) + ": ");
			nums[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums, Collections.reverseOrder());
		for(Integer num : nums) {
			System.out.println(num);
		}
	}

}
