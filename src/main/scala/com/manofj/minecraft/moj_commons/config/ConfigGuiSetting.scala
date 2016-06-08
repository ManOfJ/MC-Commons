package com.manofj.minecraft.moj_commons.config


import net.minecraftforge.fml.client.config.IConfigElement

import com.manofj.minecraft.moj_commons.collection.java.alias.JavaList


/**
  * [[net.minecraftforge.fml.client.config.GuiConfig]] のコンストラクタ引数を提供する関数を持つ
  */
trait ConfigGuiSetting {

  /** GUIに表示されるコンフィグの要素一覧 */
  def configElements: JavaList[ IConfigElement ]

  def modId: String

  def configId: String = DEFAULT_CONFIG_ID

  /** コンフィグの要素が変更されたとき､ワールドを再起動させる必要があるか */
  def allRequireWorldRestart: Boolean = false

  /** コンフィグの要素が変更されたとき､Minecraftを再起動させる必要があるか */
  def allRequireMcRestart: Boolean = false

  def title: String

  def titleLine2: String = null

}
