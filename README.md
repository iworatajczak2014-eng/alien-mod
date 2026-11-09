# Alien Mod (Fabric, Minecraft 1.21.11)

Client-side mod: every OTHER player is rendered with a green alien skin.
Your own character is unchanged. Works on any server (nothing is sent over the network).

## Toggle key
Default key is **H**. Change it in-game:
Options > Controls > Key Binds > **Alien Mod** > "Toggle Alien Players".
A short message above the hotbar shows ON/OFF.

## Build the .jar
Requirements: JDK 21 and Gradle 9.x (or any Gradle to generate the wrapper once).

1. In this folder run once:  `gradle wrapper --gradle-version 9.2.1`
2. Then:  `./gradlew build`   (Windows: `gradlew.bat build`)
3. Your mod is `build/libs/alienmod-1.0.0.jar` (not the `-sources` one).

No Gradle installed? Generate a blank project at https://fabricmc.net/develop/template/
(Minecraft 1.21.11, Yarn mappings), then copy this project's `src/` folder
into it and fix `fabric.mod.json`/package names if you changed them.
Alternatively open this folder in IntelliJ IDEA and let it import the Gradle project.

## Install
Put the jar in `.minecraft/mods/` together with Fabric API (for 1.21.11) and
Fabric Loader 0.18.1 or newer.

## If Gradle says a version does not exist
Edit `gradle.properties` and use the newest versions listed at
https://fabricmc.net/develop/ for 1.21.11.

## Simple Voice Chat integration (optional)
If the Simple Voice Chat mod is installed, aliens open and close their mouths
while that player is talking (you must be able to hear them in voice chat).
Without Simple Voice Chat the mod works normally, just without mouth movement.
Build note: the API is pulled from https://maven.maxhenkel.de/repository/public
(`voicechat_api_version` in gradle.properties; change it if that version is not found).
