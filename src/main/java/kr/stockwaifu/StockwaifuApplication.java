package kr.stockwaifu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// exclude 없이 깔끔하게
@SpringBootApplication
public class StockwaifuApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockwaifuApplication.class, args);
    }
}