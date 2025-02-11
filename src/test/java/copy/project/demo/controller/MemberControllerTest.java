package copy.project.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import copy.project.demo.TestUtils;
import copy.project.demo.dto.MemberDTO;
import copy.project.demo.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by SungHui on 2025. 2. 1.
 */

@Slf4j
@SpringBootTest
@Transactional
@AutoConfigureMockMvc // MockMvc를 자동 설정해서 실제 HTTP 요청과 응답 처리(모의)
@TestPropertySource(properties = "jwt.secret=eW91ci12ZXJ5LXNlY3VyZS1zZWNyZXQta2V5LXlvdXItdmVyeS1zZWN1cmUtc2VjcmV0LWtleQ==")
class MemberControllerTest {

    @Value("${jwt.secret}")
    private String key;

    // 실제 Controller / Service / Repository 가 포함된 컨텍스트에서 테스트
    @Autowired
    private MockMvc mvc;

    // JSON 직렬/역직렬화
    @Autowired
    private ObjectMapper om;


    // 회원 생성 테스트
    @WithMockUser(username = "testUser", roles = {"USER"})
    @Test
    void createMember() throws Exception {

        // given
        Member member = TestUtils.createTestMember();
        String jMember = om.writeValueAsString(member);
        log.info("Member created : " + jMember);

        // when
        ResultActions actions = mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jMember));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("sunghui"))
                .andExpect(jsonPath("$.nickname").value("crong"))
                .andExpect(jsonPath("$.phone").value("01012341234"));
    }

    // 회원 조회 테스트 (ID)
    @Test
    void getMemberById() throws Exception {

        // given
        Member member = TestUtils.createTestMember(); // 회원 생성
        String jMember = om.writeValueAsString(member); // json 문자열로 직렬화

        // POST 요청으로 회원 생성
        String response = mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jMember))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // response에서 생성된 MemberDTO를 역직렬화해서 ID 추출
        MemberDTO createdMember = om.readValue(response, MemberDTO.class);
        Long id = createdMember.getId();

        // when
        ResultActions actions = mvc.perform(get("/members/id/{id}", id));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("sunghui"))
                .andExpect(jsonPath("$.nickname").value("crong"))
                .andExpect(jsonPath("$.phone").value("01012341234"));
    }

    // 회원 조회 테스트 (이름)
    @Test
    void getMemberByName() throws Exception {

        // given
        Member member = TestUtils.createTestMember();
        String jMember = om.writeValueAsString(member);

        String response = mvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jMember))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        MemberDTO createdMember = om.readValue(response, MemberDTO.class);
        String name = createdMember.getName();

        // when
        ResultActions actions = mvc.perform(get("/members/name/{name}", name));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("sunghui"))
                .andExpect(jsonPath("$[0].nickname").value("crong"))
                .andExpect(jsonPath("$[0].phone").value("01012341234"));
    }

    // 회원 조회 테스트 (닉네임)
    @Test
    void getMemberByNickname() throws Exception {

        // given
        Member member = TestUtils.createTestMember();
        String jMember = om.writeValueAsString(member);

        String response = mvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jMember))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        MemberDTO createdMember = om.readValue(response, MemberDTO.class);
        String nickname = createdMember.getNickname();

        // when
        ResultActions actions = mvc.perform(get("/members/nickname/{nickname}", nickname));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("sunghui"))
                .andExpect(jsonPath("$.nickname").value("crong"))
                .andExpect(jsonPath("$.phone").value("01012341234"));
    }

    // 회원 조회 테스트(휴대폰 번호)
    @Test
    void getMemberByPhone() throws Exception {

        // given
        Member member = TestUtils.createTestMember();
        String jMember = om.writeValueAsString(member);

        String response = mvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jMember))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        MemberDTO createdMember = om.readValue(response, MemberDTO.class);
        String phone = createdMember.getPhone();

        // when
        ResultActions actions = mvc.perform(get("/members/phone/{phone}", phone));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("sunghui"))
                .andExpect(jsonPath("$.nickname").value("crong"))
                .andExpect(jsonPath("$.phone").value("01012341234"));
    }
}