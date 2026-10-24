package meow.cats.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

import java.awt.*;

public abstract class Item {

    public Integer x = null;
    public Integer y = null;
    public String name;
    public int Width = 160;
    public int Height = 20;
    public int dx = 0;
    public int dy = 0;
    public boolean space = true;
    public Color mainColor = new Color(0x99000000, true);
    public Color secondColor = new Color(0x99878EE6, true);
    public Color thirdColor = new Color(0x66B4BBFF, true);

    public boolean Space(){
        return space;
    }

    public Item doNotSpace(){
        space = false;
        return this;
    }

    public Item(String name){
        this.name = name;
    }

    public Item setX(int x){
        this.x = x;
        return this;
    }

    public Item setY(int y){
        this.y = y;
        return this;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }

    public Item setDx(int x){
        dx = x;
        return this;
    }

    public Item setDy(int y){
        dy = y;
        return this;
    }

    public Item setWidth(int Width){
        this.Width = Width;
        return this;
    }

    public Item setHeight(int Height){
        this.Height = Height;
        return this;
    }

    public int getWidth(){
        return Width;
    }

    public int getHeight(){
        return Height;
    }

    public Item setMainColor(Color x){
        this.mainColor = x;
        return this;
    }

    public Item setSecondColor(Color x){
        this.secondColor = x;
        return this;
    }

    public Color getMainColor(){
        return mainColor;
    }

    public Color getSecondColor(){
        return secondColor;
    }

    public Color getThirdColor(){
        return thirdColor;
    }

    public void render(GuiGraphicsExtractor context, int x, int y) {
    }

    public boolean mouseClicked(int mouseX, int mouseY) {
        return false;
    }

    public void mouseReleased() {
    }

    public void mouseDragged(int mouseX, int mouseY) {}

    public void doSound(){
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1f));
    }
}
