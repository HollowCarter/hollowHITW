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
			//System.out.println("Received chat message: " + message.getString());//: <HollowCarter> teset
			//System.out.println("Received chat signedMessage: " + signedMessage.toString());//: class_7471[link=class_7826[index=0, sender=b9308a81-1d23-4dba-9e22-ceae592d4649, sessionId=cb15a5c9-fd20-4c13-b5fc-62162159fc04], signature=ZviwYTwRBPU3u7DpXjjyXEbbCoCsb4yIndi6pdUEoNb3Hf225Vadoprg23yXcnnjwrx+g1NCbRIBugPQHooKLfmE2wfKCdOULtuxROeXOz+z/JBBfHWqIGD1RaOpAShh0nQM4uvOWaBY4Zz7Y8KMSc9eKA7tsr6OR4TMsIwVuUEnu7B3cx4HtxG6PbRNWyuL2NSZZaWS18KTOwfboiOCwuhNhYmuPTwRhAZArW4nvsCETCNytHqasgNeksx5frnseL8/rjAwhLXgxE/DFKSL8RZDiPu68s8u8kenZo/lW9xXxDcSFT68ocY4aQoHD9HTRwCiPV0E0eEP8WolN1avWQ==, signedBody=class_7608[content=teset, timeStamp=2023-07-04T12:34:34.651Z, salt=-4406712308971988000, lastSeen=class_7635[entries=[]]], unsignedContent=null, filterMask=net.minecraft.class_7649@175c607b]
			//System.out.println("Received chat sender: " + sender.toString());//: com.mojang.authlib.GameProfile@73092752[id=b9308a81-1d23-4dba-9e22-ceae592d4649,name=HollowCarter,properties={textures=[com.mojang.authlib.properties.Property@96ddcba]},legacy=false]
			//System.out.println("Received chat params: " + params.toString());//: class_7602[chatType=class_2556[chat=class_7463[translationKey=chat.type.text, parameters=[SENDER, CONTENT], style={}], narration=class_7463[translationKey=chat.type.text.narrate, parameters=[SENDER, CONTENT], style={}]], name=literal{HollowCarter}[style={clickEvent=ClickEvent{action=SUGGEST_COMMAND, value='/tell HollowCarter '},hoverEvent=HoverEvent{action=<action show_entity>, value='net.minecraft.class_2568$class_5248@9414672b'},insertion=HollowCarter}], targetName=null]
			//System.out.println("Received chat receptionTimestamp: " + receptionTimestamp.toString());//: 2023-07-04T12:34:34.697293600Z 
		});

		ClientReceiveMessageEvents.GAME.register((message, over)->{
			
			System.out.println("Received game message: " + message.getString());
			System.out.println("Received game message: " + over);

		});
	}
}

//Read Chat
//Select messages relavnt to code
//process messages for relvant info
//output to log file or csv

//Placement, Tie Size, TiedWith, Death Reason, Minutes, Seconds, walls, map, date/time, traps(why not), Total players(why not)