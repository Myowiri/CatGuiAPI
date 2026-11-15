package meow.cats.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.function.Consumer;

public class Button extends Item {
    private String name;
    private final Consumer<Button> onPress;
    public int rx;
    public int ry;

    public Button(String name, Consumer<Button> onPress) {
        super(name);
        this.name = name;
        this.onPress = onPress;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void render(GuiGraphicsExtractor context, int x, int y) {
        int width = this.getWidth();
        int height = this.getHeight();

        rx = (this.x != null ? this.x : x) + dx;
        ry = (this.y != null ? this.y : y) + dy;

        context.fill(
                rx, ry, rx + width, ry + height,
                this.getSecondColor().getRGB()
        );

        context.text(
                Minecraft.getInstance().font,
                name,
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

        if (onPress != null) {
            onPress.accept(this);
        }
        doSound();
        return true;
    }

    @Override
    public int getX() {
        return rx;
    }

    @Override
    public int getY() {
        return ry;
    }
}
