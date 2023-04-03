package com.slava;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class tryTypesExample implements TryType<LinkedList> {

    @SuppressWarnings("unchecked")
    public String getType() {
        final Type[] genericInterfaces = getClass().getGenericInterfaces();
        //genericInterfaces[0].getTypeName();
       // final Type genericSuperclass = getClass()
       //         .getGenericSuperclass();
       // return ((Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        return genericInterfaces[0].getTypeName();
    }

    @Test
    public void test(){
        String t=getType();
        System.out.println(t);
    }

}
