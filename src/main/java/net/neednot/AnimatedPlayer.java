package net.neednot;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;

public interface AnimatedPlayer {
    ModifierLayer<IAnimation> outro_getModAnimation();
}
