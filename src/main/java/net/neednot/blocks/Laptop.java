package net.neednot.blocks;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.neednot.ClientOutro;
import net.neednot.Outro;


public class Laptop extends HorizontalFacingBlock {
    public Laptop(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(PAUSED, true));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }
    SoundInstance soundInstance;
    public static final BooleanProperty PAUSED = BooleanProperty.of("paused");
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0.01f, 0f, 1f, 0.2f, 1f);
    }
    //right click the laptop to toggle paused and play states
    //play state goes to paused ater it's over
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PAUSED);
        builder.add(Properties.HORIZONTAL_FACING);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (soundInstance == null) {
            soundInstance = new PositionedSoundInstance(Outro.PLAY_OUTRO , SoundCategory.AMBIENT , 1 , 1 , new Random() {
                @Override
                public Random split() {
                    return null;
                }

                @Override
                public RandomSplitter nextSplitter() {
                    return null;
                }

                @Override
                public void setSeed(long seed) {

                }

                @Override
                public int nextInt() {
                    return 0;
                }

                @Override
                public int nextInt(int bound) {
                    return 0;
                }

                @Override
                public long nextLong() {
                    return 0;
                }

                @Override
                public boolean nextBoolean() {
                    return false;
                }

                @Override
                public float nextFloat() {
                    return 0;
                }

                @Override
                public double nextDouble() {
                    return 0;
                }

                @Override
                public double nextGaussian() {
                    return 0;
                }
            } , pos);
        }
        if (world.isClient) {
            if (world.getBlockState(pos).get(PAUSED)) {
                MinecraftClient.getInstance().getSoundManager().play(soundInstance);
                Outro.RESET = 0;
                ClientPlayNetworking.send(new Identifier("outro" , "click") , PacketByteBufs.empty());
            } else {
                MinecraftClient.getInstance().getSoundManager().stopAll();
                Outro.RESET = 1;
            }
        }
        world.setBlockState(pos, state.with(PAUSED, !world.getBlockState(pos).get(PAUSED)));
        return ActionResult.SUCCESS;
    }
}
