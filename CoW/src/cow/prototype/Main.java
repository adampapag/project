package cow.prototype;


public class Main {

	public static void main(String[] args) {

		String[] args2 = new String[50];
		args2[0] = "1";
		args2[1] = "2";
		args2[2] = "2";
		args2[3] = "1";
//		args2[4] = "3";
//		args2[5] = "33";
		args2[49] = "121";
		System.out.println(new MorphismRequestHandler().handle(args2).get(0).getString());

	}
}
