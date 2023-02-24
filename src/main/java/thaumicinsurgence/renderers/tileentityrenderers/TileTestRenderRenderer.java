package thaumicinsurgence.renderers.tileentityrenderers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;
import thaumcraft.client.renderers.tile.TileInfusionPillarRenderer;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.tileentity.TileEntityTestRender;

@SideOnly(Side.CLIENT)
public class TileTestRenderRenderer extends TileEntitySpecialRenderer {

    private final ModelCube model = new ModelCube(0);;

    @SideOnly(Side.CLIENT)
    public void renderTileEntityAt(TileEntityTestRender tile, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        UtilsFX.bindTexture(VersionInfo.ModID, "model/pillar-1.png");
        GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 0.5F, (float) par6 + 0.5F);
        this.model.render();
        GL11.glPopMatrix();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileEntityTestRender) par1TileEntity, par2, par4, par6, par8);
    }
}
