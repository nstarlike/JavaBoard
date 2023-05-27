package nstarlike.jcw.util;

public class Validator {
	private static final String LOGIN_ID_REGEX = "^[a-zA-Z]+[0-9a-zA-Z]{7,}$";
	private static final String PASSWORD_REGEX = "(?i).{6,20}";
	private static final String KOREAN_NAME_REGEX = "^[가-힣0-9]+$";
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
	
	public static String regex(String value, String regex, String msg) throws ValidatorInvalidException {
		if(!value.matches(regex)) {
			throw new ValidatorInvalidException(msg);
		}
		
		return value;
	}
	
	public static String regex(String value, String regex) throws ValidatorInvalidException {
		return regex(value, regex, "The format of value is invalid.");
	}
	
	public static String loginId(String value, String msg) throws ValidatorInvalidException {
		return regex(value, LOGIN_ID_REGEX, msg);
	}
	
	public static String loginId(String value) throws ValidatorInvalidException {
		return loginId(value, "The format of your id is invalid.");
	}
	
	public static String password(String value, String msg) throws ValidatorInvalidException {
		return regex(value, PASSWORD_REGEX, msg);
	}
	
	public static String password(String value) throws ValidatorInvalidException {
		return password(value, "The format of your password is invalid.");
	}
	
	public static String koreanName(String value, String msg) throws ValidatorInvalidException {
		return regex(value, KOREAN_NAME_REGEX, msg);
	}
	
	public static String koreanName(String value) throws ValidatorInvalidException {
		return koreanName(value, "The format of your name is invalid.");
	}
	
	public static String cell1(String value, String msg) throws ValidatorInvalidException {
		return regex(value, CELL1_REGEX, msg);
	}
	
	public static String cell1(String value) throws ValidatorInvalidException {
		return cell1(value, "The format of area code of your cell number is invalid.");
	}
	
	public static String cell2(String value, String msg) throws ValidatorInvalidException {
		return regex(value, CELL2_REGEX, msg);
	}
	
	public static String cell2(String value) throws ValidatorInvalidException {
		return cell2(value, "The format of exchange code of your cell number is invalid.");
	}
	
	public static String cell3(String value, String msg) throws ValidatorInvalidException {
		return regex(value, CELL3_REGEX, msg);
	}
	
	public static String cell3(String value) throws ValidatorInvalidException {
		return cell3(value, "The format of carrier code of your cell number is invalid.");
	}
	
	public static String cell(String value, String msg) throws ValidatorInvalidException {
		return regex(value, CELL_REGEX, msg);
	}
	
	public static String cell(String value) throws ValidatorInvalidException {
		return cell(value, "The format of your cell number is invalid.");
	}
	
	public static String tel1(String value, String msg) throws ValidatorInvalidException {
		return regex(value, TEL1_REGEX, msg);
	}
	
	public static String tel1(String value) throws ValidatorInvalidException {
		return tel1(value, "The format of area code of your tel number is invalid.");
	}
	
	public static String tel2(String value, String msg) throws ValidatorInvalidException {
		return regex(value, TEL2_REGEX, msg);
	}
	
	public static String tel2(String value) throws ValidatorInvalidException {
		return tel2(value, "The format of exchange code of your tel number is invalid.");
	}
	
	public static String tel3(String value, String msg) throws ValidatorInvalidException {
		return regex(value, TEL3_REGEX, msg);
	}
	
	public static String tel3(String value) throws ValidatorInvalidException {
		return tel3(value, "The format of carrier code of your tel number is invalid.");
	}
	
	public static String tel(String value, String msg) throws ValidatorInvalidException {
		return regex(value, TEL_REGEX, msg);
	}
	
	public static String tel(String value) throws ValidatorInvalidException {
		return tel(value, "The format of your tel number is invalid.");
	}
	
	public static String email1(String value, String msg) throws ValidatorInvalidException {
		return regex(value, EMAIL1_REGEX, msg);
	}
	
	public static String email1(String value) throws ValidatorInvalidException {
		return email1(value, "The format of local part of your email is invalid.");
	}
	
	public static String email2(String value, String msg) throws ValidatorInvalidException {
		return regex(value, EMAIL2_REGEX, msg);
	}
	
	public static String email2(String value) throws ValidatorInvalidException {
		return email2(value, "The format of domain of your email is invalid.");
	}
	
	public static String email(String value, String msg) throws ValidatorInvalidException {
		return regex(value, EMAIL_REGEX, msg);
	}
	
	public static String email(String value) throws ValidatorInvalidException {
		return email(value, "The format of your email is invalid.");
	}
	
	public static String zipcode(String value, String msg) throws ValidatorInvalidException {
		return regex(value, ZIPCODE_REGEX, msg);
	}
	
	public static String zipcode(String value) throws ValidatorInvalidException {
		return zipcode(value, "The format of your zipcode is invalid.");
	}
	
	public static String korean(String value, String msg) throws ValidatorInvalidException {
		return regex(value, KOREAN_REGEX, msg);
	}
	
	public static String number(String value, String msg) throws ValidatorInvalidException {
		return regex(value, NUMBER_REGEX, msg);
	}
	
	public static String chineseCharacter(String value, String msg) throws ValidatorInvalidException {
		return regex(value, CHINESE_CHARACTER_REGEX, msg);
	}
	
	public static String empty(String value, String msg) throws ValidatorInvalidException {
		if(value == null || value.isEmpty()) {
			throw new ValidatorInvalidException("You must enter a value.");
		}
		
		return value;
	}
}
