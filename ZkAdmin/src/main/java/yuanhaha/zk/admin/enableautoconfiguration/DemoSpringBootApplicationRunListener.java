package yuanhaha.zk.admin.enableautoconfiguration;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author yuanhaha
 */
public class DemoSpringBootApplicationRunListener  implements SpringApplicationRunListener{


    @Override
    public void starting() {

        System.out.println("DemoSpringBootApplicationRunListener Starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("DemoSpringApplicationRunListener environment prepared");

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("DemoSpringApplicationRunListener context prepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

        System.out.println("DemoSpringApplicationListener context loaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {

        System.out.println("DemoSpringApplicationRunListener  started ");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("DemoSpringApplicationRunListener running");

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

        System.out.println("DemoSpringApplicationRunListener failed");
    }
}
