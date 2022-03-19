package com.example.cloudgateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;



import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {

    private static Logger log = Logger.getLogger(LogFilter.class.getName());

    public LogFilter() {
        super(Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("message", "cookieName", "cookieValue");
    }


    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> {
            //Filtro previo a la invocación del servicio real asociado al gateway
            log.info("Path requested: " + exchange.getRequest().getPath());
            log.info("Ejecutando la primera instancia..." + config.message);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //Filtro posterior a la invocación del servicio real asociado al gateway
                Optional.ofNullable(config.cookieValue).ifPresent(cookie ->
                        exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cookie).build()));
                log.info("Time response: " + Calendar.getInstance().getTime());
            }));
        };
    }


    public static class Config {
        //Put the configuration properties
        private String message;
        private String cookieName;
        private String cookieValue;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }
    }
}
