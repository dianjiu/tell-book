package cn.org.dianjiu.tellbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        System.setProperty("cfg.env", "local");
        SpringApplication.run(Application.class, args);
    }

}
