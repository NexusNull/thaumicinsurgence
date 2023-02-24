package thaumicinsurgence.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumicinsurgence.main.utils.VersionInfo;

public class TileEntityTestRender extends TileEntity {

    public static final String tileEntityName = VersionInfo.ModID + ".testRender";
    public TileEntityTestRender() {

    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 2), (double)(this.zCoord + 1));
    }
}
