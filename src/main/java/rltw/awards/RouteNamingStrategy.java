package rltw.awards;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Value;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.web.router.naming.HyphenatedUriNamingStrategy;
import jakarta.inject.Singleton;

@Singleton
@Replaces(HyphenatedUriNamingStrategy.class)
public class RouteNamingStrategy extends HyphenatedUriNamingStrategy {

    @Value("${micronaut.context-path}")
    String contextPath;

    @Override
    public String resolveUri(Class type) {
        return contextPath + super.resolveUri(type);
    }

    @Override
    public String resolveUri(BeanDefinition<?> type) {
        return contextPath + super.resolveUri(type);
    }

    @Override
    public String resolveUri(String type) {
        return contextPath + super.resolveUri(type);
    }
}
