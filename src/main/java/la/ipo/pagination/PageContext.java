package la.ipo.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageContext extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 485665978330471580L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PageContext.class);

	private static ThreadLocal<PageContext> context = new ThreadLocal<PageContext>();

	public static PageContext getContext() {
		PageContext ci = context.get();
		if (ci == null) {
			ci = new PageContext();
			context.set(ci);
		}
		return ci;
	}

	public static void removeContext() {
		context.remove();
	}

	protected void initialize() {
		LOGGER.info("PageContext init...");
	}

}
