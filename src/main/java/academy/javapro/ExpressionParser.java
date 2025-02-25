package academy.javapro;

class ExpressionParser {
    private final String input;
    private int position;

    public ExpressionParser(String input) {
        this.input = input;
        this.position = 0;
    }

    // expr → expr + term
    public double parseExpression() {
    	
    	double result = parseTerm();  // TODO: Get the leftmost term value by calling parseTerm()
    	
    	while(position < input.length() && input.charAt(position) == '+') { // TODO: While we haven't reached the end of input and current char is '+'
    		position++; // TODO: Increment position to skip the '+' operator
    		double rightTerm = parseTerm(); // TODO: Get the next term on the right
    		result += rightTerm; // TODO: Add the right term to our running total
    	
    	}
    	return result; // TODO: Return the final value
    	
       // throw new UnsupportedOperationException("Implement parseExpression");
    }

    // term → term * factor
    private double parseTerm() {

    	double factor = parseFactor(); // TODO: Get the leftmost factor value by calling parseFactor()
    	
    	while(position < input.length() && input.charAt(position) == '*' ) {  // TODO: While we haven't reached the end of input and current char is '*'
    		
    		position++; // TODO: Increment position to skip the '*' operator
    		double rightFactor = parseFactor(); // TODO: Get the next factor on the right
    		factor *= rightFactor; // TODO: Multiply the right factor with our running total
    		
    	}
    	return factor;  // TODO: Return the final value
       // throw new UnsupportedOperationException("Implement parseTerm");
    }

    // factor → ( expr )
    private double parseFactor() {
       
        
        
       
        
       
    	
    	if(position < input.length() && input.charAt(position) == '(') {  // TODO: Check if we have an opening parenthesis and haven't reached end of input
    		position++;//Skips Open Parenthesis 
    		double result = parseExpression(); // TODO: Parse the expression inside the parentheses
    		
    		if(position < input.length() && input.charAt(position) == ')') {  // TODO: Increment position to skip the closing parenthesis
    			position++;//Skips Closed
  
    		}
    		  return result; // TODO: Return the result of the parenthesized expression
    	}else {
    		return parseNumber();  // TODO: If no parentheses, parse and return a number
    	}
    	
    	
       // throw new UnsupportedOperationException("Implement parseFactor");
    }

    // Parse a numeric value
    private double parseNumber() {
        
       
        
        
        
    	
    	StringBuilder collectDigits = new StringBuilder(); // TODO: Create a StringBuilder to collect digits
    	boolean hasDecimal = false;
    	
    	while(position < input.length() && (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')) {  // TODO: While we haven't reached the end and current char is digit or decimal point
    		 if (hasDecimal) {
                 throw new IllegalArgumentException("Multiple decimal points in number at position: " + position);
             }
             hasDecimal = true;
         
    	collectDigits.append(input.charAt(position)); // TODO: Append the current digit to our number string
         position++; // TODO: Move to next character

     if (collectDigits.length() == 0) { 
         throw new IllegalArgumentException("Expected a number at position: " + position);
         
     }
     return Double.parseDouble(collectDigits.toString()); // TODO: Convert the string of digits to a double and return it
     
    	}
    		
        throw new UnsupportedOperationException("Implement parseNumber");
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "2 + 3 * (4 + 5)",    // Complex expression with parentheses
                "2 + 3 * 4",          // Basic arithmetic with precedence
                "(2 + 3) * 4",        // Parentheses changing precedence
                "2 * (3 + 4) * (5 + 6)", // Multiple parentheses
                "1.5 + 2.5 * 3"       // Decimal numbers
        };

        // Process each test case
        for (String expression : testCases) {
            System.out.println("\nTest Case: " + expression);
            try {
                ExpressionParser parser = new ExpressionParser(expression.replaceAll("\\s+", "")); // Remove spaces
                double result = parser.parseExpression();
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}