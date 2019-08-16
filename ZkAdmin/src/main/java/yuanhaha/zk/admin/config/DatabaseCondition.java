package yuanhaha.zk.admin.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 当环境变量中含有以下变量的时候，该条件成立
 * @author yuanhaha
 */
public class DatabaseCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return environment.containsProperty("database.driverName")
                && environment.containsProperty("database.url")
                && environment.containsProperty("database.username")
                && environment.containsProperty("database.password");
    }
}
