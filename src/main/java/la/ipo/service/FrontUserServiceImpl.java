/**
 * 
 */
package la.ipo.service;

import java.util.List;

import la.ipo.model.User;
import la.ipo.model.UserExample;
import la.ipo.repository.mybatis.FrontUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 2014年4月2日 下午5:13:54
 * @author Administrator
 *
 */
@Service("frontUserService")
@Component("frontUserService")
public class FrontUserServiceImpl implements FrontUserService  {

	@Autowired
	private FrontUserMapper frontUserMapper;
	
	@Override
	public User getUserById(Long id) {
		return frontUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> getUserByParam(UserExample example) {
		return frontUserMapper.selectByExample(example);
	}

	@Override
	public User getUserByEmail(String email) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		List<User> users = this.getUserByParam(example);
		if(users != null && users.size() ==1)
			return users.get(0);
		return new User();
	}

	@Override
	public void addUser(User user) {
		frontUserMapper.insert(user);
	}

	@Override
	public void updateUser(User user) {
		frontUserMapper.updateByPrimaryKey(user);
	}

	@Override
	public void deleteUser(Long id) {
		frontUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> findAll() {
		UserExample example = new UserExample();
		example.createCriteria();
		return this.getUserByParam(example);
	}

}
