package copy.project.demo.library;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by SungHui on 2025. 1. 24.
 */

// ModelMapper를 설정을 통해 필드에 접근 가능하게 함.
/* ModelMapper를 Bean으로 설정하여 의존성 주입을 받음 */
@Configuration // 설정 파일 클래스 어노테이션
public class ModelMapperConfig {

    @Bean // 빈으로 설정
    public ModelMapper modelMapper() { 
        ModelMapper modelMapper = new ModelMapper(); // ModelMapper 객체 생성
        
        modelMapper.getConfiguration() // getConfiguration() 메서드로 Configuration 객체를 가져옴
                .setFieldMatchingEnabled(true) // 필드 매칭 활성화. setter 없이도 필드에 접근 가능하도록 설정
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        // 필드 접근 레벨 설정. PRIVATE로 설정하면 private 필드에 접근 가능. 

        return modelMapper; // 설정한 ModelMapper 객체 반환
    }


}
