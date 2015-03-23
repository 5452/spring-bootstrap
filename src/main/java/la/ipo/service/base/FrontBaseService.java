package la.ipo.service.base;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "front", isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
public interface FrontBaseService {

}
