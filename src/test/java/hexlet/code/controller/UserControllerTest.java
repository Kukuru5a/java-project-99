package hexlet.code.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
        .JwtRequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import org.springframework.test.web.servlet.MockMvc;
import hexlet.code.util.EntityGenerator;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EntityGenerator entityGenerator;

    @Autowired
    UserRepository userRepository;

    private User testUser;
    private JwtRequestPostProcessor token;

    @BeforeEach
    public void setUp() {
        testUser = entityGenerator.generateUser();
        userRepository.save(testUser);
        token = jwt().jwt(builder -> builder.subject(testUser.getEmail()));
    }


    @AfterEach
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    public void testGetList() throws Exception {
        var request = get("/api/users")
                .with(token);
        var res = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = res.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }
    @Test
    public void testShow() throws Exception {
        var request = get("/api/users/" + testUser.getId()).with(token);
        var res = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = res.getResponse().getContentAsString();
        assertThatJson(body).and(
                a -> a.node("id").isEqualTo(testUser.getId()),
                a -> a.node("email").isEqualTo(testUser.getEmail()),
                a -> a.node("firstName").isEqualTo(testUser.getFirstName()),
                a -> a.node("lastName").isEqualTo(testUser.getLastName()));
    }
    @Test
    public void testCreate() throws Exception {
        var newUser = entityGenerator.generateUser();
        var data = new UserCreateDTO();
        data.setEmail(newUser.getEmail());
        data.setFirstName(newUser.getFirstName());
        data.setLastName(newUser.getLastName());
        data.setPassword(newUser.getPassword());

        var request = post("/api/users")
                .with(token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var user = userRepository.findByEmail(data.getEmail()).get();

        assertNotNull(user);
        assertThat(user.getFirstName()).isEqualTo(data.getFirstName());
        assertThat(user.getLastName()).isEqualTo(data.getLastName());
        assertThat(encoder.matches(newUser.getPassword(), user.getPassword())).isTrue();
    }
    @Test
    public void testUpdateUser() throws Exception {
        var data = new UserUpdateDTO();
        data.setEmail(JsonNullable.of("anyMail@mail.ru"));
        data.setPassword(JsonNullable.of("myNewPassword"));
        var request = put("/api/users/"
                + testUser.getId()).with(token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        var res = mockMvc.perform(request).andExpect(status().isOk());
        var user = userRepository.findById(testUser.getId()).get();
        assertThat(user.getEmail()).isEqualTo("anyMail@mail.ru");
        assertThat(encoder.matches("myNewPassword", user.getPassword())).isTrue();
    }

    @Test
    public void testUpdateWrongUser() throws Exception {
        var anotherUser = entityGenerator.generateUser();
        userRepository.save(anotherUser);
        token = jwt().jwt(builder -> builder.subject(anotherUser.getEmail()));
        var data = new UserUpdateDTO();
        data.setEmail(JsonNullable.of("newMail@mail.ru"));
        data.setPassword(JsonNullable.of("newPassword"));
        var request = put("/api/users/" + testUser.getId())
                .with(token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        mockMvc.perform(request)
                .andExpect(status().isForbidden());
        assertThat(userRepository.findByEmail(testUser.getEmail())).isPresent();
        assertThat(userRepository.findByEmail("newMail@mail.ru")).isEmpty();
    }

    @Test
    public void testDelete() throws Exception {
        var request = delete("/api/users/" + testUser.getId())
                .with(token);
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
        assertThat(userRepository.existsById(testUser.getId())).isFalse();
    }

    @Test
    public void testDeleteWrongUser() throws Exception {
        var anotherUser = entityGenerator.generateUser();
        userRepository.save(anotherUser);
        token = jwt().jwt(builder -> builder.subject(anotherUser.getEmail()));
        var request = delete("/api/users/" + testUser.getId())
                .with(token);
        mockMvc.perform(request)
                .andExpect(status().isForbidden());
        assertThat(userRepository.existsById(testUser.getId())).isTrue();
    }

}
