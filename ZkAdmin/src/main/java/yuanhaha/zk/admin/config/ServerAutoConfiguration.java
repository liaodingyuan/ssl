package yuanhaha.zk.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
public class ServerAutoConfiguration {

    @Configuration
    @Conditional(MyTestConditional.class)
    @Description(value = "student")
    public static class StudentAutoConfiguration {
        @Bean
        public Student student() {
            System.out.println("student create....");
            return new Student();
        }


    }

    @Configuration
    @Conditional(MyTestConditional.class)
    @Description(value = "teacher")
    public static class TeacherAutoConfiguration {
        @Bean
        public Teacher teacher() {
            System.out.println("teacher create.....");
            return new Teacher();
        }
    }
}