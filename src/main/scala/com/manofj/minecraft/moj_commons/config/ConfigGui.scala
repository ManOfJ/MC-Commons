package com.manofj.minecraft.moj_commons.config


import net.minecraft.client.gui.GuiScreen

import net.minecraftforge.fml.client.config.GuiConfig


/**
  * シンプルなコンフィグGUI
  */
abstract class ConfigGui( parent: GuiScreen, params: ConfigGuiSetting )
  extends GuiConfig( parent,
                     params.configElements,
                     params.modId,
                     params.configId,
                     params.allRequireWorldRestart,
                     params.allRequireMcRestart,
                     params.title,
                     params.titleLine2 )
