//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xinyan.message.center.common.utils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class UuidConvert extends ClassicConverter {
    public UuidConvert() {
    }

    public String convert(ILoggingEvent event) {
        return LogUtil.getLogId();
    }
}
