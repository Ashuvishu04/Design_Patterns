package org.akshay.designpatterns.StructuralPattern.CommandPattern;

import com.google.common.collect.Lists;
import com.sun.tools.javac.util.List;
import org.akshay.designpatterns.StructuralPattern.CommandPattern.BankAccountCommand.Action;

public class BankAccount {

  private int balance;
  private int overDraftLimit = -500;

  public void deposit(int amount){
    balance += amount;
    System.out.println("Amount added is " + amount +
        " and total balance is " + balance);
  }

  public boolean withDraw(int amount){
    if(balance-amount>=overDraftLimit) {
      balance -= amount;
      System.out.println("Amount withdrew is " + amount +
          " and total balance is " + balance);
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "BankAccount{" +
        "balance=" + balance +
        '}';
  }
}

interface Command{
  void call();
  void undo();
}

class BankAccountCommand implements Command{

private BankAccount account;
private boolean isSucceeded;

  @Override
  public void call() {
    switch (action){
      case DEPOSIT:
        isSucceeded = true;
        account.deposit(amount);
        break;
      case WITHDRAW:
        isSucceeded = account.withDraw(amount);
        break;
    }
  }

  @Override
  public void undo() {
    if(!isSucceeded)
      return;
    switch (action){
      case DEPOSIT:
        account.withDraw(amount);
        break;
      case WITHDRAW:
        account.deposit(amount);
        break;
    }
  }

  public enum Action{
  DEPOSIT,WITHDRAW
}

private Action action;
private int amount;

  public BankAccountCommand(BankAccount account, Action action, int amount) {
    this.account = account;
    this.action = action;
    this.amount = amount;
  }
}

class Demo{

  public static void main(String[] args) {
    BankAccount account = new BankAccount();
    System.out.println(account);

    List<BankAccountCommand> commands = List.of(
      new BankAccountCommand(account, Action.DEPOSIT, 100),
      new BankAccountCommand(account, Action.WITHDRAW, 1000)
  );


    for(Command c : commands){
      c.call();
      System.out.println(account);
    }

    System.out.println();

    for(Command c : Lists.reverse(commands)){
      c.undo();
      System.out.println(account);
    }

  }
}
