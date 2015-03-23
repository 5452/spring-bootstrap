/**
 * 
 */
package la.ipo;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 2014年1月24日 下午12:08:33
 * @author WANGJUN
 *
 */
public class CryptTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CryptTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String salt = "";
		String password = "wangjun";
		String hashedPassword = "";
		for(int i = 0; i < 3; i++) {
			salt = BCrypt.gensalt();
			hashedPassword = BCrypt.hashpw(password, salt);
			LOGGER.info("salt:" + salt);
			LOGGER.info("hashedPassword:" + hashedPassword);
			LOGGER.info("" + BCrypt.checkpw(password, hashedPassword));
		}
		System.exit(0);
	}

}
