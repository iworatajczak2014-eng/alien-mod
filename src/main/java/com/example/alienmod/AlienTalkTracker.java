package com.example.alienmod;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Remembers when each player's voice was last received through Simple Voice Chat.
 * Voice packets arrive on an audio thread, so this uses a concurrent map.
 * Does not reference the voice chat API, so it is safe without that mod installed.
 */
public final class AlienTalkTracker {
	/** Voice packets come roughly every 20 ms; this bridges tiny gaps between them. */
	private static final long TALK_WINDOW_MS = 250;

	private static final Map<UUID, Long> LAST_VOICE = new ConcurrentHashMap<>();

	private AlienTalkTracker() {
	}

	public static void markSpeaking(UUID player) {
		LAST_VOICE.put(player, System.currentTimeMillis());
	}

	public static boolean isSpeaking(UUID player) {
		Long last = LAST_VOICE.get(player);
		return last != null && System.currentTimeMillis() - last < TALK_WINDOW_MS;
	}

	/** Used to flap the mouth open and closed while talking. */
	public static boolean mouthOpenNow() {
		return (System.currentTimeMillis() / 120L) % 2L == 0L;
	}
}
