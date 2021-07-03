package tech.presstabs.manaplus;

import java.util.UUID;

public class ManaManager {
    public float mana;
    public float maxMana;
    public float manaRegenRate;

    public UUID playerUUID;

    public ManaManager(UUID uid) {
        mana = 100;
        maxMana = 100;
        manaRegenRate = 2.5F;
        playerUUID = uid;
    }
}
