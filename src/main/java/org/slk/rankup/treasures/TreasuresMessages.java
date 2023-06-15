package org.slk.rankup.treasures;

import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalDateTime;

public enum TreasuresMessages {
    JOIN_WORLD("\nBem-vindo ao mundo de Tesouros, aqui vais encontrar diversas recompensas. Tens apenas &e%timeLeft% minutos&r para encontrar o máximo" +
            " de tesouros que conseguires e lembra-te que a &eBússola&r é a chave para o teu sucesso, boa sorte.\n"),
    TIME_LEFT("Restam-te apenas &e%timeLeft% minutos&r no mundo de Tesouros!"),
    LEAVE_WORLD_TIME("\nO teu tempo no mundo dos Tesouros acabou!\n"),
    LEAVE_WORLD_FORCE("\nFoste forçado a sair do mundo de Tesouros, não te preocupes, recebeste uma passagem nova com a duração restante.\n");

    private final String message;
    TreasuresMessages(String message){
        this.message = message;
    }
    public String get(Player player){
        Duration diff = Duration.between(TreasuresManager.TIME_MAP.get(player), LocalDateTime.now());
        return message.replace("%timeLeft%", String.valueOf(Math.round(diff.toMinutes())));
    }
    public String getRaw(){ return message; }
}