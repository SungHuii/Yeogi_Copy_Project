package copy.project.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import copy.project.demo.TestUtils;
import copy.project.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by SungHui on 2025. 2. 1.
 */

@SpringBootTest
@Transactional
@AutoConfigureMockMvc // MockMvc를 자동 설정해서 실제 HTTP 요청과 응답 처리(모의)
class MemberControllerTest {

    // 실제 Controller / Service / Repository 가 포함된 컨텍스트에서 테스트
    @Autowired
    private MockMvc mvc;

    // JSON 직렬/역직렬화
    @Autowired
    private ObjectMapper om;

    // 회원 생성 테스트
    @Test
    void createMember() throws Exception {

        // given
        Member member = TestUtils.createTestMember();
        String jMember = om.writeValueAsString(member);

        // when
        ResultActions actions = mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jMember));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("sunghui"))
                .andExpect(jsonPath("$.nickname").value("crong"));

    }

    // 회원 조회 테스트 (ID)
    @Test
    void getMemberById() throws Exception {


    }
}