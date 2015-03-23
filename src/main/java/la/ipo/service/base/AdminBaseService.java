package la.ipo.service.base;

import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "admin", rollbackFor = Exception.class)
public interface AdminBaseService {

}
