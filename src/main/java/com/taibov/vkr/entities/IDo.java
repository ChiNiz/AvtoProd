package com.taibov.vkr.entities;

import org.hibernate.Session;

public interface IDo {
    void Do(Session session);
//    void DoWithBack(Session session, Object object);
}
