package io.conferencr.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PaperTest {

    @Test @Transactional
    public void testVoteUp() {

        Speaker speaker = new Speaker(
                "boo@deadmockingbird.com",
                "Boo",
                "Radley"
        );

        speaker.persist();

        Reviewer reviewer1 = new Reviewer("atticus@deadmockingbird.com");
        reviewer1.persist();

        Reviewer reviewer2 = new Reviewer("jean@deadmockingbird.com");
        reviewer2.persist();

        Paper paper = new Paper(
                "A Book Report",
                "To Kill a Mockingbird is a great book",
                "During his first five years in Maycomb, Atticus practiced economy more than anything; for several years thereafter he invested his earningsin his brother's education.",
                speaker
        );

        paper.persist();

        assertEquals(0, paper.getVotes());
        assertNotNull(paper.id);

        UpVote upVote1 = new UpVote(paper, reviewer1);

        paper.voteUp(upVote1);
        assertEquals(1, paper.getVotes());
        paper.persist();
        assertEquals(1, paper.getVotes());
        paper.getAllVotes().get().forEach(upVote -> {
            assertEquals(reviewer1, upVote.reviewer);
        });

        UpVote upVote2 = new UpVote(paper, reviewer2);
        paper.voteUp(upVote2);
        assertEquals(2, paper.getVotes());

        Collection<UpVote> upVotes = paper.getAllVotes().get();
        assertTrue(upVotes.contains(upVote1));
        assertTrue(upVotes.contains(upVote2));
    }

}
