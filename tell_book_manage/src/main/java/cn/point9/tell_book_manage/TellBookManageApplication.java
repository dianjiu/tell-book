package cn.point9.tell_book_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TellBookManageApplication {

    public static void main(String[] args) {
        //System.setProperty("cfg.env", "local");
        SpringApplication.run(TellBookManageApplication.class, args);
    }

}
