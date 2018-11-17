package com.cit.common.manager;

/**
 * Created by odziea on 11/14/2018.
 */
public interface IRestTemplateMapper<T> {

    public T getForObject(Class<T> clazz, ApplicationType applicationType, String request);
}
