package net.neednot.packets;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.neednot.AnimatedPlayer;
import net.neednot.Outro;

public class Read {
    public static void registerPacket() {
        ClientPlayNetworking.registerGlobalReceiver(new Identifier("outro", "read"), ((client , handler , buf , responseSender) -> {
            boolean pose = !buf.readBoolean();
            AbstractClientPlayerEntity player = client.player;
            if (pose) player = (AbstractClientPlayerEntity) client.world.getPlayerByUuid(buf.readUuid());
            final AbstractClientPlayerEntity player1 = player;
            client.execute(() -> {
                if (pose) {
                    ModifierLayer<IAnimation> animationContainer = ((AnimatedPlayer) player1).outro_getModAnimation();
                    //Use setAnimation to set the current animation. It will be played automatically.
                    animationContainer.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new Identifier("outro" , "solute"))));
                } else {
                    Outro.RESET = true;
                }
            });
        }));
    }
}
