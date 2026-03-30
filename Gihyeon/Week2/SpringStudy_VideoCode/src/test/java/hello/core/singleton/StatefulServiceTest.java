package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("상태를 유지할 경우 문제점 예시")
    void statefulServiceSingleton() {
        StatefulService statefulService = new StatefulService();

        statefulService.order("userA", 10000);
        statefulService.order("userB", 20000);

        int price = statefulService.getPrice();
        System.out.println("price = " + price);

        assertThat(price).isEqualTo(20000);
    }
}