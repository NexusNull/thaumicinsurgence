package thaumicinsurgence.renderers.tileentityrenderers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;
import thaumcraft.client.renderers.tile.TileRunicMatrixRenderer;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileMatrixAlphaRenderer extends TileRunicMatrixRenderer {

    private ModelCube model = new ModelCube(0);
    private ModelCube model_over = new ModelCube(32);
    static Map<String, ResourceLocation> boundTextures = new HashMap();
    int type;

    @SideOnly(Side.CLIENT)
    public TileMatrixAlphaRenderer(int type) {
        super(type);
        this.type = type;
    }

    @SideOnly(Side.CLIENT)
    private void drawHalo(TileEntity is, double x, double y, double z, float par8, int count) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
        int q = !FMLClientHandler.instance().getClient().gameSettings.fancyGraphics ? 10 : 20;
        Tessellator tessellator = Tessellator.instance;
        RenderHelper.disableStandardItemLighting();
        float f1 = (float) count / 500.0F;
        float f3 = 0.9F;
        float f2 = 0.0F;
        Random random = new Random(245L);
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDisable(3008);
        GL11.glEnable(2884);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();

        for (int i = 0; i < q; ++i) {
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
            tessellator.startDrawing(6);
            float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
            float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
            fa = fa / (20.0F / ((float) Math.min(count, 50) / 50.0F));
            f4 = f4 / (20.0F / ((float) Math.min(count, 50) / 50.0F));
            tessellator.setColorRGBA_I(16777215, (int) (255.0F * (1.0F - f2)));
            tessellator.addVertex(0.0D, 0.0D, 0.0D);
            tessellator.setColorRGBA_I(13369599, 0);
            tessellator.addVertex(-0.866D * (double) f4, fa, (-0.5F * f4));
            tessellator.addVertex(0.866D * (double) f4, fa, (-0.5F * f4));
            tessellator.addVertex(0.0D, fa, (f4));
            tessellator.addVertex(-0.866D * (double) f4, fa, (-0.5F * f4));
            tessellator.draw();
        }

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glDisable(2884);
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glColor4f(0F, 0F, 0F, 0F);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        RenderHelper.enableStandardItemLighting();
        GL11.glBlendFunc(770, 771);
        GL11.glPopMatrix();
    }

    @SideOnly(Side.CLIENT)
    public void renderInfusionMatrix(TileEntityInfusionMatrixAlpha is, double par2, double par4, double par6,
            float par8) {
        GL11.glPushMatrix();
        UtilsFX.bindTexture(VersionInfo.ModID, "model/infusion-2-gilded.png");
        GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 0.5F, (float) par6 + 0.5F);
        float ticks = (float) Minecraft.getMinecraft().renderViewEntity.ticksExisted + par8;
        if (is.getWorldObj() != null) {
            GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
        }

        // this is a reference to instability, was originally 50.0 and 50.0F respectively
        float instability = Math
                .min(6.0F, 1.0F + (float) is.instability * 0.66F * ((float) Math.min(is.craftCount, 100) / 100.0F));
        float o = is.startUp;
        float i = 0F;

        for (int a = 0; a < 2; ++a) {
            for (int b = 0; b < 2; ++b) {
                if (a == 0 && b == 0) i = 0;
                if (a == 0 && b == 1) i = 2;
                if (a == 1 && b == 1) i = 4;
                if (a == 1 && b == 0) i = 6;
                for (int c = 0; c < 2; ++c) {
                    ++i;

                    GL11.glPushMatrix();
                    GL11.glTranslatef(
                            (0.25F - 0.5F * b) * (1 - o) + o * (float) Math.cos(Math.PI * 0.25F * i),
                            (0.25F - 0.5F * c) * (1 - o),
                            (0.25F - 0.5F * a) * (1 - o) + o * (float) Math.sin(Math.PI * 0.25F * i));
                    GL11.glRotatef(90.0F * o, 0.5F, 0.0F, 0.5F);
                    GL11.glRotatef(ticks % 360.0F * o, 0.5F, 0.0F, 0.5F);
                    GL11.glScaled(0.45D, 0.45D, 0.45D);

                    GL11.glRotatef(90.0F * o, (a > 0 ? 1F : -1F), (b > 0 ? 1F : -1F), (c > 0 ? 1F : -1F));

                    this.model.render();
                    GL11.glPopMatrix();
                }
            }
        }

        if (is.active) {
            GL11.glPushMatrix();
            GL11.glAlphaFunc(516, 0.003921569F);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 1);

            for (int a = 0; a < 2; ++a) {
                for (int b = 0; b < 2; ++b) {
                    if (a == 0 && b == 0) i = 0;
                    if (a == 0 && b == 1) i = 2;
                    if (a == 1 && b == 1) i = 4;
                    if (a == 1 && b == 0) i = 6;
                    for (int c = 0; c < 2; ++c) {
                        // this is a reference to instability
                        i++;

                        GL11.glPushMatrix();
                        GL11.glTranslatef(
                                (0.25F - 0.5F * b) * (1 - o) + o * (float) Math.cos(Math.PI * 0.25F * i),
                                (0.25F - 0.5F * c) * (1 - o),
                                (0.25F - 0.5F * a) * (1 - o) + o * (float) Math.sin(Math.PI * 0.25F * i));
                        GL11.glRotatef(90.0F * o, 0.5F, 0.0F, 0.5F);
                        GL11.glRotatef(ticks % 360.0F * o, 0.5F, 0.0F, 0.5F);
                        GL11.glRotatef(90.0F * o, (a > 0 ? 1F : -1F), (b > 0 ? 1F : -1F), (c > 0 ? 1F : -1F));

                        GL11.glScaled(0.46D, 0.46D, 0.46D);
                        int j = 15728880;
                        int k = j % 65536;
                        int l = j / 65536;
                        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) k, (float) l);
                        GL11.glColor4f(
                                0.8F,
                                0.1F,
                                1.0F,
                                (MathHelper.sin((ticks + (float) (a * 2) + (float) (b * 3) + (float) (c * 4)) / 4.0F)
                                        * 0.1F + 0.2F) * is.startUp);
                        this.model_over.render();
                        GL11.glPopMatrix();
                    }
                }
            }

            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            GL11.glPopMatrix();
        }

        GL11.glPopMatrix();
        if (is.crafting) {
            this.drawHalo(is, par2, par4, par6, par8, is.craftCount);
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        switch (this.type) {
            case 0:
                this.renderInfusionMatrix((TileEntityInfusionMatrixAlpha) par1TileEntity, par2, par4, par6, par8);
                break;
            case 1:
                this.renderTileEntityAt(par1TileEntity, par2, par4, par6, par8);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void bindTexture(String texture) {
        ResourceLocation rl;
        if (boundTextures.containsKey(texture)) {
            rl = boundTextures.get(texture);
        } else {
            rl = new ResourceLocation("thaumicinsurgence", texture);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
    }

    @SideOnly(Side.CLIENT)
    public static void bindTexture(String mod, String texture) {
        ResourceLocation rl;
        if (boundTextures.containsKey(mod + ":" + texture)) {
            rl = boundTextures.get(mod + ":" + texture);
        } else {
            rl = new ResourceLocation(mod, texture);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
    }

    @SideOnly(Side.CLIENT)
    public void bindTexture(ResourceLocation resource) {
        Minecraft.getMinecraft().renderEngine.bindTexture(resource);
    }
}
