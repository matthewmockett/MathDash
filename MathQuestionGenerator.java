import java.util.Random;

/**
 * This class is responsible for generating random math questions
 * based on the specified level and section. The section determines
 * the type of math operation (addition, subtraction, multiplication, division),
 * and the level affects the range of numbers used in the questions.
 * 
 * @Author Vincent
 */

public class MathQuestionGenerator {
    private Random random;
    private int level;
    private String section;

    /**
     * Constructs a MathQuestionGenerator with a new Random instance.
     */
    
    public MathQuestionGenerator() {
        this.random = new Random();
    }

    /**
     * Sets the difficulty level for the questions.
     *
     * @param level The difficulty level.
     */
    
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * Sets the section or type of math questions to generate.
     *
     * @param section The section/type of questions
     */
    
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * Generates a math question based on the current level and section.
     *
     * @return A new Question object representing the math question.
     * @throws IllegalArgumentException if the section is not recognized.
     */
    
    public Question generateQuestion() {
        switch (section) {
            case "Addition":
                return generateAdditionQuestion();
            case "Subtraction":
                return generateSubtractionQuestion();
            case "Multiplication":
                return generateMultiplicationQuestion();
            case "Division":
                return generateDivisionQuestion();
            default:
                throw new IllegalArgumentException("Invalid section: " + section);
        }
    }
    
    // Generates an addition question.
    private Question generateAdditionQuestion() {
        int operand1 = random.nextInt(level * 10);
        int operand2 = random.nextInt(level * 10);
        return new Question(operand1, operand2, '+', operand1 + operand2);
    }

    // Generates a subtraction question.
    private Question generateSubtractionQuestion() {
        int operand1 = random.nextInt(level * 10);
        int operand2 = random.nextInt(level * 10);
        return new Question(operand1, operand2, '-', operand1 - operand2);
    }

 // Generates a multiplication question.
    private Question generateMultiplicationQuestion() {
        int operand1 = random.nextInt(level * 5);
        int operand2 = random.nextInt(level * 5);
        return new Question(operand1, operand2, '*', operand1 * operand2);
    }

 // Generates a division question ensuring no division by zero
    private Question generateDivisionQuestion() {
        int operand1 = random.nextInt(level * 5) + 1; // Avoid division by zero
        int operand2 = random.nextInt(level * 5) + 1;
        return new Question(operand1 * operand2, operand2, '/', operand1); // Ensure clean division
    }

}

