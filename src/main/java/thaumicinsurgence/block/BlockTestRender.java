package thaumicinsurgence.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumicinsurgence.main.Config;
import thaumicinsurgence.main.utils.TabThaumicInsurgence;
import thaumicinsurgence.tileentity.TileEntityInfusionMatrixAlpha;
import thaumicinsurgence.tileentity.TileEntityTestRender;

import java.util.List;

public class BlockTestRender extends Block {
    public BlockTestRender() {
        super(Material.rock);
        this.setCreativeTab(TabThaumicInsurgence.tabThaumicInsurgence);
        this.setBlockName("testRender");
        this.setHardness(10f);
        this.setResistance(1.5f);
        this.setHarvestLevel("pickaxe", 1);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
    public boolean isOpaqueCube() {
        return false;
    }
    public int getRenderType() {
        return Config.blockStoneDeviceFourRI;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityTestRender();
    }

    @Override
    public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist,
                                        Entity par7Entity) {
        int metadata = world.getBlockMetadata(i, j, k);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }
}
