package jun18;

public class maxarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = arrayutils.takeinput();
		arrayutils.display(arr);

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}

		}
		System.out.println(max);

	}

}
