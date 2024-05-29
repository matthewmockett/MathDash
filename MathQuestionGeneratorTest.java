import static org.junit.Assert.*;
import org.junit.Test;

public class MathQuestionGeneratorTest {

    @Test
    public void testGenerateQuestionReturnsQuestionForAddition() {
        MathQuestionGenerator generator = new MathQuestionGenerator();
        generator.setSection("Addition");
        generator.setLevel(1);
        assertNotNull("Should return a Question object for Addition", generator.generateQuestion());
    }

    @Test
    public void testGenerateQuestionReturnsQuestionForSubtraction() {
        MathQuestionGenerator generator = new MathQuestionGenerator();
        generator.setSection("Subtraction");
        generator.setLevel(1);
        assertNotNull("Should return a Question object for Subtraction", generator.generateQuestion());
    }

    @Test
    public void testGenerateQuestionReturnsQuestionForMultiplication() {
        MathQuestionGenerator generator = new MathQuestionGenerator();
        generator.setSection("Multiplication");
        generator.setLevel(1);
        assertNotNull("Should return a Question object for Multiplication", generator.generateQuestion());
    }

    @Test
    public void testGenerateQuestionReturnsQuestionForDivision() {
        MathQuestionGenerator generator = new MathQuestionGenerator();
        generator.setSection("Division");
        generator.setLevel(1);
        assertNotNull("Should return a Question object for Division", generator.generateQuestion());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateQuestionThrowsExceptionForInvalidSection() {
        MathQuestionGenerator generator = new MathQuestionGenerator();
        generator.setSection("Invalid");
        generator.setLevel(1);
        generator.generateQuestion(); // Expect an IllegalArgumentException
    }
}
