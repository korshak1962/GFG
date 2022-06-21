package com.slava.binarySearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class ReversOrder {

    @Test
    public void test() {
        Integer[] nums = {5, 4, 3, 2};

        int res=Arrays.binarySearch(nums,2, Comparator.reverseOrder());
        System.out.println(res);
    }



    /*
    M1(Reviews)


1. Async transaction
2. Generic payload -> interaction between the services
3. Error handling
4. Transaction history
5. Retry mechanism


I got info from Google  - 1 mln review per day - 1k data = 1GB
it API - load balancer -> messaging queue -  validate (lambda fun) - store in Cassandra(MongDB) - table ReviewByCompanyID (CompanyID,review, TimeUUID)


M1 - Google   adapter1 - convert (I can validate it in case of errors I can send back to Provider ) source info into some standard Value Object - asynck quue - could be stored and send
M2 -FaceBook   adapter2 - convert source info into some standard VO
M3 - Apple    adapter3 - convert source info into some standard VO

1. Navigate traffic ?

pipeline - load balancer - to Application servers -


Review MS -> M1(Google) -> API 
     */
}
