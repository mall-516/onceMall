package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: hu chang
 * Date: 2022/10/3
 * Time: 14:51
 * Description:
 */
@SpringBootApplication(scanBasePackages = {"com.mall"})
@MapperScan("com.mall.db.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
