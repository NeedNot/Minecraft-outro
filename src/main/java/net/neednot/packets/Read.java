package net.neednot.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.neednot.Outro;

public class Read {
    public static void registerPacket() {
        ClientPlayNetworking.registerGlobalReceiver(new Identifier("outro", "read"), ((client , handler , buf , responseSender) -> {
            client.execute(() -> {
                Outro.RESET = 0;
            });
        }));
    }
}
