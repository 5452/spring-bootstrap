package la.ipo.util;




/**
 * Redis键生成类，防止在代码里硬编码，后续可以继续扩展
 * 
 * 2014年2月20日 下午4:29:20
 * @author WANGJUN
 * 
 */
abstract class KeyUtils {
	static final String UID = "uid:";

	static String active(String uid) {
		return UID + uid + ":active";
	}

	static String resetPassword(String uid) {
		return UID + uid + ":reset_password";
	}

}