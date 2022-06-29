package io.conferencr.infrastructure;

import io.conferencr.domain.Reviewer;
import io.conferencr.domain.SessionAbstract;
import io.conferencr.domain.Speaker;
import io.conferencr.domain.UpVoteJson;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class SessionAbstractVoteTest {

    static Long sessionAbstractId;

    static Long reviewerId;

    static final String title = "A presentation";

    static final String slug = "This is going to be a great presentation";

    static final String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam lectus mi, malesuada non neque sed, pretium vestibulum dui. Etiam convallis cursus ullamcorper. Proin ut ex sit amet lacus laoreet gravida. Donec convallis sit amet dolor sed rutrum. Maecenas eu elit ac tellus sollicitudin finibus id at nisi. Ut nunc augue, vehicula at rutrum vitae, vestibulum quis quam. Aliquam vel augue id felis pellentesque aliquam. Vivamus tempus ex quis ultrices accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent hendrerit ipsum tincidunt tortor tempus tincidunt. Nunc fringilla diam at nunc porttitor, a imperdiet tortor suscipit. Cras scelerisque enim eget cursus gravida. Sed rhoncus risus ut ante dapibus lacinia. Quisque nisi turpis, porttitor sed purus ut, sodales placerat nibh. Suspendisse in purus quis eros porta commodo at in dui.%n                     Quisque tempus porttitor tellus quis pellentesque. Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas neque sem, placerat a ullamcorper a, tristique eget ante. Cras a lorem eros. Morbi hendrerit, leo sit amet ultrices viverra, tellus dolor pellentesque ligula, et pulvinar enim nisl a neque. Cras pharetra pretium est quis placerat. Mauris a cursus erat, sit amet tincidunt dolor. Etiam sit amet suscipit tortor. Sed.";

    @BeforeAll
    @Transactional
    public static void setUp() {

        Speaker speaker = new Speaker("dduck@disney.com", "Donald", "Duck");
        speaker.persist();
        SessionAbstract sessionAbstract = new SessionAbstract(
                title,
                slug,
                body,
                speaker);
        sessionAbstract.persist();
        sessionAbstractId = sessionAbstract.id;
        Reviewer reviewer = new Reviewer("mickey.mouse@steamboatwillie.com");
        reviewer.persist();
        reviewerId = reviewer.id;
    }

    @Test
    @Transactional
    public void testUpvote() {

        UpVoteJson upVoteJson = new UpVoteJson(sessionAbstractId, reviewerId);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .and()
                        .body(upVoteJson)
                        .when().post("/vote")
                        .then()
                        .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals(1, response.jsonPath().getString("votes"));

    }
}
