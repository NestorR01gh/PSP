
public class Ej5 {

	public static int numMayor(int[] nums) {
		int mayor = nums[0];
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] > mayor) {
				mayor = nums[i];
			}
		}
		return mayor;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 3, 5, 3, 2, 4, 44, 42, 2, 4, 34};
		System.out.print(numMayor(nums));
	}

}
