package com.manofj.minecraft.example


import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

import com.manofj.minecraft.moj_commons.config.javaFile2ForgeConfig


@Mod( modid      = ExampleMod.ID,
      name       = ExampleMod.NAME,
      version    = ExampleMod.VERSION,
      guiFactory = ExampleMod.GUI_FACTORY )
object ExampleMod {

  final val ID          = "example"
  final val NAME        = "Example Mod"
  final val VERSION     = "1.0"
  final val GUI_FACTORY = "com.manofj.minecraft.example.ExampleGuiFactory"

  @Mod.EventHandler
  def preInit( event: FMLPreInitializationEvent ): Unit = {
    ExampleGuiHandler.captureConfig( event.getSuggestedConfigurationFile )
    MinecraftForge.EVENT_BUS.register( ExampleGuiHandler )
  }

}
