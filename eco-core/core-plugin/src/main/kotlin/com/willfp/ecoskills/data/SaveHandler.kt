package com.willfp.ecoskills.data

import com.willfp.ecoskills.EcoSkillsPlugin
import com.willfp.ecoskills.data.storage.PlayerProfile
import com.willfp.ecoskills.expMultiplierCache
import org.bukkit.Bukkit

class SaveHandler {
    companion object {
        fun save(plugin: EcoSkillsPlugin) {
            if (Bukkit.getOnlinePlayers().isEmpty()) {
                return
            }
            if (plugin.configYml.getBool("autosave.log")) {
                plugin.logger.info("Auto-Saving player data!")
            }
            PlayerProfile.saveAll(plugin.configYml.getBool("autosave.async"))
            expMultiplierCache.clear()
            if (plugin.configYml.getBool("autosave.log")) {
                plugin.logger.info("Saved data!")
            }
        }
    }

    class Runnable(
        private val plugin: EcoSkillsPlugin
    ) : java.lang.Runnable {
        override fun run() {
            save(plugin)
        }
    }
}