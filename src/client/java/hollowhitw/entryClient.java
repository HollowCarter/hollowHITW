package hollowhitw;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

import com.mojang.authlib.GameProfile;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

import hollowhitw.writeCSV;

public class entryClient implements ClientModInitializer{
    public static final String MODID ="hollowhitw";
    public static final KeyBinding outCSV = new KeyBinding("key." + MODID + ".outCSV", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "key.categories." + MODID);
    @Override
    public void onInitializeClient() {
        System.out.println(MODID + " On");

        AtomicReference<String> playerNa = new AtomicReference<>("");

        Integer placemnent = 99;
        Integer tieS = 2;
        String tieW ="LibertaRado";
        String deathRea ="Revenge";
        Integer M = 2;
        Integer S = 59;
        Integer walls = 33;
        String map = "Beach";

        String datePatern = "yy/MM/dd";
        DateFormat dateFormat = new SimpleDateFormat(datePatern);

        AtomicReference<String> message = new AtomicReference<>("");
        ClientReceiveMessageEvents.GAME.register((mess, over)->{message.set(mess.getString());});

        KeyBindingHelper.registerKeyBinding(outCSV);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            
            Date time = Calendar.getInstance().getTime();
            String today = dateFormat.format(time);

            while (outCSV.wasPressed()) {

                PlayerEntity player = client.player;
                if(player != null){
                    GameProfile gameProfile = player.getGameProfile();
                    playerNa.set(gameProfile.getName());
                }
                String gameN[] = new String[]{placemnent.toString(),tieS.toString(),tieW,deathRea,M.toString(),S.toString(),walls.toString(),map,today};
                writeCSV writer = new writeCSV();
                try {
                    writer.writerCSV(gameN);
                } catch (IOException e) {
                }

                client.player.sendMessage(Text.literal(playerNa+","+placemnent+","+tieS+","+tieW+","+deathRea+","+M+","+S+","+walls+","+map+","+today), false);
            }
        });

    }
}
