package com.williambl.floriculture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.FabricTag;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Floriculture implements ModInitializer {

	private static final String MODID = "floriculture";

	public static final Tag<Block> FLOWER_SPREAD_ALLOWED = TagRegistry.block(new Identifier(MODID, "flower_spread_allowed"));
	public static final Tag<Block> FLOWER_SPREAD_DISALLOWED = TagRegistry.block(new Identifier(MODID, "flower_spread_disallowed"));

	@Override
	public void onInitialize() {
	}
}
