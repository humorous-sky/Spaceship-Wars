
public class Solve {

	public static void main(String[] args) {
		String s = "8";
		for (int i = 0; i < 21; i ++) {
			s = solve(s);
		}
		System.out.println(s);
	}
	public static String solve(String s) {
		String[] str = (s + " ").split("");
		int count = 1;
		String prev = str[0];
		String res = "";
		for (int i = 1; i < str.length; i ++) {
			prev = str[i - 1];
			if (!prev.equals(str[i])) {
				res += count + "" + prev;
				count = 1;
			} else {
				count++;
			}
		}
		System.out.println(res);
		return res;
	}
}
