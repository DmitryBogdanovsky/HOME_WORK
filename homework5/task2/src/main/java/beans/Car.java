package beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

    @Getter
    @Setter
    @ToString
    public class Car implements InitializingBean, DisposableBean {

        private String modelName;
        private int year;
        private String color;

        @Override
        public void afterPropertiesSet() {
            System.out.println("afterPropertiesSet() success......");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println("destroy() success......");
        }

        public void defaultInit() throws Exception {
            System.out.println("Default init() success......");
        }

        public void defaultDestroy() throws Exception {
            System.out.println("Default destroy() success......");
        }

        public static Car getInstance() {
            return new Car();
        }
    }
