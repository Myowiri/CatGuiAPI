package meow.cats.items;

import meow.cats.CatGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.Locale;
import java.util.function.Consumer;

public class Slider extends Item {
    private final String name;
    private final float min;
    private final float max;
    private final Consumer<Float> onChange;
    private float value;
    private boolean dragging;
    private int rx;
    private int ry;


    public Slider(String name, float value, float min, float max,
                  Consumer<Float> onChange) {
        super(name);
        this.name = name;
        this.min = min;
        this.max = max;
        this.value = clamp(value);
        this.onChange = onChange;
    }
    
    @Override
    public void render(GuiGraphicsExtractor context, int x, int y) {
        rx = (this.x != null ? this.x : x) + dx;
        ry = (this.y != null ? this.y : y) + dy;
        int width = this.getWidth();
        int height = this.getHeight();

        context.fill(rx, ry, rx + width, ry + height, this.getMainColor().getRGB());

        String label = String.format(
                Locale.ROOT, "%s: %.1f", name, value
        );

        context.text(
                Minecraft.getInstance().font,
                label,
                rx + 6,
                ry + 2,
                -1
        );

        int barX = rx + 6;
        int barY = ry + height - 5;
        int barWidth = width - 12;
        float range = max - min;
        float progress = range == 0.0f
                ? 0.0f
                : (value - min) / range;

        progress = Math.max(0.0f, Math.min(1.0f, progress));

        int filledWidth = Math.round(barWidth * progress);

        context.fill(barX, barY, barX + barWidth, barY + 2, this.getThirdColor().getRGB());
        context.fill(barX, barY, barX + filledWidth, barY + 2, this.getSecondColor().getRGB());
    }
    
    @Override
    public boolean mouseClicked(int mouseX, int mouseY) {
        int width = this.getWidth();
        int height = this.getHeight();

        if (mouseX < rx || mouseX >= rx + width
                || mouseY < ry || mouseY >= ry + height) {
            return false;
        }

        dragging = true;
        setFromMouse(mouseX);
        return true;
    }


    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        if (dragging) {
            setFromMouse(mouseX);
        }
    }
    @Override
    public void mouseReleased() {
        dragging = false;
    }

    private void setFromMouse(int mouseX) {
        int barX = rx + 6;
        int barWidth = this.getWidth() - 12;
        float progress = (float) (mouseX - barX) / barWidth;
        progress = Math.max(0.0f, Math.min(1.0f, progress));

        value = min + progress * (max - min);
        value = clamp(value);
        onChange.accept(value);
    }

    private float clamp(float value) {
        return Math.max(min, Math.min(max, value));
    }
    
    @Override
    public int getX(){
        return rx;
    }
    @Override
    public int getY(){
        return ry;
    }
}