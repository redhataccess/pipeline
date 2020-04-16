package com.redhat.common;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sfloess
 */
public class AbstractBase {
    private final Logger logger = Logger.getLogger(getClass());

    protected Logger getLogger() {
        return logger;
    }

    protected void logDebug(final Object... toLog) {
        getLogger().debug(StringUtils.join(toLog));
    }

    protected void logIfDebug(final Object... toLog) {
        if (getLogger().isDebugEnabled()) {
            logDebug(toLog);
        }
    }

    protected void logInfo(final Object... toLog) {
        getLogger().info(StringUtils.join(toLog));
    }

    protected void logIfInfo(final Object... toLog) {
        if (getLogger().isInfoEnabled()) {
            logInfo(toLog);
        }
    }

    protected void logWarning(final Object... toLog) {
        getLogger().warn(StringUtils.join(toLog));
    }

    protected void logError(final Object... toLog) {
        getLogger().error(StringUtils.join(toLog));
    }
}
