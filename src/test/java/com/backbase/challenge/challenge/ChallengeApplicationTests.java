package com.backbase.challenge.challenge;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.backbase.ChallengeApplication;
import com.backbase.model.response.ResponseBody;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChallengeApplicationTests {

	public RestTemplate restTemplate = new RestTemplate();
	public HttpEntity<ResponseBody> entity = new HttpEntity<>(new ResponseBody());

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();

	// check create game servis that is return a value : /games
	@Test
	public void testCreateGameReturnResult() {
		ResponseEntity<ResponseBody> responseVal = restTemplate.exchange(createURLWithPort("/games"), HttpMethod.POST,
				entity, ResponseBody.class);
		assertThat(responseVal, notNullValue());

	}

	// test game url that is in the response body correctly// game id is unique
	// random that is why It can't be predictable
	@Test
	public void testCreatedGameUrl() throws JSONException {

		ResponseEntity<ResponseBody> response = restTemplate.exchange(createURLWithPort("/games"), HttpMethod.POST,
				entity, ResponseBody.class);

		String expected = response.getBody().getUri();
		assertTrue(expected.contains("/games/" + response.getBody().getId()));

	}

	/// check the status of create game is 201 (created-post) : /games
	@Test
	public void testStatusOfCreateGame() {
		ResponseEntity<ResponseBody> responseVal = restTemplate.postForEntity(createURLWithPort("/games"), entity,
				ResponseBody.class);
		assertEquals(201, responseVal.getStatusCodeValue());
	}

	// check created game id is an integer : /games
	@Test
	public void checkCreatedGameId() {
		ResponseEntity<ResponseBody> responseVal = restTemplate.postForEntity(createURLWithPort("/games"), entity,
				ResponseBody.class);
		assertTrue(responseVal.getBody().getId() instanceof Integer);
	}

	// *End*//

	// ************Test 2. ​Make a move service: *******//
	// check the service response status code and body that is not null after
	// calling HttpMethod.Put
	@Test
	public void testPlayingServiceStatus() {
		// every game id is uniqe and created random. Therefore created game service
		// called first to get gameId
		ResponseEntity<ResponseBody> respFromPreviousService = restTemplate.postForEntity(createURLWithPort("/games"),
				entity, ResponseBody.class);
		String uri = createURLWithPort("/games/") + respFromPreviousService.getBody().getId() + "/pits/2";

		ResponseEntity<ResponseBody> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, ResponseBody.class,
				respFromPreviousService.getBody().getId());

		String exampleStatus = "{1=6, 2=0, 3=7, 4=7, 5=7, 6=7, 7=1, 8=7, 9=6, 10=6, 11=6, 12=6, 13=6, 14=0}";

		assertThat(response.getBody(), notNullValue());
		assertEquals(response.getBody().getStatus().toString(), exampleStatus);
		assertEquals(response.getStatusCode().value(), 200);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
