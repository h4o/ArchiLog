package fr.unice.polytech.al.verticles;

import com.netflix.appinfo.MyDataCenterInstanceConfig;

/**
 * Created by user on 28/01/17.
 */
public class DataCenterConfig extends MyDataCenterInstanceConfig {
    @Override
    public String getHostName(boolean refresh){
        return "46.101.31.80";
    }
    @Override
    public String getIpAddress(){
        return "46.101.31.80";
    }

}
