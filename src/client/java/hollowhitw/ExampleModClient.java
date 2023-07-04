package hollowhitw;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.scoreboard.Scoreboard;

public class ExampleModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			PlayerEntity player = client.player;

			if(player != null){
				GameProfile gameProfile = player.getGameProfile();
                String playerName = gameProfile.getName();
                //System.out.println("Player name: " + playerName);

			}
		});


		ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params,receptionTimestamp) ->
		{
			System.out.println("Received chat message: " + message.getString());
			System.out.println("Received chat signedMessage: " + signedMessage.toString());
			System.out.println("Received chat sender: " + sender.toString());
			System.out.println("Received chat params: " + params.toString());
			System.out.println("Received chat receptionTimestamp: " + receptionTimestamp.toString());
		});
	}
}

//Read Chat
//Select messages relavnt to code
//process messages for relvant info
//output to log file or csv

//Placement, Tie Size, TiedWith, Death Reason, Minutes, Seconds, walls, map, date/time, traps(why not), Total players(why not)