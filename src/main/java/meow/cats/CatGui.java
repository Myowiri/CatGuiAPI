package meow.cats;

import meow.cats.items.BooleanButton;
import meow.cats.items.Item;
import meow.cats.items.ColorPicker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CatGui extends Screen {

    public final List<Item> buttons = new ArrayList<>();
    public int Width = 160;
    public int Height = 20;
    public int ScrollY = 0;
    public int maxY;

    public CatGui() {
        super(Component.literal("CatGui"));
        addButtons();
    }

    public void addButtons(){

    }

    @Override
    public boolean mouseClicked(MouseButtonEvent click, boolean doubled) {
        for (Item button : buttons) {
            button.mouseClicked((int)click.x(), (int)click.y());
        }

        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent click, double deltaX, double deltaY) {

        for (Item item : buttons) {
            item.mouseDragged((int)click.x(), (int)click.y());
        }

        return super.mouseDragged(click, deltaX, deltaY);
    }


    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (verticalAmount < 0 && (getMaxY() + 40 + ScrollY) > getScreenHeight())  {
            ScrollY -= 10;
        } else if (verticalAmount > 0 && ScrollY<0) {
            ScrollY += 10;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent click) {
        for (Item item : buttons) {
            item.mouseReleased();
        }

        return super.mouseReleased(click);
    }

    public int getMaxY() {
        return maxY;
    }

    public int getSpacing(){
        return 5;
    }

    public int getScreenHeight(){
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    public int getScreenWidth(){
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    public int getStartX(){
        int screenWidth = getScreenWidth();
        int buttonWidth = this.getWidth();
        int x = (screenWidth - buttonWidth) / 2;
        return x;
    }

    public int getStartY(){
        int screenHeight = getScreenHeight();
        int buttonHeight = this.getHeight();
        int spacing = getSpacing();
        int totalHeight = buttonHeight * 2 + spacing;
        int y = (screenHeight - totalHeight) / 3-60;
        return y;
    }


    @Override
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        int y = getStartY();
        int x = getStartX();
        for (Item item : buttons) {
            item.render(context, x, y + ScrollY);
            if (item.Space())
            y += item.getHeight() + getSpacing() ;
        }
        maxY = y;
    }

    public int getWidth(){
        return Width;
    }

    public int getHeight(){
        return Height;
    }

}