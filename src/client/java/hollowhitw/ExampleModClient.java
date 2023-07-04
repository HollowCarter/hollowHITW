package hollowhitw;

import com.mojang.authlib.GameProfile;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.MinecraftClient;

public class ExampleModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			PlayerEntity player = client.player;

			if(player != null){
				GameProfile gameProfile = player.getGameProfile();
                String playerName = gameProfile.getName();
                System.out.println("Player name: " + playerName);
			}
		});
	}
}

//Get Players Name
//Read Chat
//Select messages relavnt to code
//process messages for relvant info
//output to log file or csv

//Placement, Tie Size, TiedWith, Death Reason, Minutes, Seconds, walls, map, date/time, traps(why not), Total players(why not)