package com.example.alienmod;

/** Duck interface mixed into PlayerEntityRenderState to carry per-player alien flags. */
public interface AlienRenderState {
	boolean alienmod$isAlien();

	void alienmod$setAlien(boolean alien);

	boolean alienmod$isTalking();

	void alienmod$setTalking(boolean talking);
}
