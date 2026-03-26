package seedu.goldencompass.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.goldencompass.exception.GoldenCompassException;
import seedu.goldencompass.internship.Internship;
import seedu.goldencompass.internship.Interview;
import seedu.goldencompass.internship.InterviewList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListInterviewCommandTest {

    private InterviewList interviewList;
    private ListInterviewCommand listInterviewCommand;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        interviewList = new InterviewList();
        listInterviewCommand = new ListInterviewCommand(interviewList);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void list_emptyList_printsNoInterviewsMessage() throws GoldenCompassException {
        listInterviewCommand.execute();
        String output = outputStream.toString().trim();
        assertEquals("You don't have any interviews!", output);
    }

    @Test
    public void list_singleInterview_printsCorrectly() throws GoldenCompassException {
        Internship internship = new Internship("Software Engineer", "Google");
        Interview interview = new Interview(internship, LocalDate.parse("2026-03-25"));
        interviewList.add(interview);

        listInterviewCommand.execute();
        String output = outputStream.toString().trim();
        System.out.println(output);
        assertTrue(output.contains("Here are the interview invitations:"));
        assertTrue(output.contains("1. Google - Software Engineer @ 2026-03-25"));
    }

}
