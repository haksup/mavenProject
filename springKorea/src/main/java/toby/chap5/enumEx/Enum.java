package toby.chap5.enumEx;

public class Enum {
//	public enum Level{
//		BASIC, SILVER, GOLD
//	}
	
	public enum Level{
		BASIC(1), SILVER(2), GOLD(3);
		
		private final int value;
		
		Level(int value){
			this.value = value;
		}
		
		public int intValue(){
			return value;
		}
		
		public static Level valueOf(int value){
			switch(value){
			case 1 : return BASIC;
			case 2 : return SILVER;
			case 4 : return GOLD;
			default : throw new AssertionError("Unknown value: " + value);
			}
		}
	}
	
	public enum LevelStr{
		BASIC("기본"), SILVER("은"), GOLD("금");
		
		private final String value;
		
		LevelStr(String value){
			this.value = value;
		}
		
		public String StringValue(){
			return value;
		}
		
		public static LevelStr valueOfSting(String value){
			switch(value){
			case "기본" : return BASIC;
			case "은" : return SILVER;
			case "금" : return GOLD;
			default : throw new AssertionError("Unknown value: " + value);
			}
		}
	}
	
	public static void main(String args[]){
		System.out.println(Level.BASIC);
		System.out.println(Level.BASIC.intValue());
		System.out.println(Level.valueOf(1));
		System.out.println(Level.BASIC.value);
		
		System.out.println(LevelStr.BASIC.StringValue());
	}
}
