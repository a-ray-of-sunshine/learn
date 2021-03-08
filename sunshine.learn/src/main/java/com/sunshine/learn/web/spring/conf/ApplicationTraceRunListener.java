package com.sunshine.learn.web.spring.conf;

import java.net.InetAddress;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class ApplicationTraceRunListener implements SpringApplicationRunListener, Ordered {

  public ApplicationTraceRunListener(SpringApplication application, String[] args) {

  }

  /**
   * Called immediately when the run method has first started. Can be used for very early initialization.
   */
  @Override
  public void starting() {

  }

  /**
   * Called once the environment has been prepared, but before the {@link ApplicationContext} has been created.
   *
   * @param environment the environment
   */
  @Override
  public void environmentPrepared(ConfigurableEnvironment environment) {

  }

  /**
   * Called once the {@link ApplicationContext} has been created and prepared, but before sources have been loaded.
   *
   * @param context the application context
   */
  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {

  }

  /**
   * Called once the application context has been loaded but before it has been refreshed.
   *
   * @param context the application context
   */
  @Override
  public void contextLoaded(ConfigurableApplicationContext context) {

  }

  /**
   * Called immediately before the run method finishes.
   *
   * @param context the application context or null if a failure occurred before the context was created
   * @param exception any run exception or null if run completed successfully.
   */
  @SneakyThrows
  @Override
  public void finished(ConfigurableApplicationContext context, Throwable exception) {

    ConfigurableEnvironment env = context.getEnvironment();

    String protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
      protocol = "https";
    }
    log.info(
        "\n---------------------------------------------------------------------------------------\n\t"
            +
            "Application '{}' is running! Access URLs:\n\t" +
            "Local: \t\t{}://localhost:{}/swagger-ui.html\n\t" +
            "External: \t{}://{}:{}/swagger-ui.html\n\t" +
            "\n---------------------------------------------------------------------------------------",
        env.getProperty("spring.application.name"),
        protocol,
        env.getProperty("server.port"),
        protocol,
        InetAddress.getLocalHost().getHostAddress(),
        env.getProperty("server.port"));
  }

  /**
   * Get the order value of this object.
   * <p>Higher values are interpreted as lower priority. As a consequence,
   * the object with the lowest value has the highest priority (somewhat analogous to Servlet {@code load-on-startup}
   * values).
   * <p>Same order values will result in arbitrary sort positions for the
   * affected objects.
   *
   * @return the order value
   * @see #HIGHEST_PRECEDENCE
   * @see #LOWEST_PRECEDENCE
   */
  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }
}
