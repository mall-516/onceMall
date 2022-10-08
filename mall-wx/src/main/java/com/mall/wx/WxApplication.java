package com.mall.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: hu chang
 * Date: 2022/9/29
 * Time: 15:05
 * Description:
 */
@SpringBootApplication(scanBasePackages = {"com.mall.db","com.mall.wx"})
@MapperScan("com.mall.db.mapper")
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class,args);
    }
}
