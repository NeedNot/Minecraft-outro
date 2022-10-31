package net.neednot.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.List;

public class Click {
    public static void registerPacket() {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier("outro", "click"),
                ((server , player , handler , buf , responseSender) -> {
                    server.execute(() -> {
                        for (ServerPlayerEntity player1 : PlayerLookup.world(player.getWorld())) {
                            ServerPlayNetworking.send(player1, new Identifier("outro", "read"), PacketByteBufs.empty());
                        }
                    });
                }));
    }
}
