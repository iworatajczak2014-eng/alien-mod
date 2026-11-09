package com.example.alienmod;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.ClientReceiveSoundEvent;
import de.maxhenkel.voicechat.api.events.EventRegistration;

/**
 * Loaded only by Simple Voice Chat (via the "voicechat" entrypoint).
 * If the voice chat mod is not installed, this class is never touched.
 */
public class AlienVoicechatPlugin implements VoicechatPlugin {

	@Override
	public String getPluginId() {
		return AlienModClient.MOD_ID;
	}

	@Override
	public void initialize(VoicechatApi api) {
	}

	@Override
	public void registerEvents(EventRegistration registration) {
		// Fires for every chunk of voice audio received from another player.
		registration.registerEvent(ClientReceiveSoundEvent.EntitySound.class,
				event -> AlienTalkTracker.markSpeaking(event.getEntityId()));
	}
}
