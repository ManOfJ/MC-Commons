package com.manofj.minecraft.example


import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

import com.manofj.minecraft.moj_commons.config.javaFile2ForgeConfig
import com.manofj.minecraft.moj_commons.logging.LoggerLikeMod
import com.manofj.minecraft.moj_commons.util.MinecraftMod


@Mod( modid      = ExampleMod.modId,
      name       = ExampleMod.modName,
      version    = ExampleMod.modVersion,
      guiFactory = ExampleMod.guiFactory )
object ExampleMod
  extends MinecraftMod
  with    LoggerLikeMod
{

  final val modId      = "example"
  final val modName    = "Example Mod"
  final val modVersion = "1.0"
  final val guiFactory = "com.manofj.minecraft.example.ExampleGuiFactory"

  @Mod.EventHandler
  def preInit( event: FMLPreInitializationEvent ): Unit = {
    logger.info( "== Pre initialization start ========" )

    ExampleGuiHandler.captureConfig( event.getSuggestedConfigurationFile )
    MinecraftForge.EVENT_BUS.register( ExampleGuiHandler )

    logger.info( "== Pre initialization end ========" )
  }

}
