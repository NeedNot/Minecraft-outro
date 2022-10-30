package net.neednot.mixin;


import com.mojang.brigadier.LiteralMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.neednot.ClientOutro;
import net.neednot.Outro;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.texture.Sprite$Animation")
public abstract class SpriteMixin {
    @Shadow private int frameTicks;

    @Shadow private int frameIndex;

    @Inject(method = "tick()V", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        if (Outro.RESET != 0) {
            this.frameIndex = 0;
            this.frameTicks = 0;
            MinecraftClient.getInstance().player.sendMessage(Text.literal(("reset")));
        }
        System.out.println(this.frameTicks + " Frame ticks");
    }
}
