package org.example.media;

import org.example.media.dto.AuthRequest;
import org.example.media.dto.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void shouldRegisterAndLoginSuccessfully() {
        // Регистрация
        AuthRequest registerRequest = new AuthRequest("testuser", "password");

        ResponseEntity<String> registerResponse = restTemplate.postForEntity(
                getBaseUrl() + "/auth/register",
                registerRequest,
                String.class
        );

        assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Логин
        AuthRequest loginRequest = new AuthRequest("testuser", "password");

        ResponseEntity<AuthResponse> loginResponse = restTemplate.postForEntity(
                getBaseUrl() + "/auth/login",
                loginRequest,
                AuthResponse.class
        );

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotNull();
        assertThat(loginResponse.getBody().getToken()).isNotEmpty();
    }
}
