package meow.cats.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.function.Consumer;

public class BooleanButton extends Item {
    private boolean state;
    private final String name;
    private final Consumer<Boolean> onChange;
    public int rx;
    public int ry;

    public BooleanButton(boolean state, String name, Consumer<Boolean> onChange) {
        super(name);
        this.state = state;
        this.name = name;
        this.onChange = onChange;
    }

    @Override
    public void render(GuiGraphicsExtractor context, int x, int y) {
        int width = this.getWidth();
        int height = this.getHeight();

        rx = (this.x != null ? this.x : x) + dx;
        ry = (this.y != null ? this.y : y) + dy;


        context.fill(
                rx, ry, rx + width, ry + height,
                state ? this.getSecondColor().getRGB() : this.getMainColor().getRGB()
        );

        context.text(
                Minecraft.getInstance().font,
                name + ": " + (state ? "On" : "Off"),
                rx + 10,
                ry + (height - 9) / 2,
                -1
        );

    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY) {
        int width = this.getWidth();
        int height = this.getHeight();

        boolean inside = mouseX >= rx && mouseX < rx + width
                && mouseY >= ry && mouseY < ry + height;

        if (!inside) {
            return false;
        }

        state = !state;
        onChange.accept(state);
        doSound();
        return true;
    }

    @Override
    public int getX(){
        return rx;
    }
    @Override
    public int getY(){
        return ry;
    }



    public boolean getState() {
        return state;
    }
}