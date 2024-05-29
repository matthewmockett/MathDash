/**
 * The Question class represents a single arithmetic question. It stores the operands,
 * the operator, and the correct answer. This class provides a structured way to represent
 * an arithmetic problem that can be used in quizzes or as part of educational software.
 *
 * @author Mohammed
 */
public class Question {
    private int operand1;
    private int operand2;
    private char operator;
    private int correctAnswer;
    // Timer functionality can be added here


    /**
     * Constructs a new {@code Question} with specified operands, operator, and the correct answer.
     *
     * @param operand1 the first operand in the arithmetic question
     * @param operand2 the second operand in the arithmetic question
     * @param operator the operator symbol, e.g., '+', '-', '*', or '/'
     * @param correctAnswer the correct answer for the arithmetic question
     */
    public Question(int operand1, int operand2, char operator, int correctAnswer) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Returns the first operand of the arithmetic question.
     *
     * @return the first operand
     */
    // Accessor methods for operands, operator, and correct answer
    public int getOperand1() {
        return operand1;
    }

    /**
     * Returns the second operand of the arithmetic question.
     *
     * @return the second operand
     */
    public int getOperand2() {
        return operand2;
    }

    /**
     * Returns the operator of the arithmetic question.
     *
     * @return the operator symbol
     */
    public char getOperator() {
        return operator;
    }

    /**
     * Returns the correct answer of the arithmetic question.
     *
     * @return the correct answer
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns a string representation of the arithmetic question in the format:
     * "operand1 operator operand2 = ?".
     *
     * @return the string representation of the arithmetic question
     */
    @Override
    public String toString() {
        return String.format("%d %c %d = ?", operand1, operator, operand2);
    }
}
