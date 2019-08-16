package yuanhaha.zk.admin.config;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.util.Map;

public class MyTestConditional implements ConfigurationCondition {

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        // 还有一个PARSE_CONFIGURATION属性，会在解析@Configuration时进行condition的解析
        // REGISTER_BEAN 会在注册Bean的时候进行condition的解析
        return ConfigurationPhase.REGISTER_BEAN;
    }


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> map = metadata.getAnnotationAttributes("org.springframework.context.annotation.Description");
        System.out.println(map);
        return false;
    }
}
