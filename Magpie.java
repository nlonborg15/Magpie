import java.util.Random;

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
    else if (findKeyword(statement, "said", 0) >= 0)
    {
      response = transformSaidStatement(statement);//early on so that it will preempt the others
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
    else if (findKeyword(statement, "is", 0) >= 0)
    {
      response = transformIsStatement(statement);
    }
    else if (findKeyword(statement, "am", 0) >= 0)
    {
      response = transformIAmStatement(statement);
    }
    else if (findKeyword(statement, "are", 0) >= 0)
    {
      response = transformAreStatement(statement);
    }
    else if (findKeyword(statement, "was", 0) >= 0
               || findKeyword(statement, "were", 0) >= 0)
    {
      response = transformWasWereStatement(statement);
    }
    else if (findKeyword(statement, "had", 0) >= 0)
    {
      response = transformHadStatement(statement);
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
  /*
   IsStatement: --doesn't work if not a simple statement. ex: he said this is best doesn't work
   -if keyword "is"
   ¥put " why is" in front
   ¥next put section before "is"
   ¥follow with rest of statement
   
   IAmStatement
   -if keyword "am"
   ¥put "why are you" in front
   ¥follow with rest of statement
   
   AreStatement
   -if keyword "are" with nouns before it
   ¥put "why are they" in front
   ¥next put subjects (nouns)--don't know how i would do this
   ¥end with rest of statement
   -if keyword "they" followed by "are"
   ¥put "why are they" in front
   ¥end with rest of statement
   -if keyword "we" followed by "are"
   ¥start with "why are you"
   ¥end with rest of statement
   -if keyword "you" followed by "are"
   ¥start with "why am I"
   ¥end with rest of statement
   
   I threw the ball to you.
   -if keyword
   -final: Why did you throw the ball to me
   
   I have been waiting, Obi-Wan.
   -
   -final: Why have you been waiting
   
   I was hoping we would meet.
   -If keyword "I" or "he" or "she" and "was"
   ¥start with "Why were you"
   ¥end with rest of phrase (after "was")
   -if keywords "you" or "we" or "they" and "were"
   ¥start with "Why were you"
   ¥end with rest of phrase (after "were")
   -final: Why were you hoping we would meet
   
   We had not read the books.
   -keyword "had"
   ¥Start with "Why had"
   ¥Next put section before "had"--doesn't work with things starting with "said"
   ¥Finish with section after "had"
   -final: Why had you not read the books
   
   I worked hard
   -
   -final: Why did you work hard
   
   I said you are intelligent
   -keyword "said"
   ¥Start with "why d0"
   ¥next put noun of who is saying + "say"
   ¥next put who is recieving
   ¥correctly conjugated "to be"
   ¥rest of statement after "to be"
   -final: Why did you say I am intelligent
   
   I tallied the results.
   -
   -final: Why did you tally the results
   */
  
  private String transformAreStatement (String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    
    int psn = findKeyword (statement, "are", 0);
    
    String restOfStatement = statement.substring(psn + 3, statement.length()).trim();
    
    if (findKeyword (statement, "we", 0) >= 0)
    {
      return "Why are you " + restOfStatement + "?";
    }
    else if (findKeyword (statement, "you", 0) >= 0)
    {
      return "Why am I " + restOfStatement + "?";
    }
    else
    {
      return "Why are they " + restOfStatement + "?";
    }
  }
  
  private String transformIAmStatement (String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psn = findKeyword (statement, "am", 0);
    
    String restOfStatement = statement.substring(psn + 2, statement.length()).trim();
    return "Why are you " + restOfStatement + "?";
  }
  
  private String transformIsStatement (String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psn = findKeyword (statement, "is", 0);
    
    String beforeIsStatement = statement.substring(0, psn).trim();
    String restOfStatement = statement.substring(psn + 2, statement.length()).trim();
    return "Why is " + beforeIsStatement + " " + restOfStatement;
  }
  private String transformWasWereStatement (String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    
    if (findKeyword(statement, "was", 0) >= 0)
    {
      int psn = findKeyword (statement, "was", 0);
      String restOfStatement = statement.substring(psn + 3, statement.length()).trim();
      String beginningOfStatement = statement.substring(0, psn).toLowerCase().trim();
      if (findKeyword(statement, "I", 0) >= 0)
      {
        return "Why were you " + restOfStatement + "?";
      }
      else 
      {
        return "Why was " + beginningOfStatement + " " + restOfStatement + "?";
      }
    }
    else
    {
      int psn = findKeyword (statement, "were", 0);
      String restOfStatement = statement.substring(psn + 4, statement.length()).trim();
      String beginningOfStatement = statement.substring(0, psn).toLowerCase().trim();
      return "Why were " + beginningOfStatement + " " + restOfStatement + "?";
    }
    
  }
  private String transformHadStatement (String statement)
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psn = findKeyword (statement, "had", 0);
    String restOfStatement = statement.substring(psn + 3, statement.length()).trim();
    String beginningOfStatement = statement.substring(0, psn).toLowerCase().trim();
    return "Why had " + beginningOfStatement + " " + restOfStatement + "?";
    
  }
  private String transformSaidStatement (String statement)//changes said statement
  {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psn = findKeyword (statement, "said", 0);
    int psn2;
    if (findKeyword(statement, "am", 0) >= 0)
    {
      psn2 = findKeyword (statement, "am", 0) + 2;
    }
    else if (findKeyword(statement, "are", 0) >= 0)
    {
      psn2 = findKeyword (statement, "are", 0) + 3;
    }
    else if (findKeyword(statement, "is", 0) >= 0)
    {
      psn2 = findKeyword(statement, "is", 0) + 2;
    }
    else
    {
      psn2 = 0;
    }
    String section1 = statement.substring(0, psn);
    String section2 = statement.substring(psn, statement.length());
    String restOfStatement = statement.substring(psn2, statement.length()).trim();
    return "Why do " + getPronoun(section1) + " say " + getPronoun(section2) + " " + getToBe(statement) + " " + restOfStatement + "?";
  }
  
  
  
  private String getRandomResponse ()
  {
    Random r = new Random ();
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  
  private String getPronoun (String statement) //meant to be able to switch pronouns
  {
    if (findKeyword(statement, pronouns [0][0], 0) >= 0)
    {
      return pronouns[0][1];
    }
    else if (findKeyword(statement, pronouns [0][1], 0) >= 0)
    {
      return pronouns[0][0];
    }
    else if (findKeyword(statement, pronouns [1][0], 0) >= 0)
    {
      return pronouns[1][1];
    }
    else
    {
      return pronouns[1][1];
    }
  }
  private String [] [] pronouns = 
  { {"I", "you"},
    {"We", "you"} };
  
  private String getToBe(String statement)
  {
    if (findKeyword(statement, toBe [0][0], 0) >= 0)
    {
      return toBe[0][1];
    }
    else if (findKeyword(statement, toBe [1][0], 0) >= 0
               ||findKeyword(statement, toBe [4][0], 0) >= 0
               ||findKeyword(statement, toBe [5][0], 0) >= 0)
    {
      return pronouns[1][1];
    }
    else if (findKeyword(statement, toBe [2][0], 0) >= 0
               ||findKeyword(statement, toBe [3][0], 0) >= 0)
    {
      return toBe[2][1];
    }
    else
    {
      return pronouns[1][1];
    }
  }
  
  private String [] [] toBe =
  { {"I", "am"},
    {"you", "are"},
    {"he", "is"},
    {"she"},
    {"we"},
    {"they"} };
  
  private String [] randomResponses = 
  {
    "Interesting, tell me more",
    "Hmmm.",
    "Do you really think so?",
    "You don't say.",
    "Could you explain a little more?",
    "I'm not sure I understand",
    "...I don't even know what to say to that",
    "I'm a little confused"
  };
}



