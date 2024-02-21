package com.sparta.newsfeed.controller;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.newsfeed.config.WebSecurityConfig;
import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.jwt.JwtUtil;
import com.sparta.newsfeed.security.JwtAuthenticationFilter;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.ProductService;
import com.sparta.newsfeed.service.UserService;
import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@WebMvcTest(
    controllers = {UserController.class, ProductController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
@MockBean(JpaMetamodelMappingContext.class)
public class ControllerTest {
    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    ProductService productService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .addFilter(jwtAuthenticationFilter)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .build();
    }

    private void mockUserSetup() {
        // Mock 테스트 유저 생성
        User user = new User("닉네임", "email@email.com", "password", "자기소개");
        UserDetailsImpl testUserDetails = new UserDetailsImpl(user);
        //credentials을 NULL로 하면 에러가나서 password입력
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "password", testUserDetails.getAuthorities());
    }




    @Test
    @DisplayName("회원 가입 요청 처리")
    void signupTest() throws Exception {
        // given
        MultiValueMap<String, String> signupRequestForm = new LinkedMultiValueMap<>();
        signupRequestForm.add("nickname", "test");
        signupRequestForm.add("email", "test@test.com");
        signupRequestForm.add("password", "test");
        signupRequestForm.add("userinfo", "test");

        // when - then
        mvc.perform(post("/api/users/signup")
                .params(signupRequestForm)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("상품 등록")
    void productCreateTest() throws Exception{
        //given
        this.mockUserSetup();

        MultipartFile file = null;
        ProductRequestDto requestDto = new ProductRequestDto("category", "title", "productInfo", 10000, file);

        String postInfo = objectMapper.writeValueAsString(requestDto);
        //when - then
        mvc.perform(post("/api/product")
            .content(postInfo)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .principal(mockPrincipal)
        )
            .andExpect(status().isOk())
            .andDo(print());
    }
}
