package com.manofj.minecraft.moj_commons.config


import net.minecraftforge.common.config.ConfigElement
import net.minecraftforge.fml.client.config.IConfigElement
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

import com.manofj.minecraft.moj_commons.collection.java.alias.JavaList
import com.manofj.minecraft.moj_commons.util.ImplicitConversions.AnyExtension


/**
  * シンプルなコンフィグハンドラ
  * コンフィグGUIのパラメータ供給も兼ねる
  */
abstract class ConfigGuiHandler
  extends ConfigHandler
  with    ConfigGuiSetting
{

  // バインドされたコンフィグオブジェクト
  private[ this ] var configOpt = Option.empty[ ForgeConfig ]

  override def config: ForgeConfig = configOpt.get

  override def configElements: JavaList[ IConfigElement ] =
    new ConfigElement( config.getCategory( configId ) ).getChildElements

  override def captureConfig( forgeConfig: ForgeConfig ): Unit =
    forgeConfig.? match {
      case None => throw new NullPointerException
      case opt @ Some( _ ) =>
        configOpt = opt
        syncConfig( true )
    }

  @SubscribeEvent
  override def onConfigChanged( event: OnConfigChangedEvent ): Unit =
    if ( event.getModID == modId && event.getConfigID == configId ) syncConfig( false )

}
