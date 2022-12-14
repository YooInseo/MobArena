package me.github.mobarena.Util;

import me.github.mobarena.MobArena;
import me.github.mobarena.data.PlayerData;
import me.github.mobarena.data.type.SendType;
import me.github.skyexcelcore.data.Time;
import me.github.skyexcelcore.util.ActionBar;
import org.bukkit.entity.Player;

public class StringData {


    public static String sendJoinMessage(Player player) {
        PlayerData data = new PlayerData(player);

        String path = "message.join-message.msg";
        String msg = MobArena.config.getString(path);

        msg = Translate.Color(msg).replaceAll("%arena%", data.getArena());
        sendMessage(player, "join-message", msg);
        return MobArena.config.getString(path);
    }


    public static String sendCoolTimeMessage(Player player) {
        PlayerData data = new PlayerData(player);

        String path = "message.cooltime-message.msg";
        String msg = MobArena.config.getString(path);

        Time time = new Time(data.getCooltime());

        msg = Translate.Color(msg).replaceAll("%arena%", data.getArena());
        msg = Translate.Color(msg).replaceAll("%cooltime-d%", String.valueOf(time.DAY()) + "일 ");
        msg = Translate.Color(msg).replaceAll("%cooltime-h%", String.valueOf(time.HOUR()) + "시간 ");
        msg = Translate.Color(msg).replaceAll("%cooltime-m%", String.valueOf(time.MINUTE()) + "분 ");
        msg = Translate.Color(msg).replaceAll("%cooltime-s%", String.valueOf(time.SECOND()) + "초 ");

        msg = Translate.Color(msg).replaceAll("%cooltime%", data.getArena());
        sendMessage(player, "cooltime-message", msg);
        return MobArena.config.getString(path);
    }


    public static String sendMessage(Player player, String path, String msg) {

        SendType type = SendType.valueOf(MobArena.config.getString("message." + path + ".send-type"));

        int fadein = MobArena.config.getInteger("message." + path + ".fade-in");
        int fadeout = MobArena.config.getInteger("message." + path + ".fade-out");
        int stay = MobArena.config.getInteger("message." + path + ".stay");

        if (type.equals(SendType.Title)) {
            player.sendTitle(msg, "", fadein, stay, fadeout);
        } else if (type.equals(SendType.SubTitle)) {
            player.sendTitle("", msg, fadein, stay, fadeout);
        } else if (type.equals(SendType.Message)) {
            player.sendMessage(MobArena.prefix + msg);
        } else if (type.equals(SendType.ActionBar)) {
            ActionBar.sendMessage(player, msg);
        }
        return msg;
    }
}
