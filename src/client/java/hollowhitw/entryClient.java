package hollowhitw;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class entryClient implements ClientModInitializer{
    public static final String MODID ="hollowhitw";
    public static final KeyBinding mapT = new KeyBinding("key." + MODID + ".mapT", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories." + MODID);

    public String message = "";
    String placemnent = "";//
    String tieS = "";//
    String tieW ="";//
    String deathRea ="";//
    String M = "";//
    String S = "";//
    String walls = "";//
    String map = "";
;
    @Override
    public void onInitializeClient() {
        System.out.println(MODID + " On");

        AtomicReference<String> playerNa = new AtomicReference<>("");

        String datePatern = "yy/MM/dd";
        DateFormat dateFormat = new SimpleDateFormat(datePatern);

        ClientReceiveMessageEvents.GAME.register((mess, over)->{message = mess.getString();
            if (message.contains("you were eliminated in") || message.contains(" you survived the walls")){ // [CHAT] [?] ?? HollowCarter, you were eliminated in 16th (Score: 50?)
                placemnent = message;
            }
            if (message.contains("Finished in ")) { // [CHAT] ?? Finished in 3m 10s. [CHAT] ?? Finished in 4m. [CHAT] ?? Finished in 2s
                M = message;
                S = message;
            }

            if (message.contains("Dodged ")){
                walls = message; // [CHAT] ?? Dodged 33 walls.
            }
            if (message.contains("Game Winner(s):")){ // [CHAT] [?] Game Winner(s): ?? Deliryia
                tieW = message;
                tieS = message; 
            }
            System.out.println(message);
        });

        KeyBindingHelper.registerKeyBinding(mapT);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (mapT.wasPressed()) {
                
                client.player.sendMessage(Text.literal(map), false);
            }
        });

        SuggestionProvider<FabricClientCommandSource> suggestionProvider = (context, builder) -> {
            // Implement your suggestion logic here
            builder.suggest("Revenge");
            builder.suggest("Mummy");
            builder.suggest("So_Lonely");
            builder.suggest("Chicken_Tornado");
            builder.suggest("Swimmy_Fish");
            builder.suggest("Hoglin");
            builder.suggest("Feeling_Hot");
            builder.suggest("Creepy_Crawlies");
            builder.suggest("Pillagers");
            builder.suggest("Jack_Frost");
            builder.suggest("Hmm");
            builder.suggest("Eggs");
            builder.suggest("Matrix");
            builder.suggest("Arrow_Storm");
            builder.suggest("Sticky_Shoes");
            builder.suggest("Hot_Coals");
            builder.suggest("Sandfall");
            builder.suggest("Springy_Shoes");
            builder.suggest("Leg_Day");
            builder.suggest("Low_Gravity");
            builder.suggest("Super_Speed");
            builder.suggest("One_Punch");
            builder.suggest("Fishing_Rod");
            builder.suggest("Cobwebs");
            builder.suggest("Levitation_Dart");
            builder.suggest("Blast-Off");
            builder.suggest("Hot_Potato");
            builder.suggest("Snowball_Fight");
            builder.suggest("Skill_Issue");
            builder.suggest("Glitch-Lag");
            return builder.buildFuture();
        };

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                ClientCommandManager.literal("HITWstats")
                .then(ClientCommandManager.argument("death", StringArgumentType.word()).suggests(suggestionProvider)
                .executes(context -> {
                    deathRea = StringArgumentType.getString(context, "death");

                    Date time = Calendar.getInstance().getTime();
                    String today = dateFormat.format(time);

                     PlayerEntity player = context.getSource().getPlayer();
                     if(player != null){
                        GameProfile gameProfile = player.getGameProfile();
                        playerNa.set(gameProfile.getName());
                    }

                    String gameN[] = new String[]{placemnent.toString(),tieS.toString(),tieW,deathRea,M.toString(),S.toString(),walls.toString(),map,today};

                    writeCSV writer = new writeCSV();
                    try {
                        writer.writerCSV(gameN);
                    } catch (IOException e) {}

                    context.getSource().sendFeedback(Text.literal("Test:"+ playerNa+","+placemnent.toString()+","+tieS.toString()+","+tieW+","+deathRea+","+M.toString()+","+S.toString()+","+walls.toString()+","+map+","+today));
                    return 0;
                })));
            });
        }
    }

