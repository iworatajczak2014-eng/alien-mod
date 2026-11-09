package com.example.alienmod.mixin;

import com.example.alienmod.AlienRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntityRenderState.class)
public class PlayerEntityRenderStateMixin implements AlienRenderState {
	@Unique
	private boolean alienmod$alien;

	@Unique
	private boolean alienmod$talking;

	@Override
	public boolean alienmod$isAlien() {
		return alienmod$alien;
	}

	@Override
	public void alienmod$setAlien(boolean alien) {
		this.alienmod$alien = alien;
	}

	@Override
	public boolean alienmod$isTalking() {
		return alienmod$talking;
	}

	@Override
	public void alienmod$setTalking(boolean talking) {
		this.alienmod$talking = talking;
	}
}
