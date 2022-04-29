package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;


import java.util.List;

public interface GeneralService<T>  {
    T create(T t) throws Exception;

    T update(T t) throws  Exception;

    int delete(int id) throws Exception;

}
