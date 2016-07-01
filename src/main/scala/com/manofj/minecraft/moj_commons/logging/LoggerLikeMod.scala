package com.manofj.minecraft.moj_commons.logging


import org.apache.logging.log4j.{ LogManager, Logger }

import com.manofj.minecraft.moj_commons.util.MinecraftMod


/**
  * ロガーのように振る舞えるModであることをあらわす
  */
trait LoggerLikeMod
  extends LoggerLike
{
  this: MinecraftMod =>

  // デフォルトでは自身のModIDからロガーを取得する
  // これは net.minecraftforge.fml.common.event.FMLPreInitializationEvent#getModLog と同じ実装となる
  override protected lazy val logger: Logger = LogManager.getLogger( this.modId )

}
