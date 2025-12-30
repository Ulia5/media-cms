package org.example.media;

import org.example.media.model.Video;
import org.example.media.dto.AuthRequest;
import org.example.media.dto.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VideoIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private String token;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    void setup() {
        // Регистрация и логин
        AuthRequest request = new AuthRequest("videoUser", "pass");
        restTemplate.postForEntity(getBaseUrl() + "/auth/register", request, String.class);
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(getBaseUrl() + "/auth/login", request, AuthResponse.class);
        token = response.getBody().getToken();
    }

    @Test
    void shouldCreateVideo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Video video = new Video();
        video.setTitle("Test video");
        video.setUrl("https://example.com/test.mp4");
        video.setDuration(120);

        HttpEntity<Video> request = new HttpEntity<>(video, headers);

        ResponseEntity<Video> response = restTemplate.postForEntity(getBaseUrl() + "/videos", request, Video.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotBlank();
    }
}
