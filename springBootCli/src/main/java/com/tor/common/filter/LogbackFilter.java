package com.tor.common.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author ：Tzx
 * @date ：Created in 2021/1/8 下午 02:11
 * @description： logback日志过滤
 * @version: 1.0
 */
public class LogbackFilter extends ThresholdFilter {
    Level level;
    Level excludeLevel;

    public LogbackFilter() {
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel().equals(excludeLevel)) {
            return FilterReply.DENY;
        }
        return super.decide(event);
    }

    @Override
    public void setLevel(String level) {
        super.setLevel(level);
    }

    public void setExcludeLevel(String excludeLevel) {
        this.excludeLevel = Level.toLevel(excludeLevel);
    }

}
