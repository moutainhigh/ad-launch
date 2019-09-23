package com.ad.adlaunch;

import com.ad.adlaunch.constants.JwtProperties;
import com.ad.adlaunch.constants.QiNiuProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
*
 * 启动类
* @author : wezhyn
* @date : 2019/09/20
*
*/
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(value={JwtProperties.class, QiNiuProperties.class})
public class AdLaunchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdLaunchApplication.class, args);
    }

}
