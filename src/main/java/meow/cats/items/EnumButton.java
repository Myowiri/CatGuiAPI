package meow.cats.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class EnumButton<T extends Enum<T>> extends Item {
    private T state;
    private final T[] values;
    private final Consumer<T> onChange;
    public int rx;
    public int ry;

    public EnumButton(T state, String name, Consumer<T> onChange) {
        super(name);
        this.state = state;
        this.values = state.getDeclaringClass().getEnumConstants();
        this.onChange = onChange;
    }


    public EnumButton(T state, Component name, Consumer<T> onChange) {
        super(name);
        this.state = state;
        this.values = state.getDeclaringClass().getEnumConstants();
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
                this.getSecondColor().getRGB()
        );

        Component displayText = this.name.copy().append(": " + state.name());

        context.text(
                Minecraft.getInstance().font,
                displayText,
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

        int nextIndex = (state.ordinal() + 1) % values.length;
        state = values[nextIndex];

        onChange.accept(state);
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

    public T getState() {
        return state;
    }
}
