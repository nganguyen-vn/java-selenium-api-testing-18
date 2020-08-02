package JavaTester;

public class Topic_01_Parameter {
	//param

	public static void main(String[] args) {
		System.out.println(showName());
		System.out.println(showName("Nguyen Nga"));
		System.out.println(showName("Nguyen", "Nga"));
	}
public static String showName() {
	return "Nga Nguyen";
	
}

public static String showName(String fullName) {
return fullName;

}
public static String showName(String firstName, String lastName) {
return firstName + " "+ lastName;

}
}
