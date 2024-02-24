package hexlet.code.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {
    @Bean
    Jackson2ObjectMapperBuilder objectMapperBuilder() {
        var builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL)
                .modulesToInstall(new JsonNullableModule());
        builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return builder;
    }
//    @Bean
//    Jackson2ObjectMapperBuilder objectMapperBuilder() {
//        var builder = new Jackson2ObjectMapperBuilder();
//        builder.serializationInclusion(JsonInclude.Include.NON_NULL)
//                .modulesToInstall(new JsonNullableModule())
//                .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
//                .featuresToEnable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
//        return builder;
//    }
//    @Bean
//    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.serializerByType(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
//            @Override
//            public void serialize(LocalDateTime value, JsonGenerator gen,
//                                  SerializerProvider serializers) throws IOException {
//                gen.writeNumber(value.toEpochSecond(ZoneOffset.UTC));
//            }
//        });
//        return builder;
//    }
}
