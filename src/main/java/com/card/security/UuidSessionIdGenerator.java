package com.card.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

@Slf4j
public class UuidSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        Serializable uuid = new JavaUuidSessionIdGenerator().generateId(session);
        log.info("生成的sessionid是：" + uuid);
        return uuid;
    }
}