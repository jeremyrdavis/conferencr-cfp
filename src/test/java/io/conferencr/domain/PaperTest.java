package io.conferencr.domain;

import io.conferencr.domain.valueobjects.SessionAbstractValueObject;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PaperTest {

    static Speaker speaker;
    static final String SESSION_TITLE = "A Session Title";
    static final String SESSION_SLUG = "London Calling";
    static final String SESSION_BODY = """
            London calling to the faraway towns
            Now war is declared and battle come down
            London calling to the underworld
            Come out of the cupboard, you boys and girls
            London calling, now don't look to us
            Phony Beatlemania has bitten the dust
            London calling, see we ain't got no swing
            Except for the ring of the truncheon thing
            The ice age is coming, the sun's zooming in
            Meltdown expected, the wheat is growing thin
            Engines stop running, but I have no fear
            'Cause London is drowning
            I live by the river
            London calling to the imitation zone
            Forget it, brother, you can go it alone
            London calling to the zombies of death
            Quit holding out and draw another breath
            London calling and I don't want to shout
            But while we were talking, I saw you nodding out
            London calling, see we ain't got no high
            Except for that one with the yellowy eye
            The ice age is coming, the sun's zooming in
            Engines stop running, the wheat is growing thin
            A nuclear era, but I have no fear
            'Cause London is drowning
            I, I live by the river
            The ice age is coming, the sun's zooming in
            Engines stop running, the wheat is growing thin
            A nuclear era, but I have no fear
            'Cause London is drowning
            I, I live by the river
            Now get this
            London calling, yes, I was there, too
            And you know what they said? Well, some of it was true
            London calling at the top of the dial
            And after all this, won't you give me a smile?
            I never felt so much alike, alike, alike, alike
                """;

    @BeforeAll @Transactional
    public static void setUp() {
         speaker = new Speaker("joe@theclash.com", "Joe", "Strummer");
         speaker.persist();
    }
    @Test
    public void testCreateFromSessionAbstract() {

        SessionAbstractValueObject sessionAbstractJSON = new SessionAbstractValueObject(SESSION_TITLE, SESSION_SLUG, SESSION_BODY, speaker.getEmail());
        Paper paper = Paper.createFromSessionAbstractJSON(sessionAbstractJSON);
        assertNotNull(paper);
        assertEquals(SESSION_TITLE, paper.getTitle());
        assertEquals(SESSION_SLUG, paper.getSlug());
        assertEquals(SESSION_BODY, paper.getBody());
        assertEquals(speaker, paper.getSpeaker());
    }
}
