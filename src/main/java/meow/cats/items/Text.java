package meow.cats.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public class Text extends Item {

    public int textHeight;
    public int rx;
    public int ry;

    public Text(String name) {
        super(name);
    }

    @Override
    public void render(GuiGraphicsExtractor context, int x, int y) {
        int width = this.getWidth();

        textHeight = Minecraft.getInstance().font.split(Component.literal(this.name), width).size() * Minecraft.getInstance().font.lineHeight;

        rx = (this.x != null ? this.x : x) + dx;
        ry = (this.y != null ? this.y : y) + dy;

        context.fill(rx, ry, rx + width, ry + getHeight(), this.getMainColor().getRGB());

        Font font = Minecraft.getInstance().font;
        Component textComponent = Component.literal(this.name);


        int textY = ry + 10 ;

        context.textWithWordWrap(
                font,
                textComponent,
                rx +10,
                textY,
                width - 20,
                -1,
                true
        );
    }


    @Override
    public int getHeight(){
        return (textHeight) + 20;
    }

}
