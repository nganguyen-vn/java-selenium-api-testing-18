package automationfc;

public class Topic_06_Replace {

	public static void main(String[] args) {
	
		String inputAddress= "11 Nguyen Trai\nHai Chau\nDa Nang";
		String outputAddress= "11 Nguyen Trai Hai Chau Da Nang";
		inputAddress= inputAddress.replace("\n", " ");

	}

}
