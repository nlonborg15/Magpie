
public class Magpie
{
  public String getGreeting()//Starts off the conversation
  {
    return "Hello, let's talk.";
  }
  
  private int findKeyword(String statement, String goal,int startPos)
  {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in
    // the line below
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
    
    // Refinement--make sure the goal isn't part of a
    // word
    while (psn >= 0)
    {
      // Find the string of length 1 before and after
      // the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn).toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
      }
      // If before and after aren't letters, we've
      // found the word
      if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
            && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
      {
        return psn;
      }
      
      // The last position didn't work, so let's find
      // the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
    }
    return -1;
  }
  
  
  public String getResponse(String statement)
  {
    String response = "";
    statement = statement.trim();
    if (findKeyword(statement, "no") >= 0)
    {
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "mother") >= 0
               || findKeyword(statement, "father") >= 0
               || findKeyword(statement, "sister") >= 0
               || findKeyword(statement, "brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (findKeyword(statement, "Landgraf") >= 0
               || findKeyword(statement, "Kiang") >= 0)
    {
      response = "Oh, wow. He sounds like a really good teacher";
    }
    else if (findKeyword(statement, "cat") >= 0
               || findKeyword(statement, "dog") >= 0
               || findKeyword(statement, "iguana") >= 0
               || findKeyword(statement, "mouse") >= 0
               || findKeyword(statement, "rabbit") >= 0)
    {
      response = "Tell me more about your pets";
    }
    else if (statement.length() <= 0)
    {
      response = "Say something, please";
    }
    else if (findKeyword(statement, "overwhelm") >= 0
               || findKeyword(statement, "stress") >= 0)
    {
      response = "Do you want to talk about it?";
    }
    else if (findKeyword(statement, "weight") >= 0
               || findKeyword(statement, "lift") >= 0)
    {
      response = "Yeah, lifting is good for you. You should totally do it";
    }
    else if (findKeyword(statement, "dragon") >= 0)
    {
      response = "YEEEEESSSSS! I love dragons!";
    }
    else if (findKeyword(statement, "I want to", 0) >= 0)
    {
      response = transformIWantToStatement(statement);
    }
    else if (findKeyword(statement, "I want", 0) >=0)
    {
      response = transformIWantStatement(statement);
    }
    else
    {
      // Look for a two word (you <something> me)
      // pattern
      int psn = findKeyword(statement, "you", 0);
      
      int psn2 = findKeyword(statement, "I", 0);
      
      if (psn >= 0 && findKeyword(statement, "me", psn) >= 0)
      {
        response = transformYouMeStatement(statement);
      }
      else if (psn2 >= 0 && findKeyword(statement, "you", psn2) >= 0)
      {
        response = transformYouSomethingMeStatement(statement);
      }
      else
      {
        response = getRandomResponse();
      }
    }
    return response;
  }
  
  private String transformIWantToStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword(statement, "I want to", 0);
    String restOfStatement = statement.substring(psn + 9).trim();
    return "What would it mean to " + restOfStatement + "?";
  }
  
  private String transformIWantStatement(String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword(statement, "I want", 0);
    String restOfStatement = statement.substring(psn + 6).trim();
    return "Would you really be happy if you had " + restOfStatement + "?";
  }
  
  private String transformYouMeStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psnOfYou = findKeyword (statement, "you", 0);
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
    
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "What makes you think that I " + restOfStatement + " you?";
  }
  
  private String transformYouSomethingMeStatement(String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psnOfI = findKeyword (statement, "I", 0);
    int psnOfYou = findKeyword (statement, "you", psnOfI + 1);
    
    String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
    return "Why do you " + restOfStatement + " me?";
  }
  
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  
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
 * mother has a dog but no cat� since there is a "no" in their statement and "no" is the first if statement
 * in the respond method. The program will also respond to words that have the sections which are identical to
 * keywords. That means the program will respond to a sentence with "know" in it as though it were responsing
 * to a sentence with "no" since it just looks for the keyword and responds accordingly*/

