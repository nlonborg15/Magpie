/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statement)
  {
    String response = "";
    statement = statement.trim();
    if (statement.indexOf("no") >= 0)
    {
      response = "Why so negative?";
    }
    else if (statement.indexOf("mother") >= 0
               || statement.indexOf("father") >= 0
               || statement.indexOf("sister") >= 0
               || statement.indexOf("brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (statement.indexOf("Landgraf") >= 0
               || statement.indexOf("Kiang") >= 0)
    {
      response = "Oh, wow. He sounds like a really good teacher";
    }
    else if (statement.indexOf("cat") >= 0
               || statement.indexOf("dog") >= 0
               || statement.indexOf("iguana") >= 0
               || statement.indexOf("mouse") >= 0
               || statement.indexOf("rabbit") >= 0)
    {
      response = "Tell me more about your pets";
    }
    else if (statement.length() <= 0)
    {
      response = "Say something, please";
    }
    else if (statement.indexOf("overwhelm") >= 0
             || statement.indexOf("stress") >= 0)
    {
      response = "Do you want to talk about it?";
    }
    else if (statement.indexOf("weight") >= 0
             || statement.indexOf("lift") >= 0)
    {
      response = "Yeah, lifting is good for you. You should totally do it";
    }
    else if (statement.indexOf("dragon") >= 0)
    {
      response = "YEEEEESSSSS! I love dragons!";
    }
    else
    {
      response = getRandomResponse();
    }
    return response;
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 6;
    double r = Math.random();
    int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
    String response = "";
    
    if (whichResponse == 0)
    {
      response = "Interesting, tell me more.";
    }
    else if (whichResponse == 1)
    {
      response = "Hmmm.";
    }
    else if (whichResponse == 2)
    {
      response = "Do you really think so?";
    }
    else if (whichResponse == 3)
    {
      response = "You don't say.";
    }
    else if (whichResponse == 4)
    {
      response = "I'm not sure I understand";
    }
    else if (whichResponse == 5)
    {
      response = "Could you explain a little more?";
    }
    
    return response;
  }
}

/* Priortization in this program works based on the tree of else if statements.
 * The program tries to find a keyword from the list above in the statement, and if it can't then
 * it moves down a tier. It should respond with the response it has for "no" if the statement "My  
 * mother has a dog but no cat” since there is a "no" in their statement and "no" is the first if statement
 * in the respond method. The program will also respond to words that have the sections which are identical to
 * keywords. That means the program will respond to a sentence with "know" in it as though it were responsing
 * to a sentence with "no" since it just looks for the keyword and responds accordingly*/

