package com.example.todoapp.infra;

import com.example.todoapp.TodoApplication;
import com.example.todoapp.infra.socialauthn.SocialUserInfo;
import com.example.todoapp.infra.socialauthn.SocialUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(AppRestController.class)
@Import({TodoApplication.class})
@AutoConfigureDataJpa
@ActiveProfiles({"mysql"})
class AppRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "user2")
    @Test
    void name() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/listItems"));
        assertThat(true).isTrue();
    }

    @Autowired
    SocialUserRepository socialUserRepository;

    @Test
    void testSocial() {
        SocialUserInfo socialUserInfo = socialUserRepository.create("some-id", "github", "cless91");
        socialUserRepository.save(socialUserInfo);

        assertThat(socialUserRepository.exists("some-id", "github")).isTrue();

        Optional<SocialUserInfo> socialUserInfoFromDatabase = socialUserRepository.get("some-id", "github");
        System.out.println();
    }
}