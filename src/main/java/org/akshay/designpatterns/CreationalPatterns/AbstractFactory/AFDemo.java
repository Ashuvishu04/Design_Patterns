package org.akshay.designpatterns.CreationalPatterns.AbstractFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;
import org.reflections.Reflections;


public class AFDemo {

  public static void main(String[] args) throws Exception {
    HotDrinkMachine hotDrinkMachine = new HotDrinkMachine();
    HotDrink hotDrink = hotDrinkMachine.makeDrink();
    hotDrink.consume();
  }
}

interface HotDrink{
  void consume();
}

class Tea implements HotDrink{

  @Override
  public void consume() {
    System.out.println("This Tea is delicious");
  }
}

class Coffee implements HotDrink{

  @Override
  public void consume() {
    System.out.println("This Coffee is delicious");
  }
}

interface HotDrinkFactory{
  HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory{

  @Override
  public HotDrink prepare(int amount) {
    System.out.println("Put in tea bag, boil water , pour " + amount + " ml, add lemon enjoy!");
    return new Tea();
  }
}

class CoffeeFactory implements HotDrinkFactory{

  @Override
  public HotDrink prepare(int amount) {
    System.out.println("Grind some beans, boil water , pour " + amount + " ml, add cream and sugar enjoy!");
    return new Coffee();
  }
}

class HotDrinkMachine{

  private List<Pair<String,HotDrinkFactory>> namedFactories = new ArrayList<>();

  public HotDrinkMachine() throws Exception {
    Set<Class<? extends HotDrinkFactory>> types = new Reflections(
        "org.akshay.designpatterns.CreationalPatterns.AbstractFactory")
        .getSubTypesOf(HotDrinkFactory.class);

    for(Class<? extends HotDrinkFactory> type : types){
      namedFactories.add(new Pair<>(
          type.getSimpleName().replace("Factory",""),
          type.newInstance()
      ));
    }
  }

  public HotDrink makeDrink() throws Exception{
    System.out.println("Available Drinks: ");
    for(int i=0;i<namedFactories.size();i++){
      Pair<String, HotDrinkFactory> item = namedFactories.get(i);
      System.out.println("" + i + ": "  + item.getKey());
    }

    BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    while(true){
      String s;
      int i,amount;
      if((s = bufferedReader.readLine())!=null
      && (i = Integer.parseInt(s)) >=0
      && i<namedFactories.size()){
        System.out.println("Specify Amount : ");
        s = bufferedReader.readLine();
        if(s!=null && (amount = Integer.parseInt(s))>0){
          return namedFactories.get(i).getValue().prepare(amount);
        }
      }
      System.out.println("Incorrect Input try again!");
    }
  }
}
