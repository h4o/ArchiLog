package fr.unice.polytech.al.verticles;

import com.netflix.appinfo.MyDataCenterInstanceConfig;

/**
 * Created by user on 28/01/17.
 * Ok c'est moche, mais il y a une raison: eureka-client ne permet pas de changer l'ip ou le hostname
 * docker nous permet pas de retrieve automatiquement ceux-ci
 * c'etait le moyen le plus rapide de corriger le soucis
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
