package com.fyb.shimo.util;

import com.fyb.shimo.entity.GraphiteDisc;

import java.time.LocalDateTime;

public class OverTimeUtils {

    public static GraphiteDisc checkOverTime(GraphiteDisc graphiteDisc,LocalDateTime now){
        LocalDateTime lastUsedTime = graphiteDisc.getLastUsedTime();
        if(lastUsedTime==null){
            return graphiteDisc.setOverTime(false);
        }
        LocalDateTime after30days = lastUsedTime.plusDays(30);
        if(after30days.compareTo(now)<0){
            return graphiteDisc.setOverTime(true);
        }
        return graphiteDisc.setOverTime(false);
    }
}
