package net.fabricmc.wam;

import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class SolidPureBlock extends Block {
    public SolidPureBlock(Settings settings){
        super(Settings.of(Material.STONE).nonOpaque());
    }
}
