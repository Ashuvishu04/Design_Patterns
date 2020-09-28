package org.akshay.designpatterns.StructuralPattern.ChainOfResponsibility;

public class CORDemo {

  public static void main(String[] args) {
    Creature creature = new Creature("Goblin",2,2);
    System.out.println(creature);

    CreatureModifier root = new CreatureModifier(creature);

    root.add(new NoBonusesModifier(creature));
    System.out.println("Let's double goblin attack");
    root.add(new DoubleAttackModifier(creature));

    System.out.println("Let's Increase goblin defence");
    root.add(new IncreaseDefenceModifier(creature));

    root.handle();
    System.out.println(creature);
  }
}

class Creature{

  public String name;
  public int attack,defence;

  public Creature(String name, int attack, int defence) {
    this.name = name;
    this.attack = attack;
    this.defence = defence;
  }

  @Override
  public String toString() {
    return "Creature{" +
        "name='" + name + '\'' +
        ", attack='" + attack + '\'' +
        ", defence='" + defence + '\'' +
        '}';
  }
}

class CreatureModifier{

  protected Creature creature;
  protected CreatureModifier next;

  public CreatureModifier(Creature creature) {
    this.creature = creature;
  }

  public void add(CreatureModifier cm){
    if(next!=null){
      next.add(cm);}
    else{
      next = cm;
    }
  }

  public void handle(){
    if(next!=null){
      next.handle();
    }
  }
}

class DoubleAttackModifier extends CreatureModifier {

  public DoubleAttackModifier(Creature creature) {
    super(creature);
  }

  public void handle(){
    System.out.println("Doubling " + creature.name + "'s attack");
    creature.attack *= 2;
    super.handle();
  }
}

class IncreaseDefenceModifier extends CreatureModifier {

  public IncreaseDefenceModifier(Creature creature) {
    super(creature);
  }

  public void handle(){
    System.out.println("Increasing " + creature.name + "'s defence");
    creature.defence += 3;
    super.handle();
  }
}

class NoBonusesModifier extends  CreatureModifier{

  public NoBonusesModifier(Creature creature) {
    super(creature);
  }

  public void handle(){
    System.out.println("No Bonuses for you!");
  }
}
