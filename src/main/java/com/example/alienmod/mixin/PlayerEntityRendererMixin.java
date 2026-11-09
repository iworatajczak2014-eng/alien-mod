package com.example.alienmod.mixin;

import com.example.alienmod.AlienModClient;
import com.example.alienmod.AlienRenderState;
import com.example.alienmod.AlienTalkTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

	// Decide, per player and per frame, whether this player should look like an alien
	// and whether they are currently talking in voice chat.
	// Your own player is excluded, so only OTHER players are changed.
	@Inject(
			method = "updateRenderState(Lnet/minecraft/entity/PlayerLikeEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V",
			at = @At("RETURN")
	)
	private void alienmod$markAlien(PlayerLikeEntity entity, PlayerEntityRenderState state, float tickDelta, CallbackInfo ci) {
		boolean isSelf = (Object) entity == MinecraftClient.getInstance().player;
		AlienRenderState flags = (AlienRenderState) (Object) state;
		flags.alienmod$setAlien(AlienModClient.aliensEnabled && !isSelf);
		flags.alienmod$setTalking(!isSelf && AlienTalkTracker.isSpeaking(entity.getUuid()));
	}

	// Swap the skin texture for flagged players; alternate to the open-mouth skin while they talk.
	@Inject(
			method = "getTexture(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;)Lnet/minecraft/util/Identifier;",
			at = @At("HEAD"),
			cancellable = true
	)
	private void alienmod$swapTexture(PlayerEntityRenderState state, CallbackInfoReturnable<Identifier> cir) {
		AlienRenderState flags = (AlienRenderState) (Object) state;
		if (flags.alienmod$isAlien()) {
			boolean open = flags.alienmod$isTalking() && AlienTalkTracker.mouthOpenNow();
			cir.setReturnValue(open ? AlienModClient.ALIEN_TALK_TEXTURE : AlienModClient.ALIEN_TEXTURE);
		}
	}
}
