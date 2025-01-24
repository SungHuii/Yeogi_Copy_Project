package copy.project.demo.library;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by SungHui on 2025. 1. 24.
 */

/* ModelMapper를 Bean으로 설정하여 의존성 주입을 받음 */
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
