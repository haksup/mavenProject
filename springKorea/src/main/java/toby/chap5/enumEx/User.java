package toby.chap5.enumEx;

public class User {
	private static final int BASIC = 1;
	private static final int SILVER = 2;
	private static final int GOLD = 3;
	
	int level;
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void levelScope(){
		setLevel(1);
		if(level == BASIC){
			System.out.println("BASIC");
		}
		
//		User user = new User();
//		user.level = 0;
	}
	
	public static void main(String args[]){
		User user = new User();
		user.levelScope();
	}
}
