package net.neednot;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.neednot.blocks.Laptop;
import net.neednot.packets.Click;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Outro implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("outro");
	public static final Block lAPTOP = new Laptop(FabricBlockSettings.of(Material.METAL).strength(4.0f));
	public static final Identifier OUTRO_MUSIC = new Identifier("outro:outro");
    public static final String CLICK = "CLICK";
    public static SoundEvent PLAY_OUTRO = new SoundEvent(OUTRO_MUSIC);
	public static int RESET;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Click.registerPacket();
		Registry.register(Registry.BLOCK, new Identifier("outro", "laptop"), lAPTOP);
		Registry.register(Registry.SOUND_EVENT, Outro.OUTRO_MUSIC, PLAY_OUTRO);
		LOGGER.info("Hello Fabric world!");
	}
}
