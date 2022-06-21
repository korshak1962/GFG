package com.slava;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface TryT<T extends List> {
    //default Class<T> getType(){
      // return ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   // }
}
