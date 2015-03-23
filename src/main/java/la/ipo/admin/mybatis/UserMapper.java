/**
 * 
 */
package la.ipo.admin.mybatis;

import java.util.List;

import la.ipo.admin.model.User;

/**
 * 2014年3月24日 下午2:49:20
 * @author WANGJUN
 *
 */
public interface UserMapper {

	public User getById(Long id);
	
	public User getByEmail(String email);
	
	public List<User> getByParams(User user);
	
	public void add(User user);
	
	public void update(User user);
	
	public void delete(Long id);
	
	public List<User> findAllByPage();
}
