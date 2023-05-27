package nstarlike.jcw.util;

public class Validator {
	private static final String ID_REGEX = "^[a-zA-Z]+[0-9a-zA-Z]{7,}$";
	private static final String PASSWORD_REGEX = "(?i).{6,20}";
	private static final String CELL1_REGEX = "^01[016789]$";
	private static final String CELL2_REGEX = "^[0-9]{3,4}$";
	private static final String CELL3_REGEX = "^[0-9]{4}$";
	private static final String CELL_REGEX = "^01[016789]-[0-9]{3,4}-[0-9]{4}$";
	private static final String TEL1_REGEX = "^0[0-9]{1,2}$";
	private static final String TEL2_REGEX = "^[0-9]{3,4}$";
	private static final String TEL3_REGEX = "^[0-9]{4}$";
	private static final String TEL_REGEX = "^0[0-9]{1,2}-[0-9]{3,4}-[0-9]{4}$";
	private static final String EMAIL1_REGEX = "^[a-z0-9\\-_]+$";
	private static final String EMAIL2_REGEX = "^[a-z0-9\\-_]+@(\\.[a-z0-9\\-_]+){1,2}$";
	private static final String EMAIL_REGEX = "^[a-z0-9\\-_]+@[a-z0-9\\-_]+(\\.[a-z0-9\\-_]+){1,2}$";
	private static final String ZIPCODE_REGEX = "^[0-9]{5}$";
	private static final String KOREAN_REGEX = "^[가-힣]+$";
	private static final String NUMBER_REGEX = "^[0-9]+$";
	private static final String CHINESE_CHARACTER_REGEX = "^[一-龥]+$";
	
	public static void regex(String value, String regex, String msg) throws ValidatorInvalidException {
		if(!value.matches(regex)) {
			throw new ValidatorInvalidException(msg);
		}
	}
	
	public static void regex(String value, String regex) throws ValidatorInvalidException {
		regex(value, regex, "형식이 올바르지 않습니다.");
	}
	
	public static void id(String value, String msg) throws ValidatorInvalidException {
		regex(value, ID_REGEX, msg);
	}
	
	public static void id(String value) throws ValidatorInvalidException {
		id(value, "아이디 형식이 올바르지 않습니다.");
	}
	
	public static void password(String value, String msg) throws ValidatorInvalidException {
		regex(value, PASSWORD_REGEX, msg);
	}
	
	public static void password(String value) throws ValidatorInvalidException {
		password(value, "비밀번호 형식이 올바르지 않습니다.");
	}
	
	public static void cell1(String value, String msg) throws ValidatorInvalidException {
		regex(value, CELL1_REGEX, msg);
	}
	
	public static void cell1(String value) throws ValidatorInvalidException {
		cell1(value, "휴대폰 통신망번호 형식이 올바르지 않습니다.");
	}
	
	public static void cell2(String value, String msg) throws ValidatorInvalidException {
		regex(value, CELL2_REGEX, msg);
	}
	
	public static void cell2(String value) throws ValidatorInvalidException {
		cell2(value, "휴대폰 지역번호 형식이 올바르지 않습니다.");
	}
	
	public static void cell3(String value, String msg) throws ValidatorInvalidException {
		regex(value, CELL3_REGEX, msg);
	}
	
	public static void cell3(String value) throws ValidatorInvalidException {
		cell3(value, "휴대폰 가입자번호 형식이 올바르지 않습니다.");
	}
	
	public static void cell(String value, String msg) throws ValidatorInvalidException {
		regex(value, CELL_REGEX, msg);
	}
	
	public static void cell(String value) throws ValidatorInvalidException {
		cell(value, "휴대폰 형식이 올바르지 않습니다.");
	}
	
	public static void tel1(String value, String msg) throws ValidatorInvalidException {
		regex(value, TEL1_REGEX, msg);
	}
	
	public static void tel1(String value) throws ValidatorInvalidException {
		tel1(value, "전화번호 국번 형식이 올바르지 않습니다.");
	}
	
	public static void tel2(String value, String msg) throws ValidatorInvalidException {
		regex(value, TEL2_REGEX, msg);
	}
	
	public static void tel2(String value) throws ValidatorInvalidException {
		tel2(value, "전화번호 지역번호 형식이 올바르지 않습니다.");
	}
	
	public static void tel3(String value, String msg) throws ValidatorInvalidException {
		regex(value, TEL3_REGEX, msg);
	}
	
	public static void tel3(String value) throws ValidatorInvalidException {
		tel3(value, "전화번호 가입자번호 형식이 올바르지 않습니다.");
	}
	
	public static void tel(String value, String msg) throws ValidatorInvalidException {
		regex(value, TEL_REGEX, msg);
	}
	
	public static void tel(String value) throws ValidatorInvalidException {
		tel(value, "전화번호 형식이 올바르지 않습니다.");
	}
	
	public static void email1(String value, String msg) throws ValidatorInvalidException {
		regex(value, EMAIL1_REGEX, msg);
	}
	
	public static void email1(String value) throws ValidatorInvalidException {
		email1(value, "이메일 아이디 형식이 올바르지 않습니다.");
	}
	
	public static void email2(String value, String msg) throws ValidatorInvalidException {
		regex(value, EMAIL2_REGEX, msg);
	}
	
	public static void email2(String value) throws ValidatorInvalidException {
		email2(value, "이메일 도메인 형식이 올바르지 않습니다.");
	}
	
	public static void email(String value, String msg) throws ValidatorInvalidException {
		regex(value, EMAIL_REGEX, msg);
	}
	
	public static void email(String value) throws ValidatorInvalidException {
		email(value, "이메일 형식이 올바르지 않습니다.");
	}
	
	public static void zipcode(String value, String msg) throws ValidatorInvalidException {
		regex(value, ZIPCODE_REGEX, msg);
	}
	
	public static void zipcode(String value) throws ValidatorInvalidException {
		zipcode(value, "우편번호 형식이 올바르지 않습니다.");
	}
	
	public static void korean(String value, String msg) throws ValidatorInvalidException {
		regex(value, KOREAN_REGEX, msg);
	}
	
	public static void number(String value, String msg) throws ValidatorInvalidException {
		regex(value, NUMBER_REGEX, msg);
	}
	
	public static void chinese(String value, String msg) throws ValidatorInvalidException {
		regex(value, CHINESE_CHARACTER_REGEX, msg);
	}
}
