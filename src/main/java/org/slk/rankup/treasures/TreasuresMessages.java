package org.slk.rankup.treasures;

import org.bukkit.entity.Player;

public enum TreasuresMessages {
    JOIN_WORLD("Bem-vindo ao mundo de Tesouros, aqui vais encontrar diversas recompensas. Tens apenas &e%timeLeft% minutos&r para encontrar o máximo" +
            " de tesouros que conseguires e lembra-te que a &eBússola&r é a chave para o teu sucesso, boa sorte."),
    TIME_LEFT("Restam-te apenas &e%timeLeft% minutos&r no mundo de Tesouros!"),
    LEAVE_WORLD_TIME("O teu tempo no mundo dos Tesouros acabou!"),
    LEAVE_WORLD_OPTIONAL("Sais-te do mundo de Tesouros ainda com tempo restante, recebeste uma passagem nova com a duração restante."),
    LEAVE_WORLD_OPTIONAL_NO_TICKET("Sais-te do mundo de Tesouros opcionalmente, como não te restava muito tempo, não vais receber uma passagem nova."),
    LEAVE_WORLD_FORCE("Foste forçado a sair do mundo de Tesouros, não te preocupes, recebeste uma passagem nova com a duração restante.");

    private final String message;
    TreasuresMessages(String message){
        this.message = message;
    }
    public String get(Player player){ return message.replace("%timeLeft%", String.valueOf(Math.round(TreasuresManager.getTimeLeft(player).toMinutes()))); }
    public String getRaw(){ return message; }
}