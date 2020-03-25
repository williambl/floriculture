package com.williambl.floriculture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.FabricTag;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Floriculture implements ModInitializer {

	private static final String MODID = "floriculture";

	public static final Tag<Block> FLOWERS_CAN_SPREAD_ON = TagRegistry.block(new Identifier(MODID, "flowers_can_spread_on"));

	@Override
	public void onInitialize() {
	}
}
