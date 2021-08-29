package net.fabricmc.wam;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DemoMod implements ModInitializer {
	public static DemoMod INSTANCE;
	public DemoMod(){INSTANCE = this;}

	//定义方块 <纯块> (硬度0.5,爆炸抗性0.5)
	public static final PureBlock PURE_BLOCK = new PureBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final BlockItem PURE_BLOCK_ITEM = new BlockItem(PURE_BLOCK, new FabricItemSettings());
	//声明
	public static BlockEntityType<PureBlockEntity> PURE_BLOCK_ENTITY;


	public static final RedstoneWireBlock PURE_WIRE = new RedstoneWireBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().breakInstantly());
	public static final BlockItem PURE_DUST = new BlockItem(PURE_WIRE, new FabricItemSettings());//纯粉

	public static final SolidPureBlock SOLID_PURE_BLOCK = new SolidPureBlock(FabricBlockSettings.of(Material.METAL).strength(1.5f, 3.0f).sounds(BlockSoundGroup.METAL));//纯凝块
	public static final BlockItem SOLID_PURE_BLOCK_ITEM = new BlockItem(SOLID_PURE_BLOCK, new FabricItemSettings());

	//物品组
	public static final ItemGroup PURE_GROUP = FabricItemGroupBuilder.create(
			new Identifier("wam", "pure_group"))
			.icon(() -> new ItemStack(DemoMod.PURE_BLOCK))
			.appendItems(a -> {//a ==> Anything *** (<list变量>)
				a.add(new ItemStack(DemoMod.PURE_BLOCK_ITEM));
				a.add(new ItemStack(DemoMod.SOLID_PURE_BLOCK_ITEM));
				a.add(new ItemStack(DemoMod.PURE_DUST));
			})
			.build();

	@Override
	public void onInitialize() {
		// 注册<方块>(可以放置)  &  <实体>(可以拿在手中) *** 纯块
		var Pure_Block_Id = new Identifier("wam", "pure_block");
		Registry.register(Registry.BLOCK, Pure_Block_Id, PURE_BLOCK);
		Registry.register(Registry.ITEM, Pure_Block_Id, PURE_BLOCK_ITEM);
		BlockRenderLayerMap.INSTANCE.putBlock(DemoMod.PURE_BLOCK, RenderLayer.getTranslucent());//顺便整个半透明材质
		// 注册 *** 纯粉
		var Pure_Dust_Id = new Identifier("wam", "pure_dust");
		Registry.register(Registry.ITEM, Pure_Dust_Id, PURE_DUST);
		// 注册 *** 纯粉线
		Registry.register(Registry.BLOCK, new Identifier("wam", "pure_wire"), PURE_WIRE);
		// 注册 *** 纯凝块
		var Solid_Pure_Block_Id = new Identifier("wam", "solid_pure_block");
		Registry.register(Registry.BLOCK, Solid_Pure_Block_Id, SOLID_PURE_BLOCK);
		Registry.register(Registry.ITEM, Solid_Pure_Block_Id, SOLID_PURE_BLOCK_ITEM);
		BlockRenderLayerMap.INSTANCE.putBlock(DemoMod.SOLID_PURE_BLOCK, RenderLayer.getTranslucent());//顺便整个半透明材质


		//注册， 在onInitialize()方法中
		PURE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "wam:pure_block_entity", FabricBlockEntityTypeBuilder.create(PureBlockEntity::new, PURE_BLOCK).build(null));
	}
}