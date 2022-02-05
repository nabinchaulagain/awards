package rltw.awards.log;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Filter("/**")
public class RequestLogFilter implements HttpServerFilter {
    @Inject
    private SecurityService securityService;

    private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        logRequest(request);

        return chain.proceed(request);
    }

    public void logRequest(HttpRequest<?> request) {
        String ip = request.getRemoteAddress().toString();
        String user = securityService.isAuthenticated() ? securityService.getAuthentication().get().getName() : "Anonymous";
        String userAgent = request.getHeaders().get(HttpHeaders.USER_AGENT);
        String requestMethod = request.getMethodName();
        String path = request.getPath();
        Optional<?> body = request.getBody();
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Kathmandu")).format(Instant.now());
        Map<String, List<String>> queryParams = request.getParameters().asMap();

        logger.trace(String.format("%s - %s (%s) [%s] - %s ?%s %s - %s", ip, user, userAgent, time, path, queryParams.toString(), requestMethod, body.isPresent() ? body.get() : ""));
    }
}
