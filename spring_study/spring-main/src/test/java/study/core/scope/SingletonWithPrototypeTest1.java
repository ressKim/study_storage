package study.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUserProtoType() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        //ObjectProvider 같은경우 spring 에서 제공한다 . 즉 spring 에 의존적이다.
        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        /*
        Provider 는 javax에서 제공하는 것이다 (gradle에 추가해줘야된다.)
        장점은 간단하다 , 단점도 간단하다
         */
        /**
         * 프로토타입 빈은 언제 사용할까??
         * 매번 사용할 때마다 의존관계 주입이 완료된것이 필요하다면 쓴다.
         * 그렇지만 싱글톤 내에서 거의 해결이 되서 거ㅓㅓㅓ의 안쓴다고 보면 된다.
         * javax의 Provider 에서는 이렇게 설명한다.
         * retrieving multiple instances.
         * lazy or optional retrieval of an instance.
         * breaking circular dependencies.
         * abstracting scope so you can look up an instance in a smaller scope from an instance in a containing scope.
         *
         * -(거의 사용 안한다고 생각해도 된다
         */
        /**
         * 그럼 어떤걸 쓸까?? spring 에서 제공하는 ObjectProvider
         * java 표준 javax 의 Provider?
         * jpa는 java표준이 이겼지만 spring 과의 비교에서는 spring꺼를 쓰는게 현재로써는
         * 편하다.
         */
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
