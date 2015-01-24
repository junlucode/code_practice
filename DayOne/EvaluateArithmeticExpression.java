import java.util.Stack;

public class EvaluateArithmeticExpression{
	public int evaluate(String[] input) throws IllegalArgumentException{
		if (input == null) throw new IllegalArgumentException();
		
		Stack<Integer> evaluationStack = new Stack<Integer>();
		try {
			for (String element : input) {
				switch(element) {
				case "+":
					evaluationStack.push(evaluationStack.pop() + evaluationStack.pop());
					break;
				case "-": 
					int minusNum = evaluationStack.pop();
					evaluationStack.push(evaluationStack.pop() - minusNum);
					break;
				case "*":
					evaluationStack.push(evaluationStack.pop() * evaluationStack.pop());
					break;
				case "/":
					int divisionNum = evaluationStack.pop();
					evaluationStack.push(evaluationStack.pop() / divisionNum);
					break;
				default:
					evaluationStack.push(Integer.valueOf(element));
					break;
				}
			}
			if (evaluationStack.size() != 1) {
				throw new IllegalArgumentException();
			}
			return evaluationStack.pop();
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void main(String[] argvs){
		EvaluateArithmeticExpression evaluator = new EvaluateArithmeticExpression();
		
		String[] testcase1 = {"2", "1", "+", "3", "*"};
		System.out.println(evaluator.evaluate(testcase1));
		String[] testcase2 = {"4", "13", "5", "/", "+"};
		System.out.println(evaluator.evaluate(testcase2));
		String[] testcase3 = {"3"};
		System.out.println(evaluator.evaluate(testcase3));
		String[] testcase4 = {"2", "1", "+", "3"};
		System.out.println(evaluator.evaluate(testcase4));
	}
}
