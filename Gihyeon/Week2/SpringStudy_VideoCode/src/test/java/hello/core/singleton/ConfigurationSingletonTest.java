package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

class ConfigurationSingletonTest {

    @Test
    @DisplayName("@Configuration과 싱글톤")
    void configurationTest() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberServiceMemberRepository(memberService);
        MemberRepository memberRepository2 = orderServiceMemberRepository(orderService);

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("@Configuration 클래스는 CGLIB으로 감싸진다")
    void configurationDeep() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }

    private MemberRepository memberServiceMemberRepository(MemberServiceImpl memberService) {
        try {
            var field = MemberServiceImpl.class.getDeclaredField("memberRepository");
            field.setAccessible(true);
            return (MemberRepository) field.get(memberService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MemberRepository orderServiceMemberRepository(OrderServiceImpl orderService) {
        try {
            var field = OrderServiceImpl.class.getDeclaredField("memberRepository");
            field.setAccessible(true);
            return (MemberRepository) field.get(orderService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}