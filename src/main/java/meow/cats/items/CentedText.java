package meow.cats.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class CentedText extends Item{

    public int rx;
    public int ry;

    public CentedText(String name) {
        super(name);
    }

    @Override
    public void render(GuiGraphicsExtractor context, int x, int y) {
        int width = this.getWidth();
        int height = this.getHeight();

        rx = (this.x != null ? this.x : x) + dx;
        ry = (this.y != null ? this.y : y) + dy;


        context.fill(rx, ry, rx + width, ry + height, this.getMainColor().getRGB());

        context.centeredText(
                Minecraft.getInstance().font,
                name,
                rx + width/2,
                ry + (height - 9) / 2,
                -1
        );

    }

}
