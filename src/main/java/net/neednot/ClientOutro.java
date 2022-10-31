package net.neednot;

import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.neednot.packets.Read;
import org.lwjgl.glfw.GLFW;

public class ClientOutro implements ClientModInitializer {

    public static KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        // This code runs on the client only.
        // It is safe to use client-only classes here.
        // This is the place to register client-side only functionality.
        Read.registerPacket();
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "net.neednot.solute", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "net.neednot.outro" // The translation key of the keybinding's category.
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                var animationContainer = ((AnimatedPlayer) MinecraftClient.getInstance().player).outro_getModAnimation();

                //Use setAnimation to set the current animation. It will be played automatically.
                animationContainer.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new Identifier("outro", "solute"))));
            }
        });
    }
}
