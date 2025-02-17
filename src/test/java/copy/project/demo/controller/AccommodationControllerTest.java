package copy.project.demo.controller;

import copy.project.demo.service.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by SungHui on 2025. 2. 17.
 */
/* 숙소 컨트롤러 테스트 */
@SpringBootTest
@AutoConfigureMockMvc
class AccommodationControllerTest {

    @Mock
    private AccommodationService accommodationService; // 숙소 서비스 모의 객체

    @InjectMocks
    private AccommodationController accommodationController; // 숙소 컨트롤러 모의 객체

    private MockMvc mockMvc; // MockMvc 객체

    private ModelMapper mm = new ModelMapper(); // ModelMapper 객체 생성

    @BeforeEach // 테스트 실행 전 초기화 작업
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accommodationController).build(); // MockMvc 객체 생성
    }

    // 숙소 정보 등록 테스트
    /*@Test
    public void testRegister() throws Exception{

        // given
        // 숙소 DTO 객체 생성
        AccommodationDTO accommodationDTO = new AccommodationDTO(
                1L, "테스트 숙소", "테스트 숙소 설명", AccommodationType.HOTEL, "서울시 성북구",
                BigDecimal.valueOf(37.123412), BigDecimal.valueOf(126.987654), "image.jpg", new ArrayList<>()
        );

        Accommodation savedAccommodation = mm.map(accommodationDTO, Accommodation.class); // DTO -> 엔티티로 변환
        when(accommodationService.registerAccommodation(any(Accommodation.class))).thenReturn(savedAccommodation); // 숙소 등록 서비스 호출 시 반환값 설정

        // when
        ResultActions resultActions = mockMvc.perform((RequestBuilder) post("/accommodation")
                        .contentType(MediaType.valueOf("application/json"))
                        .contentType(MediaType.valueOf("{ \"name\": \"Sample Hotel\", \"description\": \"Hotel Description\", \"type\": \"HOTEL\", \"address\": \"Sample Address\", \"latitude\": 37.5665, \"longitude\": 126.9780, \"imageUrl\": \"imageUrl\" }")))
                .andExpect(status().isOk()) // 응답 상태가 OK(200)인지 검증
                .andExpect((ResultMatcher) jsonPath("$.name").value("Sample Hotel")) // 응답 JSON에서 name 필드가 "Sample Hotel"인지 검증
                .andExpect((ResultMatcher) jsonPath("$.type").value("HOTEL"));// 응답 JSON에서 type 필드가 "HOTEL"인지 검증
    }*/


}