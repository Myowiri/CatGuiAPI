package meow.cats.Utils;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.renderer.RenderPipelines;
import org.joml.Matrix3x2f;

import java.awt.*;

public class RenderUtils {
    public static void horizontalGradient(GuiGraphicsExtractor context, float x1, float y1, float x2, float y2, Color left, Color right) {
        int ix1 = Math.round(x1);
        int iy1 = Math.round(y1);
        int ix2 = Math.round(x2);
        int iy2 = Math.round(y2);
        gradient(context, ix1, iy1, ix2, iy2, left.getRGB(), left.getRGB(), right.getRGB(), right.getRGB());
    }

    public static void verticalGradient(GuiGraphicsExtractor context, float x1, float y1, float x2, float y2, Color top, Color bottom) {
        int ix1 = Math.round(x1);
        int iy1 = Math.round(y1);
        int ix2 = Math.round(x2);
        int iy2 = Math.round(y2);
        gradient(context, ix1, iy1, ix2, iy2, top.getRGB(), bottom.getRGB(), bottom.getRGB(), top.getRGB());
    }

    public static void gradient(
            GuiGraphicsExtractor graphics,
            int x1,
            int y1,
            int x2,
            int y2,
            int topLeft,
            int bottomLeft,
            int bottomRight,
            int topRight
    ) {
        graphics.guiRenderState.addGuiElement(
                new RectRenderState(
                        RenderPipelines.GUI,
                        TextureSetup.noTexture(),
                        new Matrix3x2f(graphics.pose()),
                        x1,
                        y1,
                        x2,
                        y2,
                        topLeft,
                        bottomLeft,
                        bottomRight,
                        topRight,
                        graphics.scissorStack.peek()
                )
        );
    }
}
