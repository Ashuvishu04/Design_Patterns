package org.akshay.designpatterns.StructuralPattern.FacadePattern;

import java.util.ArrayList;
import java.util.List;

public class FPDemo {

  public static void main(String[] args) {
    Console console = Console.newConsole(30,20);
    console.render();
  }
}

class Buffer{

  private char[] characters;
  private int lineWidth;

  public Buffer(int lineWidth,int lineHeight){
    this.lineWidth = lineWidth;
    characters = new char[lineHeight*lineHeight];
  }

  public char charAT(int x, int y){
    return characters[y*lineWidth+x];
  }
}

class Viewport{

  private Buffer buffer;
  private int width;
  private int height;
  private int offsetX;
  private int offsetY;

  public Viewport(Buffer buffer,int width, int height, int offsetX, int offsetY){

    this.buffer = buffer;
    this.width = width;
    this.height = height;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
  }

  public char charAT(int x, int y){
    return buffer.charAT(x+offsetX,y+offsetY);
  }
}

class Console{
  private List<Viewport> viewports = new ArrayList<>();
  int width,height;

  public Console(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void addViewport(Viewport viewport){
    viewports.add(viewport);
  }

  public static Console newConsole(int width,int height){
    Buffer buffer = new Buffer(width,height);
    Viewport viewport = new Viewport(buffer,width,height,0,0);
    Console console = new Console(width,height);
    console.addViewport(viewport);
    return console;
  }

  public void render(){
    for(int y=0;y<height;++y){
      for(int x=0;x<width;++x){
        for(Viewport vp : viewports){
          System.out.println(vp.charAT(x,y));
        }
        System.out.println();
      }
    }
  }
}
