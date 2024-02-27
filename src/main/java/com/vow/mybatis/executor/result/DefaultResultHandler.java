package com.vow.mybatis.executor.result;

import com.vow.mybatis.reflaction.factory.ObjectFactory;
import com.vow.mybatis.session.ResultContext;
import com.vow.mybatis.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: vow
 * @date: 2024/2/26 15:14
 * @description:
 */
public class DefaultResultHandler implements ResultHandler {

    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }

    /**
     * 通过 ObjectFactory 反射工具类，产生特定的 List
     */
    @SuppressWarnings("unchecked")
    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }


    @Override
    public void handleResult(ResultContext context) {
        list.add(context.getResultObject());
    }

    public List<Object> getResultList() {
        return list;
    }
}
