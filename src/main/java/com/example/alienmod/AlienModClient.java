package com.example.alienmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class AlienModClient implements ClientModInitializer {
	public static final String MOD_ID = "alienmod";

	public static final Identifier ALIEN_TEXTURE = Identifier.of(MOD_ID, "textures/entity/alien.png");
	/** Same skin with the mouth open; alternated with the closed one while a player talks. */
	public static final Identifier ALIEN_TALK_TEXTURE = Identifier.of(MOD_ID, "textures/entity/alien_talk.png");

	/** Whether other players are currently rendered as aliens. */
	public static boolean aliensEnabled = true;

	private static final KeyBinding.Category CATEGORY =
			KeyBinding.Category.create(Identifier.of(MOD_ID, "main"));

	private static KeyBinding toggleKey;

	@Override
	public void onInitializeClient() {
		// Shows up in Options > Controls > Key Binds under "Alien Mod", so it can be rebound there.
		toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.alienmod.toggle",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_H,
				CATEGORY
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleKey.wasPressed()) {
				aliensEnabled = !aliensEnabled;
				if (client.player != null) {
					client.player.sendMessage(
							Text.literal("Alien players: " + (aliensEnabled ? "ON" : "OFF")), true);
				}
			}
		});
	}
}
