package com.yzjttcgs.integratedappletinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.yzjttcgs.integratedappletinterface.mapper")
public class IntegratedAppletInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegratedAppletInterfaceApplication.class, args);
    }

}
