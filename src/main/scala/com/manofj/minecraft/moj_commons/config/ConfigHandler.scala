package com.manofj.minecraft.moj_commons.config


import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


/**
  * Minecraft Forgeコンフィグを取り扱う
  */
trait ConfigHandler {

  def config: ForgeConfig

  /** コンフィグオブジェクトをハンドラにバインドする */
  def captureConfig( forgeConfig: ForgeConfig ): Unit

  def captureConfig[ A ]( parameter: A )
                        ( implicit conversion: A => ForgeConfig ): Unit =
    captureConfig( conversion( parameter ) )

  /**
    * コンフィグファイルの設定値とメモリ上の設定値を同期させる
    * @param load コンフィグのロードが必要か否か
    */
  def syncConfig( load: Boolean ): Unit

  /**
    * コンフィグGUIでの設定値変更時に呼び出される
    * このメソッドを機能させるには実装オブジェクトを
    * Minecraft Forgeのイベントバスに登録する必要がある
    */
  @SubscribeEvent
  def onConfigChanged( event: OnConfigChangedEvent ): Unit

}
