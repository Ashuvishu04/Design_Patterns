package org.akshay.designpatterns.StructuralPattern.FlyweightPattern;

import java.util.ArrayList;
import java.util.List;

public class FPProblem {

  public static void main(String[] args) {
    Sentence sentence = new Sentence("hello world");
    sentence.getWord(1).capitalize = true;
    System.out.println(sentence);
  }
}

class Sentence
{
  private String plainText;
  List<WordToken> tokens = new ArrayList<>();

  public Sentence(String plainText)
  {
    this.plainText = plainText;
  }

  public WordToken getWord(int index)
  {
    // todo
    WordToken token = new WordToken(index);
    tokens.add(token);
    return token;
  }

  @Override
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    String words[] = plainText.split(" ");
    for(int i=0;i<words.length;i++){
        for(WordToken token : tokens){
          if(token.index == i && token.capitalize){
              sb.append(words[i].toUpperCase());
          }
          else{
          sb.append(words[i]);}
        }
        if(i!=words.length-1)
        sb.append(" ");
    }
    return sb.toString();
  }

  class WordToken
  {
    public int index;
    public boolean capitalize;

    public WordToken(int index){
      this.index = index;
    }
  }
}
