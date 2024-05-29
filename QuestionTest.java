import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class QuestionTest {

    @Test
    void testQuestionConstructorAndAccessors() {
        // Initialize a question
        Question question = new Question(3, 4, '+', 7);
        
        // Verify each field is correctly set and accessible
        assertEquals(3, question.getOperand1(), "Operand1 should be correctly set and returned.");
        assertEquals(4, question.getOperand2(), "Operand2 should be correctly set and returned.");
        assertEquals('+', question.getOperator(), "Operator should be correctly set and returned.");
        assertEquals(7, question.getCorrectAnswer(), "Correct answer should be correctly set and returned.");
    }

    @Test
    void testToString() {
        // Initialize a question
        Question question = new Question(5, 2, '-', 3);
        
        // Verify the toString method
        assertEquals("5 - 2 = ?", question.toString(), "toString should return the correct format.");
    }
}
